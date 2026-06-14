package com.blood.blooddonation.controller;

import com.blood.blooddonation.model.Donor;
import com.blood.blooddonation.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/donors")
@CrossOrigin(origins = "*")
public class DonorController {

    @Autowired
    private DonorService donorService;

    @PostMapping
    public Donor addDonor(@RequestBody Donor donor) {
        return donorService.saveDonor(donor);
    }

    @GetMapping
    public List<Donor> getAllDonors() {
        return donorService.getAllDonors();
    }

    @GetMapping("/search")
    public List<Donor> searchByBloodGroup(@RequestParam String bloodGroup) {
        return donorService.getDonorsByBloodGroup(bloodGroup);
    }

    @GetMapping("/match")
    public List<Donor> searchByGroupAndLocation(@RequestParam String bloodGroup, @RequestParam String location) {
        return donorService.getDonorsByGroupAndLocation(bloodGroup, location);
    }

    // NEW ENDPOINT: Triggers spatial filtering calculation engine routines
    @GetMapping("/nearest")
    public List<Map<String, Object>> getNearestDonors(@RequestParam String bloodGroup,
                                                      @RequestParam Double lat,
                                                      @RequestParam Double lng,
                                                      @RequestParam Double radius) {
        return donorService.getNearestDonors(bloodGroup, lat, lng, radius);
    }

    @DeleteMapping("/{id}")
    public void deleteDonor(@PathVariable Long id) {
        donorService.deleteDonor(id);
    }
}