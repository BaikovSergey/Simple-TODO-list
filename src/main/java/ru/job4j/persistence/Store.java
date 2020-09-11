package ru.job4j.persistence;

import org.hibernate.SessionFactory;
import ru.job4j.domain.Task;
import ru.job4j.domain.User;

import java.util.Collection;
import java.util.List;

public interface Store {
    Task createTask(Task task, SessionFactory sf);
    User createUser(User user, SessionFactory sf);
    void updateTask(Task task, SessionFactory sf);
    void updateUser(User user, SessionFactory sf);
    void deleteTask(Integer id, SessionFactory sf);
    void deleteUser(Integer id, SessionFactory sf);
    Collection<Task> findAllTasks(SessionFactory sf);
    Collection<User> findAllUsers(SessionFactory sf);
    Task findTaskById(Integer id, SessionFactory sf);
    User findUserById(Integer id, SessionFactory sf);
    User findUserByEmail(String email, SessionFactory sf);
}
