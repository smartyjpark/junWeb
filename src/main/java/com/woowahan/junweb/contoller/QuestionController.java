package com.woowahan.junweb.contoller;

import com.woowahan.junweb.model.Answer;
import com.woowahan.junweb.model.Question;
import com.woowahan.junweb.model.User;
import com.woowahan.junweb.repository.AnswerRepository;
import com.woowahan.junweb.repository.QuestionRepository;
import com.woowahan.junweb.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/qna")
public class QuestionController {
    private static final Logger log = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AnswerRepository answerRepository;

    @GetMapping("/{questionId}")
    public String showQuestionContent(@PathVariable long questionId, Model model, HttpSession session) {
        Question question = questionRepository.findOne(questionId);
        List<Answer> answers = question.getAnswers();
        model.addAttribute("question", question);
        model.addAttribute("answers", answers);
        model.addAttribute("size", answers.size());
        if(session.getAttribute("sessionedUser") != null){
            User user = (User)session.getAttribute("sessionedUser");
            model.addAttribute("isMyQuestion", question.getWriter().getId() == user.getId());
        }
        return "qna/show";
    }

    @GetMapping("")
    public String showQuestions(Model model) {
        List<Question> questions = (List<Question>) questionRepository.findAll();
        model.addAttribute("questions", questions);
        return "qna/list";
    }

    @PostMapping("")
    public String createQuestion(String writer, String title, String contents) {
        User user = userRepository.findUserByUserId(writer);
        Question question = new Question(user, title, contents);
        questionRepository.save(question);
        return "redirect:/";
    }

    @GetMapping("/{questionId}/update")
    public String showQuestions(@PathVariable long questionId, Model model) {
        Question question = questionRepository.findOne(questionId);
        model.addAttribute("question", question);
        return "qna/updateForm";
    }

    @PutMapping("/{questionId}/update")
    public String updateQuestion(@PathVariable long questionId, String contents, String title) {
        Question question = questionRepository.findQuestionByQuestionId(questionId);
        question.setTitle(title);
        question.setContents(contents);
        questionRepository.save(question);
        return "redirect:/qna/{questionId}";
    }

    @DeleteMapping("/{questionId}/delete")
    public String deleteQuestion(@PathVariable long questionId) {
        Question question = questionRepository.findQuestionByQuestionId(questionId);
        question.setDeleted(true);
        questionRepository.save(question);
        return "redirect:/";
    }
}
