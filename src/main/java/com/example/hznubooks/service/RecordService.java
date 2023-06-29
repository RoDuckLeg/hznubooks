package com.example.hznubooks.service;

import com.example.hznubooks.entity.Record;

import java.sql.Date;
import java.util.List;

public interface RecordService {
    List<Record> getRecordByUsername(String username);
    List<Record> getRecords();

    int updateRecord(int recordid, double price);

    int addRecord(String username, int bookid);
}
