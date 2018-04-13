package net.iclassmate.teacherspace.utils;

import android.util.Log;
import android.widget.Button;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import net.iclassmate.teacherspace.bean.exam.ExamLists;
import net.iclassmate.teacherspace.bean.single.SingleAll;
import net.iclassmate.teacherspace.bean.spaper.StudentExam;
import net.iclassmate.teacherspace.constant.Constants;

import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by xydbj on 2016/1/27.
 */
public class HttpManger {
    private DataCallback dataCallback;

    public HttpManger(DataCallback dataCallback) {
        this.dataCallback = dataCallback;
    }
    //请求网络数据

    /*石中玉
    *请求单科网络数据
    *http:// x.x.x.x:port/querysingleexam/{meId}/{seId}/{schoolId}/{classIds}
    */
    public void getSingleData(String meId, String seId, String schoolId, String classIds) {
        HttpUtils httpUtils = new HttpUtils();
        Log.i("info", "请求网址=" + String.format(Constants.SINGLE_LESSON2, meId, seId, schoolId, classIds));
        httpUtils.send(HttpRequest.HttpMethod.GET, String.format(Constants.SINGLE_LESSON2, meId, seId, schoolId, classIds), new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String ret = responseInfo.result;
                try {
                    JSONObject object = new JSONObject(ret);
                    int resultCode = object.getInt("resultCode");
                    if (resultCode == 0) {
                        JSONArray array = object.getJSONArray("singleExams");
                        SingleAll singleAll = new SingleAll();
                        Log.i("info", "数据长度=" + array.length());
                        singleAll.parserJson(array);
                        dataCallback.sendData(singleAll);
                        Log.i("info", "请求成功" + ret);
                    } else {
                        dataCallback.sendData(404);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.sendData(404);
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Log.i("info", "请求失败" + e.getMessage());
                dataCallback.sendData(404);
            }
        });
    }

    /*石中玉
     *请求试卷
     *http:// x.x.x.x:port /getmessage/{schoolId}/{userId}
     */
    public void getTestPaper(String schoolId, String meId, String seId, String studentId) {
        HttpUtils httpUtils = new HttpUtils();
        Log.i("info", "请求试卷网址=" + String.format(Constants.TEST_PAPER, schoolId, meId, seId, studentId));
        httpUtils.send(HttpRequest.HttpMethod.GET, String.format(Constants.TEST_PAPER, schoolId, meId, seId, studentId), new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String ret = responseInfo.result;
                //Log.i("info", "试卷信息=" + ret);
                try {
                    JSONObject object = new JSONObject(ret);
                    int resultCode = object.getInt("resultCode");
                    if (resultCode == 0) {
                        StudentExam studentExamPapers = new StudentExam();
                        studentExamPapers.parserJson(object);
                        dataCallback.sendData(studentExamPapers);
                        //Log.i("info", "请求成功" + ret);
                    } else {
                        dataCallback.sendData(404);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    dataCallback.sendData(404);
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Log.i("info", "请求失败" + e.getMessage());
                dataCallback.sendData(404);
            }
        });
    }

