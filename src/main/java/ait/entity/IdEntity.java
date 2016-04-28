package ait.entity;

import ait.utils.ObjectUtils;

/**
 * Created by suomiy on 4/27/16.
 */

public abstract class IdEntity {

    private Long id;

    public IdEntity() {
    }

    IdEntity(long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IdEntity)) return false;

        IdEntity that = (IdEntity) o;

        if (!ObjectUtils.equals(id, that.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

