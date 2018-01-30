package com.woowahan.junweb.repository;

import com.woowahan.junweb.model.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, Long> {
    Question findQuestionByQuestionId(long questionId);
}
