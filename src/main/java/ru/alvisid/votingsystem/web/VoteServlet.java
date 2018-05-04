package ru.alvisid.votingsystem.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.alvisid.votingsystem.util.Utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class VoteServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(VoteServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("getAll");
        request.setAttribute("votes", Utils.getRestaurantVotes(Utils.MENUS));
        request.getRequestDispatcher("/votes.jsp").forward(request, response);
    }
}
