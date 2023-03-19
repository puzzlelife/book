package com.book.room.model;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 存储时间范围
 * @Author: liyimeng
 * @Date 2023/3/18 18:18
 */
@Data
public class DateRange {
    private Date begin; //开始时间

    private Date end; //结束时间

    public String toString(){
        return "{" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(begin) +" ,"  + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(end) +"}";
    }

}