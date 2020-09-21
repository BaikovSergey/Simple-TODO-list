package ru.job4j.persistence;

import org.junit.Test;
import ru.job4j.application.TODOList;
import ru.job4j.domain.Task;
import ru.job4j.domain.User;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PsqlStoreTest {

    /**
     * Creates Task object for tests.
     * @return Task object.
     */
    private Task generateTask() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return new Task("TEST TASK", formatter.format(date), false);
    }

    private User generateUser() {
        return new User("TEST USER", "testuser@email.ru", "123456");
    }

    @Test
    public void createTask() {
        Task task = generateTask();
        task.setDescription("createTask");
        int id = TODOList.instOf().createTask(task).getId();
        Task result = TODOList.instOf().findTaskById(id);
        TODOList.instOf().deleteTask(id);
        assertThat("createTask", is(result.getDescription()));
    }

    @Test
    public void updateTask() {
        Task task = generateTask();
        int id = TODOList.instOf().createTask(task).getId();
        task.setDescription("changed description");
        TODOList.instOf().updateTask(task);
        Task result = TODOList.instOf().findTaskById(id);
        TODOList.instOf().deleteTask(id);
        assertThat("changed description", is(result.getDescription()));

    }

    @Test
    public void deleteTask() {
        Task task = generateTask();
        int id = TODOList.instOf().createTask(task).getId();
        TODOList.instOf().deleteTask(id);
        Collection<Task> all = TODOList.instOf().findAllTasks();
        assertThat(0, is(all.size()));
    }

    @Test
    public void findAllTasks() {
        Collection<Task> all = TODOList.instOf().findAllTasks();
        assertThat(0, is(all.size()));
    }

    @Test
    public void findTaskById() {
        Task task = generateTask();
        task.setDescription("findById");
        int id = TODOList.instOf().createTask(task).getId();
        Task result = TODOList.instOf().findTaskById(id);
        TODOList.instOf().deleteTask(id);
        assertThat("findById", is(result.getDescription()));
    }

    @Test
    public void createUser() {
        User user = generateUser();
        user.setName("User was created");
        int id = TODOList.instOf().createUser(user).getId();
        User result = TODOList.instOf().findUserById(id);
        TODOList.instOf().deleteUser(id);
        assertThat("User was created", is(result.getName()));
    }

    @Test
    public void updateUser() {
        User user = generateUser();
        int id = TODOList.instOf().createUser(user).getId();
        user.setName("changed description");
        TODOList.instOf().updateUser(user);
        User result = TODOList.instOf().findUserById(id);
        TODOList.instOf().deleteUser(id);
        assertThat("changed description", is(result.getName()));

    }

    @Test
    public void deleteUser() {
        User user = generateUser();
        int id = TODOList.instOf().createUser(user).getId();
        TODOList.instOf().deleteUser(id);
        Collection<User> allUsers = TODOList.instOf().findAllUsers();
        assertThat(0, is(allUsers.size()));
    }

    @Test
    public void findAllUsers() {
        Collection<User> allUsers = TODOList.instOf().findAllUsers();
        assertThat(0, is(allUsers.size()));
    }

    @Test
    public void findUserById() {
        User user = generateUser();
        user.setName("findById");
        int id = TODOList.instOf().createUser(user).getId();
        User result = TODOList.instOf().findUserById(id);
        TODOList.instOf().deleteUser(id);
        assertThat("findById", is(result.getName()));
    }

    @Test
    public void findUserByEmail() {
        User user = generateUser();
        int id = TODOList.instOf().createUser(user).getId();
        User result = TODOList.instOf().findUserByEmail("testuser@email.ru");
        TODOList.instOf().deleteUser(id);
        assertThat("testuser@email.ru", is(result.getEmail()));
    }

}