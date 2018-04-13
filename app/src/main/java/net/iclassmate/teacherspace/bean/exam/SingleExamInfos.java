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
public class SingleExamInfos implements Serializable, Parserable {
    /**
     * classId : -1
     * courseId : -1
     * courseName : 数学
     * gradeId : 203
     * meId : 7
     * schoolId : 11270
     * seId : 31
     * seName : 杭州10月阶段性测试6校联考_数学
     */

    private List<String> classIds;
    private String classId;
    private int courseId;
    private String courseName;
    private int gradeId;
    private int meId;
    private int schoolId;
    private int seId;
    private String seName;
    private String seDate;
    private int flag;

    //通知界面需要
    private String msgId;
    private int isReaded;

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
                classId = jsonObject.getString("classId");
                courseId = jsonObject.getInt("courseId");
                courseName = jsonObject.getString("courseName");
                gradeId = jsonObject.optInt("gradeId");
                meId = jsonObject.getInt("meId");
                schoolId = jsonObject.getInt("schoolId");
                seId = jsonObject.getInt("seId");
                seName = jsonObject.getString("seName");
                seDate = jsonObject.getString("seDate");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public void setMeId(int meId) {
        this.meId = meId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public void setSeId(int seId) {
        this.seId = seId;
    }

    public void setSeName(String seName) {
        this.seName = seName;
    }

    public List<String> getClassIds() {
        return classIds;
    }

    public void setClassIds(List<String> classIds) {
        this.classIds = classIds;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getGradeId() {
        return gradeId;
    }

    public int getMeId() {
        return meId;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public int getSeId() {
        return seId;
    }

    public String getSeName() {
        return seName;
    }

    public String getSeDate() {
        return seDate;
    }

    public void setSeDate(String seDate) {
        this.seDate = seDate;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public int getIsReaded() {
        return isReaded;
    }

    public void setIsReaded(int isReaded) {
        this.isReaded = isReaded;
    }
}
