package com.lvqingyang.designpatterns.imageloader;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * 使用LRU算法做内存缓存
 *
 * @author Lv Qingyang
 * @date 2018/4/2
 * @email biloba12345@gamil.com
 * @github https://github.com/biloba123
 * @blog https://biloba123.github.io/
 * @see ImageCache
 * @since
 */
public class MemoryCache implements ImageCache {
    private LruCache<String, Bitmap> mLruCache;

    public MemoryCache(){
        mLruCache=new LruCache<String, Bitmap>((int) (Runtime.getRuntime().maxMemory()/1024)){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight()/1024;
            }
        };
    }

    @Override
    public void put(String url, Bitmap bitmap){
        mLruCache.put(url, bitmap);
    }

    @Override
    public Bitmap get(String url){
        return mLruCache.get(url);
    }
}
