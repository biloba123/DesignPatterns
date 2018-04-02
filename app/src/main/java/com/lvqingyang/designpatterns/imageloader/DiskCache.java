package com.lvqingyang.designpatterns.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.lvqingyang.designpatterns.imageloader.tools.AppUtil;
import com.lvqingyang.designpatterns.imageloader.tools.DiskLruCache;
import com.lvqingyang.designpatterns.imageloader.tools.FileUtil;
import com.lvqingyang.designpatterns.imageloader.tools.Md5Util;

import java.io.File;
import java.io.IOException;

/**
 * LRU外存缓存
 *
 * @author Lv Qingyang
 * @date 2018/4/2
 * @email biloba12345@gamil.com
 * @github https://github.com/biloba123
 * @blog https://biloba123.github.io/
 * @see ImageCache
 * @since
 */
public class DiskCache implements ImageCache {
    private DiskLruCache mDiskLruCache;

    public DiskCache(Context context){
        try {
            File file= FileUtil.getDiskCacheDir(context, "Bitmaps");

            mDiskLruCache=DiskLruCache.open(
                    file,
                    AppUtil.getAppVersion(context),
                    1,
                    10*1024*1024
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void put(String url, Bitmap bitmap) {
        try {
            DiskLruCache.Editor editor=mDiskLruCache.edit(Md5Util.MD5(url));
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, editor.newOutputStream(0));
            editor.commit();
            mDiskLruCache.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Bitmap get(String url) {
        try {
            DiskLruCache.Snapshot snapshot=mDiskLruCache.get(Md5Util.MD5(url));
            if (snapshot!=null) {
                return BitmapFactory.decodeStream(snapshot.getInputStream(0));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
