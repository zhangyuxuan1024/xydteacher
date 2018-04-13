package net.iclassmate.teacherspace.bean.single;

import net.iclassmate.teacherspace.bean.Parserable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by xydbj on 2016/2/24.
 */
public class Improvers implements Parserable, Serializable {
    /**
     * classOrderOffset : --
     * gradeOrderOffset : --
     * offsetOrder : --
     */

    private String classId;
    private String className;
    private int classOrder;
    private String classOrderOffset;
    private int gradeOrder;
    private String gradeOrderOffset;
    private int lastClassOrder;
    private int lastGradeOrder;
    private String offsetOrder;
    private int schoolId;
    private double score;
    private String studentId;
    private String studentName;
    @Override
    public void parserJson(JSONObject jsonObject) {
        if (jsonObject != null) {
            classId = jsonObject.optString("classId");
            className = jsonObject.optString("className");
            classOrder = jsonObject.optInt("classOrder");
            classOrderOffset = jsonObject.optString("classOrderOffset");
            gradeOrder = jsonObject.optInt("gradeOrder");
            gradeOrderOffset = jsonObject.optString("gradeOrderOffset");
            lastClassOrder = jsonObject.optInt("lastClassOrder");
            lastGradeOrder = jsonObject.optInt("lastGradeOrder");
            offsetOrder = jsonObject.optString("offsetOrder");
            schoolId = jsonObject.optInt("schoolId");
            score = jsonObject.optDouble("score");
            studentId = jsonObject.optString("studentId");
            studentName = jsonObject.optString("studentName");
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

    public int getLastClassOrder() {
        return lastClassOrder;
    }

    public void setLastClassOrder(int lastClassOrder) {
        this.lastClassOrder = lastClassOrder;
    }

    public int getLastGradeOrder() {
        return lastGradeOrder;
    }

    public void setLastGradeOrder(int lastGradeOrder) {
        this.lastGradeOrder = lastGradeOrder;
    }

    public String getOffsetOrder() {
        return offsetOrder;
    }

    public void setOffsetOrder(String offsetOrder) {
        this.offsetOrder = offsetOrder;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
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
}
