package com.revature.quizzard.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_roles")
public class UserRole {

    @Id
    private String id;

    @Column(name = "role_name", nullable = false, unique = true)
    private String roleName;

    public UserRole() {
        super();
    }

    public UserRole(String id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return Objects.equals(id, userRole.id) && Objects.equals(roleName, userRole.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleName);
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "id='" + id + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }

}
