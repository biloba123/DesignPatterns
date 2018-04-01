package com.lvqingyang.designpatterns.imageloader;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * 使用LruCache缓存Bitmap
 *
 * @author Lv Qingyang
 * @date 2018/3/31
 * @email biloba12345@gamil.com
 * @github https://github.com/biloba123
 * @blog https://biloba123.github.io/
 */
public class BitmapCache {
    private LruCache<String, Bitmap> mLruCache;

    private BitmapCache(){
        mLruCache=new LruCache<String, Bitmap>((int) (Runtime.getRuntime().maxMemory()/1024)){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight()/1024;
            }
        };
    }


    public static BitmapCache getInstance(){
        return Holder.sBitmapCache;
    }

    public Bitmap put(String url, Bitmap bitmap){
        return mLruCache.put(url, bitmap);
    }

    public Bitmap get(String url){
        return mLruCache.get(url);
    }

    private static class Holder{
        public static BitmapCache sBitmapCache=new BitmapCache();
    }
}
