package com.opengl.youyang.mikuhome;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by youyang on 15/12/19.
 */
public class NewsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private static final int NORMAL_ITEM = 0;
    private static final int GROUP_ITEM = 1;
    private MainActivity mContext;
    private List<NewsListEntity> mDataList;
    private LayoutInflater mLayoutInflater;


    public NewsListAdapter(MainActivity mContext, List<NewsListEntity>mDataList) {
        this.mContext =mContext;
        this.mDataList =mDataList;
        mLayoutInflater =LayoutInflater.from(mContext);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType ==NORMAL_ITEM) {
            return new NormalItemHolder(mLayoutInflater.inflate(R.layout.item_function_list, parent, false));
        } else{
            return new GroupItemHolder(mLayoutInflater.inflate(R.layout.item_function_with_time, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NewsListEntity entity =mDataList.get(position);
        if(null==entity) {
            return;
        }
        if(holder instanceof GroupItemHolder){
            bindGroupItem(entity, (GroupItemHolder) holder);
        }else{
            NormalItemHolder viewHolder =(NormalItemHolder)holder;
            bindNormalItem(entity, viewHolder.newsTitle, viewHolder.newsIcon);
        }

    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }



    /**
     * 新闻标题 */
    public class NormalItemHolder extends RecyclerView.ViewHolder {
        TextView newsTitle;
        ImageView newsIcon;

        public NormalItemHolder(View itemView) {
            super(itemView);
            newsTitle =(TextView) itemView.findViewById(R.id.base_swipe_item_title);
            newsIcon =(ImageView) itemView.findViewById(R.id.base_swipe_item_icon);
            itemView.findViewById(R.id.base_swipe_item_container).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showNewsDetail(getPosition());
                }
            });
        } }

    /**
     * 带日期
     */
    public class GroupItemHolder extends NormalItemHolder {
        TextView newsTime;
        public GroupItemHolder(View itemView) {
            super(itemView);
            newsTime =(TextView)itemView.findViewById(R.id.base_swipe_group_item_time);
        }
    }

    @Override
    public int getItemViewType(int position) {//第一个要显示时间
        if(position == 0)
            return NORMAL_ITEM;
        String currentDate =mDataList.get(position).getPublishDate();
        int prevIndex = position - 1;
        boolean isDifferent = !mDataList.get(prevIndex).getPublishDate().equals(currentDate);
        return isDifferent ?GROUP_ITEM : NORMAL_ITEM;
    }
    @Override
    public long getItemId(int position) {
        return mDataList.get(position).getNewsID();
    }

    void bindNormalItem(NewsListEntity entity, TextView newsTitle, ImageView newsIcon) {
        if(entity.getIconUrl().isEmpty()) {
            if(newsIcon.getVisibility() !=View.GONE)
                newsIcon.setVisibility(View.GONE);
        } else{
             //TODO 设置图像

            if(newsIcon.getVisibility() !=View.VISIBLE)
                newsIcon.setVisibility(View.VISIBLE);
        }
        newsTitle.setText(entity.getTitle());
    }

    void bindGroupItem(NewsListEntity entity, GroupItemHolder holder) {
        bindNormalItem(entity, holder.newsTitle, holder.newsIcon);
        holder.newsTime.setText(entity.getPublishDate());
    }

    void showNewsDetail(int pos) {
        Intent intent =new Intent();
        if(pos == 0){
            intent.setClass(mContext,ColorFilterActivity.class);
            mContext.startActivity(intent);
        }else if (pos == 1){
            intent.setClass(mContext,TouchWrapActivity.class);
            mContext.startActivity(intent);
        }else if (pos == 2){
            intent.setClass(mContext,BitmapMeshActivity.class);
            mContext.startActivity(intent);
        }else if (pos == 3){
            intent.setClass(mContext,MatrixImageActivity.class);
            mContext.startActivity(intent);
        }
        NewsListEntity entity =mDataList.get(pos);
    }


    public static int daysOfTwo(Date originalDate, Date compareDateDate) {
        Calendar aCalendar =Calendar.getInstance();
        aCalendar.setTime(originalDate);
        int originalDay =aCalendar.get(Calendar.DAY_OF_YEAR);
        aCalendar.setTime(compareDateDate);
        int compareDay =aCalendar.get(Calendar.DAY_OF_YEAR);
        return originalDay -compareDay;
    }

    public static String FriendlyDate(Date compareDate) {
        Date nowDate = new Date();
        int dayDiff =daysOfTwo(nowDate, compareDate);
        if(dayDiff <= 0)
            return"今日";
        else if(dayDiff == 1)
            return"昨日";
        else if(dayDiff == 2)
            return"前日";
        else
            return new SimpleDateFormat("M月d日 E").format(compareDate);
    }

}
