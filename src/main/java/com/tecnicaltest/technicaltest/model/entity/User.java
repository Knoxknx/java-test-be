package com.tecnicaltest.technicaltest.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Objects;

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