package com.example.data.service;

import com.example.data.DataApplication;
import com.example.data.data.AuthorOfBookRepository;
import com.example.data.data.AuthorRepository;
import com.example.data.data.BookRepository;
import com.example.data.entity.Author;
import com.example.data.entity.AuthorOfBook;
import com.example.data.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DataApplication.class)
class BookServiceImplTest {

  @Autowired private BookRepository bookRepository;

  @Autowired private AuthorRepository authorRepository;

  @Autowired private AuthorOfBookRepository authorOfBookRepository;

  @Autowired private BookService bookService;

  @BeforeEach
  public void init() {

    Author mark = new Author();
    mark.setFirstname("Mark");
    mark.setLastname("Twain");
    authorRepository.save(mark);

    Author jules = new Author();
    mark.setFirstname("Jules");
    mark.setLastname("Verne");
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
    book2.setTitle("Михаил СТрогов");
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
  }

  @Test
  public void testCreation() {
    boolean founded = false;
    for (Book book : bookRepository.findAll()) {
      if (book.getTitle().contains("Тома Сойера")) {
        founded = true;
      }
    }
    Assert.isTrue(founded);
  }
}
