package org.courses.DAO.entities;

import org.courses.domain.jdbc.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "Type")
public class Type extends BaseEntity {

    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;

    public Type(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public Type(String name) {
        this.name = name;
        this.id = -1;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  "" + id + "_" + name;
    }
}
