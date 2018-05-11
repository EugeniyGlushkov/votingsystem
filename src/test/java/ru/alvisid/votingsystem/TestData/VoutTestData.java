package ru.alvisid.votingsystem.TestData;

import ru.alvisid.votingsystem.model.Vote;

public class VoutTestData {
    public static final Vote vote_1 = new Vote(UserTestData.user_1, MenuTestData.menu_1);
    public static final Vote vote_2 = new Vote(UserTestData.user_2, MenuTestData.menu_2);
    public static final Vote vote_3 = new Vote(UserTestData.user_3, MenuTestData.menu_2);
    public static final Vote vote_4 = new Vote(UserTestData.user_3, MenuTestData.menu_4);

    private VoutTestData() {

    }
}
