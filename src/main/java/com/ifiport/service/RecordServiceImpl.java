package com.ifiport.service;

import java.util.List;

import com.ifiport.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifiport.model.Record;
import com.ifiport.repository.RecordRepository;

@Service("recordService")
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordRepository recordRepository;

    @Override
    public List<Record> getAllRecords() {
        return recordRepository.findAll();
    }

    @Override
    public Record getRecordById(long id) {
        return recordRepository.findOne(id);
    }

    @Override
    public void insert(Record record){
        recordRepository.save(record);
    }

    @Override
    public List<Record> findByUser(User user){
        return recordRepository.findByCreatorId(user.getId());
    }


    @Override
    public void deleteById(long id){
        recordRepository.delete(id);
    }
}
