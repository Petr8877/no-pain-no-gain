package nopainnogain.auditservice.entity;

import jakarta.persistence.*;
import nopainnogain.auditservice.core.enums.Role;
import nopainnogain.auditservice.core.enums.TypeOfEntity;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "app", name = "audit")
@Inheritance(strategy = InheritanceType.JOINED)
public class Audit {

    @Id
    private UUID id;
    private LocalDateTime dtCreate;
//    @ElementCollection
//    @CollectionTable(schema = "app", name = "audit_user", joinColumns = @JoinColumn(name = "user_id"))
//    private List<User> user;
    private String text;
    @Enumerated(value = EnumType.STRING)
    private TypeOfEntity type;
    private int idType;
//    @Column(name = "user_id")
    private UUID qwe;
    private String userEmail;
    private String userFio;
    @Enumerated(value = EnumType.STRING)
    private Role userRole;

    public Audit() {
    }

//    public Audit(UUID uuid, LocalDateTime dtCreate, User user, String text, TypeOfEntity type, int id) {
//        this.uuid = uuid;
//        this.dtCreate = dtCreate;
//        this.user = Collections.singletonList(user);
//        this.text = text;
//        this.type = type;
//        this.id = id;
//    }

//    public UUID getUuid() {
//        return uuid;
//    }
//
//    public void setUuid(UUID uuid) {
//        this.uuid = uuid;
//    }
//
//    public LocalDateTime getDtCreate() {
//        return dtCreate;
//    }
//
//    public void setDtCreate(LocalDateTime dtCreate) {
//        this.dtCreate = dtCreate;
//    }
//
//    public List<User> getUser() {
//        return user;
//    }
//
//    public void setUser(List<User> user) {
//        this.user = user;
//    }
//
//    public String getText() {
//        return text;
//    }
//
//    public void setText(String text) {
//        this.text = text;
//    }
//
//    public TypeOfEntity getType() {
//        return type;
//    }
//
//    public void setType(TypeOfEntity type) {
//        this.type = type;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public Audit(UUID uuid, LocalDateTime dtCreate, String text, TypeOfEntity type, int idType, UUID userUUID, String userEmail, String userFio, Role userRole) {
        this.id = uuid;
        this.dtCreate = dtCreate;
        this.text = text;
        this.type = type;
        this.idType = idType;
        this.qwe = userUUID;
        this.userEmail = userEmail;
        this.userFio = userFio;
        this.userRole = userRole;
    }

    public UUID getUuid() {
        return id;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TypeOfEntity getType() {
        return type;
    }

    public void setType(TypeOfEntity type) {
        this.type = type;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public UUID getQwe() {
        return qwe;
    }

    public void setQwe(UUID qwe) {
        this.qwe = qwe;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserFio() {
        return userFio;
    }

    public void setUserFio(String userFio) {
        this.userFio = userFio;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }
}
