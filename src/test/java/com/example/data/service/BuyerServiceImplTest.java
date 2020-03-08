package com.example.data.service;

import com.example.data.DataApplication;
import com.example.data.data.*;
import com.example.data.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DataApplication.class)
public class BuyerServiceImplTest {

  @Autowired private BookRepository bookRepository;

  @Autowired private AuthorRepository authorRepository;

  @Autowired private AuthorOfBookRepository authorOfBookRepository;

  @Autowired private BookService bookService;

  @Autowired private BuyerService buyerService;

  @Autowired private SoldBookRepository soldBookRepository;

  @Autowired private BuyerRepository buyerRepository;

  @BeforeEach
  public void init() {

    Author mark = new Author();
    mark.setFirstname("Mark");
    mark.setLastname("Twain");
    authorRepository.save(mark);

    Author jules = new Author();
    jules.setFirstname("Jules");
    jules.setLastname("Verne");
    authorRepository.save(jules);

    Book book = new Book();
    book.setDescription("Увлекательные приключения Тома Сойера");
    book.setTitle("Приключения Тома Сойера");
    book.setYear(1876);
    bookRepository.save(book);

    AuthorOfBook aob1 = new AuthorOfBook();
    aob1.setAuthor(mark);
    aob1.setBook(book);
    authorOfBookRepository.save(aob1);

    Book book2 = new Book();
    book2.setTitle("Михаил Строгов");
    book2.setYear(1876);
    bookRepository.save(book2);

    AuthorOfBook aob2 = new AuthorOfBook();
    aob2.setAuthor(mark);
    aob2.setBook(book2);
    authorOfBookRepository.save(aob2);

    AuthorOfBook aob3 = new AuthorOfBook();
    aob3.setAuthor(jules);
    aob3.setBook(book2);
    authorOfBookRepository.save(aob3);

    Buyer buyer1 = new Buyer();
    buyer1.setName("A");
    buyer1.setAddress("Nsk");
    buyerRepository.save(buyer1);

    SoldBook sb = new SoldBook();
    sb.setBook(book);
    sb.setBuyer(buyer1);
    sb.setCost(25);
    soldBookRepository.save(sb);

    SoldBook sb1 = new SoldBook();
    sb1.setBook(book2);
    sb1.setBuyer(buyer1);
    sb1.setCost(50);
    soldBookRepository.save(sb1);

    Buyer buyer2 = new Buyer();
    buyer2.setName("B");
    buyer2.setAddress("Berdsk");
    buyerRepository.save(buyer2);

    buyerService.sell(book, buyer2, 67);
  }

  @Test
  public void testSellOperation() {
    int startCount = soldBookRepository.findAll().size();
    Book book = bookRepository.findBooksByYear(1876).get(0);
    Buyer buyer = buyerRepository.findBuyersByName("A").get(0);
    buyerService.sell(book, buyer, 65);
    startCount++;
    boolean sold = soldBookRepository.findAll().size() == startCount;
    Assert.isTrue(sold);
  }

  @Test
  public void testCostByBook() {
    int testRes = 92;
    Book book = bookRepository.findBooksByYear(1876).get(0);
    int sum = buyerService.costSoldByBook(book);
    int res = soldBookRepository.findBookByBook(book).stream().mapToInt(SoldBook::getCost).sum();
    Assert.isTrue(sum == testRes && res == testRes);
  }

  @Test
  public void testCostByAuthor() {
    int testRes = 75;
    Buyer buyer = buyerRepository.findBuyersByName("A").get(0);
    int sum = buyerService.costSoldByBuyer(buyer);
    int res = soldBookRepository.findBookByBuyer(buyer).stream().mapToInt(SoldBook::getCost).sum();
    Assert.isTrue(sum == testRes && res == testRes);
  }
}
