package ru.alvisid.votingsystem.TestData;

import ru.alvisid.votingsystem.model.Menu;
import ru.alvisid.votingsystem.model.User;
import ru.alvisid.votingsystem.model.Vote;
import ru.alvisid.votingsystem.util.MenuUtils;

import java.time.LocalDate;

public class VoteTestData {
    public static final Vote vote_1 = getVote(UserTestData.user_1, MenuTestData.menu_1);
    public static final Vote vote_2 = getVote(UserTestData.user_2, MenuTestData.menu_2);
    public static final Vote vote_3 = getVote(UserTestData.user_3, MenuTestData.menu_2);
    public static final Vote vote_4 = getVote(UserTestData.user_3, MenuTestData.menu_4);

    public static final Vote new_vote =
            new Vote(null, UserTestData.user_1, MenuTestData.menu_3);

    public static final Vote new_test_vote = getVote(UserTestData.user_1, MenuTestData.menu_3);

    private VoteTestData() {
    }

    private static Vote getVote(User user, Menu menu) {
        return new Vote(TestData.getNewVoteId(), user, menu);
    }
}
