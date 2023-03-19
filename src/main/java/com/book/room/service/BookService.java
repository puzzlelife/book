package com.book.room.service;

import com.book.room.dao.BookingRecordDao;
import com.book.room.model.BookingRecord;
import com.book.room.utils.BookUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: wangyao
 * @Date 2023/3/18 18:44
 */
@Service
public class BookService {
    @Resource
    private BookingRecordDao recordDao;

    public void bookRoom(Date startDate, Date endDate){

    }

    public void cancelRoom(int seqNum){
        recordDao.invalidRecord(seqNum);
    }

    public void queryAvailableRoom(Date startDate, Date endDate){

    }

    public void queryBookedRoom(Date startDate, Date endDate){

    }

}
