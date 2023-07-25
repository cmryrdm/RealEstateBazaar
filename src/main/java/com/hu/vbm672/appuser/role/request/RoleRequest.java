package com.hu.vbm672.appuser.role.request;

import com.hu.vbm672.appuser.AppUserRole;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class RoleRequest {

    @SequenceGenerator(
            name = "roleRequest_sequence",
            sequenceName = "roleRequest_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "roleRequest_sequence"
    )
    private Long id;
    private String email;
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;


    public RoleRequest(String email, AppUserRole appUserRole) {
        this.email = email;
        this.appUserRole = appUserRole;
    }
}
