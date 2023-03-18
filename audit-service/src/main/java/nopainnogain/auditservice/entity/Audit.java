package nopainnogain.auditservice.entity;

import jakarta.persistence.*;
import nopainnogain.auditservice.core.enums.TypeOfEntity;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Entity
@Table(schema = "app", name = "audit")
@Inheritance(strategy = InheritanceType.JOINED)
public class Audit {

    @Id
    private UUID uuid;
    private LocalDateTime dtCreate;
    @ElementCollection
    @CollectionTable(schema = "app", name = "audit_user", joinColumns = @JoinColumn(name = "user_id"))
    private List<User> user;
    private String test;
    @Enumerated(value = EnumType.STRING)
    private TypeOfEntity type;
    private int id;

    public Audit() {
    }

    public Audit(UUID uuid, LocalDateTime dtCreate, User user, String test, TypeOfEntity type, int id) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.user = Collections.singletonList(user);
        this.test = test;
        this.type = type;
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public TypeOfEntity getType() {
        return type;
    }

    public void setType(TypeOfEntity type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
