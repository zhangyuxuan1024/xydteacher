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
public class Exam implements Parserable, Serializable {
    private ExamInfo examInfo;
    private MultiExamInfo multiExamInfo;
    private List<SingleExamInfos> singleExamInfosesList;

    @Override
    public void parserJson(JSONObject jsonObject) {
        if (jsonObject != null) {
            examInfo = new ExamInfo();
            multiExamInfo = new MultiExamInfo();
            singleExamInfosesList = new ArrayList<>();
            SingleExamInfos infos = null;
            try {
                examInfo.parserJson(jsonObject.getJSONObject("examInfo"));
                multiExamInfo.parserJson(jsonObject.optJSONObject("multiExamInfo"));
                JSONArray array = jsonObject.getJSONArray("singleExamInfos");
                for (int i = 0; i < array.length(); i++) {
                    infos = new SingleExamInfos();
                    infos.parserJson(array.getJSONObject(i));
                    singleExamInfosesList.add(infos);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public ExamInfo getExamInfo() {
        return examInfo;
    }

    public void setExamInfo(ExamInfo examInfo) {
        this.examInfo = examInfo;
    }

    public MultiExamInfo getMultiExamInfo() {
        return multiExamInfo;
    }

    public void setMultiExamInfo(MultiExamInfo multiExamInfo) {
        this.multiExamInfo = multiExamInfo;
    }

    public List<SingleExamInfos> getSingleExamInfosesList() {
        return singleExamInfosesList;
    }

    public void setSingleExamInfosesList(List<SingleExamInfos> singleExamInfosesList) {
        this.singleExamInfosesList = singleExamInfosesList;
    }
}
