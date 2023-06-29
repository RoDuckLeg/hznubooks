package com.example.hznubooks.service.impl;

import com.example.hznubooks.entity.Record;
import com.example.hznubooks.mapper.RecordMapper;
import com.example.hznubooks.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    RecordMapper recordMapper;

    @Override
    public List<Record> getRecordByUsername(String username) {
        return recordMapper.getRecordByUsername(username);
    }

    @Override
    public List<Record> getRecords() {
        return recordMapper.getRecords();
    }

    @Override
    public int updateRecord(int recordid, double price) {
        recordMapper.updateRecord(recordid, price);
        return 1;
    }

    @Override
    public int addRecord(String username, int bookid) {
        recordMapper.addRecord(username,bookid);
        return 1;
    }
}
