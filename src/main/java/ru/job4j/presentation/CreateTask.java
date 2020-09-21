package ru.job4j.presentation;

import ru.job4j.application.TODOList;
import ru.job4j.domain.Task;
import ru.job4j.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateTask extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        HttpSession sc = req.getSession();
        int userId = (int)sc.getAttribute("userId");
        User user = TODOList.instOf().findUserById(userId);
        TODOList.instOf().createTask(new Task(req.getParameter("description"),
                formatter.format(date), false, user));
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
