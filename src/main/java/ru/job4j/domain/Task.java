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
    @Column(name = "status")
    private Boolean status;

    public Task() {
    }

    public Task(String description, String created, Boolean status) {
        this.description = description;
        this.created = created;
        this.status = status;
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
                && Objects.equals(status, task.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, created, status);
    }

}
