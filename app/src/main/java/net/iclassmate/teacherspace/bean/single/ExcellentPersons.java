package net.iclassmate.teacherspace.bean.single;

import net.iclassmate.teacherspace.bean.Parserable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by xydbj on 2016/2/24.
 */
public class ExcellentPersons implements Parserable, Serializable {
    /**
     * classAvgScore
     * classId
     * className : 初三1班
     * classOrder : 10
     * classOrderOffset : 9
     * gradeOrder : 142
     * gradeOrderOffset : 65
     * offsetOrder : --
     * score : 86
     * studentId : 37045082
     * studentName : 常世龙
     */

    private double classAvgScore;
    private String classId;
    private String className;
    private int classOrder;
    private String classOrderOffset;
    private int gradeOrder;
    private String gradeOrderOffset;
    private String offsetOrder;
    private double score;
    private String studentId;
    private String studentName;

    @Override
    public void parserJson(JSONObject jsonObject) {
        if (jsonObject != null) {
            try {
                classAvgScore = jsonObject.optDouble("classAvgScore");
                classId = jsonObject.getString("classId");
                className = jsonObject.getString("className");
                classOrder = jsonObject.getInt("classOrder");
                classOrderOffset = jsonObject.getString("classOrderOffset");
                gradeOrder = jsonObject.getInt("gradeOrder");
                gradeOrderOffset = jsonObject.getString("gradeOrderOffset");
                offsetOrder = jsonObject.getString("offsetOrder");
                score = jsonObject.getDouble("score");
                studentId = jsonObject.getString("studentId");
                studentName = jsonObject.getString("studentName");
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

    public int getClassOrder() {
        return classOrder;
    }

    public void setClassOrder(int classOrder) {
        this.classOrder = classOrder;
    }

    public String getClassOrderOffset() {
        return classOrderOffset;
    }

    public void setClassOrderOffset(String classOrderOffset) {
        this.classOrderOffset = classOrderOffset;
    }

    public int getGradeOrder() {
        return gradeOrder;
    }

    public void setGradeOrder(int gradeOrder) {
        this.gradeOrder = gradeOrder;
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

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public double getClassAvgScore() {
        return classAvgScore;
    }

    public void setClassAvgScore(double classAvgScore) {
        this.classAvgScore = classAvgScore;
    }
}
