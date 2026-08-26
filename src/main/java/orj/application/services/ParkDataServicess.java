package orj.application.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import orj.application.entity.ParkingHistoryData;
import orj.application.entity.parkingData;
import orj.application.repositary.ParkingHistoryRepositary;
import orj.application.repositary.ParkingRepositary;

@Service
public class ParkDataServicess {
	
	@Autowired
	private ParkingRepositary park;
	
	@Autowired
	private ParkingHistoryRepositary History;
	
	 public List<parkingData> getAllData() {
	        return park.findAll();
	    }

	 
	 public String deleteParkingData(int slotno) {

	        if (park.existsById(slotno)) {
	            park.deleteById(slotno);
	            return "Parking data deleted successfully";
	        }

	        return "Parking data not found";
	    }
	 
	 public String vehicleIn(parkingData data ,HttpSession session) {
		 
		 Object attribute = session.getAttribute("user");
		 if (attribute == null) {
				return "Please Login First";
			}

		    List<parkingData> parkingList = park.findAll();
		    

		    int slot = 1;

		    while (true) {

		        boolean found = false;

		        for (parkingData p : parkingList) {

		            if (p.getSlotno() == slot) {
		                found = true;
		                break;
		            }
		        }

		        if (!found) {
		            break;
		        }

		        slot++;
		    }

		    data.setSlotno(slot);
		    data.setInTime(LocalDateTime.now());
		    data.setOutTime(null);
		    data.setDuration(0);
		    data.setAmount(0);
		    
		    park.save(data);
		    
		    return "Parking Data Saved Successfully";
		}
	 

	 public String vehicleOut(int slotno,HttpSession session) {
		 
		 Object attribute = session.getAttribute("user");
		 if (attribute == null) {
				return "Please Login First";
			}

	         parkingData data = park.findById(slotno)
	                 .orElseThrow(() ->
	                         new RuntimeException("Slot not found"));

	         LocalDateTime outTime = LocalDateTime.now();

	         data.setOutTime(outTime);

	         Duration duration = Duration.between(
	                 data.getInTime(),
	                 outTime
	         );

	         long hours = duration.toHours();

	         if (hours == 0) {
	             hours = 1;
	         }
	         double amount = hours * 40;
	         data.setDuration(hours);

	         data.setAmount(amount);
	         
	         ParkingHistoryData Ph=new ParkingHistoryData();
	         
	         Ph.setSlotno(data.getSlotno());
	         
	         Ph.setDriverName(data.getDriverName());
	         
	         Ph.setCarNumber(data.getCarNumber());
	         
	         Ph.setAmount(data.getAmount());
	         
	         Ph.setDuration(data.getDuration());
	         
	         Ph.setInTime(data.getOutTime());
	         
	         Ph.setOutTime(data.getOutTime());
	       
	         History.save(Ph);
	         
	         park.save(data);

	         return "Slotnumber :"+slotno +" -> Car is exit";
	   }
	     
	     
	 }
	 
