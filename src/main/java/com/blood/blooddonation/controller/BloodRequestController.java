package com.blood.blooddonation.controller;

import com.blood.blooddonation.model.BloodRequest;
import com.blood.blooddonation.service.BloodRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests")
public class BloodRequestController {

    @Autowired
    private BloodRequestService bloodRequestService;

    @PostMapping
    public BloodRequest addRequest(@RequestBody BloodRequest request) {
        return bloodRequestService.saveRequest(request);
    }

    @GetMapping
    public List<BloodRequest> getAllRequests() {
        return bloodRequestService.getAllRequests();
    }

    @PutMapping("/{id}/approve")
    public BloodRequest approveRequest(@PathVariable Long id) {
        return bloodRequestService.updateStatus(id, "APPROVED");
    }

    @PutMapping("/{id}/reject")
    public BloodRequest rejectRequest(@PathVariable Long id) {
        return bloodRequestService.updateStatus(id, "REJECTED");
    }
    @DeleteMapping("/{id}")
public void deleteRequest(@PathVariable Long id) {
    bloodRequestService.deleteRequest(id);
}
}