package com.example.InterviewSystem.controllers;

import com.example.InterviewSystem.models.Interview;
import com.example.InterviewSystem.repo.InterviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class InterviewController {

    @Autowired
    private InterviewRepository interviewRepository;
    @GetMapping("/")
    public String interviewMain(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(role -> role
                .getAuthority().equals("USER"))) {
            long now = System.currentTimeMillis();
            Date currentDate = new Date(now);
            Iterable<Interview> interviews = interviewRepository.findByDateEndAfter(currentDate);
            model.addAttribute("interviews", interviews );
            return "interview-main";}
        else {
            long now = System.currentTimeMillis();
            Date currentDate = new Date(now);
            Iterable<Interview> interviews = interviewRepository.findAll();
            model.addAttribute("interviews", interviews );
            return "interview-main-admin";
        }


    }

    @GetMapping("/interview/add")
    public String interviewAdd(Model model) {
        return "interview-add";
    }

    @PostMapping("/interview/add")
    public String interviewAdd(@RequestParam String nameInterview, @RequestParam Date dateStart, @RequestParam Date dateEnd, @RequestParam String description, Model model) {
        Interview interview = new Interview(nameInterview, dateStart, dateEnd, description);
        interviewRepository.save(interview);
        return "redirect:/";
    }
    @GetMapping("/interview/{idInterview}")
    public String interviewDetails(@PathVariable(value = "idInterview") long idInterview, Model model) {
        if (!interviewRepository.existsById(idInterview)) {
            return "redirect:/interview";
        }
        Optional<Interview> interview = interviewRepository.findById(idInterview);
        ArrayList<Interview> res = new ArrayList<>();
        interview.ifPresent(res::add);
        model.addAttribute("interview", res);
        return "interview-detail";
    }
    @GetMapping("/interview/{idInterview}/edit")
    public String interviewEdit(@PathVariable(value = "idInterview") long idInterview, Model model) {
        if (!interviewRepository.existsById(idInterview)) {
            return "redirect:/";
        }
        Optional<Interview> interview = interviewRepository.findById(idInterview);
        ArrayList<Interview> res = new ArrayList<>();
        interview.ifPresent(res::add);
        model.addAttribute("interview", res);
        return "interview-edit";
    }
    @PostMapping("/interview/{idInterview}/edit")
    public String interviewUpdate(@PathVariable(value = "idInterview") long idInterview, @RequestParam String nameInterview, @RequestParam Date dateStart, @RequestParam Date dateEnd, @RequestParam String description, Model model) {
        Interview interview = interviewRepository.findById(idInterview).orElseThrow();
        interview.setNameInterview(nameInterview);
        interview.setDescription(description);
        interview.setDateStart(dateStart);
        interview.setDateEnd(dateEnd);
        interviewRepository.save(interview);
        return "redirect:/";
    }
    @PostMapping("/interview/{idInterview}/remove")
    public String interviewDelete(@PathVariable(value = "idInterview") long idInterview, Model model) {
        Interview interview = interviewRepository.findById(idInterview).orElseThrow();
        interviewRepository.delete(interview);
        return "redirect:/";
    }
}
