package com.example.InterviewSystem.repo;
import com.example.InterviewSystem.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
