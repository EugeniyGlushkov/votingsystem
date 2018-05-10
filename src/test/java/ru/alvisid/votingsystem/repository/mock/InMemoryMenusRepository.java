package ru.alvisid.votingsystem.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import ru.alvisid.votingsystem.TestData.MenuTestData;
import ru.alvisid.votingsystem.model.Menu;
import ru.alvisid.votingsystem.repository.MenusRepository;
import ru.alvisid.votingsystem.util.DateTimeUtil;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class InMemoryMenusRepository implements MenusRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryMenusRepository.class);
    private Map<Integer, Menu> menuRepository = new ConcurrentHashMap<>();

    {
        MenuTestData.MENUS.forEach(menu -> menuRepository.put(menu.getId(), menu));
    }

    @Override
    public Menu save(Menu menu) {
        log.info("save {}", menu);

        if (menu.isNew()) {
            menu.setId(MenuTestData.getIdCounter().incrementAndGet());
            menuRepository.put(menu.getId(), menu);
            return menu;
        }

        return menuRepository.computeIfPresent(menu.getId(), (id, oldUser) -> menu);
    }

    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        return menuRepository.remove(id) != null;
    }

    @Override
    public Menu get(int id) {
        log.info("get {}", id);
        return menuRepository.get(id);
    }

    @Override
    public List<Menu> getAll() {
        log.info("getAll {}");
        return menuRepository.values().stream()
                .sorted(Comparator.comparing(Menu::getDate).thenComparing(menu -> menu.getRestaurant().getName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Menu> getBetween(LocalDate startDate, LocalDate endDate) {
        log.info("getBetween {} - {}", startDate, endDate);
        return getAllFiltered(menu -> DateTimeUtil.isBetween(menu.getDate(), startDate, endDate));
    }

    private List<Menu> getAllFiltered(Predicate<Menu> filter) {
        List<Menu> menus = new ArrayList<>(menuRepository.values());
        return CollectionUtils.isEmpty(menus) ? Collections.emptyList() :
                menus.stream()
                        .filter(filter)
                        .sorted(Comparator.comparing(Menu::getDate).reversed())
                        .collect(Collectors.toList());
    }
}
