package com.ysu.drffpjcxt.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ysu.drffpjcxt.entity.FarmerProfile;
import com.ysu.drffpjcxt.entity.vo.farmer.FarmerProfileVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 农户档案表(FarmerProfile)表数据库访问层
 *
 * @author makejava
 * @since 2025-06-12 10:09:20
 */
@Mapper
public interface FarmerProfileMapper extends BaseMapper<FarmerProfile>
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    FarmerProfile queryById(Object id);

    /**
     * 统计总行数
     *
     * @param FarmerProfileMapper 查询条件
     * @return 总行数
     */
    long count(FarmerProfileMapper FarmerProfileMapper);

    /**
     * 新增数据
     *
     * @param FarmerProfileMapper 实例对象
     * @return 影响行数
     */
    int insert(FarmerProfileMapper FarmerProfileMapper);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<FarmerProfile> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<FarmerProfileMapper> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<FarmerProfile> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<FarmerProfileMapper> entities);

    /**
     * 修改数据
     *
     * @param FarmerProfileMapper 实例对象
     * @return 影响行数
     */
    int update(FarmerProfileMapper FarmerProfileMapper);


    /**
     * 分页查询所有农户信息及其风险状态
     * @param page 分页参数
     * @return 分页结果
     */
    IPage<FarmerProfileVO> selectAllFarmerWithRiskStatus(IPage<FarmerProfileVO> page);
}

