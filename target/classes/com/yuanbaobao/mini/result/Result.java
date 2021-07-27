package com.yuanbaobao.mini.result;

public class Result<T> {
    private int code;
    private String msg;
    private T data;

    public static <T> Result<T> success(final T data) {

        return new Result<T>(data);
    }

    public static <T> Result<T> redirect(final T data) {
        return new Result<T>(-1, data);
    }

    public static <T> Result<T> error(final CodeMsg cm) {

        return new Result<T>(cm);
    }

    public Result(final T data) {
        this.code = 200;
        this.msg = "success";
        this.data = data;
    }

    public Result(final int code, final T data) {
        this.code = code;
        this.msg = "success";
        this.data = data;
    }

    public Result(final CodeMsg cm) {
        if (cm == null) {
            return;
        }
        this.code = cm.getCode();
        this.msg = cm.getMsg();
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(final int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(final String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(final T data) {
        this.data = data;
    }

}
