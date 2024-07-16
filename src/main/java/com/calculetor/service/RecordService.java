package com.calculetor.service;

import com.calculetor.model.Operation;
import com.calculetor.model.OperationType;
import com.calculetor.model.Record;
import com.calculetor.model.User;
import com.calculetor.repository.OperationRepository;
import com.calculetor.repository.RecordRepository;
import com.calculetor.repository.UserRepository;
import com.calculetor.utils.RecordResponse;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RecordService {

    private final OperationRepository operationRepository;
    private final RecordRepository recordRepository;
    private final UserRepository userRepository;
    Integer cost=3;
    Integer OperationPerformAmount=80000;

    public void addition( Integer userId, Record record) {
        User user=  this.userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
        Integer balance = record.getBalance();
        Integer operationAmount = record.getOperationAmount();
        Operation operation = record.getOperation();
        if(operationAmount > balance) {
            throw new RuntimeException("Balance low");
        }else{
            balance = balance - cost;
            operationAmount=OperationPerformAmount+operationAmount;
            record.setOperationAmount(operationAmount);
            operation.setType(OperationType.addition);
            this.operationRepository.save(operation);
            this.recordRepository.save(record);
        }


    }

    public void subtraction( Integer userId, Record record) {
        User user=  this.userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
        Integer balance = record.getBalance();
        Integer operationAmount = record.getOperationAmount();
        Operation operation = record.getOperation();
        if(operationAmount > balance) {
            throw new RuntimeException("Balance low");
        }else{
            balance = balance - cost;
            operationAmount=OperationPerformAmount-operationAmount;
            record.setOperationAmount(operationAmount);
            operation.setType(OperationType.subtraction);
            this.operationRepository.save(operation);
            this.recordRepository.save(record);
        }


    }


    public void multiplication( Integer userId, Record record) {
        User user=  this.userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
        Integer balance = record.getBalance();
        Integer operationAmount = record.getOperationAmount();
        Operation operation = record.getOperation();
        if(operationAmount > balance) {
            throw new RuntimeException("Balance low");
        }else{
            balance = balance - cost;
            operationAmount=OperationPerformAmount*operationAmount;
            record.setOperationAmount(operationAmount);
            operation.setType(OperationType.multiplication);
            this.operationRepository.save(operation);
            this.recordRepository.save(record);
        }


    }

    public void division( Integer userId, Record record) {
        User user=  this.userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
        Integer balance = record.getBalance();
        Integer operationAmount = record.getOperationAmount();
        Operation operation = record.getOperation();
        if(operationAmount > balance) {
            throw new RuntimeException("Balance low");
        }else{
            balance = balance - cost;
            operationAmount=OperationPerformAmount/operationAmount;
            record.setOperationAmount(operationAmount);
            operation.setType(OperationType.division);
            this.operationRepository.save(operation);
            this.recordRepository.save(record);
        }


    }

    public RecordResponse getAllRecord(Integer pageNumber, Integer pageSize, String sortBy, String sortDir){
        Sort sort=null;
        if(sortDir.equalsIgnoreCase("asc")) {
            sort=Sort.by(sortBy).ascending();
        }else {
            sort=Sort.by(sortBy).descending();
        }

        Pageable pageable	= PageRequest.of(pageNumber, pageSize, sort);
        Page<Record> records = this.recordRepository.findAll(pageable);
        List<Record> content = records.getContent();
        RecordResponse recordResponse =  new RecordResponse();
        recordResponse.setRecords(content);
        recordResponse.setLastPage(records.isLast());
        recordResponse.setPageNumber(records.getNumber());
        recordResponse.setPageSize(records.getSize());
        recordResponse.setTotalElement(records.getTotalElements());
        recordResponse.setTotalPage(records.getTotalPages());
        return recordResponse;

    }
     public void deleteRecord(Integer recordId) {
        Record record = this.recordRepository.findById(recordId).orElseThrow(() -> new RuntimeException("Record not found"));
        this.recordRepository.delete(record);
    }

}
