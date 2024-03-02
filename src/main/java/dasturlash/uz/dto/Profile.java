package dasturlash.uz.dto;

import dasturlash.uz.enums.ProfileRoles;
import dasturlash.uz.enums.ProfileStatus;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "profile_table")
public class Profile extends BaseEntity{
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "login",unique = true)
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "phone")
    private String phone;
    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private ProfileStatus status;
    @Column(name = "roles")
    @Enumerated(value = EnumType.STRING)
    private ProfileRoles roles;

    public String getFild() {
        return getId() + " " + name + " " + surname + " " + login + " " + phone + " " + status + " " + roles + " " + getCreatedDate();
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ProfileStatus getStatus() {
        return status;
    }

    public void setStatus(ProfileStatus status) {
        this.status = status;
    }

    public ProfileRoles getRoles() {
        return roles;
    }

    public void setRoles(ProfileRoles roles) {
        this.roles = roles;
    }

}
