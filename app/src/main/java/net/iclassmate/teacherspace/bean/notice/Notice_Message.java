package net.iclassmate.teacherspace.bean.notice;

/**
 *
 * Created by xyd on 2016/3/18.
 */
public class Notice_Message {
    private Notice_Info notice_info;
    private int resultCode;
    private String resultDesc;
    public Notice_Message(){
        super();
    }

    public Notice_Message(Notice_Info notice_info, int resultCode, String resultDesc) {
        this.notice_info = notice_info;
        this.resultCode = resultCode;
        this.resultDesc = resultDesc;
    }

    public Notice_Info getNotice_info() {
        return notice_info;
    }

    public void setNotice_info(Notice_Info notice_info) {
        this.notice_info = notice_info;
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

    @Override
    public String toString() {
        return "Notice_Message{" +
                "notice_info=" + notice_info +
                ", resultCode=" + resultCode +
                ", resultDesc='" + resultDesc + '\'' +
                '}';
    }
}
