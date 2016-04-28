package ait.entity;

import ait.utils.Column;

/**
 * Created by suomiy on 4/27/16.
 */
public class User extends IdEntity {

    public User() {
    }

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Column
    private String name;

    @Column
    private String surname;

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
}
