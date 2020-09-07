package ru.job4j.presentation;

import ru.job4j.application.TODOList;
import ru.job4j.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        TODOList.instOf().createUser(new User(req.getParameter("name"),
                req.getParameter("email"), req.getParameter("password")));
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}
