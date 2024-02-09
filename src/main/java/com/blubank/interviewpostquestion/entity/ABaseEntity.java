package com.blubank.interviewpostquestion.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.concurrent.ThreadLocalRandom;

@MappedSuperclass
@Setter
@Getter
public abstract class ABaseEntity {

    @Id
    private Long id;

    @Column(updatable = false)
    private Timestamp creationDate;


    @PrePersist
    protected void onCreate() {
        if (id == null) {
            id = ThreadLocalRandom.current().nextLong(1000, 100000000);
        }
        creationDate = new Timestamp(System.currentTimeMillis());
    }

}