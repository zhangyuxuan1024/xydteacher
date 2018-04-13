package net.iclassmate.teacherspace.bean.single;

import android.util.Log;

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
public class SingleAll implements Serializable {
    private List<Single> list;

    public void parserJson(JSONArray jsonArray) {
        if (jsonArray != null) {
            list = new ArrayList<>();
            try {
                for (int i = 0; i < jsonArray.length(); i++) {
                    Single single = new Single();
                    single.parserJson(jsonArray.getJSONObject(i));
                    list.add(single);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Single> getList() {
        return list;
    }

    public void setList(List<Single> list) {
        this.list = list;
    }
}
