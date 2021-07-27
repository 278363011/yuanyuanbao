package com.yuanbaobao.mini.constants;

public interface Constant {
    String SELECT_SUCCESS = "查询成功";
    String SELECT_ERROR = "查询失败";
    String SELECT_EXCEPTION = "查询异常";

    String UPDATE_SUCCESS = "更新成功";
    String UPDATE_ERROR = "更新失败";
    String UPDATE_EXCEPTION = "更新异常";

    String DELETE_SUCCESS = "删除成功";
    String DELETE_ERROR = "删除失败";
    String DELETE_EXCEPTION = "删除异常";

    String INSERT_SUCCESS = "插入成功";
    String INSERT_ERROR = "插入失败";
    String INSERT_EXCEPTION = "插入异常";

    // 微信code认证接口服务器地址
    String WX_SERVER_CODE_URL = "https://api.weixin.qq.com/sns/jscode2session";

    // restTemplate 最大读取时间
    int REST_READ_OUT = 1000 * 10;

    // restTemplate 最大链接时间
    int REST_CONNECT_OUT = 1000 * 10;


}
