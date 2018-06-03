package ru.alvisid.votingsystem.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import ru.alvisid.votingsystem.util.MenuUtils;
import ru.alvisid.votingsystem.web.vote.VoteRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.alvisid.votingsystem.util.DateTimeUtil.parseLocalDate;

public class VoteServlet extends HttpServlet {
    private VoteRestController voteController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        WebApplicationContext springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        voteController = springContext.getBean(VoteRestController.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("votes", voteController.getAll());
        request.getRequestDispatcher("/votes.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        LocalDate startDate = parseLocalDate(req.getParameter("startDate"));
        LocalDate endDate = parseLocalDate(req.getParameter("endDate"));
        req.setAttribute("votes", voteController.getBetween(startDate, endDate));
        req.getRequestDispatcher("/votes.jsp").forward(req, resp);
    }
}
