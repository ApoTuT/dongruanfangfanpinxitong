package com.ysu.drffpjcxt.service;

import com.ysu.drffpjcxt.entity.DataDictionary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 数据字典表(DataDictionary)表服务接口
 *
 * @author makejava
 * @since 2025-06-12 10:09:19
 */
public interface DataDictionaryService
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DataDictionary queryById(Object id);

    /**
     * 分页查询
     *
     * @param DataDictionary 筛选条件
     * @param pageRequest     分页对象
     * @return 查询结果
     */
    Page<DataDictionary> queryByPage(DataDictionary DataDictionary, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param DataDictionary 实例对象
     * @return 实例对象
     */
    DataDictionary insert(DataDictionary DataDictionary);

    /**
     * 修改数据
     *
     * @param DataDictionary 实例对象
     * @return 实例对象
     */
    DataDictionary update(DataDictionary DataDictionary);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Object id);

}
