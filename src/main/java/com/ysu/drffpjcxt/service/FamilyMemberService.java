package com.ysu.drffpjcxt.service;

import com.ysu.drffpjcxt.entity.FamilyMember;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 家庭成员表(FamilyMember)表服务接口
 *
 * @author makejava
 * @since 2025-06-12 10:09:20
 */
public interface FamilyMemberService
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    FamilyMember queryById(Object id);

    /**
     * 分页查询
     *
     * @param FamilyMember 筛选条件
     * @param pageRequest   分页对象
     * @return 查询结果
     */
    Page<FamilyMember> queryByPage(FamilyMember FamilyMember, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param FamilyMember 实例对象
     * @return 实例对象
     */
    FamilyMember insert(FamilyMember FamilyMember);

    /**
     * 修改数据
     *
     * @param FamilyMember 实例对象
     * @return 实例对象
     */
    FamilyMember update(FamilyMember FamilyMember);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Object id);

}
