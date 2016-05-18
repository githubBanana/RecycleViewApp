package com.xs.recycleviewapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * @version V1.0 <描述当前版本功能>
 * @author: Xs
 * @date: 2016-05-17 17:26
 * @email Xs.lin@foxmail.com
 */
public class RefreshActivity extends ToolbarActivity {
    private static final String TAG = "RefreshActivity";

    @Override
    protected int getContentView() {
        return R.layout.activity_refresh_layout;
    }


    public static final String TYPE = "type";
    public static void startRefreshActivity(Context context,int type) {
        Intent intent = new Intent(context,RefreshActivity.class);
        intent.putExtra(TYPE,type);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Bundle bundle = getIntent().getExtras();
        int type = bundle.getInt(TYPE);*/
        int type = 1;
        switch (type) {
            case 1:
                setTitle("refresh-layout");
                break;
            case 2:
                break;
        }

        TopicFragment fragment = TopicFragment.newInstance(RefreshActivity.this,1);
        getSupportFragmentManager().beginTransaction().replace(R.id.container_Id,fragment).commit();

    }
}
