package ru.alvisid.votingsystem;

public class AuthorizedUser {

    private static int id = 100001;

    public static int id() {
        return id;
    }

    public static void setId(int id) {
        AuthorizedUser.id = id;
    }
}
