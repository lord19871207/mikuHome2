package com.opengl.youyang.mikuhome;

import android.util.SparseArray;

/**
 * Created by youyang on 15/12/20.
 */
public class Constont {
    public static final SparseArray<String> array = new SparseArray();

    private static final int COLOR_GRAY = 1;// 灰色
    public static final int COLOR_SHADOW = 0; // 阴影
    public static final int COLOR_REFLECT = 2;// 反色效果
    public static final int COLOR_OREIGNE = 3;// 橙色
    public static final int COLOR_OLD = 4;// 怀旧
    public static final int COLOR_HIGHCOMPARE = 5;// 高对比度

    static {
        array.put(0,"颜色过滤器的使用");
        array.put(1,"水波纹效果");
        array.put(2,"bitmapmesh窗帘效果");
        array.put(3,"Matrix的用法");



    }
}
