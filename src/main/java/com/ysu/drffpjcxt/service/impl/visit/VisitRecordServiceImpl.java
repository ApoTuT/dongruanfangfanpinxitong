package com.ysu.drffpjcxt.service.impl.visit;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ysu.drffpjcxt.entity.VisitRecord;
import com.ysu.drffpjcxt.entity.dto.visit.VisitRecordAddDTO;
import com.ysu.drffpjcxt.entity.dto.visit.VisitRecordQueryDTO;
import com.ysu.drffpjcxt.entity.vo.visit.VisitRecordVO;
import com.ysu.drffpjcxt.mapper.VisitRecordMapper;
import com.ysu.drffpjcxt.mapper.FarmerProfileMapper;
import com.ysu.drffpjcxt.mapper.UserMapper;
import com.ysu.drffpjcxt.mapper.VisitPlanMapper;
import com.ysu.drffpjcxt.service.VisitRecordService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitRecordServiceImpl implements VisitRecordService {

    @Autowired
    private VisitRecordMapper visitRecordMapper;

    @Autowired
    private VisitPlanMapper visitPlanMapper;
    @Autowired
    private FarmerProfileMapper farmerProfileMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean addVisitRecord(VisitRecordAddDTO addDTO) {
        // 校验关联数据是否存在
        if (visitPlanMapper.queryById(addDTO.getPlanId()) == null
                || farmerProfileMapper.queryById(addDTO.getFarmerId()) == null
                || userMapper.queryById(addDTO.getVisitorId()) == null) {
            return false;
        }
        VisitRecord visitRecord = new VisitRecord();
        BeanUtils.copyProperties(addDTO, visitRecord);

        visitRecord.setFarmerId(addDTO.getFarmerId());
        visitRecord.setVisitorId(addDTO.getVisitorId());
        visitRecord.setStatus(addDTO.getStatus());
        return visitRecordMapper.insert(visitRecord) > 0;
    }

    @Override
    public boolean deleteVisitRecord(Long recordId) {
        // 在实际应用中, updateBy 应从当前登录用户信息中获取
        String currentUser = "system";
        return visitRecordMapper.softDeleteById(recordId, currentUser) > 0;
    }

    @Override
    public PageInfo<VisitRecordVO> getVisitRecords(VisitRecordQueryDTO queryDTO) {
        // 使用PageHelper进行分页
        PageHelper.startPage(queryDTO.getPageNum(), queryDTO.getPageSize());
        List<VisitRecordVO> records = visitRecordMapper.selectByQuery(queryDTO);
        return new PageInfo<>(records);
    }
}