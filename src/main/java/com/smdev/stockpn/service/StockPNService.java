package com.smdev.stockpn.service;

import com.smdev.stockpn.repository.StockPNRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;
import java.util.Map;

@Transactional
@Service
public class StockPNService {

    @Autowired
    StockPNRepository stockPNRepository;

    @Autowired
    PlatformTransactionManager manager;

    public List<Map<String, Object>> getPos(Map<String, Object> params) {
        TransactionStatus status = manager.getTransaction(new DefaultTransactionDefinition());
        return stockPNRepository.getPos(params);
    }

    public List<Map<String, Object>> getNeg(Map<String, Object> params) {
        return stockPNRepository.getNeg(params);
    }

    public List<Map<String, Object>> getUSA(Map<String, Object> params) {
        return stockPNRepository.getUSA(params);
    }

    public List<Map<String, Object>> getDetail(Map<String, Object> params) {
        return stockPNRepository.getDetail(params);
    }
}
