package com.book.room.controller;

import com.book.room.service.BookService;
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
    public String book(@RequestParam("startDate") Date startDate ,
                       @RequestParam("endDate") Date endDate) {
        String result ;
        try {
            result = bookService.bookRoom(startDate,endDate);
            return result;
        } catch (Exception e){
            result = e.getMessage();
            return result;
        }
    }

    @PostMapping("/room/cancel")
    public String cancel(@RequestParam("seqNum") long seqNum) {
        bookService.cancelRoom(seqNum);
        return "success";
    }

    @PostMapping("/room/queryAvailableRoom")
    public String availableRoom(@RequestParam("startDate") Date startDate ,
                                @RequestParam("endDate") Date endDate) {
        return bookService.queryAvailableRoom(startDate,endDate);
    }

    @PostMapping("/room/queryBookedRoom")
    public String bookedRoom(@RequestParam("startDate") Date startDate ,
                                @RequestParam("endDate") Date endDate) {
        return bookService.queryBookedRoom(startDate,endDate);
    }

}
