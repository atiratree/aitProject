package ait.entity;

import ait.db.Column;
import ait.db.Tables;

/**
 * Created by suomiy on 4/27/16.
 */
public class User extends IdEntity implements Tables.User {

    public User() {
    }

    public User(String email, String name, String surname, String passwordHash) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.passwordHash = passwordHash;
    }

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String email;

    @Column
    private String passwordHash;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public String toString() {
        return String.format("Id: %d, %s, %s %s", getId(), getEmail(), getName(), getSurname());
    }
}
