package com.book.room.model;

import lombok.Data;

import java.util.Set;

/**
 * @Author: wangyao
 * @Date 2023/3/18 18:18
 */
@Data
public class BookingRecord {
    private int id;
    private String bookerName;
    private Set<Integer> bookingInterval;
    private String date;
}
