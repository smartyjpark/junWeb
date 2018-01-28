package com.woowahan.junweb.Contoller;

import com.woowahan.junweb.Model.Question;
import com.woowahan.junweb.Model.User;
import com.woowahan.junweb.Repository.QuestionRepository;
import com.woowahan.junweb.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/qna")
public class QuestionController {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{questionId}")
    public String createQuestion(@PathVariable long questionId, Model model){
        Question question = questionRepository.findOne(questionId);
        System.out.println(question);
        model.addAttribute("question", question);
        return "qna/show";
    }

    @GetMapping("")
    public String showQuestions(Model model){
        List<Question> questions = (List<Question>) questionRepository.findAll();
        model.addAttribute("questions", questions);
        return "qna/list";
    }

    @PostMapping("")
    public String createQuestion(String writer, String title, String contents){
        System.out.println("come on!");
        User user = userRepository.findUserByUserId(writer);
        Question question = new Question(user, title, contents);
        System.out.println(question);
        questionRepository.save(question);
        return "redirect:/";
    }
}
