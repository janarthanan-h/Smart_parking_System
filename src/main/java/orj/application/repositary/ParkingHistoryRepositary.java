package orj.application.repositary;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import orj.application.entity.ParkingHistoryData;

public interface ParkingHistoryRepositary extends JpaRepository<ParkingHistoryData, Integer> {

	Optional<ParkingHistoryData> findByCarNumber(String carNumber);
}
