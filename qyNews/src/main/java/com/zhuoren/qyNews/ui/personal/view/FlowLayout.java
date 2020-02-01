package com.zhuoren.qyNews.ui.personal.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.zhuoren.qyNews.MyApp;
import com.zhuoren.qyNews.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by 5499 on 2019/11/19.
 */

public class FlowLayout extends ViewGroup {
    private List<String> mLabels;

    private int row_dis;
    private int col_dis;

    private HashSet<String> selected= new HashSet<>();


    public FlowLayout(Context context) {
        this(context,null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout);
        row_dis= a.getDimensionPixelSize(R.styleable.FlowLayout_row_dis,10);
        col_dis= a.getDimensionPixelSize(R.styleable.FlowLayout_col_dis,10);
        a.recycle();
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int right = 0;
        int bottom=getChildAt(0).getMeasuredHeight();
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            int childW = child.getMeasuredWidth();
            int childH = child.getMeasuredHeight();
            right += childW;
            if (right+col_dis > r) {
                right = childW;
                bottom = bottom + childH+row_dis;
            }
            child.layout(right - childW, bottom - childH, right, bottom);
            right+=col_dis;
        }
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if(heightMode== MeasureSpec.AT_MOST){
            heightSize= getTotalHeight(widthSize);
        }
        setMeasuredDimension(widthSize,heightSize);
    }

    private int getTotalHeight(int ws){
        double totalWidth=0.0;
        for(int i=0;i<getChildCount();i++){
            totalWidth+=(getChildAt(i).getMeasuredWidth()+col_dis);
        }
        int rows= (int) Math.ceil(totalWidth/ws);
        int totalHeight= rows* getChildAt(0).getMeasuredHeight()+(rows-1)*row_dis;
        return totalHeight;
    }

    public void setLabels(List<String> labels){
        if(this.mLabels==null){
            this.mLabels= new ArrayList<>();
        }
        this.mLabels.addAll(labels);
        generateLabels();
    }

    private void generateLabels(){
        for(String label:mLabels){
            final TextView textView=new TextView(getContext());
            textView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
//            textView.setBackgroundResource(R.drawable.shape_item_lable_bg);
            textView.setBackgroundResource(R.drawable.shape_unselected_lable_bg);
            textView.setText(label);
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(17);
            textView.setGravity(Gravity.CENTER);
            textView.setPadding(12,5,12,5);
//            textView.setSelected(true);

            //在这里非核心，属于润色部分，也可移除
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    textView.setSelected(textView.isSelected() ? false : true);
                    Log.d("flowlayout",""+textView.isSelected());
                    if (textView.isSelected()) {
                        textView.setTextColor(getResources().getColor(R.color.colorPrimary));
                        textView.setBackgroundResource(R.drawable.shape_initselected_lable_bg);
//                        textView.setSelected(false);
                        selected.add(textView.getText().toString());
                        Log.d("flowlayout","添加"+textView.getText().toString()+","+selected.size());

                    } else {
                        textView.setTextColor(getResources().getColor(R.color.tv_gray));
                        textView.setBackgroundResource(R.drawable.shape_unselected_lable_bg);
//                        textView.setSelected(true);
                        selected.remove(textView.getText().toString());
                        Log.d("flowlayout","移除"+textView.getText().toString()+","+selected.size());


                    }

                }
            });

            addView(textView);
        }
    }

    public void initSelectedLables(){
        for(String text:MyApp.getInstance().getSelectedLables()){
            for(int i=0;i<getChildCount();i++){
                TextView textView= (TextView) getChildAt(i);
                if(textView.getText().toString().equals(text)){
                    textView.setTextColor(getResources().getColor(R.color.colorPrimary));
                    textView.setBackgroundResource(R.drawable.shape_initselected_lable_bg);
                    textView.setSelected(true);
                    selected.add(textView.getText().toString());
                    Log.d("Hashset:",""+selected.size());
                }
            }
        }
    }

    public HashSet<String> getSelected(){
        return selected;
    }

}
