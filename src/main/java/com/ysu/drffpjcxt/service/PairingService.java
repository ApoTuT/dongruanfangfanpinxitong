package com.ysu.drffpjcxt.service;

import com.ysu.drffpjcxt.entity.Pairing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 帮扶结对关系表(Pairing)表服务接口
 *
 * @author makejava
 * @since 2025-06-12 10:09:23
 */
public interface PairingService
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Pairing queryById(Object id);

    /**
     * 分页查询
     *
     * @param pairing    筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    Page<Pairing> queryByPage(Pairing pairing, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param pairing 实例对象
     * @return 实例对象
     */
    Pairing insert(Pairing pairing);


    Pairing update(Pairing pairing);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Object id);

}
