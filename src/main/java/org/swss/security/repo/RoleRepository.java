package org.swss.security.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.swss.security.ent.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
