package org.courses.domain.hbm;

import org.courses.domain.hbm.sqliteconvertion.DateConverter;
import org.joda.time.DateTime;

import javax.persistence.*;

/*
CREATE TABLE `StatisticWearing`
        (
        `id`          INTEGER PRIMARY KEY AUTOINCREMENT,
        `storage`     INTEGER NOT NULL,
        `start_using` TEXT    NOT NULL,
        `stop_using`  TEXT,
        FOREIGN KEY (`storage`) REFERENCES `Storage` (`id`)
        );
        */
@Entity
@Table(name = "StatisticWearing")
public class Statistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "storage")
    private Storage storage;

    @Column(name = "start_using")
    @Convert(converter = DateConverter.class)
    private DateTime startUsing;

    @Column(name = "stop_using")
    @Convert(converter = DateConverter.class)
    private DateTime stopUsing;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public DateTime getStartUsing() {
        return startUsing;
    }

    public void setStartUsing(DateTime startUsing) {
        this.startUsing = startUsing;
    }

    public DateTime getStopUsing() {
        return stopUsing;
    }

    public void setStopUsing(DateTime stopUsing) {
        this.stopUsing = stopUsing;
    }

    @Override
    public String toString() {
        return String.format("Statistic { id: %d, storage: %d, startUsing: %s, stopUsing: %s }",
                id,
                storage.getId(),
                startUsing != null ? startUsing.toString() : null,
                stopUsing != null ? stopUsing.toString() : null
        );
    }
}
