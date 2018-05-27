package ru.alvisid.votingsystem.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.alvisid.votingsystem.model.Vote;

public interface CrudVotesRepository extends JpaRepository<Vote,Integer> {
}
