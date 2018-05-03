package ru.alvisid.votingsystem.DAO;

import ru.alvisid.votingsystem.model.User;
import ru.alvisid.votingsystem.model.Vote;

import java.util.List;

public interface DAO {
    Vote add(Vote vote);
    List<Vote> readByUser(User user);
    List<Vote> readAll();
}
