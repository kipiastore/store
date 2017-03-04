package ru.store.entities;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class User {

    private String username;
    private String password;
    private String status;
    private String fullName;
    private boolean enabled = true;
    private Set<UserRole> userRole = new HashSet<>(0);
    private Timestamp createdDate = new Timestamp(new java.util.Date().getTime());
    private Timestamp lastModifiedDate = new Timestamp(new java.util.Date().getTime());
    private String owner;
    private String lastModifiedBy;

    public User() {

    }

    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public boolean isEnabled() {
        return this.enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public Set<UserRole> getUserRole() {
        return this.userRole;
    }
    public void setUserRole(Set<UserRole> userRole) {
        this.userRole = userRole;
    }
    public Timestamp getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
    public Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }
    public void setLastModifiedDate(Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }
    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Override
    public String toString() {
        return "{" +
                "\"username\":\"" + username + "\"," +
                "\"password\":\"" + password + "\"," +
                "\"enabled\":\"" + enabled + "\"," +
                "\"status\":\"" + status + "\"," +
                "\"fullName\":\"" + fullName + "\"," +
                "\"userRole\":" + userRole +
                '}';
    }
}


