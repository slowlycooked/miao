package com.ifiport.repository;

/**
 * Created by Martin on 2017/08/10.
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifiport.model.Record;

import java.util.List;

@Repository("recordRepository")
public interface RecordRepository extends JpaRepository<Record, Long>{

    List<Record> findByCreatorId(Long id);
}
