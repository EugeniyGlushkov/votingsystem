package ru.alvisid.votingsystem.model;

public abstract class AbstractNamedEntity extends AbsractBaseEntity {
    protected String name;

    public AbstractNamedEntity() {
        super();
    }

    public AbstractNamedEntity(String name) {
        this.name = name;
    }

    public AbstractNamedEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
