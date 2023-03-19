package com.book.room.utils;


import com.book.room.model.DateRange;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 预定工具包
 * @Author: liyimeng
 * @Date 2023/3/18 18:44
 */
public class BookUtils {
    /**
     * 把时间按天拆分存在转换为存放在库中的数据
     * @param startDate
     * @param endDate
     * @return
     */
    public static List<DateRange> convertToStoreData(Date startDate, Date endDate) {
        return splitDateRangeByDay(startDate, endDate);
    }


    /**
     * 判断传入的时间是否在已知时间间隙中
     *
     * @param startDate
     * @param endDate
     * @param date
     * @return
     */
    public static boolean isInInterval(Date startDate, Date endDate, Date date) {
        return date.after(startDate) && date.before(endDate);
    }


    private static List<DateRange> splitDateRangeByDay(Date begin, Date end) {
        long time = end.getTime() - begin.getTime();
        if (time == 0) {
            return new ArrayList<>();
        }
        List<DateRange> list = new ArrayList<>();
        DateRange range = new DateRange();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(begin);
        range.setBegin(begin);
        while (true) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            if (calendar.getTime().after(end)) {
                range.setEnd(end);
                list.add(range);
                break;
            }
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            //今天的开始
            Date tmpBeginTime = calendar.getTime();
            //计算出上一天的最后一秒
            calendar.add(Calendar.SECOND, -1);
            range.setEnd(calendar.getTime());
            list.add(range);
            //创建新的时间段
            range = new DateRange();
            range.setBegin(tmpBeginTime);
            //回到今天
            calendar.add(Calendar.SECOND, +1);
        }
        return list;
    }

}