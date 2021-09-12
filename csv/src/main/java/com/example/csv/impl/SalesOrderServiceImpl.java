package com.example.csv.impl;

import com.example.csv.dal.model.SalesOrder;
import com.example.csv.dal.repository.SalesOrderRepository;
import com.example.csv.service.SalesOrderService;
import com.example.csv.service.dto.SalesOrderDTO;
import com.example.csv.utils.CSVHelper;
import com.example.csv.utils.ResultList;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SalesOrderServiceImpl implements SalesOrderService {
    @Autowired
    SalesOrderRepository repository;

    @Override
    public void save(MultipartFile file) {
        try {
            List<SalesOrder> tutorials = CSVHelper.csvToSalesModelObject(file.getInputStream());
            repository.saveAll(tutorials);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    @Override
    public ResultList<SalesOrderDTO> getAllSalesOrders(Integer page, Integer pageSize) {

        ResultList resultList = new ResultList();
        resultList.setCurrentPage(page);
        resultList.setPageSize(pageSize);
        resultList.setSuccess(true);

        try {
            Pageable paging = PageRequest.of(page, pageSize);
            Page<SalesOrder> pageRes = repository.findAll(paging);

            List<SalesOrderDTO> data = new ArrayList<>();
            for (SalesOrder salesOrder : pageRes.getContent()) {
                SalesOrderDTO dto = new SalesOrderDTO();
                BeanUtils.copyProperties(salesOrder, dto);
                data.add(dto);
            }

            resultList.setData(data);
            resultList.setTotalNum(pageRes.getTotalElements());
            resultList.setTotalPages(pageRes.getTotalPages());
            resultList.setSuccess(true);
            resultList.setCode(HttpStatus.OK.value());
        }catch (Exception e){
            e.printStackTrace();
            resultList.setSuccess(false);
            resultList.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resultList.setErrMsg(e.getMessage());
        }
        return resultList;
    }
}
