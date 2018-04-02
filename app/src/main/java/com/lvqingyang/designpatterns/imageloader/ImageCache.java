package com.lvqingyang.designpatterns.imageloader;

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
    void put(String url, Bitmap bitmap);

    Bitmap get(String url);
}
