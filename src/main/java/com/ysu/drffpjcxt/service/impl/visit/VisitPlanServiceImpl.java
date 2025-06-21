package com.ysu.drffpjcxt.service.impl.visit;

import com.ysu.drffpjcxt.entity.User;
import com.ysu.drffpjcxt.entity.VisitPlan;
import com.ysu.drffpjcxt.entity.dto.visit.VisitPlanSaveDTO;
import com.ysu.drffpjcxt.entity.vo.visit.VisitPlanVO;
import com.ysu.drffpjcxt.mapper.UserMapper;
import com.ysu.drffpjcxt.mapper.VisitPlanMapper;
import com.ysu.drffpjcxt.service.VisitPlanService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 走访计划服务实现类
 */
@Service
public class VisitPlanServiceImpl implements VisitPlanService {

    @Autowired
    private VisitPlanMapper visitPlanMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public boolean createVisitPlan(VisitPlanSaveDTO saveDTO) {
        VisitPlan visitPlan = new VisitPlan();
        BeanUtils.copyProperties(saveDTO, visitPlan);

        User user = userMapper.queryById(saveDTO.getPlannerId());
        if(user == null)
            return false;

        if(visitPlanMapper.insert(visitPlan) > 0)
            return true;
        else
            return false;
    }

    @Override
    @Transactional
    public boolean deleteVisitPlan(Long id) {

        int delete = visitPlanMapper.deleteById(id);
        if(delete > 0) return true;
        else return false;
    }

    @Override
    @Transactional
    public boolean updateVisitPlan(VisitPlanSaveDTO saveDTO) {
        VisitPlan visitPlan = new VisitPlan();
        BeanUtils.copyProperties(saveDTO, visitPlan);

        User user = userMapper.queryById(saveDTO.getPlannerId());
        if(user == null)
            return false;

        if(visitPlanMapper.update(visitPlan) > 0)
            return true;
        else
            return false;

    }

    @Override
    public VisitPlanVO getVisitPlanById(Long id) {
        // 1. 查询计划VO，包含plannerName
        VisitPlanVO visitPlanVO = visitPlanMapper.selectVisitPlanVOById(id);
        return visitPlanVO;
    }

    @Override
    public List<VisitPlanVO> listAllVisitPlans() {
        // 查询所有计划VOs
        return visitPlanMapper.selectAllVisitPlanVOs();
    }
}