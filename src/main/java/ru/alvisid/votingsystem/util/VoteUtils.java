package ru.alvisid.votingsystem.util;

import ru.alvisid.votingsystem.model.Menu;
import ru.alvisid.votingsystem.model.User;
import ru.alvisid.votingsystem.model.Vote;

public class VoteUtils {
    public static final Vote vote_1 = new Vote(UserUtils.user_1, MenuUtils.menu_1);
    public static final Vote vote_2 = new Vote(UserUtils.user_2, MenuUtils.menu_2);
    public static final Vote vote_3 = new Vote(UserUtils.user_3, MenuUtils.menu_3);
    public static final Vote vote_4 = new Vote(UserUtils.user_3, MenuUtils.menu_4);

    private VoteUtils() {
    }

    public static Vote getNewVote(User user, Menu menu) {
        return new Vote(user, menu);
    }
}
