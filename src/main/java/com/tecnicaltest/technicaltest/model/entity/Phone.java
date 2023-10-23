package com.tecnicaltest.technicaltest.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Table(name = "PHONE")
@Entity
public class Phone {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "PHONE_ID")
  public Long id;
  @Column(name = "NUMBER")
  private String number;
  @Column(name = "CITY_CODE")
  private String cityCode;
  @Column(name = "COUNTRY_CODE")
  private String countryCode;
  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "USER_ID")
  private User user;

  @Column(name = "CREATE_AT")
  private Date createdAt;
  @Column(name = "UPDATE_AT")
  private Date updatedAt;
}

