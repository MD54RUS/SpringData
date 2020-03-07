package com.example.data.data;

import com.example.data.entity.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Long> {
  List<Author> findAuthorsByFirstname(String name);
}
