package com.lvqingyang.designpatterns.imageloader;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * 图标缓存策略接口
 *
 * @author Lv Qingyang
 * @date 2018/4/1
 * @email biloba12345@gamil.com
 * @github https://github.com/biloba123
 * @blog https://biloba123.github.io/
 * @see 
 * @since
 */
public interface ImageCache {
    public static final int MEMORY = 487;
    public static final int DISK = 913;
    public static final int DOUBLE = 154;

    public static ImageCache newType(Context context, int type){
        ImageCache cache;
        switch (type){
            case MEMORY:
                cache= new MemoryCache();
                break;
            case DISK:
                cache= new DoubleCache(context);
                break;
            case DOUBLE:
                cache= new DoubleCache(context);
                break;
            default:
                throw new RuntimeException("no such image cache.");
        }
        return cache;
    }

    void put(String url, Bitmap bitmap);

    Bitmap get(String url);
}
