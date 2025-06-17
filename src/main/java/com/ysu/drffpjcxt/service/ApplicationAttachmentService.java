package com.ysu.drffpjcxt.service;



import com.ysu.drffpjcxt.entity.ApplicationAttachment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 申请佐证材料表(ApplicationAttachment)表服务接口
 *
 * @author makejava
 * @since 2025-06-12 10:09:17
 */
public interface ApplicationAttachmentService
{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ApplicationAttachment queryById(Object id);

    /**
     * 分页查询
     *
     * @param ApplicationAttachment 筛选条件
     * @param pageRequest            分页对象
     * @return 查询结果
     */
    Page<ApplicationAttachment> queryByPage(ApplicationAttachment ApplicationAttachment, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param ApplicationAttachment 实例对象
     * @return 实例对象
     */
    ApplicationAttachment insert(ApplicationAttachment ApplicationAttachment);

    /**
     * 修改数据
     *
     * @param ApplicationAttachment 实例对象
     * @return 实例对象
     */
    ApplicationAttachment update(ApplicationAttachment ApplicationAttachment);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Object id);

}
