package ru.alvisid.votingsystem.model;

import java.util.Objects;

public abstract class AbsractBaseEntity {
    protected Integer id;

    public AbsractBaseEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        return true;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
