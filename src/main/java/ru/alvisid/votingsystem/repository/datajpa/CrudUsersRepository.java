package ru.alvisid.votingsystem.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alvisid.votingsystem.model.User;

public interface CrudUsersRepository extends JpaRepository<User,Integer> {
}
