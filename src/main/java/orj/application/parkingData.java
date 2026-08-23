package orj.application;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class parkingData{
	
	@Id
    private int slotno;

    private String driverName;

    private String carNumber;

    private LocalDateTime inTime;

    private LocalDateTime outTime;

    private long duration;

    private double amount;

}
