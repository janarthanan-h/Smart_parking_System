package orj.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import orj.application.entity.userData;
import orj.application.services.UserDataServices;

@RestController
public class UserDataConrtoller {
	
	@Autowired
	UserDataServices userser;
	
	@GetMapping("/getUser")
	public List<userData> UserData() {
		return userser.getUser();
	}
	
	@PostMapping("/CreatUser")
	private String CreatUser(@RequestBody userData data) {
		return userser.CreatUser(data);

	}

}
