package net.iclassmate.teacherspace.utils;

import android.os.AsyncTask;
import android.util.Log;

import net.iclassmate.teacherspace.bean.single.SingleAll;
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
public class LoadSingleData extends AsyncTask<String, Void, String> {
    private String meId;
    private String seId;
    private String schoolId;
    private String classIds;
    private String msgId;
    private int isReaded;
    private DataCallback dataCallback;

    public LoadSingleData(String meId, String seId, String schoolId, String classIds, DataCallback dataCallback) {
        this.meId = meId;
        this.seId = seId;
        this.schoolId = schoolId;
        this.classIds = classIds;
        this.dataCallback = dataCallback;
    }

    public LoadSingleData(String meId, String seId, String schoolId, String classIds, String msgId, int isReaded, DataCallback dataCallback) {
        this.meId = meId;
        this.seId = seId;
        this.schoolId = schoolId;
        this.classIds = classIds;
        this.msgId = msgId;
        this.isReaded = isReaded;
        this.dataCallback = dataCallback;
    }

    @Override
    protected String doInBackground(String... strings) {
        String result = "";
        if (strings[0] != null) {
            if (msgId == null || isReaded == 1) {
                result = getSingle(meId, seId, schoolId, classIds);
            } else {
                result = getSingle(meId, seId, schoolId, classIds, msgId, isReaded);
            }
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.i("info", "单科数据=" + s);
        if (s == null || s.equals("") || s.equals("null")) {
            dataCallback.sendData(404);
            return;
        }
        try {
            JSONObject object = new JSONObject(s);
            int resultCode = -1;
            resultCode = object.getInt("resultCode");
            if (resultCode == 0) {
                JSONArray array = object.getJSONArray("singleExams");
                SingleAll singleAll = new SingleAll();
                singleAll.parserJson(array);
                dataCallback.sendData(singleAll);
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
   * http:// x.x.x.x:port/querysingleexam/{meId}/{seId}/{schoolId}/{classIds}
   * */
    public String getSingle(String meId, String seId, String schoolId, String classIds) {
        String result = "";
        JSONObject object = new JSONObject();
        try {
            object.put("meId", meId);
            object.put("seId", seId);
            object.put("schoolId", schoolId);
            JSONArray array = new JSONArray();
            String[] ret = classIds.split(",");
            for (int i = 0; i < ret.length; i++) {
                array.put(ret[i]);
            }
            object.put("classIds", array);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            String params = object.toString();
            Log.i("info", "考试请求参数=" + params);
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
            URL url = new URL(Constants.SINGLE_LESSON);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");

            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(), "utf-8");
            osw.write(params);
            osw.flush();

            conn.setConnectTimeout(1000 * 100);
            conn.setReadTimeout(1000 * 100);
            Log.i("info", "请求返回码=" + conn.getResponseCode());
            if (conn.getResponseCode() == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
                String str = "";
                while ((str = br.readLine()) != null) {
                    result += str;
                }

                String s = params + "=-=" + result;
                if (s.contains("\\")) {
                    s = s.replace("\\", "");
                    s = s.replace("\"[", "[");
                    s = s.replace("]\"", "]");
                }
                br.close();
                conn.disconnect();

                s = s + "xydszyxyd" + FileUtils.read2Sd("xyd.dat");
                FileUtils.write2Sd(s,"xyd.dat");
                //Log.i("info", "写入的内容=" + s);
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

    public String getSingle(String meId, String seId, String schoolId, String classIds, String msgId, int isReaded) {
        String result = "";
        JSONObject object = new JSONObject();
        try {
            object.put("meId", meId);
            object.put("seId", seId);
            object.put("schoolId", schoolId);
            JSONArray array = new JSONArray();
            String[] ret = classIds.split(",");
            for (int i = 0; i < ret.length; i++) {
                array.put(ret[i]);
            }
            object.put("classIds", array);
            object.put("msgId", msgId);
            object.put("isReaded", isReaded);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            String params = object.toString();
            Log.i("info", "考试请求参数=" + params);
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

            URL url = new URL(Constants.SINGLE_LESSON);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");

            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(), "utf-8");
            osw.write(params);
            osw.flush();

            conn.setConnectTimeout(1000 * 100);
            conn.setReadTimeout(1000 * 100);
            Log.i("info", "请求返回码=" + conn.getResponseCode());
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
