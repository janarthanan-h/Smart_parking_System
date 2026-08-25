package orj.application.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import orj.application.entity.parkingData;


public interface ParkingRepositary extends JpaRepository<parkingData, Integer> {

}
