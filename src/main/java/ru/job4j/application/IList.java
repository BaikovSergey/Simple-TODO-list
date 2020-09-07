package ru.job4j.application;

import ru.job4j.domain.Task;

import java.util.Collection;
import java.util.List;

public interface IList {
    Task create(Task task);
    void update(Task task);
    void delete(Integer id);
    Collection<Task> findAll();
    Task findById(Integer id);
}
