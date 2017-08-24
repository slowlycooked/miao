package com.ifiport.model;

/**
 * Created by Martin on 2017/08/10.
 */

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "record")
public class Record {

    @Id
    @GeneratedValue
    private long id;
    private String type;
    private float amount;
    @ManyToOne
    private User creator;
    private Date created;
    private Date updated;
   // @Column(name="record_date1")
    private Date recordDate1;
  //  @Column(name="record_date2")
    private Date recordDate2;
    private String notes;

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

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

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

    public Record(String type, float amount, Date datetime) {
        super();
        this.type = type;
        this.amount = amount;
        this.recordDate1 = datetime;

    }


    public void setCreator(User creator) {
        this.creator = creator;
    }

    public User getCreator() {
        return creator;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getRecordDate1() {
        return recordDate1;
    }

    public void setRecordDate1(Date recordDate) {
        this.recordDate1 = recordDate;
    }

    public Date getRecordDate2() { return recordDate2; }

    public void setRecordDate2(Date recordDate2) {
        this.recordDate2 = recordDate2;
    }

}