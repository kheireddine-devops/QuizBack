package com.quiz.quizback.domain.entities;

import com.quiz.quizback.domain.enums.GenderEnum;
import com.quiz.quizback.domain.enums.RoleEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter @Setter @NoArgsConstructor @ToString
@Document
public class User implements UserDetails,Serializable {
    @Id
    private String id;
    private String firstname;
    private String lastname;
    private Date birthDate;
    private GenderEnum gender;
    private String email;
    private String password;
    private RoleEnum role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }
}
