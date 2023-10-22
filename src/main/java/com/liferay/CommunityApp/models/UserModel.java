package com.liferay.CommunityApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.liferay.CommunityApp.enums.UserRole;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "TB_USER")
public class UserModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID userId;
    @Column(name = "login")
    private String login;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    private UserRole role = UserRole.USER;

    @JsonIgnore
    @ManyToMany(mappedBy = "members")
    private List<CommunityModel> communities =new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "author")
    private List<PostModel> posts = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "author")
    private List<CommentModel> comments = new ArrayList<>();

    public UserModel() {
    }

    public UserModel(UUID userId, String login, String email, String password, UserRole role, List<CommunityModel> communities,
                     List<PostModel> posts, List<CommentModel> comments) {
        this.userId = userId;
        this.login = login;
        this.email = email;
        this.password = password;
        this.role = role;
        this.communities = communities;
        this.posts = posts;
        this.comments = comments;
    }

    public UserModel(String email, String login, String password) {
        this.email = email;
        this.login = login;
        this.password = password;
    }

    public UserModel(UUID userId, String login, String email, String password, UserRole role) {
        this.userId = userId;
        this.login = login;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserModel(UUID userId, String login, String email, String password, UserRole role, List<CommunityModel> communities) {
        this.userId = userId;
        this.login = login;
        this.email = email;
        this.password = password;
        this.role = role;
        this.communities = communities;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public UserRole getRole() {
        return role;
    }

    public List<CommunityModel> getCommunities() {
        return communities;
    }

    public List<PostModel> getPosts() {
        return posts;
    }

    public List<CommentModel> getComments() {
        return comments;
    }

    
    //    Métodos da interface UserDetail

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
