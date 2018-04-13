package net.iclassmate.teacherspace.bean.spaper;

import net.iclassmate.teacherspace.bean.Parserable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xydbj on 2016/3/23.
 */
public class StudentExamPapers implements Serializable, Parserable {
    private List<ExamPaperUrls> examPaperUrlsList;
    private List<ExamQuestionPapers> examQuestionPapersList;
    private String paperName;
    private int courseId;
    private double personScore;

    @Override
    public void parserJson(JSONObject jsonObject) {
        try {
            paperName = jsonObject.getString("paperName");
            courseId = jsonObject.getInt("courseId");
            personScore = jsonObject.getDouble("personScore");
            examPaperUrlsList = new ArrayList<>();

            JSONArray array1 = jsonObject.optJSONArray("examPaperUrls");
            if (array1 != null && array1.length() > 0) {
                for (int i = 0; i < array1.length(); i++) {
                    ExamPaperUrls examPaperUrls = new ExamPaperUrls();
                    examPaperUrls.parserJson(array1.getJSONObject(i));
                    examPaperUrlsList.add(examPaperUrls);
                }
            }

            examQuestionPapersList = new ArrayList<>();
            JSONArray array2 = jsonObject.optJSONArray("examQuestionPapers");
            if (array2 != null && array2.length() > 0) {
                for (int i = 0; i < array2.length(); i++) {
                    ExamQuestionPapers examQuestionPapers = new ExamQuestionPapers();
                    examQuestionPapers.parserJson(array2.getJSONObject(i));
                    examQuestionPapersList.add(examQuestionPapers);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public List<ExamPaperUrls> getExamPaperUrlsList() {
        return examPaperUrlsList;
    }

    public void setExamPaperUrlsList(List<ExamPaperUrls> examPaperUrlsList) {
        this.examPaperUrlsList = examPaperUrlsList;
    }

    public List<ExamQuestionPapers> getExamQuestionPapersList() {
        return examQuestionPapersList;
    }

    public void setExamQuestionPapersList(List<ExamQuestionPapers> examQuestionPapersList) {
        this.examQuestionPapersList = examQuestionPapersList;
    }

    public double getPersonScore() {
        return personScore;
    }

    public void setPersonScore(double personScore) {
        this.personScore = personScore;
    }
}
