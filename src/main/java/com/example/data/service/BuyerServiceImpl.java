package com.example.data.service;

import com.example.data.data.SoldBookRepository;
import com.example.data.entity.Book;
import com.example.data.entity.Buyer;
import com.example.data.entity.SoldBook;
import org.springframework.stereotype.Service;

@Service
public class BuyerServiceImpl implements BuyerService {
  private final SoldBookRepository soldBookRepository;

  public BuyerServiceImpl(SoldBookRepository soldBookRepository) {
    this.soldBookRepository = soldBookRepository;
  }

  @Override
  public void sell(Book book, Buyer buyer, int cost) {
    SoldBook soldBook = new SoldBook();
    soldBook.setCost(cost);
    soldBook.setBuyer(buyer);
    soldBook.setBook(book);
    soldBookRepository.save(soldBook);
  }

  @Override
  public int costSoldByBook(Book book) {
    return soldBookRepository.findBookByBook(book).stream().mapToInt(SoldBook::getCost).sum();
  }

  @Override
  public int costSoldByBuyer(Buyer buyer) {
    return soldBookRepository.findBookByBuyer(buyer).stream().mapToInt(SoldBook::getCost).sum();
  }
}
