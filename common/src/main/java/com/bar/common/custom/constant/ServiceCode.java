package com.bar.common.custom.constant;

public class ServiceCode {

    public static final int OK = 21020000;
    public static final String OK_DEFAULT_MSG = "操作成功";

    public static final int DELETE_SUCCESS = 21020001;
    public static final String DELETE_SUCCESS_MSG = "删除成功";

    public static final int DELETE_FAILURE = 41020002;
    public static final String DELETE_FAILURE_MSG = "删除失败";

    public static final int UNAUTHORIZED = 41020003;
    public static final String UNAUTHORIZED_DEFAULT_MSG = "未授权";

    public static final int MISSING_PARAM_CODE = 41020004;
    public static final String MISSING_PARAM_CODE_DEFAULT_MSG = "缺少参数";

    public static final int PAGE_NOT_FOUND = 41020005;
    public static final String PAGE_NOT_FOUND_DEFAULT_MSG = "找不到请求的页面";

    public static final int RESOURCE_NOT_FOUND = 41020006;
    public static final String RESOURCE_NOT_FOUND_DEFAULT_MSG = "找不到请求的资源";

    //无效参数
    public static final int INVILD_PARAM_CODE = 41020007;
    public static final String INVILD_PARAM_DEFAULT_MSG = "无效参数";

    public static final int ERROR = 41020000;
    public static final String ERROR_DEFAULT_MSG = "操作失败";

    public static final int SERVER_ERROR = 51020000;
    public static final String SERVER_ERROR_DEFAULT_MSG = "服务端内部错误";

    public static final int SERVER_REPETITION = 61020001;
    public static final String SERVER_REPETITION_DEFAULT_MSG = "操作失败，存在重复数据";

    public static final int JSON_FORMART_ERROR = 51020001;
    public static final String JSON_ERROR_DEFAULT_MSG = "格式化json字符串失败";

}
