package orj.application.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ParkingHistoryData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int SlNo;
	
	private int slotno;

    private String driverName;

    private String carNumber;

    private LocalDateTime inTime;

    private LocalDateTime outTime;

    private long duration;

    private double amount;
}
