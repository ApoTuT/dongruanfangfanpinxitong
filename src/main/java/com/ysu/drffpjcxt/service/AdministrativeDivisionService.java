package com.ysu.drffpjcxt.service;



import com.ysu.drffpjcxt.entity.AdministrativeDivision;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 行政区划表(AdministrativeDivision)表服务接口
 *
 * @author makejava
 * @since 2025-06-12 10:09:15
 */
public interface AdministrativeDivisionService
{

    /**
     * 通过ID查询单条数据
     *
     * @param code 主键
     * @return 实例对象
     */
    AdministrativeDivision queryById(String code);

    /**
     * 分页查询
     *
     * @param AdministrativeDivision 筛选条件
     * @param pageRequest             分页对象
     * @return 查询结果
     */
    Page<AdministrativeDivision> queryByPage(AdministrativeDivision AdministrativeDivision, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param AdministrativeDivision 实例对象
     * @return 实例对象
     */
    AdministrativeDivision insert(AdministrativeDivision AdministrativeDivision);

    /**
     * 修改数据
     *
     * @param tAdministrativeDivision 实例对象
     * @return 实例对象
     */
    AdministrativeDivision update(AdministrativeDivision tAdministrativeDivision);

    /**
     * 通过主键删除数据
     *
     * @param code 主键
     * @return 是否成功
     */
    boolean deleteById(String code);

}
