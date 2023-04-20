package com.example.jpaauthspring.user;

import com.example.jpaauthspring.userauthority.UserAuthority;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import org.hibernate.annotations.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity(name = "users")
@SQLDelete(sql = "UPDATE users SET deleted_at=now() WHERE id=?")
@Where(clause = "deleted_at IS NULL")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String username;

    private String email;

    private String phoneNumber;

    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserAuthority> userAuthority;

    @CreatedDate
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    @Nullable
    @ColumnDefault("NULL")
    private Date deletedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<UserAuthority> getUserAuthority() {
        return userAuthority;
    }

    public void setUserAuthority(List<UserAuthority> userAuthority) {
        this.userAuthority = userAuthority;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Nullable
    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(@Nullable Date deletedAt) {
        this.deletedAt = deletedAt;
    }
}
