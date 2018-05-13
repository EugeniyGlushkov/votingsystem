package ru.alvisid.votingsystem.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.alvisid.votingsystem.model.Menu;
import ru.alvisid.votingsystem.repository.MenusRepository;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcMenusRepositoryImpl implements MenusRepository {

    private static final BeanPropertyRowMapper<Menu> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Menu.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertMenu;

    private final SimpleJdbcInsert insertPrices;


    @Autowired
    public JdbcMenusRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertMenu = new SimpleJdbcInsert(dataSource)
                .withTableName("menus")
                .usingGeneratedKeyColumns("id");
        this.insertPrices = new SimpleJdbcInsert(dataSource)
                .withTableName("prices");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Menu save(Menu menu) {
        MapSqlParameterSource mapMenu = new MapSqlParameterSource()
                .addValue("id", menu.getId())
                .addValue("restaurants_id", menu.getRestaurant().getId())
                .addValue("date", Date.valueOf(menu.getDate()));

        if (menu.isNew()) {
            Number newKey = insertMenu.executeAndReturnKey(mapMenu);
            menu.setId(newKey.intValue());

            for (Map.Entry<String, Float> entry : menu.getMenu().entrySet()) {
                insertPrices.execute(getPricesMapSqlParSrc(newKey.intValue(), entry.getKey(), entry.getValue()));
            }

            return menu;
        } else if (namedParameterJdbcTemplate.update("UPDATE menus SET restaurants_id=:restaurants_id, " +
                "date=:date WHERE id=:id", mapMenu) == 0) {
            return null;
        }

        namedParameterJdbcTemplate.update("DELETE FROM prices WHERE menu_id=:menu_id",
                new MapSqlParameterSource().addValue("menu_id", menu.getId()));

        for (Map.Entry<String, Float> entry : menu.getMenu().entrySet()) {
            namedParameterJdbcTemplate.update("UPDATE prices SET dish=:dish, price=:price WHERE menu_id=:menu_id"
                    , getPricesMapSqlParSrc(menu.getId(), entry.getKey(), entry.getValue()));
        }

        return menu;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Menu get(int id) {
        return null;
    }

    @Override
    public List<Menu> getAll() {
        return null;
    }

    @Override
    public List<Menu> getBetween(LocalDate startDate, LocalDate endDate) {
        return null;
    }

    private MapSqlParameterSource getPricesMapSqlParSrc(int menu_id, String dish, Float price) {
        return new MapSqlParameterSource()
                .addValue("menu_id", menu_id)
                .addValue("dish", dish)
                .addValue("price", BigDecimal.valueOf(price).setScale(2, BigDecimal.ROUND_HALF_UP));
    }
}
