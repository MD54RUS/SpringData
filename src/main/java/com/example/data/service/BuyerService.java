package com.example.data.service;

import com.example.data.entity.Book;
import com.example.data.entity.Buyer;

public interface BuyerService {

  void sell(Book soldBook, Buyer buyer, int cost);

  int costSoldByBook(Book book);

  int costSoldByBuyer(Buyer buyer);
}
