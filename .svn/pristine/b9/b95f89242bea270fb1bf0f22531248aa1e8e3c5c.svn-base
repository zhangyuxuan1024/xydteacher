package net.iclassmate.teacherspace.bean.exam;

import net.iclassmate.teacherspace.bean.Parserable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xydbj on 2016/3/9.
 */
public class MultiExamInfo implements Parserable, Serializable {
    /**
     * classId : -1
     * gradeId : 203
     * meId : 7
     * meName : 杭州10月阶段性测试6校联考
     * schoolId : 11270
     */

    private List<String> classIds;
    private String classId;
    private int gradeId;
    private int meId;
    private String meName;
    private int schoolId;
    private double multiExamAvg;
    private String gradeName;
    private String className;
    private String meDate;

    @Override
    public void parserJson(JSONObject jsonObject) {
        if (jsonObject != null) {
            try {
                classIds = new ArrayList<>();
                String ids = jsonObject.getString("classIds");
                if (ids != null && !ids.equals("--") && !ids.equals("") && !ids.equals("null")) {
                    JSONArray array = jsonObject.optJSONArray("classIds");
                    if (array != null) {
                        for (int i = 0; i < array.length(); i++) {
                            classIds.add(array.getString(i));
                        }
                    }
                }
                classId = jsonObject.optString("classId");
                gradeId = jsonObject.optInt("gradeId");
                meId = jsonObject.optInt("meId");
                meName = jsonObject.optString("meName");
                schoolId = jsonObject.optInt("schoolId");
                multiExamAvg = jsonObject.optDouble("multiExamAvg");
                gradeName = jsonObject.optString("gradeName");
                className = jsonObject.optString("className");
                meDate = jsonObject.getString("meDate");
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

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public double getMultiExamAvg() {
        return multiExamAvg;
    }

    public void setMultiExamAvg(double multiExamAvg) {
        this.multiExamAvg = multiExamAvg;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMeDate() {
        return meDate;
    }

    public void setMeDate(String meDate) {
        this.meDate = meDate;
    }

    public List<String> getClassIds() {
        return classIds;
    }

    public void setClassIds(List<String> classIds) {
        this.classIds = classIds;
    }
}
