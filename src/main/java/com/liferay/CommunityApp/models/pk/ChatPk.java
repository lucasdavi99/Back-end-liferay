package com.liferay.CommunityApp.models.pk;

import com.liferay.CommunityApp.models.MessageModel;
import com.liferay.CommunityApp.models.UserModel;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
@Embeddable
public class ChatPk implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "message_id")
    private MessageModel message;

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public MessageModel getMessage() {
        return message;
    }

    public void setMessage(MessageModel message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatPk chatPk = (ChatPk) o;
        return Objects.equals(user, chatPk.user) && Objects.equals(message, chatPk.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, message);
    }
}
