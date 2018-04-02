package com.lvqingyang.designpatterns.imageloader;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * 一句话功能描述
 * 功能详细描述
 *
 * @author Lv Qingyang
 * @date 2018/4/2
 * @email biloba12345@gamil.com
 * @github https://github.com/biloba123
 * @blog https://biloba123.github.io/
 * @see MemoryCache
 * @see DiskCache
 * @since
 */
public class DoubleCache implements ImageCache {
    private MemoryCache mMemoryCache;
    private DiskCache mDiskCache;

    public DoubleCache(Context context){
        mMemoryCache=new MemoryCache();
        mDiskCache=new DiskCache(context);
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        mMemoryCache.put(url, bitmap);
        mDiskCache.put(url, bitmap);
    }

    @Override
    public Bitmap get(String url) {
        Bitmap bitmap=mMemoryCache.get(url);
        if (bitmap == null) {
            bitmap=mDiskCache.get(url);
        }
        return bitmap;
    }
}
