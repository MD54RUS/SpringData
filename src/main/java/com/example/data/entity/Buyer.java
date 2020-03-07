package com.example.data.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Buyer {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "address")
  private String address;
}
