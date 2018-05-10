package ru.alvisid.votingsystem.web.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.alvisid.votingsystem.service.MenusService;
import ru.alvisid.votingsystem.to.PriceTransfer;
import ru.alvisid.votingsystem.util.MenuUtils;

@Controller
public class PriceRestController {
    private final MenusService service;

    @Autowired
    public PriceRestController(MenusService service) {
        this.service = service;
    }

    public PriceTransfer getPrice(int menuId) {
        return MenuUtils.getPriceForTransfer(service.get(menuId));
    }


}
