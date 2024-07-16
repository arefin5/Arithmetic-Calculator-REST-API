package com.calculetor.controller;

import com.calculetor.model.Record;
import com.calculetor.service.RecordService;
import com.calculetor.utils.AppConstant;
import com.calculetor.utils.RecordResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/records")
@RequiredArgsConstructor
public class RecordController {
    private final RecordService recordService;

    @PostMapping("/user/{userId}/record-add")
    public String addtion( @PathVariable("userId")Integer userId, @RequestBody Record record){
        this.recordService.addition(userId,record);
    return "addition success";
    }
    @PostMapping("/user/{userId}/record-sub")
    public String subtraction( @PathVariable("userId")Integer userId, @RequestBody Record record){
        this.recordService.subtraction(userId, record);
        return "subtraction success";
    }

    @PostMapping("/user/{userId}/record-multi")
    public String multiplication( @PathVariable("userId")Integer userId, @RequestBody Record record){
        this.recordService.multiplication(userId, record);
        return "multiplication success";
    }

    @PostMapping("/user/{userId}/record-div")
    public String division( @PathVariable("userId")Integer userId, @RequestBody Record record){
        this.recordService.division(userId, record);
        return "division success";
    }
     @DeleteMapping("/{recordId}")
    public String deleteRecord(@PathVariable("recordId") Integer recordId) {
        this.recordService.deleteRecord(recordId);
        return "Record deleted successfully";
    }

    @GetMapping("/")
    public RecordResponse getAllRecords(

            @RequestParam(value = "pageNumber",defaultValue = AppConstant.PAGE_NUMBER,required = false)Integer pageNumber,
            @RequestParam(value="pageSize",defaultValue = AppConstant.PAGE_SIZE,required = false)Integer pageSize,
            @RequestParam(value="sortBy",defaultValue = AppConstant.SORT_BY,required = false)String sortBy,
            @RequestParam(value="sortDir",defaultValue = AppConstant.SORT_DIR,required = false)String sortDir
    ){
            RecordResponse allRecord = this.recordService.getAllRecord(pageNumber, pageSize, sortBy, sortDir);
    return allRecord;
    }
}
