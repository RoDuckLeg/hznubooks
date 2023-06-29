package com.example.hznubooks.mapper;

import com.example.hznubooks.entity.Record;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Date;
import java.util.List;
@Mapper
public interface RecordMapper {
    List<Record> getRecords();
    List<Record> getRecordByUsername(String username);

    int updateRecord(int recordid, double price);

    int addRecord(String username, int bookid);
}