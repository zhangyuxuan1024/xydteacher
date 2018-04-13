package net.iclassmate.teacherspace.bean.general;

/**
 * Created by xydbj on 2016.2.26.
 */
public class ExcellentPersons {
    private String classAvgScore;
    private String classId;
    private String className;
    private String classOrder;
    private String classOrderOffset;
    private String gradeOrder;
    private String gradeOrderOffset;
    private String offsetOrder;
    private String schoolId;
    private String score;
    private String studentId;
    private String studentName;

    public String getClassAvgScore() {
        return classAvgScore;
    }

    public void setClassAvgScore(String classAvgScore) {
        this.classAvgScore = classAvgScore;
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

    public String getClassOrder() {
        return classOrder;
    }

    public void setClassOrder(String classOrder) {
        this.classOrder = classOrder;
    }

    public String getClassOrderOffset() {
        return classOrderOffset;
    }

    public void setClassOrderOffset(String classOrderOffset) {
        this.classOrderOffset = classOrderOffset;
    }

    public String getGradeOrder() {
        return gradeOrder;
    }

    public void setGradeOrder(String gradeOrder) {
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

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
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

    @Override
    public String toString() {
        return "ExcellentPersons{" +
                "classAvgScore='" + classAvgScore + '\'' +
                ", classId='" + classId + '\'' +
                ", className='" + className + '\'' +
                ", classOrder='" + classOrder + '\'' +
                ", classOrderOffset='" + classOrderOffset + '\'' +
                ", gradeOrder='" + gradeOrder + '\'' +
                ", gradeOrderOffset='" + gradeOrderOffset + '\'' +
                ", offsetOrder='" + offsetOrder + '\'' +
                ", schoolId='" + schoolId + '\'' +
                ", score='" + score + '\'' +
                ", studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                '}';
    }
}
