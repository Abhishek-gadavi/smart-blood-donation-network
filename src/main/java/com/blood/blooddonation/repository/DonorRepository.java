package com.blood.blooddonation.repository;

import com.blood.blooddonation.model.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface DonorRepository extends JpaRepository<Donor, Long> {

    List<Donor> findByBloodGroup(String bloodGroup);
    List<Donor> findByBloodGroupAndLocationIgnoreCase(String bloodGroup, String location);

    // ADVANCED GPS FEATURE: Computes real-world straight-line sphere distance calculations natively in SQL
    @Query(value = "SELECT d.id, d.name, d.blood_group as bloodGroup, d.phone, d.location, d.age, d.latitude, d.longitude, " +
                   "(6371 * acos(cos(radians(:reqLat)) * cos(radians(d.latitude)) * " +
                   "cos(radians(d.longitude) - radians(:reqLng)) + sin(radians(:reqLat)) * " +
                   "sin(radians(d.latitude)))) AS distance " +
                   "FROM donor d WHERE d.blood_group = :bloodGroup " +
                   "HAVING distance <= :radius " +
                   "ORDER BY distance ASC", nativeQuery = true)
    List<Object[]> findNearestDonorsRaw(@Param("bloodGroup") String bloodGroup, 
                                     @Param("reqLat") Double reqLat, 
                                     @Param("reqLng") Double reqLng, 
                                     @Param("radius") Double radius);
}