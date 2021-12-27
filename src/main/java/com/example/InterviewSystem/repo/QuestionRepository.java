package com.example.InterviewSystem.repo;
import com.example.InterviewSystem.models.Interview;
import com.example.InterviewSystem.models.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, Long> {
    Iterable<Question> findByInterview(Interview interview);
}
