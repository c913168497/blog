package com.cnc.common.persistence.exception;

/**
 * Created by Administrator on 2016-12-15.
 */
public enum  BizExceptionType {

    DB_INSERT_RESULT_0(90040001, "数据库操作,insert返回0"),
    DB_UPDATE_RESULT_0(90040002, "数据库操作,update返回0"),
    DB_SELECTONE_IS_NULL(90040003, "数据库操作,selectOne返回null"),
    DB_LIST_IS_NULL(90040004, "数据库操作,list返回null"),
    TOKEN_IS_ILLICIT(90040005, "Token 验证非法"),
    SESSION_IS_OUT_TIME(90040006, "会话超时"),
    DB_GET_SEQ_NEXT_VALUE_ERROR(90040007, "获取序列出错");

    BizExceptionType(int code,String msg) {
        this.msg = msg;
        this.code = code;
    }

    /**
     * 异常信息
     */
    private String msg;
    /**
     * 具体异常码
     */
    private int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getOrdinal() {
        return this.ordinal();
    }
}
