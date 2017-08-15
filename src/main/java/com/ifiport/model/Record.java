package com.ifiport.model;

/**
 * Created by Martin on 2017/08/10.
 */

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
public class Record {

    @Id
    @GeneratedValue
    @Column(name="id")
    private long id;

    @Column(name="type")
    private String type;
    @Column(name="amount")
    private int amount;

    @ManyToOne
    private User creator;


    public Record() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    private Date created;
    private Date updated;

    @PrePersist
    protected void onCreate() {
        updated = created = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }
    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }


    public Record(String type, int amount) {
        super();
        this.type = type;
        this.amount = amount;

    }


    public void setCreator(User creator) {
        this.creator = creator;
    }

    public User getCreator() {
        return creator;
    }
}