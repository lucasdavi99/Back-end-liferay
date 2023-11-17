package com.liferay.CommunityApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.liferay.CommunityApp.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_USER")
public class UserModel extends RepresentationModel<UserModel> implements UserDetails, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private UUID id;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String login;
    private String password;
    private String name;
    private String bio;
    private String local;
    private UserRole role;
    private byte profilePhoto;
    private byte coverPhoto;

    @JsonIgnore
    @ManyToMany(mappedBy = "members")
    private List<CommunityModel> communities = new ArrayList<>();

    @OneToMany(mappedBy = "author")
    private List<CommunityModel> myCommunnities = new ArrayList<>();

    public UserModel(String email, String login, String password) {
        this.email = email;
        this.login = login;
        this.password = password;
    }

    //public UserModel(UUID id, String name, String email, String encodedPasswordUser1, UserRole role) {
    //    this.id = id;
    //    this.name = name;
    //    this.login = encodedPasswordUser1;
    //    this.role = role;
    //}

    public UserModel(UUID id, String login, String email, String password, UserRole role) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.password = password;
        this.role =role;
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
