package com.example.csv.controller;

import com.example.csv.service.SalesOrderService;
import com.example.csv.service.dto.SalesOrderDTO;
import com.example.csv.utils.CSVHelper;
import com.example.csv.utils.Result;
import com.example.csv.utils.ResultList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/sales-order")
public class SalesOrderController {
    @Autowired
    SalesOrderService salesOrderService;

    @PostMapping("/upload")
    public Result uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        Result result = new Result();

        if (CSVHelper.hasCSVFormat(file)) {
            try {
                salesOrderService.save(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                result.setMessage(message);
                result.setCode(HttpStatus.OK.value());
                result.setSuccess(true);
                return result;
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                result.setMessage(message);
                result.setSuccess(false);
                result.setCode(HttpStatus.EXPECTATION_FAILED.value());
                return result;
            }
        }

        message = "Please upload a csv file!";
        result.setMessage(message);
        result.setSuccess(false);
        return result;
    }

    @GetMapping("/list")
    public ResultList<SalesOrderDTO> getAllOrders(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        return salesOrderService.getAllSalesOrders(page,size);
    }
}
