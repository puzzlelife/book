package com.book.room.service;

import com.book.room.base.OrderStatus;
import com.book.room.dao.BookingRecordDao;
import com.book.room.model.BookingRecord;
import com.book.room.utils.BookUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Author: wangyao
 * @Date 2023/3/18 18:44
 */
@Service
public class BookService {
    @Resource
    private BookingRecordDao recordDao;

    @Transactional(rollbackFor = Exception.class)
    public void bookRoom(Date startDate, Date endDate) {
        Random random = new Random();
        long seqNum = random.nextLong();
        String userName = "user";

        List<Pair<Date, Date>> dates = BookUtils.convertToStoreData(startDate, endDate);
        dates.forEach(o -> {
            if (checkConflict(o.getLeft(), o.getRight())) {
                throw new RuntimeException("重复预订");
            }
            BookingRecord record = new BookingRecord();
            record.setBookerName(userName);
            record.setSeqNum(seqNum);
            record.setStatus(1);
            record.setStartDate(o.getLeft());
            record.setEndDate(o.getRight());
        });


    }

    public void cancelRoom(int seqNum) {
        recordDao.invalidRecord(seqNum);
    }

    public String queryAvailableRoom(Date startDate, Date endDate) {
        if (checkConflict(startDate, endDate)) {
            return "empty";
        }
        return "Available";
    }

    public String queryBookedRoom(Date startDate, Date endDate) {
        if (checkConflict(startDate, endDate)) {
            return "Booked";
        }
        return "empty";
    }

    private boolean checkConflict(Date startDate, Date endDate) {
        List<BookingRecord> bookedRecords = recordDao.queryRecordAfterDate(new Date(), OrderStatus.ORDERED.getStatus());
        boolean conflict = false;
        for (BookingRecord record : bookedRecords) {
            if (BookUtils.isInInterval(record.getStartDate(), record.getEndDate(), startDate) ||
                    BookUtils.isInInterval(record.getStartDate(), record.getEndDate(), endDate) ||
                    (startDate.before(record.getStartDate()) && endDate.after(record.getEndDate()))) {
                conflict = true;
                break;
            }
        }
        return conflict;
    }

}
