package com.swufe.scoresforapp.FourMain;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qlh.dropdownmenu.DropDownMenu;
import com.qlh.dropdownmenu.view.MultiMenusView;
import com.qlh.dropdownmenu.view.SingleMenuView;
import com.swufe.scoresforapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MajorFragment extends Fragment {

    private FragmentActivity instance;
    private String[] headers; // 菜单头部选项
    private List<View> popupViews = new ArrayList<>(); // 菜单列表视图
    private DropDownMenu mDropDownMenu;
    private MultiMenusView multiMenusView; // 多级菜单
    private SingleMenuView singleMenuView; // 单级菜单
    // 内容视图
    private View mView, contentView;
    private TextView tvScore;

    // 用于记录选中的专业和学校
    private String selectedMajor;
    private String selectedSchool;

    public static final String CONTENT = "MajorFragment";

    // 模拟专业和学校对应的分数数据
    private Map<String, Map<String, Integer>> scoreData = new HashMap<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_major_fragment, container, false);
        init();
        initScoreData();
        return mView;
    }

    private void init() {
        instance = this.getActivity();
        initView();
        initMenus();
        initListener();
    }

    private void initView() {
        mDropDownMenu = mView.findViewById(R.id.dropDownMenu);
        tvScore = mView.findViewById(R.id.tv_score);
    }

    @SuppressLint("InflateParams")
    private void initMenus() {
        popupViews.clear();
        headers = new String[]{"专业类别", "开设学校"};
        // 初始化多级菜单
        final String[] levelOneMenu = {"医学类", "理学类", "文学类", "工学类"};
        final String[][] levelTwoMenu = {
                {"基础医学", "预防医学", "营养学", "临床医学", "麻醉学", "医学影像", "放射医学", "康复治疗学", "精神医学", "口腔医学", "口腔修复", "中医学"},
                {"数学与应用数学", "信息与计算科学", "数学基础科学", "物理学"},
                {"汉语言文学", "英语", "德语", "法语"},
                {"地质工程", "矿物资源学", "电子信息工程", "水利水电工程"}
        };
        multiMenusView = new MultiMenusView(instance, levelOneMenu, levelTwoMenu);
        popupViews.add(multiMenusView);
        // 初始化单级菜单
        String[] addressArrays = {"不限", "北京大学", "深圳大学", "复旦大学", "厦门大学", "武汉大学", "电子科技大学"};
        singleMenuView = new SingleMenuView(instance, addressArrays);
        popupViews.add(singleMenuView);
        // 初始化内容视图
        contentView = LayoutInflater.from(instance).inflate(R.layout.content_view, null);
        // 装载
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, contentView);
    }

    private void initListener() {
        // 下拉菜单
        multiMenusView.setOnSelectListener(new MultiMenusView.OnSelectListener() {
            @Override
            public void getValue(String showText) {
                mDropDownMenu.setTabText(showText);
                mDropDownMenu.closeMenu();
                // 记录选中的专业
                selectedMajor = showText;
                showScore();
            }
        });
        singleMenuView.setOnSelectListener(new SingleMenuView.OnSelectListener() {
            @Override
            public void getValue(int position, String showText) {
                mDropDownMenu.setTabText(showText);
                mDropDownMenu.closeMenu();
                // 记录选中的学校
                selectedSchool = showText;
                showScore();
            }
        });
    }

    private void initScoreData() {
        // 初始化模拟分数数据
        Map<String, Integer> pekingUnivScores = new HashMap<>();
        pekingUnivScores.put("基础医学", 650);
        pekingUnivScores.put("数学与应用数学", 680);
        pekingUnivScores.put("汉语言文学", 660);
        pekingUnivScores.put("地质工程", 640);
        scoreData.put("北京大学", pekingUnivScores);

        Map<String, Integer> shenzhenUnivScores = new HashMap<>();
        shenzhenUnivScores.put("预防医学", 620);
        shenzhenUnivScores.put("信息与计算科学", 630);
        shenzhenUnivScores.put("英语", 610);
        shenzhenUnivScores.put("矿物资源学", 600);
        scoreData.put("深圳大学", shenzhenUnivScores);

        // 可以继续添加其他学校的分数数据
    }

    private void showScore() {
        if (selectedMajor == null || selectedSchool == null) {
            return;
        }

        if ("不限".equals(selectedSchool)) {
            tvScore.setText("请选择具体学校");
            return;
        }

        Map<String, Integer> schoolScores = scoreData.get(selectedSchool);
        if (schoolScores != null) {
            Integer score = schoolScores.get(selectedMajor);
            if (score != null) {
                tvScore.setText("专业：" + selectedMajor + "，学校：" + selectedSchool + "，分数：" + score);
            } else {
                tvScore.setText("该学校未开设此专业");
            }
        } else {
            tvScore.setText("未找到该学校的分数数据");
        }
    }

}
