package com.example.my_app.repositories;
import com.example.my_app.models.User;
import java.time.chrono.JapaneseChronology;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends  JpaRepository<User,Long>{

    Optional<User> findByEmail(String email);
}