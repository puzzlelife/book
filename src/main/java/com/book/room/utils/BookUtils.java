package com.book.room.utils;



import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class BookUtils {

    public List<Integer> dateToList(Date startDate, Date endDate){
        return new ArrayList<>();
    }

    public Pair listToDate(List<Integer> var){
        return Pair.of(new Date(),new Date());
    }
}
