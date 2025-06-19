package com.ysu.drffpjcxt.service.impl.support;

import com.ysu.drffpjcxt.entity.FarmerProfile;
import com.ysu.drffpjcxt.entity.SupportMeasure;
import com.ysu.drffpjcxt.entity.SupportPlan;
import com.ysu.drffpjcxt.entity.User;
import com.ysu.drffpjcxt.entity.dto.support.SupportMeasureDTO;
import com.ysu.drffpjcxt.entity.dto.support.SupportPlanSaveRequest;
import com.ysu.drffpjcxt.entity.vo.support.SupportPlanDetailVO;
import com.ysu.drffpjcxt.mapper.FarmerProfileMapper;
import com.ysu.drffpjcxt.mapper.SupportMeasureMapper;
import com.ysu.drffpjcxt.mapper.SupportPlanMapper;
import com.ysu.drffpjcxt.mapper.UserMapper;
import com.ysu.drffpjcxt.service.SupportPlanService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@Service
public class SupportPlanServiceImpl implements SupportPlanService {

    @Autowired
    private SupportPlanMapper supportPlanMapper;
    @Autowired
    private SupportMeasureMapper supportMeasureMapper;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FarmerProfileMapper farmerProfileMapper;
    // 假设此处会实现 queryById、update 等其他方法。
    // ...

    @Override
    @Transactional // 使用事务确保原子性：所有保存操作要么全部成功，要么全部失败回滚。
    public Long createSupportPlan(SupportPlanSaveRequest request, Principal principal) {
        // 1. 验证输入参数
        Assert.notNull(request.getFarmerId(), "农户ID不能为空");
        Assert.hasText(request.getPlanName(), "计划名称不能为空");
        Assert.notEmpty(request.getMeasures(), "至少需要一条帮扶措施");
        
        // 2. 获取当前用户（计划制定者）
        User planner = userMapper.findByPhone(principal.getName());
        Assert.notNull(planner, "无法获取当前用户信息");

        // 3. 创建并保存主要的 SupportPlan 对象
        SupportPlan plan = new SupportPlan();
        BeanUtils.copyProperties(request, plan);
        plan.setCreatedBy(planner.getId());
        plan.setStatus("待审批"); // 初始状态
        plan.setIsDeleted(false);
        plan.setCreateTime(new Date());
        plan.setUpdateTime(new Date());

        // MyBatis配置应确保插入后返回自增ID到plan对象
        supportPlanMapper.insert(plan); 
        Long planId = plan.getId();
        Assert.notNull(planId, "保存帮扶计划失败，未能返回ID");

        // 4. 遍历并保存每个 SupportMeasure
        for (SupportMeasureDTO measureDTO : request.getMeasures()) {
            SupportMeasure measure = new SupportMeasure();
            BeanUtils.copyProperties(measureDTO, measure);
            measure.setPlanId(planId); // 关联到主计划
            measure.setStatus("未开始"); // 初始状态
            measure.setIsDeleted(false);
            measure.setCreateTime(new Date());
            measure.setUpdateTime(new Date());
            supportMeasureMapper.insert(measure);
        }

        return planId;
    }

    @Override
    public SupportPlan queryById(Object id) {
        return null;
    }

    @Override
    public Page<SupportPlan> queryByPage(SupportPlan tSupportPlan, PageRequest pageRequest) {
        return null;
    }

    @Override
    public SupportPlan insert(SupportPlan tSupportPlan) {
        return null;
    }

    @Override
    public SupportPlan update(SupportPlan tSupportPlan) {
        return null;
    }

    @Override
    public boolean deleteById(Object id) {
        return false;
    }

    @Override
    public SupportPlanDetailVO getPlanDetailById(Long id) {
        SupportPlan plan = supportPlanMapper.queryById(id);
        if (plan == null || plan.getIsDeleted()) {
            return null; // 如果计划不存在或已删除，返回null
        }
        List<SupportMeasure> measures = supportMeasureMapper.queryByPlanId(id);

        // 组装VO对象
        SupportPlanDetailVO detailVO = new SupportPlanDetailVO(plan, measures);

        // 填充额外信息
        if(plan.getId() != null) {
            User planner = userMapper.queryById(plan.getId());
            if(planner != null) detailVO.setPlannerName(planner.getRealName());
        }
        if(plan.getFarmerId() != null) {
            FarmerProfile farmer = farmerProfileMapper.queryById(plan.getFarmerId());
            if(farmer != null) detailVO.setFarmerName(farmer.getHeadName());
        }

        return detailVO;
    }

    @Override
    @Transactional
    public Long updateSupportPlan(Long planId, SupportPlanSaveRequest request, Principal principal) {
        // 1. 验证计划是否存在
        SupportPlan existingPlan = supportPlanMapper.queryById(planId);
        Assert.notNull(existingPlan, "要更新的帮扶计划不存在");
        Assert.isTrue(!existingPlan.getIsDeleted(), "该帮扶计划已被删除");
        // 可选：检查计划状态，例如已完成的计划不能修改
        // Assert.isTrue(!"已完成".equals(existingPlan.getStatus()), "已完成的计划不能修改");

        // 2. 更新主计划信息
        BeanUtils.copyProperties(request, existingPlan);
        existingPlan.setUpdateTime(new Date());
        supportPlanMapper.update(existingPlan);

        // 3. 更新帮扶措施 (采用“先删后增”策略，确保数据一致性)
        // 3.1 逻辑删除旧的所有措施
        supportMeasureMapper.softDeleteByPlanId(planId);

        // 3.2 新增所有新的措施
        for (SupportMeasureDTO measureDTO : request.getMeasures()) {
            SupportMeasure measure = new SupportMeasure();
            BeanUtils.copyProperties(measureDTO, measure);
            measure.setPlanId(planId); // 关联到主计划
            measure.setStatus("未开始");
            measure.setIsDeleted(false);
            measure.setCreateTime(new Date());
            measure.setUpdateTime(new Date());
            supportMeasureMapper.insert(measure);
        }

        return planId;
    }

    @Override
    @Transactional
    public void deleteSupportPlanById(Long id) {
        // 1. 验证计划是否存在
        SupportPlan plan = supportPlanMapper.queryById(id);
        Assert.notNull(plan, "要删除的帮扶计划不存在");

        // 2. 逻辑删除主计划
        supportPlanMapper.softDeleteById(id);

        // 3. 逻辑删除所有关联的措施
        supportMeasureMapper.softDeleteByPlanId(id);
    }
}