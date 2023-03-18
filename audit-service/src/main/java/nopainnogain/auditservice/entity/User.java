package nopainnogain.auditservice.entity;

import jakarta.persistence.*;
import nopainnogain.auditservice.core.enums.Role;

import java.util.List;
import java.util.UUID;

@Embeddable
public class User {

    private UUID uuid;
    private String email;
    private String fio;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    @OneToMany
    @Transient
    private List<Audit> audit;

    public User() {
    }

    public User(UUID uuid, String email, String fio, Role rile) {
        this.uuid = uuid;
        this.email = email;
        this.fio = fio;
        this.role = rile;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
