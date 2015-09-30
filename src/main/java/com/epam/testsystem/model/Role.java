package com.epam.testsystem.model;

import javax.persistence.Entity;

@Entity
public class Role extends BaseEntity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String roleName) {
        this.name = roleName;
    }
}
