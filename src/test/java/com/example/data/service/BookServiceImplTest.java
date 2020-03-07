package com.example.data.service;

import com.example.data.DataApplication;
import com.example.data.data.AuthorOfBookRepository;
import com.example.data.data.AuthorRepository;
import com.example.data.data.BookRepository;
import com.example.data.data.BooksSpecification;
import com.example.data.entity.Author;
import com.example.data.entity.AuthorOfBook;
import com.example.data.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.DirtiesContext;
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
  }

  @Test
  @DirtiesContext
  public void testCreation() {
    boolean founded = false;
    for (Book book : bookRepository.findAll()) {
      if (book.getTitle().contains("Тома Сойера")) {
        founded = true;
      }
    }
    Assert.isTrue(founded);
  }

  @Test
  @DirtiesContext
  public void testFindByYear() {
    Assert.isTrue(bookRepository.findBooksByYear(1876).size() == 2);
    Assert.isTrue(bookRepository.findBooksByYear(1878).size() == 0);
  }

  @Test
  @DirtiesContext
  public void testPaging() {
    boolean founded =
        bookService
            .findAtPage(1, 1, Sort.Direction.ASC, "title")
            .get()
            .anyMatch(book -> book.getTitle().equals("Приключения Тома Сойера"));
    Assert.isTrue(founded);
  }

  @Test
  @DirtiesContext
  public void findSame() {
    Book book = new Book();
    book.setYear(1876);
    Assert.isTrue(bookService.findSame(book).size() == 2);
  }

  @Test
  @DirtiesContext
  public void findInRange() {
    Assert.isTrue(bookRepository.findAll(BooksSpecification.yearInRange(1874, 1876)).size() == 0);
    Assert.isTrue(bookRepository.findAll(BooksSpecification.yearInRange(1874, 1877)).size() == 2);
  }

  @Test
  @DirtiesContext
  public void findByAuthorLastname() {
    Assert.isTrue(bookRepository.findByAuthorLastname("Twain").size() == 2);
    Assert.isTrue(bookRepository.findByAuthorLastname("Verne").size() == 1);
  }

  @Test
  @DirtiesContext
  public void testComplexQuery() {
    System.out.println(bookRepository.complexQueryMethod());
  }
}
