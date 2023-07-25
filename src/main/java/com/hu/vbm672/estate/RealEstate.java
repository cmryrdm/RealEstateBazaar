package com.hu.vbm672.estate;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class RealEstate {

    @SequenceGenerator(
            name = "realEstate_sequence",
            sequenceName = "realEstate_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "realEstate_sequence"
    )
    private Long id;
    private String floor;
    private String room;
    private String facade;
    private String area;
    @Enumerated(EnumType.STRING)
    private RealEstateAddress realEstateAddress;
    @Enumerated(EnumType.STRING)
    private RealEstateType realEstateType;
    private int price;

    public RealEstate(String floor, String room, String facade, String area, RealEstateAddress realEstateAddress, RealEstateType realEstateType, int price) {
        this.floor = floor;
        this.room = room;
        this.facade = facade;
        this.area = area;
        this.realEstateAddress = realEstateAddress;
        this.realEstateType = realEstateType;
        this.price = price;
    }
}
