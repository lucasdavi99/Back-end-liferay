package com.liferay.CommunityApp.models;

import com.liferay.CommunityApp.models.pk.OrderMessagePk;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "TB_ORDER_MESSAGE")
public class OrderMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private OrderMessagePk id = new OrderMessagePk();
    private String sender;
    private String recipient;

    public OrderMessage() {

    }

    public OrderMessage(UserModel user, MessageModel message, String sender, String recipient) {
        id.setUser(user);
        id.setMessage(message);
        this.sender = sender;
        this.recipient = recipient;
    }

    public UserModel getUser() {
        return id.getUser();
    }

    public void setUser(UserModel user){
        id.setUser(user);
    }

    public MessageModel getMessage() {
        return id.getMessage();
    }

    public void setMessage(MessageModel message){
        id.setMessage(message);
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderMessage that = (OrderMessage) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
