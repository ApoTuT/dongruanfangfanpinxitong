package com.ysu.drffpjcxt.service;

import com.ysu.drffpjcxt.entity.PovertyClue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 贫困线索表(PovertyClue)表服务接口
 *
 * @author makejava
 * @since 2025-06-12 10:09:25
 */
public interface PovertyClueService
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PovertyClue queryById(Object id);

    /**
     * 分页查询
     *
     * @param tPovertyClue 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    Page<PovertyClue> queryByPage(PovertyClue tPovertyClue, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param tPovertyClue 实例对象
     * @return 实例对象
     */
    PovertyClue insert(PovertyClue tPovertyClue);

    /**
     * 修改数据
     *
     * @param tPovertyClue 实例对象
     * @return 实例对象
     */
    PovertyClue update(PovertyClue tPovertyClue);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Object id);

}
