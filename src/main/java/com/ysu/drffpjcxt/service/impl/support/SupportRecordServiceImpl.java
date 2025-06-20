package com.ysu.drffpjcxt.service.impl.support;

import com.ysu.drffpjcxt.entity.SupportRecord;
import com.ysu.drffpjcxt.entity.dto.support.SupportRecordSaveRequest;
import com.ysu.drffpjcxt.mapper.SupportMeasureMapper;
import com.ysu.drffpjcxt.mapper.SupportPlanMapper;
import com.ysu.drffpjcxt.mapper.SupportRecordMapper;
import com.ysu.drffpjcxt.mapper.UserMapper;
import com.ysu.drffpjcxt.service.SupportRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.security.Principal;
import java.util.Date;

@Service
public class SupportRecordServiceImpl implements SupportRecordService {

    @Autowired
    private SupportRecordMapper supportRecordMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SupportMeasureMapper supportMeasureMapper;
    @Autowired
    private SupportPlanMapper supportPlanMapper;

    @Override
    @Transactional
    public void createSupportRecord(SupportRecordSaveRequest request, Principal principal) {
        Assert.notNull(request.getMeasureId(), "帮扶措施ID不能为空");
        Assert.notNull(request.getPlanId(), "帮扶计划ID不能为空");
        Assert.notNull(request.getFarmerId(), "农户ID不能为空");
        Assert.notNull(request.getActivityDate(), "活动日期不能为空");

        // 获取当前用户
        Long userId = request.getUserId();

        SupportRecord record = new SupportRecord();
        BeanUtils.copyProperties(request, record);
        record.setCreatedBy(userId);
        record.setIsDeleted(false);
        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());

        supportRecordMapper.insert(record);
        // 将t_support_measure中的status字段更新为“已开始”
        supportMeasureMapper.updateStatus(request.getMeasureId(), "已开始");
    }


    @Override
    public Object getAllSupportRecords() {
        return supportRecordMapper.queryAllByLimit(null, null);
    }

    @Override
    public Object getSupportRecordById(Long id) {
        return supportRecordMapper.queryById(id);
    }
}
