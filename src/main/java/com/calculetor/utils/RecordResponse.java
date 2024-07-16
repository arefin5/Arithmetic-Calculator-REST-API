package com.calculetor.utils;

import com.calculetor.model.Record;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecordResponse {
    List<Record> records;
    Integer pageNumber;
    Integer pageSize;
    long totalElement;
    long totalPage;
    boolean isLastPage;
}
