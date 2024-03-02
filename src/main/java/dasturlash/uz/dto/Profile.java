package dasturlash.uz.dto;

import dasturlash.uz.enums.ProfileRoles;
import dasturlash.uz.enums.ProfileStatus;

import java.time.LocalDateTime;

public class Profile {
    private Integer id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String phone;
    private ProfileStatus status;
    private ProfileRoles roles;
    private LocalDateTime createdDate;

    public String getFild() {
        return id + " " + name + " " + surname + " " + login + " " + phone + " " + status + " " + roles + " " + createdDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public LocalDateTime getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
