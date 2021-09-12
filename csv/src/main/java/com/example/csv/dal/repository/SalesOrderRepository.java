package com.example.csv.dal.repository;

import com.example.csv.dal.model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<Sales,Long> {
}
