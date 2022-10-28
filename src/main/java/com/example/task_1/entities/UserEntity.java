package com.example.task_1.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name ="users")
public class UserEntity {

    @Enumerated(EnumType.STRING)
    private Status status;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name ="email")
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

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<RoleEntity> role;

    @Column(name ="createdAt")
    @NotNull
    @NotEmpty
    private Timestamp createdAt;

    public UserEntity(){
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public Set<RoleEntity> getRole() {
        return role;
    }

    public void setRole(Set<RoleEntity> roleEntities) {
        this.role = roleEntities;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity userEntity = (UserEntity) o;

        if (status != userEntity.status) return false;
        if (!Objects.equals(id, userEntity.id)) return false;
        if (!Objects.equals(email, userEntity.email)) return false;
        if (!Objects.equals(familyName, userEntity.familyName)) return false;
        if (!Objects.equals(name, userEntity.name)) return false;
        if (!Objects.equals(middleName, userEntity.middleName)) return false;
        if (!Objects.equals(password, userEntity.password)) return false;
        if (!Objects.equals(role, userEntity.role)) return false;
        return Objects.equals(createdAt, userEntity.createdAt);
    }

    @Override
    public int hashCode() {
        int result = status != null ? status.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (familyName != null ? familyName.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "status=" + status +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", familyName='" + familyName + '\'' +
                ", name='" + name + '\'' +
                ", middleName='" + middleName + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + role +
                ", createdAt=" + createdAt +
                '}';
    }
}
