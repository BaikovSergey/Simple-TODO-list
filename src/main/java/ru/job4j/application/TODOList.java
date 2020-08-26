package ru.job4j.application;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.domain.Task;
import ru.job4j.persistence.PsqlStore;

import java.util.List;

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
    public Task create(Task task) {
        return PsqlStore.instOf().create(task, sf);
    }

    @Override
    public void update(Task task) {
        PsqlStore.instOf().update(task, sf);
    }

    @Override
    public void delete(Integer id) {
        PsqlStore.instOf().delete(id, sf);
    }

    @Override
    public List<Task> findAll() {
        return PsqlStore.instOf().findAll(sf);
    }

    @Override
    public Task findById(Integer id) {
        return PsqlStore.instOf().findById(id, sf);
    }
}
