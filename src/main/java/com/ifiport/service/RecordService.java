package com.ifiport.service;

/**
 * Created by Martin on 2017/08/11.
 */

import com.ifiport.model.Record;
import com.ifiport.model.User;

import java.util.List;

public interface RecordService {

    public List<Record> getAllRecords();
    public Record getRecordById(long id);


    void insert(Record newRecord);


    List<Record> findByUser(User user);

    void deleteById(long id);
}
