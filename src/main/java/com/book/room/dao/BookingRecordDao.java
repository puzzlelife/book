package com.book.room.dao;

import com.book.room.model.BookingRecord;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * @Author: liyimeng
 * @Date 2023/3/18 18:46
 */
@Mapper
public interface BookingRecordDao {
    String TABLE_NAME = " book_record ";
    String INSERT_FIELDS = " seq_num, booker_name, start_date, end_date, status";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{seqNum},#{bookerName},#{startDate},#{endDate},#{status})"})
    int insertRecord(BookingRecord record);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where start_date>=#{date} and status=#{status}"})
    List<BookingRecord> queryRecordAfterDate(Date date, int status);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where seq_num=#{seqNum}"})
    List<BookingRecord> queryRecordBySeq(long seqNum);

    @Update({"update ", TABLE_NAME, "set status=0 where seq_num=#{seqNum}"})
    int invalidRecord(long seqNum);


}
