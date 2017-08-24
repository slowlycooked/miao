package com.ifiport.repository;

/**
 * Created by Martin on 2017/08/10.
 */

import com.ifiport.model.Record;
import com.ifiport.pojo.RecordSum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("recordRepository")
public interface RecordsRepository extends JpaRepository<Record, Long>{

    List<Record> findByCreatorId(Long id);

    List<Record> findByOrderByUpdatedDesc();

    @Query(value="SELECT new com.ifiport.pojo.RecordSum(r.type, sum(r.amount)) " +
            "FROM Record r " +
            "WHERE r.recordDate1 between ?1 and ?2 " +
            "AND r.type <> '睡觉'" +
            "group by r.type")

    List<RecordSum> findRecordSummaryByDate(@DateTimeFormat(pattern="yyyy-MM-dd HH:MM:ss") Date date1,
                                            @DateTimeFormat(pattern="yyyy-MM-dd HH:MM:ss") Date date2);


    @Query(value="SELECT r FROM Record r WHERE r.recordDate1 between ?1 and ?2")
    List<Record> findByDate(@DateTimeFormat(pattern="yyyy-MM-dd HH:MM:ss") Date date1,
                            @DateTimeFormat(pattern="yyyy-MM-dd HH:MM:ss") Date date2);
}
