package ru.job4j.persistence;


import org.junit.Test;
import ru.job4j.application.TODOList;
import ru.job4j.domain.Task;
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

}