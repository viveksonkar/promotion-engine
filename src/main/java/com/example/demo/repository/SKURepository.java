package com.example.demo.repository;

import com.example.demo.data.SKU;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SKURepository extends CrudRepository<SKU, String> {
}
