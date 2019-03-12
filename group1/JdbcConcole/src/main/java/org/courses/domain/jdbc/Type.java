package org.courses.domain.jdbc;

import javax.persistence.*;
import javax.persistence.Column;

@Entity(name = "Type")
public class Type extends BaseEntity {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String typeName;

    public Type() {

    }

    public Type(int id, String typeName) {
        this.id = id;
        this.typeName = typeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
