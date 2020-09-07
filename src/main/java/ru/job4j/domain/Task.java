package ru.job4j.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "description")
    private String description;
    @Column(name = "created")
    private String created;
    @Column(name = "done")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "USER_ID_FK"))
    private User user;

    public Task() {
    }

    public Task(String description, String created, Boolean status) {
        this.description = description;
        this.created = created;
        this.status = status;
    }

    public static Task of(String description, String created, Boolean status, User user) {
        Task task = new Task();
        task.description = description;
        task.created = created;
        task.status = status;
        task.user = user;
        return task;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean done) {
        this.status = done;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return Objects.equals(id, task.id)
                && Objects.equals(description, task.description)
                && Objects.equals(created, task.created)
                && Objects.equals(status, task.status)
                && Objects.equals(user, task.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, created, status, user);
    }
}
