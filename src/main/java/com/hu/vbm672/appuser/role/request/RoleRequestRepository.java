package com.hu.vbm672.appuser.role.request;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface RoleRequestRepository extends JpaRepository<RoleRequest,Long> {
    Optional<RoleRequest> findByEmail(String email);
}
