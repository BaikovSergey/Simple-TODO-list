package ru.job4j.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.job4j.domain.Task;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;

public class PsqlStore implements Store {

    private static final class Lazy {
        private static final Store INST = new PsqlStore();
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    @Override
    public Task create(Task task, SessionFactory sf) {
        this.transaction(session -> session.save(task), sf);
        return task;
    }

    @Override
    public void update(Task task, SessionFactory sf) {
       this.transactionVoid(session -> session.update(task), sf);
    }

    @Override
    public void delete(Integer id, SessionFactory sf) {
        Task task = new Task();
        task.setId(id);
        this.transactionVoid(session -> session.delete(task), sf);
    }

    @Override
    public Collection<Task> findAll(SessionFactory sf) {
        return this.transaction(session -> session.createQuery("from ru.job4j.domain.Task").list(),sf);
    }

    @Override
    public Task findById(Integer id, SessionFactory sf) {
        return this.transaction(session -> session.get(Task.class, id), sf);
    }

    private <T> T transaction(final Function<Session, T> command, SessionFactory sf) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T result = command.apply(session);
            tx.commit();
            return result;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    private void transactionVoid(final Consumer<Session> command, SessionFactory sf) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            command.accept(session);
            tx.commit();
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
