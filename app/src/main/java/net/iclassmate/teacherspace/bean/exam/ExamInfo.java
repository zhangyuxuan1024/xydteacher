package net.iclassmate.teacherspace.bean.exam;

import net.iclassmate.teacherspace.bean.Parserable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by xydbj on 2016/3/9.
 */
public class ExamInfo implements Parserable, Serializable {
    /**
     * gradeId : 203
     * meId : 7
     * meName : 杭州10月阶段性测试6校联考
     * multiExamAvg : 325.02
     */
    private int gradeId;
    private int meId;
    private String meName;
    private double multiExamAvg;
    private int schoolId;
    private String gradeName;
    private String classId;
    private String className;

    @Override
    public void parserJson(JSONObject jsonObject) {
        if (jsonObject != null) {
            try {
                gradeId = jsonObject.optInt("gradeId");
                meId = jsonObject.optInt("meId");
                meName = jsonObject.getString("meName");
                multiExamAvg = jsonObject.getDouble("multiExamAvg");
                schoolId = jsonObject.optInt("schoolId");
                gradeName = jsonObject.getString("gradeName");
                classId = jsonObject.getString("classId");
                className = jsonObject.getString("className");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public int getMeId() {
        return meId;
    }

    public void setMeId(int meId) {
        this.meId = meId;
    }

    public String getMeName() {
        return meName;
    }

    public void setMeName(String meName) {
        this.meName = meName;
    }

    public double getMultiExamAvg() {
        return multiExamAvg;
    }

    public void setMultiExamAvg(double multiExamAvg) {
        this.multiExamAvg = multiExamAvg;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
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
}
