package com.liferay.CommunityApp.models;

import com.liferay.CommunityApp.models.pk.ChatPk;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "TB_CHAT")
public class ChatModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ChatPk id = new ChatPk();
    private Set<MessageModel> messages = new HashSet<>();

    public ChatModel() {

    }

    public ChatModel(Set<MessageModel> messages, UserModel sender, UserModel addressee) {
        this.messages = messages;
        id.setUser(sender);
        id.setUser(addressee);
    }

    public UserModel getSender(){
        return id.getUser();
    }

    public void setSender(UserModel sender){
        id.setUser(sender);
    }

    public UserModel getAddressee(){
        return id.getUser();
    }

    public void setAddressee(UserModel Addressee){
        id.setUser(Addressee);
    }

    public Set<MessageModel> getMessages() {
        return messages;
    }

    public void setMessages(Set<MessageModel> messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatModel chatModel = (ChatModel) o;
        return Objects.equals(id, chatModel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
