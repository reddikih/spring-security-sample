package my.example.spring.security.model;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by satoshi on 2018/02/20.
 */
public interface UserDetailsRepository extends JpaRepository<UserDetailsImpl, Long> {

  UserDetailsImpl findByUsername(String userName);

}
