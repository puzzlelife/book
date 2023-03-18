package com.book.room.dao;

import com.book.room.model.BookingRecord;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: wangyao
 * @Date 2023/3/18 18:46
 */
@Mapper
public interface BookingRecordDao {
    String TABLE_NAME = " book_record ";
    String INSERT_FIELDS = " booker_name, booking_interval, date";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;


    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS,
            ") values (#{bookerName},#{bookingInterval},#{date}"})
    int insertRecode(BookingRecord record);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where date=#{date}"})
    List<BookingRecord> queryRecordByDate(String date);

    @Delete({"delete from ",TABLE_NAME,"where id={id}"})
    int deleteRecord(int id);
}
