package spring.service;

import org.springframework.stereotype.Service;
import spring.dao.QuestionDao;
import spring.domain.Question;

import java.util.List;
@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao dao;

    public QuestionServiceImpl(QuestionDao dao) {
        this.dao = dao;
    }

    public List<Question> getQuestions() {
        return dao.getQuestions();
    }
}
