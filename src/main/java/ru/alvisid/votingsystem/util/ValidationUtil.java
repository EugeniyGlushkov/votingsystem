package ru.alvisid.votingsystem.util;

import ru.alvisid.votingsystem.model.AbsractBaseEntity;
import ru.alvisid.votingsystem.util.exception.NotFoundException;
import ru.alvisid.votingsystem.util.exception.OverTimeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class ValidationUtil {
    private ValidationUtil() {
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFoundWithId(T object, int id) {
        return checkNotFound(object, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(!Objects.isNull(object), msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }

    public static void checkNew(AbsractBaseEntity entity) {
        if (!entity.isNew()) {
            throw new IllegalArgumentException(entity + " must be new (id=null)");
        }
    }

    public static void assureIdConsistent(AbsractBaseEntity entity, int id) {
//      http://stackoverflow.com/a/32728226/548473
        if (entity.isNew()) {
            entity.setId(id);
        } else if (entity.getId() != id) {
            throw new IllegalArgumentException(entity + " must be with id=" + id);
        }
    }

    public static void checkOverTimeVout(LocalDateTime checkTime, LocalDate checkDate, String msg) {
        LocalDate dateNow = LocalDate.now();
        LocalTime timeNow = LocalTime.now();

        if (!checkDate.isEqual(dateNow)) {
            throw new OverTimeException("The current date " + dateNow + " is not equal " + checkDate + ". " + msg );
        }

        if (timeNow.isAfter(checkTime.toLocalTime())) {
            throw new OverTimeException("The current time " + timeNow + " is after " + checkTime + ". " +msg);
        }
    }
}
