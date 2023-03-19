package com.book.room.base;

/**
 * 预约状态
 * @Author: liyimeng
 * @Date 2023/3/19 11:49
 */
public enum OrderStatus {
    /**
     * 状态有效
     */
    CANCEL(0, "无效"),
    /**
     * 状态无效
     */
    ORDERED(1, "有效");

    int status;
    String desc;

    OrderStatus(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }

    public static OrderStatus codeOf(int code) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.getStatus() == code) {
                return status;
            }
        }
        return CANCEL;
    }
}
