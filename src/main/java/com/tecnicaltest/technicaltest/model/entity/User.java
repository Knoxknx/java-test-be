package com.tecnicaltest.technicaltest.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "\"USER\"")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "USER_ID")
  private Long id;
  @Column(name = "NAME")
  private String name;
  @Column(name = "EMAIL")
  private String email;
  @Column(name = "PASSWORD")
  private String password;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Phone> phones;

  @Column(name = "CREATE_AT")
  private Date createdAt;
  @Column(name = "UPDATE_AT")
  private Date updatedAt;

}