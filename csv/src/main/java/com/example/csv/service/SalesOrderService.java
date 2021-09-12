package com.example.csv.service;

import com.example.csv.service.dto.SalesOrderDTO;
import com.example.csv.utils.ResultList;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface SalesOrderService {

    void save(MultipartFile file);

    ResultList<SalesOrderDTO> getAllSalesOrders(Integer page, Integer pageSize);
}
