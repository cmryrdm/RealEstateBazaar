package com.hu.vbm672.appuser.role.request;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleRequestService {
    private final RoleRequestRepository roleRequestRepository;

    // add role request
    public void add(RoleRequest roleRequest) {
        roleRequestRepository.save(roleRequest);
    }

    // delete role request
    public void delete(RoleRequest roleRequest) {
        roleRequestRepository.findById(roleRequest.getId()).ifPresent(roleRequestRepository::delete);
    }

    // fetch role request
    public RoleRequest fetch(Long id) {
        return roleRequestRepository.findById(id).orElseThrow(()->new IllegalStateException(
                String.format("Request ID not found!")));
    }

    // search and return role request list
    public List<RoleRequest> search() {
        List<RoleRequest> roleRequestList = roleRequestRepository.findAll();
        return roleRequestList;
    }
}
