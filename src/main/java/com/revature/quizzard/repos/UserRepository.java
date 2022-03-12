package com.revature.quizzard.repos;

import com.revature.quizzard.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, String> {

    AppUser findAppUserByUsernameAndPassword(String username, String password);

}
