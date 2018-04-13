package net.iclassmate.teacherspace.bean.single;

import net.iclassmate.teacherspace.bean.Parserable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by xydbj on 2016/2/24.
 */
public class QstAnalyzes implements Parserable, Serializable {
    /**
     * proficiency : 0.733
     * qstFullScore : 30
     * qstName : 选择题
     * qstScore : 21.99
     */

    private double proficiency;
    private double qstFullScore;
    private String qstName;
    private double qstScore;
    private double qstGradeScore;
    private double qstClasscore;
    private double classProficiency;
    private double gradeProficiency;

    @Override
    public void parserJson(JSONObject jsonObject) {
        if (jsonObject != null) {
            try {
                proficiency = jsonObject.getDouble("proficiency");
                qstFullScore = jsonObject.getDouble("qstFullScore");
                qstName = jsonObject.getString("qstName");
                qstScore = jsonObject.getDouble("qstScore");
                qstGradeScore = jsonObject.optDouble("qstGradeScore");
                qstClasscore = jsonObject.optDouble("qstClasscore");
                classProficiency = jsonObject.optDouble("classProficiency");
                gradeProficiency = jsonObject.optDouble("gradeProficiency");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public double getProficiency() {
        return proficiency;
    }

    public void setProficiency(double proficiency) {
        this.proficiency = proficiency;
    }

    public double getQstFullScore() {
        return qstFullScore;
    }

    public void setQstFullScore(double qstFullScore) {
        this.qstFullScore = qstFullScore;
    }

    public String getQstName() {
        return qstName;
    }

    public void setQstName(String qstName) {
        this.qstName = qstName;
    }

    public double getQstScore() {
        return qstScore;
    }

    public void setQstScore(double qstScore) {
        this.qstScore = qstScore;
    }

    public double getQstGradeScore() {
        return qstGradeScore;
    }

    public void setQstGradeScore(double qstGradeScore) {
        this.qstGradeScore = qstGradeScore;
    }

    public double getQstClasscore() {
        return qstClasscore;
    }

    public void setQstClasscore(double qstClasscore) {
        this.qstClasscore = qstClasscore;
    }

    public double getClassProficiency() {
        return classProficiency;
    }

    public void setClassProficiency(double classProficiency) {
        this.classProficiency = classProficiency;
    }

    public double getGradeProficiency() {
        return gradeProficiency;
    }

    public void setGradeProficiency(double gradeProficiency) {
        this.gradeProficiency = gradeProficiency;
    }
}
