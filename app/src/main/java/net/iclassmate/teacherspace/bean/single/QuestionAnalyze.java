package net.iclassmate.teacherspace.bean.single;

import net.iclassmate.teacherspace.bean.Parserable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xydbj on 2016/2/24.
 */
public class QuestionAnalyze implements Parserable, Serializable {
    private List<ObjectQuestion> listObjectQuestion;
    private List<QstAnalyzes> listQstAnalyzes;
    private QuestionDifficulty questionDifficulty;
    private List<QuestionScoreDetails> listQuestionScoreDetails;

    @Override
    public void parserJson(JSONObject jsonObject) {
        if (jsonObject != null) {
            try {
                listObjectQuestion = new ArrayList<>();
                JSONArray array1 = jsonObject.optJSONArray("objectQuestion");
                if (array1 != null) {
                    for (int i = 0; i < array1.length(); i++) {
                        ObjectQuestion question = new ObjectQuestion();
                        question.parserJson(array1.getJSONObject(i));
                        listObjectQuestion.add(question);
                    }
                }
                listQstAnalyzes = new ArrayList<>();
                JSONArray array2 = jsonObject.getJSONArray("qstAnalyzes");
                for (int i = 0; i < array2.length(); i++) {
                    QstAnalyzes analyzes = new QstAnalyzes();
                    analyzes.parserJson(array2.getJSONObject(i));
                    listQstAnalyzes.add(analyzes);
                }

                questionDifficulty = new QuestionDifficulty();
                questionDifficulty.parserJson(jsonObject.getJSONObject("questionDifficulty"));

                listQuestionScoreDetails = new ArrayList<>();
                JSONArray array3 = jsonObject.getJSONArray("questionScoreDetails");
                for (int i = 0; i < array3.length(); i++) {
                    QuestionScoreDetails questionScoreDetails = new QuestionScoreDetails();
                    questionScoreDetails.parserJson(array3.getJSONObject(i));
                    listQuestionScoreDetails.add(questionScoreDetails);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public List<ObjectQuestion> getListObjectQuestion() {
        return listObjectQuestion;
    }

    public void setListObjectQuestion(List<ObjectQuestion> listObjectQuestion) {
        this.listObjectQuestion = listObjectQuestion;
    }

    public List<QstAnalyzes> getListQstAnalyzes() {
        return listQstAnalyzes;
    }

    public void setListQstAnalyzes(List<QstAnalyzes> listQstAnalyzes) {
        this.listQstAnalyzes = listQstAnalyzes;
    }

    public QuestionDifficulty getQuestionDifficulty() {
        return questionDifficulty;
    }

    public void setQuestionDifficulty(QuestionDifficulty questionDifficulty) {
        this.questionDifficulty = questionDifficulty;
    }

    public List<QuestionScoreDetails> getListQuestionScoreDetails() {
        return listQuestionScoreDetails;
    }

    public void setListQuestionScoreDetails(List<QuestionScoreDetails> listQuestionScoreDetails) {
        this.listQuestionScoreDetails = listQuestionScoreDetails;
    }
}
