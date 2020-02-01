package com.zhuoren.qyNews.widget;

import android.content.Context;
import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * desc: .
 * author: Zhuoren.
 * date: 2018/11/18 16:14 .
 */

public class BottomBar extends LinearLayout {

    private boolean mVisible = true;

    private LinearLayout mTabLayout;

    private LayoutParams mTabParams;
    private int mCurrentPosition = 0;
    private OnTabSelectedListener mListener;

    public BottomBar(Context context) {
        this(context, null);
    }

    public BottomBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setOrientation(VERTICAL);

        mTabLayout = new LinearLayout(context);
        mTabLayout.setBackgroundColor(Color.WHITE);
        mTabLayout.setOrientation(LinearLayout.HORIZONTAL);
        addView(mTabLayout, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        mTabParams = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        mTabParams.weight = 1;
    }

    public BottomBar addItem(final BottomBarTab tab) {
        tab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener == null) return;

                int pos = tab.getTabPosition();
                if (mCurrentPosition == pos) {
                    mListener.onTabReselected(pos);
                } else {
                    mListener.onTabSelected(pos, mCurrentPosition);
                    tab.setSelected(true);
                    mListener.onTabUnselected(mCurrentPosition);
                    mTabLayout.getChildAt(mCurrentPosition).setSelected(false);
                    mCurrentPosition = pos;
                }
            }
        });
        tab.setTabPosition(mTabLayout.getChildCount());
        tab.setLayoutParams(mTabParams);
        mTabLayout.addView(tab);
        return this;
    }

    public void setOnTabSelectedListener(OnTabSelectedListener onTabSelectedListener) {
        mListener = onTabSelectedListener;
    }



    public interface OnTabSelectedListener {
        void onTabSelected(int position, int prePosition);

        void onTabUnselected(int position);

        void onTabReselected(int position);
    }


    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        return new SavedState(superState, mCurrentPosition);
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());

        if (mCurrentPosition != ss.position) {
            mTabLayout.getChildAt(mCurrentPosition).setSelected(false);
            mTabLayout.getChildAt(ss.position).setSelected(true);
        }
        mCurrentPosition = ss.position;
    }

    static class SavedState extends BaseSavedState {
        private int position;

        public SavedState(Parcel source) {
            super(source);
            position = source.readInt();
        }

        public SavedState(Parcelable superState, int position) {
            super(superState);
            this.position = position;
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(position);
        }

        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }
    public boolean isVisible() {
        return mVisible;
    }
}
