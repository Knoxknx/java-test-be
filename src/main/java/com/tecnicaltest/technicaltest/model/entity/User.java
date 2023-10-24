package com.tecnicaltest.technicaltest.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Table(name = "\"USER\"")
@Table(name = "_USER")
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

  @JsonIgnore
//  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  private List<Phone> phones;

  @Column(name = "CREATE_AT")
  private Date createdAt;
  @Column(name = "UPDATE_AT")
  private Date updatedAt;
  @Column(name = "LAST_LOGIN")
  private Date lastLogin;

  @Column(name = "ROLE")
  @Enumerated(EnumType.STRING)
  private Role role;
  @OneToMany(mappedBy = "user")
  private List<Token> token;


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return role.getAuthorities();
  }

  @Override
  public String getUsername() {
    return null;
  }

  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return false;
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