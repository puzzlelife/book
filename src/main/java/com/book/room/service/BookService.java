package com.book.room.service;

import com.book.room.base.OrderStatus;
import com.book.room.dao.BookingRecordDao;
import com.book.room.model.BookingRecord;
import com.book.room.model.DateRange;
import com.book.room.utils.BookUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 预定服务
 *
 * @Author: liyimeng
 * @Date 2023/3/18 18:44
 */
@Service
public class BookService {
    @Resource
    private BookingRecordDao recordDao;

    @Transactional(rollbackFor = Exception.class)
    public String bookRoom(Date startDate, Date endDate, String userName) {
        if (startDate.before(new Date())) {
            return "不能预订以前的时间";
        }
        Random random = new Random();
        long seqNum = random.nextLong();

        List<DateRange> dates = BookUtils.convertToStoreData(startDate, endDate);
        dates.forEach(o -> {
            if (checkConflict(o.getBegin(), o.getEnd())) {
                throw new RuntimeException("重复预订");
            }
            BookingRecord record = new BookingRecord();
            record.setBookerName(userName);
            record.setSeqNum(seqNum);
            record.setStatus(1);
            record.setStartDate(o.getBegin());
            record.setEndDate(o.getEnd());
            recordDao.insertRecord(record);
        });
        return String.valueOf(seqNum);
    }

    public void cancelRoom(long seqNum) {
        List<BookingRecord> records = recordDao.queryRecordBySeq(seqNum);
        if (Objects.isNull(records) || records.size()==0){
            return;
        }
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

        List<BookingRecord> bookedRecords = recordDao.queryRecordAfterDate(getZero(startDate), OrderStatus.ORDERED.getStatus());
        boolean conflict = false;
        for (BookingRecord record : bookedRecords) {
            if (BookUtils.isInInterval(record.getStartDate(), record.getEndDate(), startDate) ||
                    BookUtils.isInInterval(record.getStartDate(), record.getEndDate(), endDate) ||
                    (startDate.before(record.getStartDate()) && endDate.after(record.getEndDate())) ||
                    (startDate.equals(record.getStartDate()) && endDate.equals(record.getEndDate()))) {
                conflict = true;
                break;
            }
        }
        return conflict;
    }

    private Date getZero(Date date)  {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();

    }

}
