package com.book.room.model;

import lombok.Data;

import java.util.Date;
import java.util.Set;

/**
 * 存储的时间记录
 * @Author: liyimeng
 * @Date 2023/3/18 18:18
 */
@Data
public class BookingRecord {
    private int id;
    private long seqNum;
    private String bookerName;
    private Date startDate;
    private Date endDate;
    private int status;
}
