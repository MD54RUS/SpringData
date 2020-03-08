package com.example.data.data;

import com.example.data.entity.Book;
import com.example.data.entity.Buyer;
import com.example.data.entity.SoldBook;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SoldBookRepository extends CrudRepository<SoldBook, Long> {

  List<SoldBook> findAll();

  List<SoldBook> findBookByBook(Book book);

  List<SoldBook> findBookByBuyer(Buyer buyer);
}
