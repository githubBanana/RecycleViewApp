package com.src.cy.multistateview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.kennyc.view.MultiStateView;

/**
 * @author CY
 * @date 2016/4/18 11:14
 * @email tmxdyf@163.com
 */
public class MainActivity extends AppCompatActivity {

    MultiStateView mMultiStateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMultiStateView = (MultiStateView) findViewById(R.id.multi);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.menu_load) {
            mMultiStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
        } else if (i == R.id.menu_empty) {
            mMultiStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
        } else if (i == R.id.menu_error) {
            mMultiStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);
        }
        return super.onOptionsItemSelected(item);
    }

}

