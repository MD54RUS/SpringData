package com.example.data.data;

import com.example.data.entity.Buyer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BuyerRepository extends CrudRepository<Buyer, Long> {
  List<Buyer> findBuyersByName(String name);
}
