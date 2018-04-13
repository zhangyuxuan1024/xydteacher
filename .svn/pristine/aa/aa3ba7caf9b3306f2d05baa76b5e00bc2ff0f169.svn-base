package net.iclassmate.teacherspace.bean.single;

import net.iclassmate.teacherspace.bean.Parserable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by xydbj on 2016/2/24.
 */
public class Single implements Parserable, Serializable {
    private String classId;
    private String className;
    private SingleTotalSituation singleTotalSituation;
    private QuestionAnalyze questionAnalyze;
    private GradeSituation gradeSituation;
    private ClassSituation classSituation;
    private int  schoolId;
    private int meId;
    private  int seId;
    @Override
    public void parserJson(JSONObject jsonObject) {
        if (jsonObject != null) {
            try {
                classId = jsonObject.getString("classId");
                className = jsonObject.getString("className");

                singleTotalSituation = new SingleTotalSituation();
                singleTotalSituation.parserJson(jsonObject.getJSONObject("singleTotalSituation"));

                questionAnalyze = new QuestionAnalyze();
                questionAnalyze.parserJson(jsonObject.getJSONObject("questionAnalyze"));

                gradeSituation = new GradeSituation();
                gradeSituation.parserJson(jsonObject.getJSONObject("gradeSituation"));

                classSituation =  new ClassSituation();
                classSituation.parserJson(jsonObject.getJSONObject("classSituation"));

                schoolId = jsonObject.optInt("schoolId");
                meId = jsonObject.optInt("meId");
                seId = jsonObject.optInt("seId");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public SingleTotalSituation getSingleTotalSituation() {
        return singleTotalSituation;
    }

    public void setSingleTotalSituation(SingleTotalSituation singleTotalSituation) {
        this.singleTotalSituation = singleTotalSituation;
    }

    public QuestionAnalyze getQuestionAnalyze() {
        return questionAnalyze;
    }

    public void setQuestionAnalyze(QuestionAnalyze questionAnalyze) {
        this.questionAnalyze = questionAnalyze;
    }

    public GradeSituation getGradeSituation() {
        return gradeSituation;
    }

    public void setGradeSituation(GradeSituation gradeSituation) {
        this.gradeSituation = gradeSituation;
    }

    public ClassSituation getClassSituation() {
        return classSituation;
    }

    public void setClassSituation(ClassSituation classSituation) {
        this.classSituation = classSituation;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public int getMeId() {
        return meId;
    }

    public void setMeId(int meId) {
        this.meId = meId;
    }

    public int getSeId() {
        return seId;
    }

    public void setSeId(int seId) {
        this.seId = seId;
    }
}
