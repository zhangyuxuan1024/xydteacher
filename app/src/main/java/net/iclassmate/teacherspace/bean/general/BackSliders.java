package net.iclassmate.teacherspace.bean.general;

/**
 * Created by xydbj on 2016.2.26.
             "classId": "7",
                    "className": "初三7班",
             "classOrder": 26,
                    "classOrderOffset": "-13",
                    "gradeOrder": 215,
                    "gradeOrderOffset": "-96",
             "lastClassOrder": 13,
             "lastGradeOrder": 119,
                    "offsetOrder": "--",
             "schoolId": 11270,
                    "score": 343,
             "studentId": 37045251,
                    "studentName": "陈一凡"
 */
public class BackSliders {
    private String classId;
    private String classOrder;
    private String lastClassOrder;
    private String lastGradeOrder;
    private String schoolId;
    private String studentId;
    private String className;
    private String studentName;
    private String score;
    private String gradeOrder;
    private String classOrderOffset;
    private String gradeOrderOffset;
    private String offsetOrder;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassOrder() {
        return classOrder;
    }

    public void setClassOrder(String classOrder) {
        this.classOrder = classOrder;
    }

    public String getLastClassOrder() {
        return lastClassOrder;
    }

    public void setLastClassOrder(String lastClassOrder) {
        this.lastClassOrder = lastClassOrder;
    }

    public String getLastGradeOrder() {
        return lastGradeOrder;
    }

    public void setLastGradeOrder(String lastGradeOrder) {
        this.lastGradeOrder = lastGradeOrder;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getGradeOrder() {
        return gradeOrder;
    }

    public void setGradeOrder(String gradeOrder) {
        this.gradeOrder = gradeOrder;
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

    @Override
    public String toString() {
        return "BackSliders{" +
                "classId='" + classId + '\'' +
                ", classOrder='" + classOrder + '\'' +
                ", lastClassOrder='" + lastClassOrder + '\'' +
                ", lastGradeOrder='" + lastGradeOrder + '\'' +
                ", schoolId='" + schoolId + '\'' +
                ", studentId='" + studentId + '\'' +
                ", className='" + className + '\'' +
                ", studentName='" + studentName + '\'' +
                ", score='" + score + '\'' +
                ", gradeOrder='" + gradeOrder + '\'' +
                ", classOrderOffset='" + classOrderOffset + '\'' +
                ", gradeOrderOffset='" + gradeOrderOffset + '\'' +
                ", offsetOrder='" + offsetOrder + '\'' +
                '}';
    }
}
