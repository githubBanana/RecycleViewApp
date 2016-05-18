package com.xs.recycleviewapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * @version V1.0 <描述当前版本功能>
 * @author: Xs
 * @date: 2016-05-17 17:26
 * @email Xs.lin@foxmail.com
 */
public abstract class ToolbarActivity extends AppCompatActivity {
    private static final String TAG = "ToolbarActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_id);
        setSupportActionBar(toolbar);
        showUpEnabled(true);
    }

    protected abstract int getContentView();

    public void showUpEnabled(boolean enable) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(enable);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.bga_refresh_loading01);
    }

        @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        getSupportActionBar().setTitle(title);
    }
}
