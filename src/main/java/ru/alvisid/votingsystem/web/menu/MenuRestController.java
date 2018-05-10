package ru.alvisid.votingsystem.web.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.alvisid.votingsystem.service.MenusService;

@Controller
public class MenuRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final MenusService service;

    @Autowired
    public MenuRestController(MenusService service) {
        this.service = service;
    }
}
