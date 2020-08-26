package ru.job4j.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.job4j.domain.Task;

import java.util.List;

public class PsqlStore implements Store {

    private static final class Lazy {
        private static final Store INST = new PsqlStore();
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    @Override
    public Task create(Task task, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(task);
        session.getTransaction().commit();
        session.close();
        return task;
    }

    @Override
    public void update(Task task, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.update(task);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Integer id, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        Task task = new Task();
        task.setId(id);
        session.delete(task);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Task> findAll(SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        @SuppressWarnings("unchecked")
        List<Task> result = session.createQuery("from ru.job4j.domain.Task").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    @Override
    public Task findById(Integer id, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        Task result = session.get(Task.class, id);
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
