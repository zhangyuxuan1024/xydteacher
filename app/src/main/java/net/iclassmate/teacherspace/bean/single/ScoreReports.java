package net.iclassmate.teacherspace.bean.single;

import net.iclassmate.teacherspace.bean.Parserable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by xydbj on 2016/2/24.
 */
public class ScoreReports implements Serializable, Parserable {
    /**
     * carelessIndex : 0.2
     * classOrder : 21
     * classOrderOffset : "--"
     * fullScore : 120
     * gradeOrderOffset : "--"
     * offsetOrder
     * score : 73
     * scoreRate : 60.83%
     * studentId : 37045076
     * studentName : 陈泽凯
     */
    private double carelessIndex;
    private int classOrder;
    private String classOrderOffset;
    private String gradeOrderOffset;
    private String offsetOrder;
    private int fullScore;
    private double score;
    private String scoreRate;
    private String studentId;
    private String studentName;
    private int essStatus;

    @Override
    public void parserJson(JSONObject jsonObject) {
        if (jsonObject != null) {
            try {
                carelessIndex = jsonObject.getDouble("carelessIndex");
                classOrder = jsonObject.getInt("classOrder");
                fullScore = jsonObject.getInt("fullScore");
                score = jsonObject.getDouble("score");
                scoreRate = jsonObject.getString("scoreRate");
                studentId = jsonObject.getString("studentId");
                studentName = jsonObject.getString("studentName");
                classOrderOffset = jsonObject.getString("classOrderOffset");
                gradeOrderOffset = jsonObject.getString("gradeOrderOffset");
                offsetOrder = jsonObject.getString("offsetOrder");
                essStatus = jsonObject.getInt("essStatus");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void setCarelessIndex(double carelessIndex) {
        this.carelessIndex = carelessIndex;
    }

    public void setClassOrder(int classOrder) {
        this.classOrder = classOrder;
    }

    public void setFullScore(int fullScore) {
        this.fullScore = fullScore;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public void setScoreRate(String scoreRate) {
        this.scoreRate = scoreRate;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public double getCarelessIndex() {
        return carelessIndex;
    }

    public int getClassOrder() {
        return classOrder;
    }

    public int getFullScore() {
        return fullScore;
    }

    public double getScore() {
        return score;
    }

    public String getScoreRate() {
        return scoreRate;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getClassOrderOffset() {
        return classOrderOffset;
    }

    public void setClassOrderOffset(String classOrderOffset) {
        this.classOrderOffset = classOrderOffset;
    }

    public String getGradeOrderOffset() {
        return gradeOrderOffset;
    }

    public void setGradeOrderOffset(String gradeOrderOffset) {
        this.gradeOrderOffset = gradeOrderOffset;
    }

    public String getOffsetOrder() {
        return offsetOrder;
    }

    public void setOffsetOrder(String offsetOrder) {
        this.offsetOrder = offsetOrder;
    }

    public int getEssStatus() {
        return essStatus;
    }

    public void setEssStatus(int essStatus) {
        this.essStatus = essStatus;
    }
}
