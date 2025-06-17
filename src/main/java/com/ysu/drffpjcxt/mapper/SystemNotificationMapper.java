package com.ysu.drffpjcxt.mapper;

import com.ysu.drffpjcxt.entity.SystemNotification;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 系统通知表(SystemNotification)表数据库访问层
 *
 * @author makejava
 * @since 2025-06-12 10:09:27
 */
@Mapper
public interface SystemNotificationMapper
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SystemNotification queryById(Object id);

    /**
     * 查询指定行数据
     *
     * @param SystemNotification 查询条件
     * @param pageable            分页对象
     * @return 对象列表
     */
    List<SystemNotification> queryAllByLimit(SystemNotification SystemNotification, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param SystemNotification 查询条件
     * @return 总行数
     */
    long count(SystemNotification SystemNotification);

    /**
     * 新增数据
     *
     * @param SystemNotification 实例对象
     * @return 影响行数
     */
    int insert(SystemNotification SystemNotification);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SystemNotification> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SystemNotification> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SystemNotification> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SystemNotification> entities);

    /**
     * 修改数据
     *
     * @param SystemNotification 实例对象
     * @return 影响行数
     */
    int update(SystemNotification SystemNotification);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Object id);

}

