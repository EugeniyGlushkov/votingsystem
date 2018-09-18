package ru.alvisid.votingsystem.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.alvisid.votingsystem.model.Vote;
import ru.alvisid.votingsystem.service.MenusService;
import ru.alvisid.votingsystem.service.VotesService;
import ru.alvisid.votingsystem.to.RestaurantVotes;
import ru.alvisid.votingsystem.util.DateTimeUtil;
import ru.alvisid.votingsystem.util.VoteUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

import static ru.alvisid.votingsystem.util.Util.orElse;
import static ru.alvisid.votingsystem.util.DateTimeUtil.parseLocalDate;

@Controller
public class VoteJspController {
    private static final Logger log = LoggerFactory.getLogger(VoteJspController.class);

    private final VotesService service;

    private final MenusService menusService;

    @Autowired
    public VoteJspController(VotesService service, MenusService menusService) {
        this.service = service;
        this.menusService = menusService;
    }

    public Vote get(int id) {
        log.info("get vote {}", id);
        return service.get(id);
    }

    public void delete(int id) {
        log.info("delete vote {}", id);
    }

    public Vote create(Vote vote, int userId, int menuId) {
        if (vote.getId() != 0) {
            throw new IllegalArgumentException(vote + " must be new (id=null)");
        }

        log.info("create {}", vote);
        return service.create(vote, userId, menuId);
    }

    public void update(Vote vote, int id, int userId, int menuId) {
        if (vote.getId() == null) {
            vote.setId(id);
        } else if (vote.getId() != id) {
            throw new IllegalArgumentException(vote + " must be with id=" + id);
        }

        log.info("update {}", vote);
        service.update(vote, userId, menuId);
    }

    @PostMapping("/votes")
    public String filter(HttpServletRequest request) throws Exception {
        LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
        request.setAttribute("votes"
                , VoteUtils.getRestaurantVotes(menusService.getBetween(orElse(startDate, DateTimeUtil.MIN_DATE)
                        , orElse(endDate, DateTimeUtil.MAX_DATE))));
        return "votes";
    }

    /*@GetMapping("/votes/filterreset")
    public String filterReset() {
        return "votes";
    }*/
}
