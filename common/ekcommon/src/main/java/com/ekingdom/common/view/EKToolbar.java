package com.ekingdom.common.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;

public class EKToolbar extends ConstraintLayout {
    AppCompatImageView imgBaseAction;
    AppCompatImageView imgBack;
    AppCompatImageView imgMore;
    AppCompatTextView tvTitle;
    AppCompatTextView tvMore;
    AppCompatTextView tvAction;
    Group groupBase;
    Group groupMore;
    private Drawable baseActionIconResource;
    private boolean isShowBaseActionIcon = false;
    private boolean isShowMoreIcon = false;
    private String title = "";
//    private OnToolbarActionClickListener onToolbarActionClickListener;

    public EKToolbar(Context context) {
        super(context);
    }
//
//    public EKToolbar(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        init();
//        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EKToolbar, 0, 0);
//        try {
//            baseActionIconResource = typedArray.getDrawable(R.styleable.EKToolbar_baseActionItemIcon);
//            isShowBaseActionIcon = typedArray.getBoolean(R.styleable.EKToolbar_showBaseActionItem, isShowBaseActionIcon);
//            isShowMoreIcon = typedArray.getBoolean(R.styleable.EKToolbar_showMoreItem, isShowMoreIcon);
//            title = typedArray.getString(R.styleable.EKToolbar_title);
//        } catch (Exception e) {
//            Log.e(getClass().getSimpleName(), "EKToolbar: " + e.getMessage());
//        } finally {
//            typedArray.recycle();
//        }
//
//        if (baseActionIconResource != null) {
//            imgBaseAction.setImageDrawable(baseActionIconResource);
//        }
//        groupBase.setVisibility(isShowBaseActionIcon ? View.VISIBLE : View.GONE);
//        groupMore.setVisibility(isShowMoreIcon ? View.VISIBLE : View.GONE);
//        tvTitle.setText(title);
//    }
//
//    public void setOnToolbarActionClickListener(OnToolbarActionClickListener onToolbarActionClickListener) {
//        this.onToolbarActionClickListener = onToolbarActionClickListener;
//    }
//
//    private void init() {
//        setTag("Toolbar");
//        View.inflate(getContext(), R.layout.base_toolbar_item, this);
//        imgBaseAction = findViewById(R.id.imgAction);
//        imgMore = findViewById(R.id.imgMore);
//        imgBack = findViewById(R.id.imgBack);
//        tvTitle = findViewById(R.id.tvTitle);
//        tvMore = findViewById(R.id.tvMore);
//        tvAction = findViewById(R.id.tvAction);
//        groupBase = findViewById(R.id.groupBase);
//        groupMore = findViewById(R.id.groupMore);
//
//        tvTitle.setSelected(true);
//
//        findViewById(R.id.clBack).setOnClickListener((v) -> {
////            AndroidUtilities.preventTwoClick(v);
//            if (onToolbarActionClickListener != null)
//                onToolbarActionClickListener.onClickBack(v);
//        });
//        tvAction.setOnClickListener((v) -> {
////            AndroidUtilities.preventTwoClick(v);
//            if (onToolbarActionClickListener != null)
//                onToolbarActionClickListener.onClickBaseActionItem(v);
//        });
//        tvMore.setOnClickListener((v) -> {
////            AndroidUtilities.preventTwoClick(v);
//            if (onToolbarActionClickListener != null)
//                onToolbarActionClickListener.onClickMoreItem(v);
//        });
//    }
//
//    public void setShowBaseActionIcon(boolean isShow) {
//        this.isShowBaseActionIcon = isShow;
//        groupBase.setVisibility(this.isShowBaseActionIcon ? View.VISIBLE : View.GONE);
//    }
//
//    public void setBaseImageIcon(int drawableID) {
//        imgBaseAction.setImageResource(drawableID);
//    }
//
//    public void setTitle(String title) {
//        tvTitle.setText(title);
//    }
//
//    public interface OnToolbarActionClickListener {
//        void onClickBack(View view);
//
//        void onClickBaseActionItem(View view);
//
//        void onClickMoreItem(View view);
//    }
}