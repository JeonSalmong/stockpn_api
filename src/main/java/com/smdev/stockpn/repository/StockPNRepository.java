package com.smdev.stockpn.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface StockPNRepository {
    public List<Map<String, Object>> getPos(Map<String, Object> params);

    public List<Map<String, Object>> getNeg(Map<String, Object> params);

    public List<Map<String, Object>> getUSA(Map<String, Object> params);

    public List<Map<String, Object>> getDetail(Map<String, Object> params);

    public List<Map<String, Object>> getDetail_us(Map<String, Object> params);
}
