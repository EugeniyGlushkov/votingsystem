package ru.alvisid.votingsystem.util;

import ru.alvisid.votingsystem.model.Menu;
import ru.alvisid.votingsystem.model.User;
import ru.alvisid.votingsystem.model.Vote;
import ru.alvisid.votingsystem.repository.jpa.VoteCompositeKey;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

public class VoteUtils {

    private VoteUtils() {
    }

    public static Vote getNewVote(User user, Menu menu) {
        return new Vote(user, menu);
    }


    public static VoteCompositeKey getCompositeKey(int userId, int menuId) {
        return new VoteCompositeKey(userId, menuId);
    }
}
