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

    // 模拟专业和学校对应的分数数据，包含详细信息
    private Map<String, Map<String, Map<String, Object>>> scoreData = new HashMap<>();

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
        String[] addressArrays = {"不限", "北京大学", "深圳大学", "复旦大学", "厦门大学", "武汉大学", "电子科技大学", "清华大学", "浙江大学", "南京大学", "上海交通大学", "中山大学"};
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
        // 北京大学
        Map<String, Map<String, Object>> pekingUnivScores = new HashMap<>();
        addMajorInfo(pekingUnivScores, "基础医学", 650, 1, new int[]{640, 655, 650});
        addMajorInfo(pekingUnivScores, "数学与应用数学", 680, 2, new int[]{670, 685, 680});
        addMajorInfo(pekingUnivScores, "汉语言文学", 660, 3, new int[]{650, 665, 660});
        addMajorInfo(pekingUnivScores, "电子信息工程", 675, 4, new int[]{665, 680, 675});
        scoreData.put("北京大学", pekingUnivScores);

        // 深圳大学
        Map<String, Map<String, Object>> shenzhenUnivScores = new HashMap<>();
        addMajorInfo(shenzhenUnivScores, "预防医学", 620, 3, new int[]{610, 625, 620});
        addMajorInfo(shenzhenUnivScores, "信息与计算科学", 630, 4, new int[]{620, 635, 630});
        addMajorInfo(shenzhenUnivScores, "英语", 610, 5, new int[]{600, 615, 610});
        addMajorInfo(shenzhenUnivScores, "地质学", 605, 6, new int[]{595, 610, 605});
        scoreData.put("深圳大学", shenzhenUnivScores);

        // 复旦大学
        Map<String, Map<String, Object>> fudanUnivScores = new HashMap<>();
        addMajorInfo(fudanUnivScores, "临床医学", 670, 2, new int[]{660, 675, 670});
        addMajorInfo(fudanUnivScores, "物理学", 665, 3, new int[]{655, 670, 665});
        addMajorInfo(fudanUnivScores, "法语", 655, 4, new int[]{645, 660, 655});
        addMajorInfo(fudanUnivScores, "计算机科学与技术", 680, 3, new int[]{670, 685, 680});
        scoreData.put("复旦大学", fudanUnivScores);

        // 厦门大学
        Map<String, Map<String, Object>> xiamenUnivScores = new HashMap<>();
        addMajorInfo(xiamenUnivScores, "康复治疗学", 635, 4, new int[]{625, 640, 635});
        addMajorInfo(xiamenUnivScores, "数学基础科学", 640, 5, new int[]{630, 645, 640});
        addMajorInfo(xiamenUnivScores, "德语", 630, 6, new int[]{620, 635, 630});
        addMajorInfo(xiamenUnivScores, "海洋科学", 645, 5, new int[]{635, 650, 645});
        scoreData.put("厦门大学", xiamenUnivScores);

        // 武汉大学
        Map<String, Map<String, Object>> wuhanUnivScores = new HashMap<>();
        addMajorInfo(wuhanUnivScores, "口腔医学", 660, 3, new int[]{650, 665, 660});
        addMajorInfo(wuhanUnivScores, "电子信息工程", 655, 4, new int[]{645, 660, 655});
        addMajorInfo(wuhanUnivScores, "汉语言文学", 645, 5, new int[]{635, 650, 645});
        addMajorInfo(wuhanUnivScores, "测绘工程", 650, 4, new int[]{640, 655, 650});
        scoreData.put("武汉大学", wuhanUnivScores);

        // 电子科技大学
        Map<String, Map<String, Object>> uestcScores = new HashMap<>();
        addMajorInfo(uestcScores, "电子信息工程", 650, 4, new int[]{640, 655, 650});
        addMajorInfo(uestcScores, "地质工程", 630, 5, new int[]{620, 635, 630});
        addMajorInfo(uestcScores, "矿物资源学", 620, 6, new int[]{610, 625, 620});
        addMajorInfo(uestcScores, "通信工程", 660, 3, new int[]{650, 665, 660});
        scoreData.put("电子科技大学", uestcScores);

        // 清华大学
        Map<String, Map<String, Object>> tsinghuaUnivScores = new HashMap<>();
        addMajorInfo(tsinghuaUnivScores, "基础医学", 660, 1, new int[]{650, 665, 660});
        addMajorInfo(tsinghuaUnivScores, "数学与应用数学", 690, 1, new int[]{680, 695, 690});
        addMajorInfo(tsinghuaUnivScores, "汉语言文学", 670, 2, new int[]{660, 675, 670});
        addMajorInfo(tsinghuaUnivScores, "机械工程", 685, 2, new int[]{675, 690, 685});
        scoreData.put("清华大学", tsinghuaUnivScores);

        // 浙江大学
        Map<String, Map<String, Object>> zhejiangUnivScores = new HashMap<>();
        addMajorInfo(zhejiangUnivScores, "临床医学", 675, 2, new int[]{665, 680, 675});
        addMajorInfo(zhejiangUnivScores, "物理学", 670, 3, new int[]{660, 675, 670});
        addMajorInfo(zhejiangUnivScores, "法语", 660, 4, new int[]{650, 665, 660});
        addMajorInfo(zhejiangUnivScores, "化学工程与技术", 670, 3, new int[]{660, 675, 670});
        scoreData.put("浙江大学", zhejiangUnivScores);

        // 南京大学
        Map<String, Map<String, Object>> nankaiUnivScores = new HashMap<>();
        addMajorInfo(nankaiUnivScores, "康复治疗学", 640, 4, new int[]{630, 645, 640});
        addMajorInfo(nankaiUnivScores, "数学基础科学", 645, 5, new int[]{635, 650, 645});
        addMajorInfo(nankaiUnivScores, "德语", 635, 6, new int[]{625, 640, 635});
        addMajorInfo(nankaiUnivScores, "天文学", 650, 4, new int[]{640, 655, 650});
        scoreData.put("南京大学", nankaiUnivScores);

        // 上海交通大学
        Map<String, Map<String, Object>> sjtuScores = new HashMap<>();
        addMajorInfo(sjtuScores, "临床医学", 680, 1, new int[]{670, 685, 680});
        addMajorInfo(sjtuScores, "电子信息工程", 675, 2, new int[]{665, 680, 675});
        addMajorInfo(sjtuScores, "机械工程", 670, 3, new int[]{660, 675, 670});
        addMajorInfo(sjtuScores, "生物医学工程", 670, 3, new int[]{660, 675, 670});
        scoreData.put("上海交通大学", sjtuScores);

        // 中山大学
        Map<String, Map<String, Object>> sunYatSenUnivScores = new HashMap<>();
        addMajorInfo(sunYatSenUnivScores, "口腔医学", 665, 2, new int[]{655, 670, 665});
        addMajorInfo(sunYatSenUnivScores, "计算机科学与技术", 660, 3, new int[]{650, 665, 660});
        addMajorInfo(sunYatSenUnivScores, "物理学", 655, 4, new int[]{645, 660, 655});
        addMajorInfo(sunYatSenUnivScores, "地理学", 650, 5, new int[]{640, 655, 650});
        scoreData.put("中山大学", sunYatSenUnivScores);
    }

    private void addMajorInfo(Map<String, Map<String, Object>> schoolScores, String major, int score, int rank, int[] scoresInRecentYears) {
        Map<String, Object> majorInfo = new HashMap<>();
        majorInfo.put("score", score);
        majorInfo.put("rank", rank);
        majorInfo.put("scoresInRecentYears", scoresInRecentYears);
        schoolScores.put(major, majorInfo);
    }

    private void showScore() {
        StringBuilder result = new StringBuilder();
        if (selectedMajor != null && "不限".equals(selectedSchool)) {
            // 只选专业不选学校，展示所有有这个专业的学校分数
            for (Map.Entry<String, Map<String, Map<String, Object>>> entry : scoreData.entrySet()) {
                String school = entry.getKey();
                Map<String, Map<String, Object>> schoolScores = entry.getValue();
                if (schoolScores.containsKey(selectedMajor)) {
                    Map<String, Object> majorInfo = schoolScores.get(selectedMajor);
                    int score = (int) majorInfo.get("score");
                    result.append("学校：").append(school).append("，专业：").append(selectedMajor).append("，分数：").append(score).append("\n");
                }
            }
            if (result.length() == 0) {
                result.append("未找到开设此专业的学校");
            }
        } else if (selectedSchool != null && !"不限".equals(selectedSchool) && selectedMajor == null) {
            // 只选学校，展示这个学校专业的详细信息
            Map<String, Map<String, Object>> schoolScores = scoreData.get(selectedSchool);
            if (schoolScores != null) {
                for (Map.Entry<String, Map<String, Object>> entry : schoolScores.entrySet()) {
                    String major = entry.getKey();
                    Map<String, Object> majorInfo = entry.getValue();
                    int score = (int) majorInfo.get("score");
                    int rank = (int) majorInfo.get("rank");
                    int[] scoresInRecentYears = (int[]) majorInfo.get("scoresInRecentYears");
                    result.append("专业：").append(major).append("，分数：").append(score).append("，排名：").append(rank).append("，近几年录取分数线：");
                    for (int i = 0; i < scoresInRecentYears.length; i++) {
                        result.append(scoresInRecentYears[i]);
                        if (i < scoresInRecentYears.length - 1) {
                            result.append(", ");
                        }
                    }
                    result.append("\n");
                }
            } else {
                result.append("未找到该学校的分数数据");
            }
        } else if (selectedMajor != null && selectedSchool != null && !"不限".equals(selectedSchool)) {
            // 两者都选
            Map<String, Map<String, Object>> schoolScores = scoreData.get(selectedSchool);
            if (schoolScores != null) {
                Map<String, Object> majorInfo = schoolScores.get(selectedMajor);
                if (majorInfo != null) {
                    int score = (int) majorInfo.get("score");
                    result.append("专业：").append(selectedMajor).append("，学校：").append(selectedSchool).append("，分数：").append(score);
                } else {
                    result.append("该学校未开设此专业");
                }
            } else {
                result.append("未找到该学校的分数数据");
            }
        } else {
            result.append("请选择专业或学校");
        }
        tvScore.setText(result.toString());
    }
}