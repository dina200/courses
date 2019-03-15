package org.courses.domain.hbm;

import javax.persistence.*;

@Entity
@Table(name = "Type")
public class Type implements SimpleEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    protected int id;

    @Column(name = "name")
    protected String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
