package com.swufe.scoresforapp.FourMain;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PathMeasure;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.swufe.scoresforapp.DrawerLayoutActivity;
import com.swufe.scoresforapp.R;
import com.swufe.scoresforapp.ScrollingSchoolActivity;
import com.swufe.scoresforapp.adapter.RecyclerAdapter;
import com.swufe.scoresforapp.bean.SchoolBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import q.rorbin.badgeview.QBadgeView;

public class SchoolFragment extends Fragment {

    public static final String CONTENT = "content";
    private View mView;
    private RecyclerView mRecyclerView;
    private RecyclerView recyclerView;
    private ImageView ivCart; //VSimageView
    private List<SchoolBean> mSchoolBeans = new ArrayList<>();

    private RelativeLayout rl;
    private PathMeasure mPathMeasure;

    private RecyclerAdapter mAdapter;

    private LinearLayoutManager mLayoutManager;

    QBadgeView mQBadgeView;//小圆点

    private boolean isShowFloatImage = true;
    private float startY;
    private int moveDistance;
    private Timer timer;
    /**用户手指按下后抬起的实际*/
    private long upTime;

    /**
     * 贝塞尔曲线中间过程的点的坐标
     */
    private float[] mCurrentPosition = new float[2];
    private int i = 0;
    private View rootView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.activity_school_fragment, container, false);


        //注册接受DrawLayoutActivity的Touch回调对象
        //重写其中的onTouchEvent函数，并进行该Fragment的逻辑处理
        DrawerLayoutActivity.MyTouchListener myTouchListener = new DrawerLayoutActivity.MyTouchListener() {
            @Override
            public void onTouchEvent(MotionEvent event) {
                //处理手势事件
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (System.currentTimeMillis() - upTime < 1500) {
                            //本次按下距离上次的抬起小于1.5s时，取消Timer
                            timer.cancel();
                        }
                        startY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (Math.abs(startY - event.getY()) > 10) {
                            if (isShowFloatImage){
                                hideFloatImage(moveDistance);
                            }
                        }
                        startY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        if (!isShowFloatImage){
                            //抬起手指1.5s后再显示悬浮按钮
//                new Handler(new Handler.Callback() {
//                    @Override
//                    public boolean handleMessage(Message msg) {
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                showFloatImage(moveDistance);
//                            }
//                        });
//                        return false;
//                    }
//                }).sendEmptyMessageDelayed(0, 1500);

                            //开始1.5s倒计时
                            upTime = System.currentTimeMillis();
                            timer = new Timer();
                            timer.schedule(new FloatTask(), 1500);
                        }
                        break;
                    default:
                        break;
                }
            }
        };

        //将myTouchListener注册到分发列表
        ((DrawerLayoutActivity)this.getActivity()).registerMyTouchListener(myTouchListener);
        /*Button btn = view.findViewById(R.id.frag2_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "第二个Fragment", Toast.LENGTH_SHORT).show();
            }
        });*/
        initData();
        initView();


        return mView;
    }

    private void initData() {
        for (int i = 0; i < 60; i++) {
            mSchoolBeans.add(new SchoolBean("学校" + i));
        }
    }

    private void initView() {
        mRecyclerView =  mView.findViewById(R.id.recycler);
        mAdapter = new RecyclerAdapter(getContext(), mSchoolBeans);
        mQBadgeView = new QBadgeView(getContext());
        ivCart = (ImageView) mView.findViewById(R.id.iv_cart);
        rl = (RelativeLayout) mView.findViewById(R.id.school_rl);
        mQBadgeView.bindTarget(ivCart).setBadgeGravity(Gravity.END | Gravity.TOP);
        ivCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "收藏", Toast.LENGTH_SHORT).show();
            }
        });

        mAdapter.setOnItemClickListener(new RecyclerAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int tag, View view, int position) {
                Toast.makeText(getContext(), "点击", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), ScrollingSchoolActivity.class);
                intent.setClass(getContext(), ScrollingSchoolActivity.class);
                intent.setFlags( Intent.FLAG_ACTIVITY_NEW_TASK);
                getContext().startActivity(intent);
            }

            @Override
            public void onItemLongClick(int tag, View view, int position) {
                Toast.makeText(getContext(), "长按", Toast.LENGTH_SHORT).show();
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), 0));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


        //控件绘制完成之后再获取其宽高
        ivCart.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.d("Tag", "控件宽度" + ivCart.getWidth());
                moveDistance = getDisplayMetrics(getContext())[0] - ivCart.getRight() + ivCart.getWidth() / 2;
                //监听结束之后移除监听事件
                ivCart.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }



    class FloatTask extends TimerTask {
        @Override
        public void run() {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    showFloatImage(moveDistance);
                }
            });
        }
    }


    /**
     * 显示悬浮图标
     *
     * @param distance
     */
    private void showFloatImage(int distance) {
        Log.e("Tag", "显示动画执行");
        isShowFloatImage = true;
        //位移动画
        TranslateAnimation ta = new TranslateAnimation(
                distance,//起始x坐标
                0,//结束x坐标
                0,//起始y坐标
                0);//结束y坐标（正数向下移动）
        ta.setDuration(300);

        //渐变动画
        AlphaAnimation al = new AlphaAnimation(0.5f, 1f);
        al.setDuration(300);

        AnimationSet set = new AnimationSet(true);
        //动画完成后不回到原位
        set.setFillAfter(true);
        set.addAnimation(ta);
        set.addAnimation(al);
        ivCart.startAnimation(set);
    }

    /**
     * 隐藏悬浮图标
     *
     * @param distance
     */
    private void hideFloatImage(int distance) {
        Log.e("Tag", "隐藏动画执行");
        isShowFloatImage = false;
        //位移动画
        TranslateAnimation ta = new TranslateAnimation(
                0,//起始x坐标,10表示与初始位置相距10
                distance,//结束x坐标
                0,//起始y坐标
                0);//结束y坐标（正数向下移动）
        ta.setDuration(300);

        //渐变动画
        AlphaAnimation al = new AlphaAnimation(1f, 0.5f);
        al.setDuration(300);

        AnimationSet set = new AnimationSet(true);
        //动画完成后不回到原位
        set.setFillAfter(true);
        set.addAnimation(ta);
        set.addAnimation(al);
        ivCart.startAnimation(set);

    }

    //得到手机屏幕宽高
    private int[] getDisplayMetrics(Context context) {
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        int widthPixels = mDisplayMetrics.widthPixels;
        int heightPixels = mDisplayMetrics.heightPixels;
        int[] array = {widthPixels, heightPixels};
        return array;
    }





}
