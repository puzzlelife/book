package com.book.room.dao;

import com.book.room.model.BookingRecord;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where date>#{date}"})
    List<BookingRecord> queryRecordAfterDate(Date date);

    @Delete({"delete from ",TABLE_NAME,"where seq_num={seqNum}"})
    int deleteRecord(int seqNum);
}
