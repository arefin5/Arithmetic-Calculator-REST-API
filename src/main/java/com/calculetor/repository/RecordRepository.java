package com.calculetor.repository;

import com.calculetor.model.Operation;
import com.calculetor.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record,Integer> {

}
