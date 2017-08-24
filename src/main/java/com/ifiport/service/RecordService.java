package com.ifiport.service;

/**
 * Created by Martin on 2017/08/11.
 */

import com.ifiport.model.Record;
import com.ifiport.model.User;
import com.ifiport.pojo.RecordSum;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public interface RecordService {

    List<Record> getAllRecords();

    List<Record> getAllRecordsOrderByDate();

    List<Record> getRecordsByDate(@DateTimeFormat(pattern = "yyyy-MM-dd HH:MM:ss") Date date1,
                                  @DateTimeFormat(pattern = "yyyy-MM-dd HH:MM:ss") Date date2);

    Record getRecordById(long id);


    void insert(Record newRecord);


    List<Record> findByUser(User user);

    void deleteById(long id);

    List<RecordSum> getRecordsSummaryByDate(@DateTimeFormat(pattern = "yyyy-MM-dd HH:MM:ss") Date date1,
                                            @DateTimeFormat(pattern = "yyyy-MM-dd HH:MM:ss") Date date2);
}
