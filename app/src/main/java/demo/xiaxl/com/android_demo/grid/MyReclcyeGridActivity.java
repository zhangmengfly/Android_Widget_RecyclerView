package demo.xiaxl.com.android_demo.grid;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import java.util.ArrayList;

import demo.xiaxl.com.android_demo.R;
import demo.xiaxl.com.android_demo.list.MyRecycleListAdapter;

/**
 *
 */
public class MyReclcyeGridActivity extends Activity {

    /**
     * UI
     */
    // recycleView
    private RecyclerView mRecyclerView = null;
    // adapter
    private MyRecycleGridAdapter mMyadapter = null;
    /**
     * 数据
     */
    //data
    private ArrayList<String> mDataList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_grid_activity);

        // 初始化数据
        initData();
        // 纵向List
        initUI();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        // -----------创建数据集-------------
        for (int i = 1; i < 100; i++) {
            mDataList.add("item" + i);
        }
    }


    /**
     * 纵向List
     */
    public void initUI() {
        // 添加按钮
        findViewById(R.id.add_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO add
                addData(3);

            }
        });
        // 删除按钮
        findViewById(R.id.sub_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO sub
                removeData(0);
            }
        });
        /**
         *RecyclerView
         */
        // ---RecyclerView---
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_vertical);
        // 动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // ---布局管理器---
        // Grid
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
//        // 横向Grid
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,
//                StaggeredGridLayoutManager.HORIZONTAL));
        //这句就是添加我们自定义的分隔线
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this,10,R.color.colorAccent));

        /**
         * adapter
         */
        // 创建Adapter，并指定数据集
        mMyadapter = new MyRecycleGridAdapter(mDataList);
        // 设置Adapter
        mRecyclerView.setAdapter(mMyadapter);
    }


    /**
     * 在mDataList中添加一条数据
     *
     * @param position
     */
    public void addData(int position) {
        //在list中添加数据，并通知条目加入一条
        mDataList.add(position, "Insert One");
        mMyadapter.notifyItemInserted(position);
    }

    //  删除数据
    public void removeData(int position) {
        mDataList.remove(position);
        mMyadapter.notifyItemRemoved(position);
    }
}
