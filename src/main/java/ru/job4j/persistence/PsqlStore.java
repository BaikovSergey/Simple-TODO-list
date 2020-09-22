package ru.job4j.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.job4j.domain.Task;
import ru.job4j.domain.User;

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
    public Task createTask(Task task, SessionFactory sf) {
        this.transaction(session -> session.save(task), sf);
        return task;
    }

    @Override
    public User createUser(User user, SessionFactory sf) {
        this.transaction(session -> session.save(user), sf);
        return user;
    }

    @Override
    public void updateTask(Task task, SessionFactory sf) {
       this.transactionVoid(session -> session.update(task), sf);
    }

    @Override
    public void updateUser(User user, SessionFactory sf) {
        this.transactionVoid(session -> session.update(user), sf);
    }

    @Override
    public void deleteTask(Integer id, SessionFactory sf) {
        Task task = new Task();
        task.setId(id);
        this.transactionVoid(session -> session.delete(task), sf);
    }

    @Override
    public void deleteUser(Integer id, SessionFactory sf) {
        User user = new User();
        user.setId(id);
        this.transactionVoid(session -> session.delete(user), sf);
    }

    @Override
    public Collection<Task> findAllTasks(SessionFactory sf) {
        return this.transaction(session -> session.createQuery("from ru.job4j.domain.Task").list(),sf);
    }

    @Override
    public Collection<User> findAllUsers(SessionFactory sf) {
        return this.transaction(session -> session.createQuery("from ru.job4j.domain.User").list(),sf);
    }

    @Override
    public Task findTaskById(Integer id, SessionFactory sf) {
        return this.transaction(session -> session.get(Task.class, id), sf);
    }

    @Override
    public User findUserById(Integer id, SessionFactory sf) {
        return this.transaction(session -> session.get(User.class, id), sf);
    }

    @Override
    public User findUserByEmail(String email, SessionFactory sf) {
        Session session = sf.openSession();
        Query<User> query = session.createQuery("from User u where u.email=:scn",
                User.class);
        query.setParameter("scn", email);
        return query.uniqueResult();
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