    /*刘学伟
    *请求通知界面网络数据
    *http:// x.x.x.x:port/ /getmessage/{schoolId}/{userId}
    */
    public void getNoticeData(String schoolId, String userId) {
        HttpUtils httpUtils = new HttpUtils();
        String url = Constants.NOTICE_URL + "/" + schoolId + "/" + userId;
        Log.i("通知界面接口", url);
                httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        String result = responseInfo.result;
                        Log.i("通知界面数据", result);
                        FileUtils.write2Sd(result,"xyd_notice.dat");
                        dataCallback.sendData(result);
                    }
                    @Override
                    public void onFailure(HttpException error, String msg) {
                        dataCallback.sendData(404);
                    }
                });

    }

    /**
     * 刘学伟
     * 短信验证码
     * http:// x.x.x.x:port/getverifycode/{phoneNum}
     */
    public void getSMSData(String phoneNumber, int optionType, String userCode, final Button button) {
        HttpUtils httpUtils = new HttpUtils();
        String url = Constants.SMS_URL +"/" + userCode + "/" + phoneNumber + "/" + optionType;
        Log.i("短信验证码接口", url);
        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                Log.i("短信验证码", result);
                try {
                    JSONObject jsonObject=new JSONObject(result);
                    int resultCode=jsonObject.getInt("resultCode");
                    if (resultCode==-2){
                        ToastUtils.show(UIUtils.getContext(),"手机号码已被使用");
                    }else if (resultCode == -1) {
                        ToastUtils.show(UIUtils.getContext(), "验证码错误或失效");
                    } else if (resultCode==-3){
                        ToastUtils.show(UIUtils.getContext(), "手机号码不匹配");
                    }else if (resultCode==0){
                        TimeUtils time = new TimeUtils(button, "重新获取");
                        time.RunTimer();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(HttpException error, String msg) {
//                dataCallback.sendData(404);
                ToastUtils.show(UIUtils.getContext(), "服务器异常，请稍后再试");
            }
        });

    }

    /**
     * 刘学伟
     * 验证验证码
     */
    public void ifCodeRight(String phoneNumber, String verifyCode, String userCode, int optionType) {
        HttpUtils httpUtils = new HttpUtils();
        String url = Constants.SMS_VERIFY_URL;
        Log.i("验证验证码接口", url);
        JSONObject json = new JSONObject();

        try {
            json.put("phoneNum", phoneNumber);
            json.put("verifyCode", verifyCode);
            json.put("userCode", userCode);
            json.put("optionType", optionType);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String jsonstr = json.toString();
        RequestParams params = new RequestParams();
        try {
            params.setBodyEntity(new StringEntity(jsonstr));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Log.i("验证验证码", params + "");

        httpUtils.send(HttpRequest.HttpMethod.POST, url, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                Log.i("验证验证码", result);
                dataCallback.sendData(result);
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                Log.i("错误信息",msg);
                dataCallback.sendData(404);
            }
        });


    }

    /**
     * 刘学伟
     * 修改密码
     * http:// x.x.x.x:port/changeorresetpwd/{userCode}/{newPassword}/{confirmPassword}
     */
    public void changeNewPWD(String userCode, String newPassword, String confirmPassword) {
        HttpUtils httpUtils = new HttpUtils();
        String url = Constants.CHANGE_PWD_URL + "/" + userCode + "/" + newPassword + "/" + confirmPassword;
        Log.i("修改密码接口", url);
        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result = responseInfo.result;
                Log.i("修改密码", result);
                dataCallback.sendData(result);
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                dataCallback.sendData(404);
            }
        });

    }

    /**
     * 刘学伟
     * 更新版本
     *
     */
    public void updateAPK(){
        HttpUtils httpUtils=new HttpUtils();
        String update_url=Constants.UPDATE_URL;
        Log.i("版本更新接口",update_url);
        httpUtils.send(HttpRequest.HttpMethod.GET, update_url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result=responseInfo.result;
                Log.i("版本更新数据", result);
                dataCallback.sendData(result);
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                dataCallback.sendData(404);
            }
        });
    }

    public void sendUserCode(String userCode){
        HttpUtils httpUtils=new HttpUtils();
        String update_url=Constants.SENDUSERCODE_URL+"/"+userCode;
        Log.i("上传账号",update_url);
        httpUtils.send(HttpRequest.HttpMethod.GET, update_url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String result=responseInfo.result;
                Log.i("上传账号", result);
                dataCallback.sendData(result);
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                dataCallback.sendData(404);
            }
        });
    }


    /*
    * 请求考试界面数据 POST
    * userId  schoolId gradeId classCode courseId termCode roleId
    * String  Integer  Integer String    Integer  Integer  Integer
    * */
    public void getExamData(String classCode, String courseId, String gradeId, String roleId, String schoolId, String termCode, String userId) {
        String[] data1 = classCode.split(",");
        String[] data2 = courseId.split(",");
        String[] data3 = gradeId.split(",");
        String[] data4 = roleId.split(",");
        String[] data5 = schoolId.split(",");
        String[] data6 = termCode.split(",");
        String[] data7 = userId.split(",");

        JSONArray array = new JSONArray();
        JSONObject object = null;
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
                array.put(i, object);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestParams params = new RequestParams();
        params.addHeader("Content-Type", "application/json;charset=UTF-8");
        //params.setContentType("application/json");
        //params.setBodyEntity(new StringEntity(array.toString(),"utf-8"));
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("queryExamReqInfos", array.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String request = jsonObject.toString();
        request = request.replace("\\", "");
        request = request.replace("\"[", "[");
        request = request.replace("]\"", "]");

        params.addBodyParameter("", request);

        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.POST, Constants.EXAM, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                String ret = responseInfo.result;
                Log.i("info", "请求成功" + ret);
                try {
                    JSONObject object = new JSONObject(ret);
                    int resultCode = object.getInt("resultCode");
                    if (resultCode == 0) {
                        JSONArray array = object.getJSONArray("examLists");
                        ExamLists examLists = new ExamLists();
                        Log.i("info", "数据长度=" + array.length());
                        examLists.parserJson(object);
                        dataCallback.sendData(examLists);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Log.i("info", "请求失败=" + e.getMessage());
            }
        });
    }

}
