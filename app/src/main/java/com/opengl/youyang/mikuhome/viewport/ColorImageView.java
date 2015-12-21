package com.opengl.youyang.mikuhome.viewport;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.opengl.youyang.mikuhome.Util;

import java.util.ArrayList;


/**
 * 类描述： 通过颜色矩阵相乘获取不同滤镜效果的图片
 *
 * @author 尤洋
 * @Package com.example.mikuhome.view
 * @ClassName: ColorTextView
 * @mail youyang@ucweb.com
 * @date 2015-3-12 下午2:56:38
 */
public class ColorImageView extends ImageView {

    private int index = 3;
    private Paint mPaint;
    private Context context;
    private float touchx;
    private int width;
    private int height;
    private Bitmap mBitmap;
    private float area1;
    private float area2;
    private float area3;
    private float area4;
    private float area5;
    private float area6;
    private ArrayList<Float> mArealist;
    private ArrayList<ColorMatrixColorFilter> mFilterList;

    /**
     * 构造方法描述：
     *
     * @Title: ColorTextView
     * @date 2015-3-12 下午2:56:39
     */
    public ColorImageView(Context context) {
        this(context, null);
    }

    private void initColorFilter() {
        mFilterList = new ArrayList<>();
        //灰色
        ColorMatrixColorFilter cmcf_gray = new ColorMatrixColorFilter(new ColorMatrix(new float[]{
                0.5F, 0, 0f, 0, 0,
                0, 0.5F, 0f, 0, 0,
                0, 0, 0.5F, 0, 0,
                0, 0f, 0, 1, 0,
        }));

        ColorMatrixColorFilter cmcf_shadow = new ColorMatrixColorFilter(new ColorMatrix(new float[]{
                0.33F, 0.59F, 0.11F, 0, 0,
                0.33F, 0.59F, 0.11F, 0, 0,
                0.33F, 0.59F, 0.11F, 0, 0,
                0, 0, 0, 1, 0,
        }));

        ColorMatrixColorFilter cmcf_reflect = new ColorMatrixColorFilter(new ColorMatrix(new float[]{
                -1, 0, 0, 1, 1,
                0, -1, 0, 1, 1,
                0, 0, -1, 1, 1,
                0, 0, 0, 1, 0,
        }));

        ColorMatrixColorFilter cmcf_origine = new ColorMatrixColorFilter(new ColorMatrix(new float[]{
                0, 0, 1, 0, 0,
                0, 1, 0, 0, 0,
                1, 0, 0, 0, 0,
                0, 0, 0, 1, 0,
        }));

        ColorMatrixColorFilter cmcf_old = new ColorMatrixColorFilter(new ColorMatrix(new float[]{
                0.393F, 0.769F, 0.189F, 0, 0,
                0.349F, 0.686F, 0.168F, 0, 0,
                0.272F, 0.534F, 0.131F, 0, 0,
                0, 0, 0, 1, 0,
        }));

        ColorMatrixColorFilter cmcf_highCompare = new ColorMatrixColorFilter(new ColorMatrix(new float[]{
                1.5F, 1.5F, 1.5F, 0, -1,
                1.5F, 1.5F, 1.5F, 0, -1,
                1.5F, 1.5F, 1.5F, 0, -1,
                0, 0, 0, 1, 0,
        }));

        mFilterList.add(cmcf_gray);
        mFilterList.add(cmcf_shadow);
        mFilterList.add(cmcf_reflect);
        mFilterList.add(cmcf_origine);
        mFilterList.add(cmcf_old);
        mFilterList.add(cmcf_highCompare);

    }


    /**
     * 构造方法描述：
     *
     * @Title: ColorTextView
     * @date 2015-3-12 下午2:56:39
     */
    public ColorImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 构造方法描述：
     *
     * @Title: ColorTextView
     * @date 2015-3-12 下午2:56:39
     */
    public ColorImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        initView(attrs);
    }

    /**
     * 方法描述：初始化自定义控件
     *
     * @return void
     * @author 尤洋
     * @Title: initView
     * @date 2015-3-12 下午3:56:47
     */
    private void initView(AttributeSet attr) {
        initColorFilter();
        inintPaint();
        initTouchArea();
        int resourceId = attr.getAttributeResourceValue(null, "colorbackground", android.R.drawable.ic_menu_always_landscape_portrait);
        mBitmap = BitmapFactory.decodeResource(context.getResources(), resourceId);
        mBitmap = Bitmap.createScaledBitmap(mBitmap, Util.getScreenSize(this)[0],
                Util.getScreenSize(this)[1]/2, true);


    }

    /**
     * 方法描述：初始化点击区域
     *
     * @return void
     * @author 尤洋
     * @Title: initTouchArea
     * @date 2015-3-26 下午12:42:09
     */
    private void initTouchArea() {
        mArealist = new ArrayList<Float>();
        mArealist.add(area1);
        mArealist.add(area2);
        mArealist.add(area3);
        mArealist.add(area4);
        mArealist.add(area5);
        mArealist.add(area6);
    }

    /**
     * 方法描述：初始化6种颜色矩阵的画笔
     *
     * @return void
     * @author 尤洋
     * @Title: inintPaint
     * @date 2015-3-26 下午12:14:28
     */
    private void inintPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(15);
    }

    public void setIndex(int index) {
        this.index = index;
        mPaint.setColorFilter(mFilterList.get(index));
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.widget.TextView#onDraw(android.graphics.Canvas)
     */
    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap, 0, 0, mPaint);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        area1 = (float) width / 6;
        area2 = (float) 2 * width / 6;
        area3 = (float) 3 * width / 6;
        area4 = (float) 4 * width / 6;
        area5 = (float) 5 * width / 6;
        area6 = (float) width;
        height = MeasureSpec.getSize(heightMeasureSpec);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        touchx = event.getX();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                Log.i("youyang", "1点击后的事件是event.getAction()=" + event.getAction());
                controlClickArea();
                postInvalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.i("youyang", "2点击后的事件是event.getAction()=" + event.getAction());
                index = 2;
                postInvalidate();
                break;
            default:
                break;
        }

        return true;
    }

    /**
     * 方法描述：在不同点击区域设置不同的index
     *
     * @return void
     * @author 尤洋
     * @Title: controlClickArea
     * @date 2015-3-26 下午1:37:52
     */
    private void controlClickArea() {
        if (touchx < area1) {
            setIndex(0);
        } else if (touchx < area2 && touchx > area1) {
            setIndex(1);
        } else if (touchx < area3 && touchx > area2) {
            setIndex(2);
        } else if (touchx < area4 && touchx > area3) {
            setIndex(3);
        } else if (touchx < area5 && touchx > area4) {
            setIndex(4);
        } else if (touchx < area6 && touchx > area5) {
            setIndex(5);
        }
    }

}
