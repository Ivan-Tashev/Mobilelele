package com.example.mobilelele.model.entity;

import javax.persistence.*;
import java.time.Instant;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(nullable = false)
    protected Instant created;
    @Column(nullable = false)
    protected Instant modified;

    @PrePersist
    public void prePersist(){
        setCreated(Instant.now());
        setModified(Instant.now());
    }

    @PreUpdate
    public void preUpdate(){
        setModified(Instant.now());
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getModified() {
        return modified;
    }

    public void setModified(Instant modified) {
        this.modified = modified;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
