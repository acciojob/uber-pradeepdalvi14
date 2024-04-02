package com.driver.services.impl;

import com.driver.model.Cab;
import com.driver.model.TripBooking;
import com.driver.model.TripStatus;
import com.driver.repository.CabRepository;
import com.driver.repository.TripBookingRepository;
import com.driver.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.model.Driver;
import com.driver.repository.DriverRepository;

import java.util.List;

@Service
public class DriverServiceImpl implements DriverService {

	@Autowired
	DriverRepository driverRepository3;

	@Autowired
	CabRepository cabRepository3;

	@Autowired
	TripBookingRepository tripBookingRepository;

	@Override
	public void register(String mobile, String password){
		//Save a driver in the database having given details and a cab with ratePerKm as 10 and availability as True by default.
		Driver d = new Driver();
		d.setMobile(mobile);
		d.setPassword(password);

		Cab cab = new Cab();
		cab.setAvailable(true);
		cab.setPerKmRate(10);

		d.setCab(cab);
		driverRepository3.save(d);
	}

	@Override
	public void removeDriver(int driverId){
		// Delete driver without using deleteById function
		List<TripBooking> bookingList = tripBookingRepository.findTrips(driverId);
		for(TripBooking trip:bookingList){
			trip.setStatus(TripStatus.CANCELED);
			driverRepository3.delete(trip.getDriver());
			tripBookingRepository.delete(trip);
		}

	}

	@Override
	public void updateStatus(int driverId){
		//Set the status of respective car to unavailable
		Driver d = driverRepository3.findById(driverId).get();

		Cab cab = d.getCab();
		cab.setAvailable(false);
	}
}
