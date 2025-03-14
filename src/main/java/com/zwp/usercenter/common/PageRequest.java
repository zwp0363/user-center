package com.zwp.usercenter.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用分页请求参数
 *
 * @author zwp
 */
@Data
public class PageRequest implements Serializable {// 可以被序列化，例如在网络传输或者持久化存储时

    private static final long serialVersionUID = 5676253005668997732L;
    /**
     * 页面大小
     */
    protected int pageSize;

    /**
     * 当前是第几页
     */
    protected int pageNum;
}
