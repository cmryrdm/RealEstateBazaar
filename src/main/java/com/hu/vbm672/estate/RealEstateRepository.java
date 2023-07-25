package com.hu.vbm672.estate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface RealEstateRepository extends JpaRepository<RealEstate,Long> {
    List<RealEstate> findAllByRealEstateAddress(RealEstateAddress realEstateAddress);

    List<RealEstate> findAllByRealEstateType(RealEstateType realEstateType);

    Optional<RealEstate> findById(long id);
}
