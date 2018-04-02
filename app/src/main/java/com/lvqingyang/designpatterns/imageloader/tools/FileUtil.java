package com.lvqingyang.designpatterns.imageloader.tools;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.util.Objects;

/**
 * 一句话功能描述
 * 功能详细描述
 *
 * @author Lv Qingyang
 * @date 2018/4/2
 * @email biloba12345@gamil.com
 * @github https://github.com/biloba123
 * @blog https://biloba123.github.io/
 * @since
 */
public class FileUtil {

    public static boolean isExternalStorageAvailable(){
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable();
    }

    public static File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (isExternalStorageAvailable()) {
            cachePath = Objects.requireNonNull(context.getExternalCacheDir()).getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }

        File file=new File(cachePath + File.separator + uniqueName);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }
}
