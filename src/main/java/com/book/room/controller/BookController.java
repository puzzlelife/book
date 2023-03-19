package com.book.room.controller;

import com.alibaba.fastjson.JSON;
import com.book.room.service.BookService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author: liyimeng
 * @Date 2023/3/18 18:43
 */
@RestController
public class BookController {

    @Resource
    private BookService bookService;

    @PostMapping("/room/book")
    public String book(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startDate,
                       @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endDate,
                       @RequestParam("userName") String userName) {
        String result;
        try {
            result = bookService.bookRoom(startDate, endDate, userName);
            return JSON.toJSONString(result);
        } catch (Exception e) {
            e.printStackTrace();
            result = e.getMessage();
            return result;
        }
    }

    @PostMapping("/room/cancel")
    public String cancel(@RequestParam("seqNum") long seqNum) {
        bookService.cancelRoom(seqNum);
        return JSON.toJSONString("success");
    }

    @PostMapping("/room/queryAvailableRoom")
    public String availableRoom(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startDate,
                                @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endDate) {
        return JSON.toJSONString(bookService.queryAvailableRoom(startDate, endDate));
    }

    @PostMapping("/room/queryBookedRoom")
    public String bookedRoom(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date startDate,
                             @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date endDate) {
        return JSON.toJSONString(bookService.queryBookedRoom(startDate, endDate));
    }

}
