package com.ifiport.model;

/**
 * Created by Martin on 2017/08/14.
 */
import javax.persistence.*;
import java.util.Date;

@Entity
public class Role {

    @Id
    @GeneratedValue
    private Long id;
    String name;

    Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}