package net.iclassmate.teacherspace.bean.single;

import net.iclassmate.teacherspace.bean.Parserable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xydbj on 2016/2/24.
 */
public class ClassSituation implements Parserable,Serializable{
    private List<ScoreReports> list;
    @Override
    public void parserJson(JSONObject jsonObject) {
        if (jsonObject != null) {
            list = new ArrayList<>();
            try {
                JSONArray array = jsonObject.optJSONArray("scoreReports");
                if(array!=null && array.length()>0){
                    for (int i = 0; i < array.length(); i++) {
                        ScoreReports reports = new ScoreReports();
                        reports.parserJson(array.getJSONObject(i));
                        list.add(reports);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public List<ScoreReports> getList() {
        return list;
    }

    public void setList(List<ScoreReports> list) {
        this.list = list;
    }
}
