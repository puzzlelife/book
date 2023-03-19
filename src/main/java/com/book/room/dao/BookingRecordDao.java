package com.book.room.dao;

import com.book.room.model.BookingRecord;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * @Author: wangyao
 * @Date 2023/3/18 18:46
 */
@Mapper
public interface BookingRecordDao {
    String TABLE_NAME = " book_record ";
    String INSERT_FIELDS = " seq_num, booker_name, start_date,end_date,date,status";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;


    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{seqNum},#{bookerName},#{startDate},#{endDate},#{date},#{status}"})
    int insertRecode(BookingRecord record);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where date>#{date} and status=#{status}"})
    List<BookingRecord> queryRecordAfterDate(Date date,int status);

    @Update({"update ",TABLE_NAME,"set status=0 where seq_num={seqNum}"})
    int invalidRecord(long seqNum);
}
