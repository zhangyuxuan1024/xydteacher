package net.iclassmate.teacherspace.bean.weike;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xydbj on 2016/5/13.
 */
public class Video {
    private String videoPath;
    private String audioPath;
    private String videoName;
    private String videoTime;
    private String videoLong;
    private boolean isCheck;

    public String getAudioPath() {
        return audioPath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoTime() {
        return videoTime;
    }

    public void setVideoTime(String videoTime) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = null;
        try {
            curDate = new Date(Long.parseLong(videoTime));
        } catch (Exception e) {
            curDate = new Date(System.currentTimeMillis());
        }
        this.videoTime = formatter.format(curDate);
    }

    public String getVideoLong() {
        return videoLong;
    }

    public void setVideoLong(String videoLong) {
        try {
            long time = Long.parseLong(videoLong);
            time = time / 1000;
            long a = time / 60;
            long b = time % 60;
            String f = "", s = "";
            if (a < 10) {
                f = "0" + a;
            } else {
                f = a + "";
            }
            if (b < 10) {
                s = "0" + b;
            } else {
                s = b + "";
            }
            this.videoLong = f + ":" + s;
        } catch (Exception e) {
            this.videoLong = "00:00";
        }
    }
}
