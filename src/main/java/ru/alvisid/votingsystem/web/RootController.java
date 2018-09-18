package ru.alvisid.votingsystem.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.alvisid.votingsystem.service.MenusService;
import ru.alvisid.votingsystem.service.VotesService;
import ru.alvisid.votingsystem.util.VoteUtils;
import ru.alvisid.votingsystem.web.vote.VoteJspController;

@Controller
public class RootController {
    private static final Logger log = LoggerFactory.getLogger(VoteJspController.class);

    @Autowired
    private MenusService menusService;

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/votes")
    public String listVotes(Model model) {
        log.info("getAll votes");
        model.addAttribute("votes", VoteUtils.getRestaurantVotes(menusService.getAll()));
        return "votes";
    }
}
