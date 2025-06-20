package com.ysu.drffpjcxt.service;

import com.ysu.drffpjcxt.entity.dto.support.SupportRecordSaveRequest;
import java.security.Principal;

/**
 * 帮扶记录服务接口
 */
public interface SupportRecordService {

    /**
     * 填报帮扶记录
     * @param request 填报请求对象
     * @param principal 当前登录用户
     */
    void createSupportRecord(SupportRecordSaveRequest request, Principal principal);


    /**
     * 查询所有帮扶记录
     * @return 帮扶记录列表
     */
    Object getAllSupportRecords();

    /**
     * 根据ID查询帮扶记录
     * @param id 记录ID
     * @return 帮扶记录
     */
    Object getSupportRecordById(Long id);
}
