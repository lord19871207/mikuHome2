package com.opengl.youyang.mikuhome;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by youyang on 15/12/20.
 */
public class ColorFilterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colorfilter);
        TextView descreption = (TextView) findViewById(R.id.descreption);
        descreption.setText("例如 暗色：" +
                "ColorMatrixColorFilter cmcf_gray = \n" +
                "         new ColorMatrixColorFilter(\n" +
                "           new ColorMatrix(new float[]{\n" +
                "                0.5F, 0, 0f, 0, 0,\n" +
                "                0, 0.5F, 0f, 0, 0,\n" +
                "                0, 0, 0.5F, 0, 0,\n" +
                "                0, 0f, 0, 1, 0,\n" +
                "        }));\n" +

                "\n   阴影\nColorMatrixColorFilter cmcf_shadow =\n" +
                "          new ColorMatrixColorFilter(\n" +
                "           new ColorMatrix(new float[]{\n" +
                "                0.33F, 0.59F, 0.11F, 0, 0,\n" +
                "                0.33F, 0.59F, 0.11F, 0, 0,\n" +
                "                0.33F, 0.59F, 0.11F, 0, 0,\n" +
                "                0, 0, 0, 1, 0,\n" +
                "        }))" +

                "");
    }
}
