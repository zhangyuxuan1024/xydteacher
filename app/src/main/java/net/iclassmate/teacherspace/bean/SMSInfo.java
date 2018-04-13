package net.iclassmate.teacherspace.bean;

/**
 *
 * Created by xyd on 2016/3/29.
 */
public class SMSInfo {
//    {"optionType":3,"resultCode":0,"resultDesc":"操作成功"}
    private int resultCode;
    private String resultDesc;
    private String phoneNum;

    public SMSInfo(int resultCode,String resultDesc,String phoneNum){
        this.resultCode=resultCode;
        this.resultDesc=resultDesc;
        this.phoneNum=phoneNum;
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

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
