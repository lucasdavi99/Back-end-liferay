package com.liferay.CommunityApp.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
@Entity
@Table(name = "TB_MESSAGE")
public class MessageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "content")
    private String contend;

    @Column(name = "datetime")
    private LocalDateTime datetime;

    public MessageModel() {

    }

    public MessageModel(UUID id, String contend, LocalDateTime datetime) {
        this.id = id;
        this.contend = contend;
        this.datetime = datetime;
    }

    public UUID getId() {
        return id;
    }


    public String getContend() {
        return contend;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageModel that = (MessageModel) o;
        return Objects.equals(id, that.id) && Objects.equals(contend, that.contend) && Objects.equals(datetime, that.datetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contend, datetime);
    }
}
