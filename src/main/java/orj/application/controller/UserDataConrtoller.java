package orj.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import orj.application.entity.userData;
import orj.application.services.UserDataServices;

@RestController
@RequestMapping("/jpa/smps/User")
public class UserDataConrtoller {
	
	@Autowired
	UserDataServices userser;
	
	@GetMapping("/getUser")
	public List<userData> UserData() {
		return userser.getUser();
	}
	
	@GetMapping("/get/{UserID}")
	private userData getdata(@PathVariable ("UserID") int id) {
		return userser.getUserById(id);
	}
	
	@PostMapping("/CreatUser")
	public String CreatUser(@RequestBody userData data) {
		return userser.CreatUser(data);
	}
	
	@DeleteMapping("/DeletUser/{UserID}")
	public String deletUser(@PathVariable ("UserID") int id) {
		return userser.deletUser(id);
	}

}
