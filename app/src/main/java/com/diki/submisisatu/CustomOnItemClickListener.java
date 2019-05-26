package com.diki.submisisatu;

import android.view.View;

public class CustomOnItemClickListener implements View.OnClickListener {
    private int position;
    private OnItemClickCallBack  onItemClickCallback;
    CustomOnItemClickListener(int position, OnItemClickCallBack onItemClickCallback){
        this.position = position;
        this.onItemClickCallback = onItemClickCallback;
    }

    @Override
    public void onClick(View view){
        onItemClickCallback.onItemClicked(view, position);

    }

    public interface OnItemClickCallBack {
        void onItemClicked(View view,int position);
    }
}
