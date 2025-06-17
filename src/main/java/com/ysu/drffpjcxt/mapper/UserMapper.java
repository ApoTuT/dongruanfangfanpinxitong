package com.ysu.drffpjcxt.mapper;

import com.ysu.drffpjcxt.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 用户基础信息表(User)表数据库访问层
 *
 * @author makejava
 * @since 2025-06-12 10:09:28
 */
@Mapper
public interface UserMapper
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Object id);

    /**
     * 查询指定行数据
     *
     * @param User    查询条件
     * @param pageable 分页对象
     * @return 对象列表
     */
    List<User> queryAllByLimit(User User, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param User 查询条件
     * @return 总行数
     */
    long count(User User);

    /**
     * 新增数据
     *
     * @param User 实例对象
     * @return 影响行数
     */
    int insert(User User);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<User> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<User> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<User> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<User> entities);

    /**
     * 修改数据
     *
     * @param User 实例对象
     * @return 影响行数
     */
    int update(User User);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Object id);
    /**
     * 【新增】通过手机号查询单条数据 (未删除的记录)
     * 用于注册时检查手机号唯一性及登录时获取用户信息。
     *
     * @param phone 手机号
     * @return 实例对象
     */
    User findByPhone(@Param("phone") String phone);

    /**
     * 【新增】通过身份证号查询单条数据 (未删除的记录)
     * 用于注册时检查身份证号唯一性。
     *
     * @param idCard 身份证号
     * @return 实例对象
     */
    User findByIdCard(@Param("idCard") String idCard);
}

