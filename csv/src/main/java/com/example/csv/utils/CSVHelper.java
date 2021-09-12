package com.example.csv.utils;

import com.example.csv.dal.model.SalesOrder;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CSVHelper {
    public static String TYPE = "text/csv";

    static String[] HEADERs = { "Region","Country","Item Type","SalesOrder Channel","Order Priority",
            "Order Date","Order ID","Ship Date","Units Sold","Unit Price","Unit Cost","Total Revenue","Total Cost","Total Profit" };

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<SalesOrder> csvToSalesModelObject(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<SalesOrder> recordList = new ArrayList<SalesOrder>();
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
            String creator = NRICGenerator.getCurrentUserNRIC();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                //TODO validate csv record
                SalesOrder order = new SalesOrder();
                order.setRegion(csvRecord.get("Region"));
                order.setCountry(csvRecord.get("Country"));
                order.setItemType(csvRecord.get("Item Type"));
                order.setSalesChannel(csvRecord.get("SalesOrder Channel"));
                order.setOrderPriority(csvRecord.get("Order Priority"));
                order.setOrderDate(formatter.parse(csvRecord.get("Order Date")));
                order.setOrderId(Long.parseLong(csvRecord.get("Order ID")));
                order.setShipDate(formatter.parse(csvRecord.get("Ship Date")));
                order.setUnitsSold(Long.parseLong(csvRecord.get("Units Sold")));
                order.setUnitPrice(Double.parseDouble(csvRecord.get("Unit Price")));
                order.setUnitCost(Double.parseDouble(csvRecord.get("Unit Cost")));
                order.setTotalRevenue(Double.parseDouble(csvRecord.get("Total Revenue")));
                order.setTotalCost(Double.parseDouble(csvRecord.get("Total Cost")));
                order.setUnitPrice(Double.parseDouble(csvRecord.get("Total Profit")));
                order.setCreator(creator);
                recordList.add(order);
            }

            return recordList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

}
