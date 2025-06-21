package com.ysu.drffpjcxt.service.impl.risk;

import com.ysu.drffpjcxt.entity.HighRiskProfile;
import com.ysu.drffpjcxt.mapper.HighRiskProfileMapper;
import com.ysu.drffpjcxt.service.HighRiskProfileService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 高风险户档案表(HighRiskProfile)表服务实现类
 *
 * @author sheng
 * @since 2025-06-21
 */
@Service("highRiskProfileService")
public class HighRiskProfileServiceImpl implements HighRiskProfileService {
    @Resource
    private HighRiskProfileMapper highRiskProfileMapper;

    @Override
    public HighRiskProfile queryById(Object id) {
        return highRiskProfileMapper.queryById(id);
    }

    @Override
    public Page<HighRiskProfile> queryByPage(HighRiskProfile highRiskProfile, PageRequest pageRequest) {
        long total = highRiskProfileMapper.count(highRiskProfile);
        List<HighRiskProfile> list = highRiskProfileMapper.queryAllByLimit(highRiskProfile, pageRequest);
        return new PageImpl<>(list, pageRequest, total);
    }

    @Override
    public HighRiskProfile insert(HighRiskProfile highRiskProfile) {
        highRiskProfile.setCreateTime(new Date());
        highRiskProfile.setUpdateTime(new Date());
        highRiskProfile.setIsDeleted(false);
        highRiskProfileMapper.insert(highRiskProfile);
        return highRiskProfile;
    }

    @Override
    public HighRiskProfile update(HighRiskProfile highRiskProfile) {
        highRiskProfile.setUpdateTime(new Date());
        highRiskProfileMapper.update(highRiskProfile);
        return queryById(highRiskProfile.getId());
    }

    @Override
    public boolean deleteById(Object id) {
        return highRiskProfileMapper.deleteById(id) > 0;
    }
}
