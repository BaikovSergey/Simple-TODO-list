package ru.job4j.application;

import ru.job4j.domain.Task;
import ru.job4j.domain.User;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Collection;
import java.util.List;

public interface IList {
    Task createTask(Task task);
    User createUser(User user);
    void updateTask(Task task);
    void updateUser(User user);
    void deleteTask(Integer id);
    void deleteUser(Integer id);
    Collection<Task> findAllTasks();
    Collection<User> findAllUsers();
    Task findTaskById(Integer id);
    User findUserById(Integer id);
}
