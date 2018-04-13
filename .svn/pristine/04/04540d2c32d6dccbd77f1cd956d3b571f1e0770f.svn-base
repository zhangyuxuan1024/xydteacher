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
public class GradeSituation implements Parserable, Serializable {
    private List<ExcellentPersons> listExcellentPersons;
    private List<RatesAndScores> listRatesAndScores;

    @Override
    public void parserJson(JSONObject jsonObject) {
        if (jsonObject != null) {
            listExcellentPersons = new ArrayList<>();
            listRatesAndScores = new ArrayList<>();
            try {
                JSONArray array1 = jsonObject.optJSONArray("excellentPersons");
                if (array1 != null && array1.length() > 0) {
                    for (int i = 0; i < array1.length(); i++) {
                        ExcellentPersons persons = new ExcellentPersons();
                        persons.parserJson(array1.getJSONObject(i));
                        listExcellentPersons.add(persons);
                    }
                }

                JSONArray array2 = jsonObject.optJSONArray("ratesAndScores");
                if (array2 != null && array2.length() > 0) {
                    for (int i = 0; i < array2.length(); i++) {
                        RatesAndScores scores = new RatesAndScores();
                        scores.parserJson(array2.getJSONObject(i));
                        listRatesAndScores.add(scores);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public List<ExcellentPersons> getListExcellentPersons() {
        return listExcellentPersons;
    }

    public void setListExcellentPersons(List<ExcellentPersons> listExcellentPersons) {
        this.listExcellentPersons = listExcellentPersons;
    }

    public List<RatesAndScores> getListRatesAndScores() {
        return listRatesAndScores;
    }

    public void setListRatesAndScores(List<RatesAndScores> listRatesAndScores) {
        this.listRatesAndScores = listRatesAndScores;
    }
}
