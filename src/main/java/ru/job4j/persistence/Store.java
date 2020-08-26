package ru.job4j.persistence;

import org.hibernate.SessionFactory;
import ru.job4j.domain.Task;

import java.util.List;

public interface Store {
    Task create(Task task, SessionFactory sf);
    void update(Task task, SessionFactory sf);
    void delete(Integer id, SessionFactory sf);
    List<Task> findAll(SessionFactory sf);
    Task findById(Integer id, SessionFactory sf);
}
