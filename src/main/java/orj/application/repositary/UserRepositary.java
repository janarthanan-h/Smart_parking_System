package orj.application.repositary;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import orj.application.entity.user;

public interface UserRepositary extends JpaRepository<user, Integer> {
	
	Optional<user> findByUserNameAndPassword(String userName,String password);

}
