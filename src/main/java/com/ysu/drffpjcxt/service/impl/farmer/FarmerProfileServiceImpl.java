package com.ysu.drffpjcxt.service.impl.farmer;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ysu.drffpjcxt.entity.FarmerProfile;
import com.ysu.drffpjcxt.entity.vo.farmer.FarmerProfileVO;
import com.ysu.drffpjcxt.mapper.FarmerProfileMapper;
import com.ysu.drffpjcxt.service.FarmerProfileService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ysu
 * @since 2024-04-12
 */
@Service
public class FarmerProfileServiceImpl extends ServiceImpl<FarmerProfileMapper, FarmerProfile> implements FarmerProfileService {

    @Override
    public FarmerProfile queryById(Object id) {
        return null;
    }



    @Override
    public IPage<FarmerProfileVO> getAllFarmerWithRiskStatus(Page<FarmerProfileVO> page) {
        return this.baseMapper.selectAllFarmerWithRiskStatus(page);
    }
}