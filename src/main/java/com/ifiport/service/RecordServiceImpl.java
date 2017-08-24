package com.ifiport.service;

import com.ifiport.model.Record;
import com.ifiport.model.User;
import com.ifiport.pojo.RecordSum;
import com.ifiport.repository.RecordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

@Service("recordService")
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordsRepository recordRepository;

    @Override
    public List<Record> getAllRecords() {
        return recordRepository.findAll();
    }

    @Override
    public List<Record> getAllRecordsOrderByDate() {
        return recordRepository.findByOrderByUpdatedDesc();
    }

    @Override
    public List<Record> getRecordsByDate(@DateTimeFormat(pattern="yyyy-MM-dd HH:MM:ss") Date date1,
                                         @DateTimeFormat(pattern="yyyy-MM-dd HH:MM:ss") Date date2) {
        return recordRepository.findByDate(date1,date2);
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

    @Override
    public List<RecordSum> getRecordsSummaryByDate(@DateTimeFormat(pattern="yyyy-MM-dd HH:MM:ss") Date date1,
                                                   @DateTimeFormat(pattern="yyyy-MM-dd HH:MM:ss") Date date2) {


        List<RecordSum> lrs = recordRepository.findRecordSummaryByDate(date1, date2);
        lrs.add(getSleepLengthByDate(date1));
        return lrs;
    }

    @PersistenceUnit
    private EntityManagerFactory emf;
    private RecordSum getSleepLengthByDate(Date date){
        DecimalFormat    df   = new DecimalFormat("######0.00");

        EntityManager em = emf.createEntityManager( );
        Query q = em.createNativeQuery("SELECT id, timestampdiff(SECOND,r.record_date1, r.record_date2 ) " +
                "FROM record r where r.type='睡觉' and DATE_FORMAT(r.record_date1,'%Y%m%d') = DATE_FORMAT(?,'%Y%m%d')");

        q.setParameter(1,date);
        List<Object[]> sleeps = q.getResultList();

        Double seconds = 0.0;
        for (Object[] s : sleeps) {
              seconds +=  Double.parseDouble( s[1].toString());
        }
        seconds = seconds/3600;
        System.out.printf("date: %2f %n", seconds);
        RecordSum rs = new RecordSum("睡觉", seconds);
        return rs;
    }






}
