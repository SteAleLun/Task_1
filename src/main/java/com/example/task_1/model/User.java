package com.example.task_1.model;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.UUID;

@Entity
@Table(name ="users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private UUID id;

    @Column(name ="email")
    private String email;

    @Column(name ="familyName")
    private String familyName;

    @Column(name ="name")
    private String name;

    @Column(name ="middleName")
    private String middleName;

    @Column(name ="role")
    private UUID role;

    @Column(name ="password")
    private String password;

    //private Enum<Status> status;
    //private Data createdAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public UUID getRole() {
        return role;
    }

    public void setRole(UUID role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    /*public Enum<Status> getStatus() {
        return status;
    }
    public void setStatus(Enum<Status> status) {
        this.status = status;
    }
    public Data getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Data createdAt) {
        this.createdAt = createdAt;
    }*/



}
