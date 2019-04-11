package org.courses.domain.hbm;

import org.courses.domain.hbm.sqliteconvertion.DateConverter;
import org.joda.time.DateTime;

import javax.persistence.*;

@Entity
@Table(name = "Storage")
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "socks")
    private Socks socks;

    @Column(name = "added")
    @Convert(converter = DateConverter.class)
    private DateTime added;

    @Column(name = "retired")
    @Convert(converter = DateConverter.class)
    private DateTime retired;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Socks getSocks() {
        return socks;
    }

    public void setSocks(Socks socks) {
        this.socks = socks;
    }

    public DateTime getAdded() {
        return added;
    }

    public void setAdded(DateTime added) {
        this.added = added;
    }

    public DateTime getRetired() {
        return retired;
    }

    public void setRetired(DateTime retired) {
        this.retired = retired;
    }

    @Override
    public String toString() {
        return String.format("Storage { id: %d, socksId: %d, added: %s, retired: %s }",
                id,
                socks.getId(),
                added.toString("dd.MM.yyyy hh:mm"),
                retired != null ? retired.toString("dd.MM.yyyy hh:mm") : null);
    }
}
