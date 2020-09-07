package ru.job4j.application;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.domain.Task;
import ru.job4j.domain.User;
import ru.job4j.persistence.PsqlStore;

import java.util.Collection;

public class TODOList implements IList {

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();

    private SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();

    private static final class Lazy {
        private static final TODOList INST = new TODOList();
    }

    public static TODOList instOf() {
        return Lazy.INST;
    }

    @Override
    public Task createTask(Task task) {
        return PsqlStore.instOf().createTask(task, sf);
    }

    @Override
    public User createUser(User user) {
        return PsqlStore.instOf().createUser(user, sf);
    }

    @Override
    public void updateTask(Task task) {
        PsqlStore.instOf().updateTask(task, sf);
    }

    @Override
    public void updateUser(User user) {
        PsqlStore.instOf().updateUser(user, sf);
    }

    @Override
    public void deleteTask(Integer id) {
        PsqlStore.instOf().deleteTask(id, sf);
    }

    @Override
    public void deleteUser(Integer id) {
        PsqlStore.instOf().deleteUser(id, sf);
    }

    @Override
    public Collection<Task> findAllTasks() {
        return PsqlStore.instOf().findAllTasks(sf);
    }

    @Override
    public Collection<User> findAllUsers() {
        return PsqlStore.instOf().findAllUsers(sf);
    }

    @Override
    public Task findTaskById(Integer id) {
        return PsqlStore.instOf().findTaskById(id, sf);
    }

    @Override
    public User findUserById(Integer id) {
        return PsqlStore.instOf().findUserById(id, sf);
    }
}
