package com.example.InterviewSystem.controllers;
import com.example.InterviewSystem.models.Interview;
import com.example.InterviewSystem.models.Question;
import com.example.InterviewSystem.repo.InterviewRepository;
import com.example.InterviewSystem.repo.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private InterviewRepository interviewRepository;

    @GetMapping("/{idInterview}/question")
    public String questionEdit(@PathVariable(value = "idInterview") long idInterview, Model model) {
        Interview interview = interviewRepository.findByIdInterview(idInterview);
        Iterable<Question> questions = questionRepository.findByInterview(interview);
        model.addAttribute("questions", questions );
        return "question-main";
    }
}
