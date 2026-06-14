package com.blood.blooddonation.service;

import com.blood.blooddonation.model.BloodRequest;
import com.blood.blooddonation.repository.BloodRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BloodRequestService {

    @Autowired
    private BloodRequestRepository bloodRequestRepository;

    public BloodRequest saveRequest(BloodRequest request) {
        return bloodRequestRepository.save(request);
    }

    public List<BloodRequest> getAllRequests() {
        return bloodRequestRepository.findAll();
    }

    public BloodRequest updateStatus(Long id, String status) {
        BloodRequest request = bloodRequestRepository.findById(id).orElse(null);

        if (request != null) {
            request.setStatus(status);
            return bloodRequestRepository.save(request);
        }

        return null;
    }
    public void deleteRequest(Long id) {
    bloodRequestRepository.deleteById(id);
}
}