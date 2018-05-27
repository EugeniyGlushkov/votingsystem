package ru.alvisid.votingsystem.util;

import ru.alvisid.votingsystem.model.Menu;
import ru.alvisid.votingsystem.model.User;
import ru.alvisid.votingsystem.model.Vote;

public class VoteUtils {

    private VoteUtils() {
    }

    public static Vote getNewVote(User user, Menu menu) {
        return new Vote(null, user, menu);
    }
}
