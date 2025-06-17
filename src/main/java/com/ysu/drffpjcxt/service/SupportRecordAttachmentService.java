package com.ysu.drffpjcxt.service;

import com.ysu.drffpjcxt.entity.SupportRecordAttachment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 帮扶记录附件表(SupportRecordAttachment)表服务接口
 *
 * @author makejava
 * @since 2025-06-12 10:09:27
 */
public interface SupportRecordAttachmentService
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SupportRecordAttachment queryById(Object id);

    /**
     * 分页查询
     *
     * @param tSupportRecordAttachment 筛选条件
     * @param pageRequest              分页对象
     * @return 查询结果
     */
    Page<SupportRecordAttachment> queryByPage(SupportRecordAttachment tSupportRecordAttachment, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param tSupportRecordAttachment 实例对象
     * @return 实例对象
     */
    SupportRecordAttachment insert(SupportRecordAttachment tSupportRecordAttachment);

    /**
     * 修改数据
     *
     * @param tSupportRecordAttachment 实例对象
     * @return 实例对象
     */
    SupportRecordAttachment update(SupportRecordAttachment tSupportRecordAttachment);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Object id);

}
