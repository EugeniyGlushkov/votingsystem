package ru.alvisid.votingsystem.TestData;

import ru.alvisid.votingsystem.model.Menu;
import ru.alvisid.votingsystem.model.User;
import ru.alvisid.votingsystem.model.Vote;
import ru.alvisid.votingsystem.util.MenuUtils;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class VoteTestData {
    public static final Vote vote_1 = getVote(UserTestData.user_1, MenuTestData.menu_1);
    public static final Vote vote_2 = getVote(UserTestData.user_2, MenuTestData.menu_2);
    public static final Vote vote_3 = getVote(UserTestData.user_3, MenuTestData.menu_2);
    public static final Vote vote_4 = getVote(UserTestData.user_3, MenuTestData.menu_4);

    public static Set<Vote> voteUserSet_1 = new HashSet<>();
    public static Set<Vote> voteUserSet_2 = new HashSet<>();
    public static Set<Vote> voteUserSet_3 = new HashSet<>();

    public static Set<Vote> voteMenuSet_1 = new HashSet<>();
    public static Set<Vote> voteMenuSet_2 = new HashSet<>();
    public static Set<Vote> voteMenuSet_3 = new HashSet<>();
    public static Set<Vote> voteMenuSet_4 = new HashSet<>();

    static {
        voteUserSet_1.add(vote_1);
        voteUserSet_2.add(vote_2);
        voteUserSet_3.add(vote_3);
        voteUserSet_3.add(vote_4);

        voteMenuSet_1.add(vote_1);
        voteMenuSet_2.add(vote_2);
        voteMenuSet_2.add(vote_3);
        voteMenuSet_4.add(vote_4);
    }

    public static final Vote new_vote =
            new Vote(null, UserTestData.user_1, MenuTestData.menu_3);

    public static final Vote new_test_vote = getVote(UserTestData.user_1, MenuTestData.menu_3);

    private VoteTestData() {
    }

    private static Vote getVote(User user, Menu menu) {
        return new Vote(TestData.getNewVoteId(), user, menu);
    }
}
