package com.locophotoapp.locophotoapp.users;

import com.locophotoapp.locophotoapp.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.email=:email")
    User findByEmail(String email);

}
