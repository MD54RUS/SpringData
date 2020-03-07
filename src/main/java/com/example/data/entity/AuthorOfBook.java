package com.example.data.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class AuthorOfBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

  @ManyToOne
  @JoinColumn(name = "book_id")
  private Book book;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

}
