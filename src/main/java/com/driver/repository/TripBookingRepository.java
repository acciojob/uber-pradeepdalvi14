package com.driver.repository;
import com.driver.model.TripBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripBookingRepository extends JpaRepository<TripBooking, Integer>{

    @Query(nativeQuery = true,value = "select * from TripBooking where drivar_driverId =:driverId")
    public List<TripBooking> findTrips(Integer driverId);
}
