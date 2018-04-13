package net.iclassmate.teacherspace.bean.general;

/**
 * Created by xydbj on 2016.2.26.
 */
public class ScoreReports {
    private String classOrder;
    private String classOrderOffset;
    private String fullScore;
    private String gradeOrderOffset;
    private String offsetOrder;
    private String score;
    private String scoreRate;
    private String studentId;
    private String studentName;

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

    public String getFullScore() {
        return fullScore;
    }

    public void setFullScore(String fullScore) {
        this.fullScore = fullScore;
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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getScoreRate() {
        return scoreRate;
    }

    public void setScoreRate(String scoreRate) {
        this.scoreRate = scoreRate;
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
        return "scoreReports{" +
                "classOrder='" + classOrder + '\'' +
                ", classOrderOffset='" + classOrderOffset + '\'' +
                ", fullScore='" + fullScore + '\'' +
                ", gradeOrderOffset='" + gradeOrderOffset + '\'' +
                ", offsetOrder='" + offsetOrder + '\'' +
                ", score='" + score + '\'' +
                ", scoreRate='" + scoreRate + '\'' +
                ", studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                '}';
    }
}
