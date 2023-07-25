package com.hu.vbm672.estate;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RealEstateService {

    private final RealEstateRepository realEstateRepository;

    public void add(RealEstate realEstate) {
        if(realEstate != null) {
            realEstateRepository.save(realEstate);
        }
    }

    public void delete(long id) {
        realEstateRepository.findById(id).ifPresent(realEstateRepository::delete);
    }

    public List<RealEstate> search(RealEstateAddress address, RealEstateType type) {
        List<RealEstate> estateList = realEstateRepository.findAllByRealEstateAddress(address);
        List<RealEstate> filteredList = new ArrayList<>();

        for (RealEstate estate : estateList) {
            if (estate.getRealEstateType() == type) {
                filteredList.add(estate);
            }
        }
        return filteredList;
    }
}
