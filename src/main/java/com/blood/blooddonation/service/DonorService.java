package com.blood.blooddonation.service;

import com.blood.blooddonation.model.Donor;
import com.blood.blooddonation.repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DonorService {

    @Autowired
    private DonorRepository donorRepository;

    public Donor saveDonor(Donor donor) {
        return donorRepository.save(donor);
    }

    public List<Donor> getAllDonors() {
        return donorRepository.findAll();
    }

    public List<Donor> getDonorsByBloodGroup(String bloodGroup) {
        return donorRepository.findByBloodGroup(bloodGroup);
    }

    public void deleteDonor(Long id) {
        donorRepository.deleteById(id);
    }

    public List<Donor> getDonorsByGroupAndLocation(String bloodGroup, String location) {
        if (bloodGroup == null || location == null) {
            return List.of();
        }
        return donorRepository.findByBloodGroupAndLocationIgnoreCase(bloodGroup.trim(), location.trim());
    }

    // GPS DYNAMIC PROXIMITY CALCULATIONS WRAPPER
    public List<Map<String, Object>> getNearestDonors(String bloodGroup, Double lat, Double lng, Double radius) {
        List<Object[]> rawResults = donorRepository.findNearestDonorsRaw(bloodGroup.trim(), lat, lng, radius);
        List<Map<String, Object>> customResponse = new ArrayList<>();

        for (Object[] row : rawResults) {
            Map<String, Object> donorMap = new HashMap<>();
            donorMap.put("id", row[0]);
            donorMap.put("name", row[1]);
            donorMap.put("bloodGroup", row[2]);
            donorMap.put("phone", row[3]);
            donorMap.put("location", row[4]);
            donorMap.put("age", row[5]);
            donorMap.put("distance", row[8]); // Appends calculated distance attribute matrix directly
            customResponse.add(donorMap);
        }
        return customResponse;
    }
}