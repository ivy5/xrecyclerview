package com.ruijia.xrecyclerview.view;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ruijia.xrecyclerview.R;

/**
 * Created by limxing on 16/7/23.
 * <p>
 * https://github.com/limxing
 * Blog: http://www.leefeng.me
 */
public class RecyclerViewFooter extends LinearLayout {
    public final static int STATE_NORMAL = 0;
    public final static int STATE_READY = 1;
    public final static int STATE_LOADING = 2;

    private Context mContext;
    private int mState;
    private View mContentView;
    private View mProgressBar;
    private TextView mHintView;
    private RelativeLayout lfrecyclerview_footer_state;
    private LoadView lfrecyclerview_footer_loadview;

    public RecyclerViewFooter(Context context) {
        super(context);
        initView(context);
    }

    public RecyclerViewFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public int getmState(){
        return mState;
    }


    public void setState(int state) {
        mState = state;
        mHintView.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.INVISIBLE);
        mHintView.setVisibility(View.INVISIBLE);
        if (state == STATE_READY) {
            mHintView.setVisibility(View.VISIBLE);
            mHintView.setText(R.string.lfrecyclerview_footer_hint_ready);
        } else if (state == STATE_LOADING) {
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mHintView.setVisibility(View.VISIBLE);
            mHintView.setText(R.string.lfrecyclerview_footer_hint_normal);
        }
    }

    public void setBottomMargin(int height) {
        if (height < 0) return;
        LayoutParams lp = (LayoutParams) mContentView.getLayoutParams();
        lp.bottomMargin = height;
        mContentView.setLayoutParams(lp);
    }

    public int getBottomMargin() {
        LayoutParams lp = (LayoutParams) mContentView.getLayoutParams();
        return lp.bottomMargin;
    }


    /**
     * normal status
     */
    public void normal() {
        mHintView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
    }


    /**
     * loading status
     */
    public void loading() {
        mHintView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    /**
     * hide footer when disable pull load more
     */
    public void hide() {
        mContentView.setVisibility(View.GONE);
    }

    /**
     * show footer
     */
    public void show() {
        mContentView.setVisibility(View.VISIBLE);
    }

    private void initView(Context context) {
        mContext = context;
        LinearLayout moreView = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.lfrecyclerview_footer, null);
        addView(moreView);
        moreView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        lfrecyclerview_footer_state = moreView.findViewById(R.id.lfrecyclerview_footer_state);
        lfrecyclerview_footer_state.setVisibility(View.GONE);
        mContentView = moreView.findViewById(R.id.lfrecyclerview_footer_content);
        mProgressBar = moreView.findViewById(R.id.lfrecyclerview_footer_progressbar);
        mHintView = moreView.findViewById(R.id.lfrecyclerview_footer_hint_textview);
        lfrecyclerview_footer_loadview = moreView.findViewById(R.id.lfrecyclerview_footer_loadview);
    }

    public TextView getHintView() {
        return mHintView;
    }

    /**
     * 设置是否显示有无数据有好提示
     *
     * @param isNone isNone
     */
    public void setNoneDataState(boolean isNone) {
        if (isNone) {
            lfrecyclerview_footer_state.setVisibility(View.VISIBLE);
        } else {
            lfrecyclerview_footer_state.setVisibility(View.GONE);
        }

    }
}
