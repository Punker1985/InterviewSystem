package com.example.InterviewSystem.repo;
import com.example.InterviewSystem.models.Interview;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface InterviewRepository extends CrudRepository<Interview, Long> {
    List<Interview> findByDateEndAfter(Date currentDate);
    Interview findByIdInterview(Long idInterview);
}
