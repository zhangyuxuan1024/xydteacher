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
public class ExamLists implements Parserable,Serializable{
  /*
    "resultCode": 0,
            "resultDesc": "成功"*/
    private int resultCode;
    private String resultDesc;
    private List<Exam> list;
    @Override
    public void parserJson(JSONObject jsonObject) {
        if (jsonObject != null) {
            try {
                resultCode = jsonObject.getInt("resultCode");
                resultDesc = jsonObject.getString("resultDesc");
                list = new ArrayList<>();
                JSONArray array =jsonObject.getJSONArray("examLists");
                for (int i = 0; i < array.length(); i++) {
                    Exam exam = new Exam();
                    exam.parserJson(array.getJSONObject(i));
                    list.add(exam);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public List<Exam> getList() {
        return list;
    }

    public void setList(List<Exam> list) {
        this.list = list;
    }
}
