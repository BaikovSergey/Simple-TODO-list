package ru.job4j.presentation;

import org.json.JSONArray;
import ru.job4j.application.TODOList;
import ru.job4j.domain.Task;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;


public class GetAllTasks extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json; charset=UTF-8");
        Collection<Task> tasks = TODOList.instOf().findAll();
        JSONArray jsonArray = new JSONArray(tasks);
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(resp.getOutputStream(),
                StandardCharsets.UTF_8), true);
        writer.println(jsonArray);
        writer.flush();
    }
}
