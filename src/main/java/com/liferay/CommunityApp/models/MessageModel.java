package com.liferay.CommunityApp.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "TB_MESSAGE")
public class MessageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "content")
    private String content;

    @Column(name = "datetime")
    private LocalDateTime datetime;

    @Column(name = "sender")
    private UserModel sender;

    @Column(name = "addressee")
    private UserModel addressee;

    @OneToMany(mappedBy = "id.message")
    private List<ChatModel> chats = new ArrayList<>();

    public MessageModel() {
    }

    public MessageModel(UUID id, String content, LocalDateTime datetime, UserModel sender, UserModel addressee) {
        this.id = id;
        this.content = content;
        this.datetime = datetime;
        this.sender = sender;
        this.addressee = addressee;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public UserModel getSender() {
        return sender;
    }

    public void setSender(UserModel sender) {
        this.sender = sender;
    }

    public UserModel getAddressee() {
        return addressee;
    }

    public void setAddressee(UserModel addressee) {
        this.addressee = addressee;
    }

    public List<ChatModel> getChats(){
        return chats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageModel that = (MessageModel) o;
        return Objects.equals(id, that.id) && Objects.equals(sender, that.sender) && Objects.equals(addressee, that.addressee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sender, addressee);
    }
}
