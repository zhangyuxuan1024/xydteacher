package net.iclassmate.teacherspace.bean.general;

import java.io.Serializable;

/**
 * Created by xydbj on 2016.2.26.
 */
public class SingleExamInfos implements Serializable{
    private int courseId;
    private String courseName;
    private String flag;
    private float score;
    private int seFullScore;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getSeFullScore() {
        return seFullScore;
    }

    public void setSeFullScore(int seFullScore) {
        this.seFullScore = seFullScore;
    }

    @Override
    public String toString() {
        return "SingleExamInfos{" +
                "courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", flag='" + flag + '\'' +
                ", score='" + score + '\'' +
                ", seFullScore='" + seFullScore + '\'' +
                '}';
    }
}
