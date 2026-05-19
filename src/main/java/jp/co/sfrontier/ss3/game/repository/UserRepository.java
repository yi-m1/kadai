package jp.co.sfrontier.ss3.game.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sfrontier.ss3.game.entity.user.UserInformation;


public interface UserRepository extends JpaRepository<UserInformation, Long>{
	
	Optional<UserInformation> findByMailAddress(String mailAddress);

}
