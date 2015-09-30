package com.epam.testsystem.repository;

import com.epam.testsystem.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
}
