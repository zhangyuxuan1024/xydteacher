package net.iclassmate.teacherspace.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;

import net.iclassmate.teacherspace.R;
import net.iclassmate.teacherspace.bean.spaper.ExamQuestionPapers;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xydbj on 2016/4/12.
 */
public class MyImageTask extends AsyncTask<String, Void, Bitmap> {
    private Context context;
    private ImageView iv;
    private String path;
    private LruCache<String, Bitmap> cache;
    private List<ExamQuestionPapers> listPagers;
    private double totalscore;
    private int position;
    private DataCallback dataCallback;

    public MyImageTask(Context context, ImageView iv, String path,
                       LruCache<String, Bitmap> cache, List<ExamQuestionPapers> listPagers, double totalscore, int position, DataCallback dataCallback) {
        this.context = context;
        this.iv = iv;
        this.path = path;
        this.cache = cache;
        this.listPagers = listPagers;
        this.totalscore = totalscore;
        this.position = position;
        this.dataCallback = dataCallback;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap bm = null;
        path = params[0];
        if (params[0] != null) {
            if (cache.get(params[0]) != null) {
                bm = cache.get(params[0]);
            } else {
                bm = getBitmap(params[0]);
                if (null != bm) {
                    cache.put(path, bm);
                }
            }
        }
        return bm;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);
        if (null == result) {
            dataCallback.sendData(404);
            return;
        }
        try {
            if (iv.getTag().equals(path)) {
                Bitmap newBitmap = drawBitmap(result, position);
                iv.setImageBitmap(newBitmap);
            }
        } catch (Exception e) {
            Log.i("info", "请求图片失败！");
            dataCallback.sendData(404);
        }
    }

    public Bitmap getBitmap(String path) {
        Bitmap bm = null;
        HttpURLConnection conn = null;
        try {
            URL url = new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            InputStream is = conn.getInputStream();
            bm = BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bm;
    }

    private Bitmap drawBitmap(Bitmap back, int pageindex) {
        ArrayList<ExamQuestionPapers> currentpagescrolist = new ArrayList<>();
        for (int i = 0; i < listPagers.size(); i++) {
            ExamQuestionPapers papers = listPagers.get(i);
            int page = Integer.parseInt(papers.getEcPage());
            if (page == pageindex) {
                currentpagescrolist.add(papers);
            }
        }
        String[] data1 = null;
        String[] data2 = null;
        List<Integer> x = new ArrayList<>();
        List<Integer> y = new ArrayList<>();
        List<Integer> w = new ArrayList<>();
        List<Integer> h = new ArrayList<>();
        List<Double> listScore = new ArrayList<>();
        List<Integer> flags = new ArrayList<>();
        for (int i = 0; i < currentpagescrolist.size(); i++) {
            ExamQuestionPapers papers = currentpagescrolist.get(i);
            String answerIpxywh = papers.getAnswerIpxywh();
            double score = papers.getScore();
            double fullScore = papers.getFullScore();
            if (answerIpxywh.contains("#")) {
                data1 = answerIpxywh.split("#");
                for (int j = 0; j < 1; j++) {
                    data2 = data1[j].split(",");
                    x.add(Integer.parseInt(data2[1]));
                    y.add(Integer.parseInt(data2[2]));
                    w.add(Integer.parseInt(data2[3]));
                    h.add(Integer.parseInt(data2[4]));
                    listScore.add(score);
                    if (fullScore == score) {
                        flags.add(1);
                    } else if (score > 0) {
                        flags.add(2);
                    } else if (score == 0) {
                        flags.add(3);
                    }
                }
            } else {
                data2 = answerIpxywh.split(",");
                x.add(Integer.parseInt(data2[2]));
                y.add(Integer.parseInt(data2[3]));
                w.add(Integer.parseInt(data2[4]));
                h.add(Integer.parseInt(data2[5]));
                listScore.add(score);
                if (fullScore == score) {
                    flags.add(1);
                } else if (score > 0) {
                    flags.add(2);
                } else if (score == 0) {
                    flags.add(3);
                }
            }
        }
        Bitmap right = BitmapFactory.decodeResource(context.getResources(),
                R.mipmap.right);
        Bitmap r_w = BitmapFactory.decodeResource(context.getResources(),
                R.mipmap.r_w);
        Bitmap wrong = BitmapFactory.decodeResource(context.getResources(),
                R.mipmap.wrong);

        int bgWidth = back.getWidth();
        int bgHeight = back.getHeight();
        Bitmap newbmp = Bitmap.createBitmap(bgWidth, bgHeight, Bitmap.Config.RGB_565);
        Canvas cv = new Canvas(newbmp);
        cv.drawBitmap(back, 0, 0, null);

        for (int i = 0; i < flags.size(); i++) {
            //Log.i("info", "画图" + flags.get(i) + ",长度=" + flags.size());
            if (flags.get(i) == 1) {
                float scaleWidth = ((float) w.get(i)) / right.getWidth();
                float scaleHeight = ((float) h.get(i)) / right.getHeight();
                Matrix matrix = new Matrix();
                matrix.postScale(scaleWidth, scaleHeight);
                Bitmap newR = Bitmap.createBitmap(right, 0, 0,
                        right.getWidth(), right.getHeight(), matrix, true);
                cv.drawBitmap(newR, x.get(i), y.get(i), null);
                drawScore(cv, listScore.get(i), x.get(i), y.get(i), matrix, scaleWidth,
                        scaleHeight, newR.getWidth());
                newR.recycle();
                newR = null;
            } else if (flags.get(i) == 2) {
                float scaleWidth = ((float) w.get(i)) / r_w.getWidth();
                float scaleHeight = ((float) h.get(i)) / r_w.getHeight();
                Matrix matrix = new Matrix();
                matrix.postScale(scaleWidth, scaleHeight);
                Bitmap newr_w = Bitmap.createBitmap(r_w, 0, 0, r_w.getWidth(),
                        r_w.getHeight(), matrix, true);
                cv.drawBitmap(newr_w, x.get(i), y.get(i), null);
                drawScore(cv, listScore.get(i), x.get(i), y.get(i), matrix, scaleWidth,
                        scaleHeight, newr_w.getWidth());
                newr_w.recycle();
                newr_w = null;
            } else if (flags.get(i) == 3) {
                float scaleWidth = ((float) w.get(i)) / wrong.getWidth();
                float scaleHeight = ((float) h.get(i)) / wrong.getHeight();
                Matrix matrix = new Matrix();
                matrix.postScale(scaleWidth, scaleHeight);
                Bitmap newwrong = Bitmap.createBitmap(wrong, 0, 0,
                        wrong.getWidth(), wrong.getHeight(), matrix, true);
                cv.drawBitmap(newwrong, x.get(i), y.get(i), null);
                drawScore(cv, listScore.get(i), x.get(i), y.get(i), matrix, scaleWidth,
                        scaleHeight, newwrong.getWidth());
                newwrong.recycle();
                newwrong = null;
            }
            if (pageindex == 1) {
                double score = totalscore;
                if (score > 0) {
                    String scoreString = String.valueOf(score);
                    char[] charArray = scoreString.toCharArray();
                    int sX = 100;
                    int sY = 100;
                    for (int j = 0; j < charArray.length; j++) {
                        char c = charArray[j];
                        switch (c) {
                            case '1':
                                Bitmap n1 = BitmapFactory.decodeResource(
                                        context.getResources(), R.mipmap.no_1);
                                // Bitmap
                                // n1=ImageBitmapUtil.decodeimagemap.get(Integer.valueOf(R.drawable.no_1));
                                cv.drawBitmap(n1, sX, sY, null);
                                sX += n1.getWidth();
                                n1.recycle();
                                n1 = null;
                                break;
                            case '2':
                                Bitmap n2 = BitmapFactory.decodeResource(
                                        context.getResources(), R.mipmap.no_2);
                                // Bitmap
                                // n2=ImageBitmapUtil.decodeimagemap.get(Integer.valueOf(R.drawable.no_2));
                                cv.drawBitmap(n2, sX, sY, null);
                                sX += n2.getWidth();
                                n2.recycle();
                                n2 = null;
                                break;
                            case '3':
                                Bitmap n3 = BitmapFactory.decodeResource(
                                        context.getResources(), R.mipmap.no_3);
                                // Bitmap
                                // n3=ImageBitmapUtil.decodeimagemap.get(Integer.valueOf(R.drawable.no_3));
                                cv.drawBitmap(n3, sX, sY, null);
                                sX += n3.getWidth();
                                n3.recycle();
                                n3 = null;
                                break;
                            case '4':
                                Bitmap n4 = BitmapFactory.decodeResource(
                                        context.getResources(), R.mipmap.no_4);
                                cv.drawBitmap(n4, sX, sY, null);
                                sX += n4.getWidth();
                                n4.recycle();
                                n4 = null;
                                break;
                            case '5':
                                Bitmap n5 = BitmapFactory.decodeResource(
                                        context.getResources(), R.mipmap.no_5);
                                cv.drawBitmap(n5, sX, sY, null);
                                sX += n5.getWidth();
                                n5.recycle();
                                n5 = null;
                                break;
                            case '6':
                                Bitmap n6 = BitmapFactory.decodeResource(
                                        context.getResources(), R.mipmap.no_6);
                                cv.drawBitmap(n6, sX, sY, null);
                                sX += n6.getWidth();
                                n6.recycle();
                                n6 = null;
                                break;
                            case '7':
                                Bitmap n7 = BitmapFactory.decodeResource(
                                        context.getResources(), R.mipmap.no_7);
                                cv.drawBitmap(n7, sX, sY, null);
                                sX += n7.getWidth();
                                n7.recycle();
                                n7 = null;
                                break;
                            case '8':
                                Bitmap n8 = BitmapFactory.decodeResource(
                                        context.getResources(), R.mipmap.no_8);
                                // Bitmap
                                // n8=ImageBitmapUtil.decodeimagemap.get(Integer.valueOf(R.drawable.no_8));
                                cv.drawBitmap(n8, sX, sY, null);
                                sX += n8.getWidth();
                                // n8.recycle();
                                n8 = null;
                                break;
                            case '9':
                                Bitmap n9 = BitmapFactory.decodeResource(
                                        context.getResources(), R.mipmap.no_9);
                                // Bitmap
                                // n9=ImageBitmapUtil.decodeimagemap.get(Integer.valueOf(R.drawable.no_9));
                                cv.drawBitmap(n9, sX, sY, null);
                                sX += n9.getWidth();
                                n9.recycle();
                                n9 = null;
                                break;
                            case '0':
                                Bitmap n0 = BitmapFactory.decodeResource(
                                        context.getResources(), R.mipmap.no_0);
                                // Bitmap
                                // n0=ImageBitmapUtil.decodeimagemap.get(Integer.valueOf(R.drawable.no_0));
                                cv.drawBitmap(n0, sX, sY, null);
                                sX += n0.getWidth();
                                n0.recycle();
                                n0 = null;
                                break;
                            case '.':
                                Bitmap nd = BitmapFactory.decodeResource(
                                        context.getResources(), R.mipmap.no_dian);
                                // Bitmap
                                // nd=ImageBitmapUtil.decodeimagemap.get(Integer.valueOf(R.drawable.no_dian));
                                cv.drawBitmap(nd, sX, sY, null);
                                sX += nd.getWidth();
                                nd.recycle();
                                nd = null;
                                break;
                            default:
                                break;
                        }
                    }
                    Bitmap deng = BitmapFactory.decodeResource(context.getResources(),
                            R.mipmap.no_00);
                    cv.drawBitmap(deng, 130, sY + 100, null);
                    deng.recycle();
                    deng = null;
                }
            }
        }

        right.recycle();
        right = null;
        r_w.recycle();
        r_w = null;
        wrong.recycle();
        wrong = null;

        cv.save(Canvas.ALL_SAVE_FLAG);// 保存
        cv.restore();// 存储
        return newbmp;
    }


    private void drawScore(Canvas cv, double score, int x, int y, Matrix matrix,
                           float scaleWidth, float scaleHeight, int i) {
        String scoreString = String.valueOf(score);
        char[] charArray = scoreString.toCharArray();
        int sX = x + i;
        int sY = y;
        for (int j = 0; j < charArray.length; j++) {
            char c = charArray[j];
            switch (c) {
                case '1':
                    Bitmap n1 = BitmapFactory.decodeResource(context.getResources(), R.mipmap.no_1);
                    Bitmap new1 = Bitmap.createBitmap(n1, 0, 0, n1.getWidth(), n1.getHeight(), matrix,
                            true);
                    cv.drawBitmap(new1, sX, sY, null);
                    sX += new1.getWidth();
                    new1.recycle();
                    new1 = null;
                    break;
                case '2':
                    Bitmap n2 = BitmapFactory.decodeResource(context.getResources(), R.mipmap.no_2);
                    Bitmap new2 = Bitmap.createBitmap(n2, 0, 0, n2.getWidth(), n2.getHeight(), matrix,
                            true);
                    cv.drawBitmap(new2, sX, sY, null);
                    sX += new2.getWidth();
                    new2.recycle();
                    new2 = null;
                    break;
                case '3':
                    Bitmap n3 = BitmapFactory.decodeResource(context.getResources(), R.mipmap.no_3);
                    Bitmap new3 = Bitmap.createBitmap(n3, 0, 0, n3.getWidth(), n3.getHeight(), matrix, true);
                    cv.drawBitmap(new3, sX, sY, null);
                    sX += new3.getWidth();
                    new3.recycle();
                    new3 = null;
                    break;
                case '4':
                    Bitmap n4 = BitmapFactory.decodeResource(context.getResources(), R.mipmap.no_4);
                    Bitmap new4 = Bitmap.createBitmap(n4, 0, 0, n4.getWidth(), n4.getHeight(), matrix,
                            true);
                    cv.drawBitmap(new4, sX, sY, null);
                    sX += new4.getWidth();
                    new4.recycle();
                    new4 = null;
                    break;
                case '5':
                    Bitmap n5 = BitmapFactory.decodeResource(context.getResources(), R.mipmap.no_5);
                    Bitmap new5 = Bitmap.createBitmap(n5, 0, 0, n5.getWidth(), n5.getHeight(), matrix,
                            true);
                    cv.drawBitmap(new5, sX, sY, null);
                    sX += new5.getWidth();
                    new5.recycle();
                    new5 = null;
                    break;
                case '6':
                    Bitmap n6 = BitmapFactory.decodeResource(context.getResources(), R.mipmap.no_6);
                    Bitmap new6 = Bitmap.createBitmap(n6, 0, 0, n6.getWidth(), n6.getHeight(), matrix,
                            true);
                    cv.drawBitmap(new6, sX, sY, null);
                    sX += new6.getWidth();
                    new6.recycle();
                    new6 = null;
                    break;
                case '7':
                    Bitmap n7 = BitmapFactory.decodeResource(context.getResources(), R.mipmap.no_7);
                    Bitmap new7 = Bitmap.createBitmap(n7, 0, 0, n7.getWidth(), n7.getHeight(), matrix,
                            true);
                    cv.drawBitmap(new7, sX, sY, null);
                    sX += new7.getWidth();
                    new7.recycle();
                    new7 = null;
                    break;
                case '8':
                    Bitmap n8 = BitmapFactory.decodeResource(context.getResources(), R.mipmap.no_8);
                    Bitmap new8 = Bitmap.createBitmap(n8, 0, 0, n8.getWidth(), n8.getHeight(), matrix,
                            true);
                    cv.drawBitmap(new8, sX, sY, null);
                    sX += new8.getWidth();
                    new8.recycle();
                    new8 = null;
                    break;
                case '9':
                    Bitmap n9 = BitmapFactory.decodeResource(context.getResources(), R.mipmap.no_9);
                    Bitmap new9 = Bitmap.createBitmap(n9, 0, 0, n9.getWidth(), n9.getHeight(), matrix,
                            true);
                    cv.drawBitmap(new9, sX, sY, null);
                    sX += new9.getWidth();
                    new9.recycle();
                    new9 = null;
                    break;
                case '0':
                    Bitmap n0 = BitmapFactory.decodeResource(context.getResources(), R.mipmap.no_0);
                    Bitmap new0 = Bitmap.createBitmap(n0, 0, 0, n0.getWidth(), n0.getHeight(), matrix,
                            true);
                    cv.drawBitmap(new0, sX, sY, null);
                    sX += new0.getWidth();
                    new0.recycle();
                    new0 = null;
                    break;
                case '.':
                    Bitmap nd = BitmapFactory.decodeResource(context.getResources(), R.mipmap.no_dian);
                    Bitmap newd = Bitmap.createBitmap(nd, 0, 0, nd.getWidth(), nd.getHeight(), matrix,
                            true);
                    cv.drawBitmap(newd, sX, sY, null);
                    sX += newd.getWidth();
                    newd.recycle();
                    newd = null;
                    break;
                default:
                    break;
            }
        }
    }

}

