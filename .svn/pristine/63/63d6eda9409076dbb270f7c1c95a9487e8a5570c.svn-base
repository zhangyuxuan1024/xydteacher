package net.iclassmate.teacherspace.utils;

import android.os.AsyncTask;
import android.util.Log;

import net.iclassmate.teacherspace.bean.exam.ExamLists;
import net.iclassmate.teacherspace.constant.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by xydbj on 2016/3/10.
 */
public class LoadExamData extends AsyncTask<String, Void, String> {
    private String classCode;
    private String courseId;
    private String gradeId;
    private String roleId;
    private String schoolId;
    private String termCode;
    private String userId;
    private String enterYear;
    private DataCallback dataCallback;


    public LoadExamData(String classCode, String courseId, String gradeId, String roleId, String schoolId, String termCode,
                        String userId, String enterYear, DataCallback dataCallback) {
        this.classCode = classCode;
        this.courseId = courseId;
        this.gradeId = gradeId;
        this.roleId = roleId;
        this.schoolId = schoolId;
        this.termCode = termCode;
        this.userId = userId;
        this.enterYear = enterYear;
        this.dataCallback = dataCallback;
    }

    @Override
    protected String doInBackground(String... strings) {
        String result = "";
        if (strings[0] != null) {
            result = getExam(classCode, courseId, gradeId, roleId, schoolId, termCode, userId, enterYear);
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s == null || s.equals("") || s.equals("null")) {
            dataCallback.sendData(404);
            return;
        }
        try {
            JSONObject object = new JSONObject(s);
            int resultCode = -1;
            resultCode = object.getInt("resultCode");
            if (resultCode == 0) {
                ExamLists examLists = new ExamLists();
                examLists.parserJson(object);
                dataCallback.sendData(examLists);
                //Log.i("info","考试="+s);
            } else {
                dataCallback.sendData(404);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            dataCallback.sendData(404);
        }
    }

    /*
   * HttpUrlConnection Post请求
   * 石中玉
   * */
    public String getExam(String classCode, String courseId, String gradeId, String roleId, String schoolId, String termCode, String userId, String enterYear) {
        String result = "";
        String[] data1 = classCode.split(",");
        String[] data2 = courseId.split(",");
        String[] data3 = gradeId.split(",");
        String[] data4 = roleId.split(",");
        String[] data5 = schoolId.split(",");
        String[] data6 = termCode.split(",");
        String[] data7 = userId.split(",");
        String[] data8 = enterYear.split(",");

        JSONArray array = new JSONArray();
        JSONObject object = null;
        JSONObject jsonObject = null;
        try {
            for (int i = 0; i < data1.length; i++) {
                object = new JSONObject();
                object.put("classCode", data1[i]);
                object.put("courseId", Integer.parseInt(data2[i]));
                object.put("gradeId", Integer.parseInt(data3[i]));
                object.put("roleId", Integer.parseInt(data4[i]));
                object.put("schoolId", Integer.parseInt(data5[i]));
                object.put("termCode", Integer.parseInt(data6[i]));
                object.put("userId", data7[i]);
                object.put("enterYear", data8[i]);
                array.put(i, object);
            }
            jsonObject = new JSONObject();
            jsonObject.put("queryExamReqInfos", array.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String params = jsonObject.toString();
        params = params.replace("\\", "");
        params = params.replace("\"[", "[");
        params = params.replace("]\"", "]");

        //Log.i("info", "考试请求参数=" + params);
        result = FileUtils.read2Sd("xyd.dat");
        if (result != null && !result.equals("")) {
            if (result.contains(params) && result.contains("=-=")) {
                String[] data = null;
                if (result.contains("xydszyxyd")) {
                    data = result.split("xydszyxyd");
                    for (int i = 0; i < data.length; i++) {
                        if (data[i].contains(params)) {
                            result = data[i];
                            break;
                        }
                    }
                }
                data = result.split("=-=");
                result = data[1];
                return result;
            }
        }
        result = "";

        try {
            URL url = new URL(Constants.EXAM);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            //conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");

            //Log.i("info", "考试请求参数=" + params);
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(), "utf-8");
            osw.write(params);
            osw.flush();

            conn.setConnectTimeout(1000 * 100);
            conn.setReadTimeout(1000 * 100);
            //Log.i("info", "请求返回码=" + conn.getResponseCode());
            if (conn.getResponseCode() == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
                String str = "";
                while ((str = br.readLine()) != null) {
                    result += str;
                }
                br.close();
                conn.disconnect();

                String s = params + "=-=" + result;
                if (s.contains("\\")) {
                    s = s.replace("\\", "");
                    s = s.replace("\"[", "[");
                    s = s.replace("]\"", "]");
                }
                s = s + "xydszyxyd" + FileUtils.read2Sd("xyd.dat");
                FileUtils.write2Sd(s,"xyd.dat");
            } else {
                Log.i("info", "请求数据失败");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.i("info", "请求数据失败  MalformedURLException");
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("info", "请求数据失败  IOException");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
