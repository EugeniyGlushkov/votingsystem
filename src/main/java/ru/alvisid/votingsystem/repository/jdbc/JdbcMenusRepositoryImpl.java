package ru.alvisid.votingsystem.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ru.alvisid.votingsystem.model.Menu;
import ru.alvisid.votingsystem.repository.MenusRepository;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.List;

public class JdbcMenusRepositoryImpl implements MenusRepository {

    private static final BeanPropertyRowMapper<Menu> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Menu.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertMenu;

    @Autowired
    public JdbcMenusRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertMenu = new SimpleJdbcInsert(dataSource)
                .withTableName("menus")
                .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Menu save(Menu menu) {
        return null;
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
}
