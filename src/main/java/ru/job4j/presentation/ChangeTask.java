package ru.job4j.presentation;

import ru.job4j.application.TODOList;
import ru.job4j.domain.Task;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeTask extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Integer id = Integer.parseInt(req.getParameter("id"));
        Task task = TODOList.instOf().findTaskById(id);
        String description = req.getParameter("description");
        task.setDescription(description);
        TODOList.instOf().updateTask(task);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
