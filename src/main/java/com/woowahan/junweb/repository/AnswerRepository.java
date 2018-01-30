package com.woowahan.junweb.repository;

import com.woowahan.junweb.model.Answer;
import com.woowahan.junweb.model.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnswerRepository extends CrudRepository<Answer, Long>{
    List<Answer> findAnswersByQuestion(Question question);
    Answer findAnswerByAnswerId(long answerId);
}
