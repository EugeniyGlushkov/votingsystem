package ru.alvisid.votingsystem.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@MappedSuperclass
public abstract class AbstractNamedEntity extends AbsractBaseEntity {

    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "name", nullable = false, unique = true)
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
    public String toString() {
        return String.format("Entity %s (%s, '%s')", getClass().getName(), id, name);
    }
}
