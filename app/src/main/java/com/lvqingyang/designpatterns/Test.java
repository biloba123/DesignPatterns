package com.lvqingyang.designpatterns;

import java.util.ArrayList;
import java.util.List;

/**
 * 一句话功能描述
 * 功能详细描述
 *
 * @author Lv Qingyang
 * @date 2018/4/8
 * @email biloba12345@gamil.com
 * @github https://github.com/biloba123
 * @blog https://biloba123.github.io/
 * @since
 */
public class Test {
    private String s;
    private List<String> mList;

    public String getS() {
        return s == null ? "" : s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public List<String> getList() {
        if (mList == null) {
            return new ArrayList<>();
        }
        return mList;
    }

    public void setList(List<String> list) {
        mList = list;
    }
}
