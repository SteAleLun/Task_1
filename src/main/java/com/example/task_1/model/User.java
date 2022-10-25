package com.example.task_1.model;

import com.example.task_1.config.PasswordMatches;
import com.example.task_1.config.ValidEmail;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name ="users")
@PasswordMatches
public class User {

    @ElementCollection(targetClass = Status.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "work_state", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Status> statuses;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name ="email")
    //@Size(min = )
    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;

    @Column(name ="familyName")
    @NotNull
    @NotEmpty
    private String familyName;

    @Column(name ="name")
    @NotNull
    @NotEmpty
    private String name;

    @Column(name ="middleName")
    @NotNull
    @NotEmpty
    private String middleName;

    @Column(name ="password")
    @NotNull
    @NotEmpty
    private String password;

    private String matchingPassword;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Role> roles;

    @Column(name ="createdAt")
    @NotNull
    @NotEmpty
    private Timestamp createdAt;

    public User(){
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(Set<Status> statuses) {
        this.statuses = statuses;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

}
