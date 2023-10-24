package com.tecnicaltest.technicaltest.model.entity;

//import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "\"USER\"")
public class User implements UserDetails {

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
  @Column(name = "IS_ACTIVE")
  private Boolean isActive;

//  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Phone> phones;

  @Column(name = "CREATE_AT")
  private Date createdAt;
  @Column(name = "UPDATE_AT")
  private Date updatedAt;
  @Column(name = "LAST_LOGIN")
  private Date lastLogin;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of();
  }
  public String getPassword() {
    return password;
  }
  @Override
  public String getUsername() {
    return email;
  }
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }
  @Override
  public boolean isEnabled() {
    return true;
  }

  @PrePersist
  public void prePersist() {
    if (Objects.isNull(createdAt) && Objects.isNull(updatedAt)) {
      createdAt = new Date();
      updatedAt = new Date();
      lastLogin = new Date();
    }

    if (Objects.isNull(isActive)) {
      isActive = true;
    }
  }
}