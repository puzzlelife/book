package com.book.room.model;

import lombok.Data;

import java.util.Date;
import java.util.Set;

/**
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
    private Date date;
    private int status;
}
