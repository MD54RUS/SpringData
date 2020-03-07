package com.example.data.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class SoldBook {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "buyer_id")
  private Buyer buyer;

  @Column(name = "cost")
  private int cost;

  @ManyToOne
  @JoinColumn(name = "book_id")
  private Book book;
}
