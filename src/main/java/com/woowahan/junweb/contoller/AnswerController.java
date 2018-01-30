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


@Controller
@RequestMapping("/qna/{questionId}/answer")
public class AnswerController {
    private static final Logger log = LoggerFactory.getLogger(AnswerController.class);

    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @PostMapping("")
    public String createAnswer(@PathVariable long questionId, String contents, HttpSession session){
        if (session.getAttribute("sessionedUser") == null) return "redirect:/users/login";
        User user = (User) session.getAttribute("sessionedUser");
        Question question = questionRepository.findQuestionByQuestionId(questionId);
        log.debug("login user: {}", user);
        Answer answer = new Answer(question, user, contents);
        answerRepository.save(answer);
        return "redirect:/qna/{questionId}";
    }

    @DeleteMapping("/{answerId}/delete")
    public String deleteAnswer(@PathVariable long questionId, @PathVariable long answerId, HttpSession session){
        if (session.getAttribute("sessionedUser") == null) return "redirect:/users/login";
        User user = (User) session.getAttribute("sessionedUser");
        Answer answer = answerRepository.findAnswerByAnswerId(answerId);

        //todo: redirect alert 하는 방법 찾기
        if (user.getId() != answer.getWriter().getId()) return "alert:/";
        answer.setDeleted(true);
        answerRepository.save(answer);
        return "redirect:/qna/{questionId}";
    }

    @PutMapping("/{answerId}/update")
    public String updateAnswer(@PathVariable long questionId, @PathVariable long answerId, HttpSession session){

        return "redirect:/qna/{questionId}";
    }
}
