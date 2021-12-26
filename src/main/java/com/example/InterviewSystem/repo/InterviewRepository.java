package com.example.InterviewSystem.repo;
import com.example.InterviewSystem.models.Interview;
import org.springframework.data.repository.CrudRepository;

public interface InterviewRepository extends CrudRepository<Interview, Long> {
}
