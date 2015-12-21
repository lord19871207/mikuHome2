package com.opengl.youyang.mikuhome;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;

import com.opengl.youyang.mikuhome.viewport.MatrixImageView;

/**
 * Created by youyang on 15/12/20.
 */
public class Util {

    /**
     * 方法描述：
     *
     * @author 尤洋
     * @Title: getScreenSize
     * @return
     * @return int[]
     * @date 2015-3-27 下午3:32:25
     * @param view
     */
    public static int[] getScreenSize(View view) {
        WindowManager wm = (WindowManager) view.getContext()
                .getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();
        int[] size=new int[2];
        size[0]=width;
        size[1]=height;
        return size;
    }
}
