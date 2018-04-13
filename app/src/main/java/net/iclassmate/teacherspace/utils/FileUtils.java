package net.iclassmate.teacherspace.utils;

import android.os.Environment;
import android.util.Log;

import net.iclassmate.teacherspace.constant.Constants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by xydbj on 2016/3/26.
 */
public class FileUtils {
    public static int CACHE_TIME = 10 * 60 * 1000;
    public static final String ISO_8859_1 = "ISO-8859-1";
    public static final String UTF_8 = "UTF-8";
    public static final String US_ASCII = "US-ASCII";

    /*
    * 字符串写入SD卡
    * */
    public static boolean write2Sd(String str, String fileName) {
        boolean result = false;
        String filePath = getSdCardPath();
        try {
            if (filePath == null || filePath == "" || filePath.equals("null")) {
                return false;
            }
            File file = new File(filePath, fileName);
            if (!file.exists()) {
                file.createNewFile();
            } else {
                file.delete();
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(str.getBytes());
            fos.close();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    /*
    * 从SD卡中读取字符串
    * */
    public static String read2Sd(String fileName) {
        String result = "";
        try {
            String filePath = getSdCardPath();
            if (filePath == null || filePath == "" || filePath.equals("null")) {
                return "";
            }
            File file = new File(filePath, fileName);
            if (!file.exists()) {
                return result;
            }
            if (NetWorkUtils.isNetworkAvailable(UIUtils.getContext()) && System.currentTimeMillis() - file.lastModified() > CACHE_TIME) {
                file.delete();
                return result;
            }
            FileInputStream is = new FileInputStream(file);
            byte[] bytes = new byte[is.available()];
            is.read(bytes);
            result = new String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 判断SDCard是否存在 [当没有外挂SD卡时，内置ROM也被识别为存在sd卡]
     *
     * @return
     */
    public static boolean isSdCardExist() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取读写SD路径
     *
     * @return
     */
    public static String getSdCardPath() {
        boolean exist = isSdCardExist();
//        FileUtils.clear();
        String sdpath = "";
        if (exist) {
            File f = Environment.getExternalStorageDirectory();
            String path = f.getPath() + "/" + Constants.APP_CACHE_NAME;
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            sdpath = file.getAbsolutePath();
        }
        return sdpath;

    }

    /*
    * 清空文件夹
    * */
    public static boolean clear() {
        boolean result = false;
        File file = new File(getSdCardPath());
        try {
            deleteAllFiles(file);
            result = true;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    /*
    * 删除文件
    * */
    public static void deleteAllFiles(File file) {
        File files[] = file.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    // 判断是否为文件夹
                    deleteAllFiles(f);
                } else {
                    f.delete();
                }
            }
        }
    }
}
