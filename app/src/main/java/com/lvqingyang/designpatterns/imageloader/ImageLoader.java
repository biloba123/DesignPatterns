package com.lvqingyang.designpatterns.imageloader;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 图片加载工具
 *
 * @author Lv Qingyang
 * @date 2018/3/31
 * @email biloba12345@gamil.com
 * @github https://github.com/biloba123
 * @blog https://biloba123.github.io/
 */
public class ImageLoader {
    private static final String TAG = "ImageLoader";
    private static volatile ImageLoader sImageLoader;
    private Application mAppContext;
    private ImageCache mImageCache;
    private ExecutorService mExecutor;
    private Handler mHandler;

    private ImageLoader(Context context) {
        mAppContext = (Application) context.getApplicationContext();
        mImageCache = new DoubleCache(context);
        mExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        mHandler = new Handler(mAppContext.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                if (msg.obj != null) {
                    ImageView iv = (ImageView) msg.obj;
                    iv.setImageBitmap(mImageCache.get((String) iv.getTag()));
                }
            }
        };
    }


    public static ImageLoader getInstance(Context context) {
        if (sImageLoader == null) {
            synchronized (ImageLoader.class) {
                if (sImageLoader == null) {
                    sImageLoader = new ImageLoader(context);
                }
            }
        }
        return sImageLoader;
    }

    public void displayImage(final String url, final ImageView iv) {
        if (url == null || url.length() < 1) {
            return;
        }

        final Bitmap bitmap = mImageCache.get(url);
        if (bitmap != null) {
            iv.setImageBitmap(bitmap);
            return;
        }

        iv.setTag(url);
        mExecutor.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = downloadBitmap(url);
                if (bitmap != null) {
                    mImageCache.put(url, bitmap);

                    if (iv.getTag().equals(url)) {
                        Message message = Message.obtain();
                        message.obj = iv;
                        mHandler.sendMessage(message);
                    }
                }
            }
        });

    }

    private Bitmap downloadBitmap(String url) {
        Bitmap bitmap = null;
        HttpURLConnection connection = null;
        try {
            URL url1 = new URL(url);
            connection = (HttpURLConnection) url1.openConnection();
            bitmap = BitmapFactory.decodeStream(connection.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }

        return bitmap;
    }

    public ImageLoader(ImageCache imageCache) {
        mImageCache = imageCache;
    }
}
