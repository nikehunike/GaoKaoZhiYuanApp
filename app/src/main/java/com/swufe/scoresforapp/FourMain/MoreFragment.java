package com.swufe.scoresforapp.FourMain;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.swufe.scoresforapp.Dao.QuestionDao;
import com.swufe.scoresforapp.Myapplication;
import com.swufe.scoresforapp.R;
import com.swufe.scoresforapp.adapter.QuizAdapter;
import com.swufe.scoresforapp.entities.Question;

import java.util.ArrayList;
import java.util.List;

public class MoreFragment extends Fragment {

    public static final String CONTENT = "content";
    private View view;
    private RecyclerView recyclerView;
    private QuizAdapter quizAdapter;
    private List<Question> questionList = new ArrayList<>();
    private Button submitButton;
    private TextView resultTextView;

    private QuestionDao questionDao;

    private StringBuilder mbtiResult = new StringBuilder();
    private List<Question> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_more_fragment, container, false);
        questionDao= Myapplication.getInstance().getQuestionDatabase().questionDao();


        initView();

        return view;
    }

    private void initView() {

        Log.d("DrawerLayoutActivity", "position: start" );
        recyclerView = view.findViewById(R.id.recycler_view);
        submitButton = view.findViewById(R.id.submit_button);
        resultTextView = view.findViewById(R.id.result_text);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);


        //添加问题
        try
        {
             list = questionDao.queryAll();
        }
        catch (Exception e)
        {
            Log.e("DB_query", "查询失败", e);
        }
//        if (list == null || list.isEmpty()) {
//            Toast.makeText(getContext(), "数据库为空", Toast.LENGTH_SHORT).show();
//        } else {
//            for (Question b : list) {
//                Log.d("QuestionInfo", b.toString());
//            }
//            Toast.makeText(getContext(), "查询成功", Toast.LENGTH_SHORT).show();
//        }

        quizAdapter = new QuizAdapter(list);
        recyclerView.setAdapter(quizAdapter);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoreFragment.this.calculateResult();
            }
        });
    }

    private void calculateResult() {
        int scoreE = 0,scoreI = 0,scoreS = 0,scoreN = 0,scoreT = 0,scoreF = 0,scoreJ = 0,scoreP = 0;
        mbtiResult.setLength(0); // 清空上次结果
        for (int i = 0; i < list.size(); i++) {
            String answer = quizAdapter.getAnswerForQuestion(i);
            if (answer != null) {
                switch (answer){
                    case "E":
                        scoreE++;
                        break;
                    case "I":
                        scoreI++;
                        break;
                    case "S":
                        scoreS++;
                        break;
                    case "N":
                        scoreN++;
                        break;
                    case "T":
                        scoreT++;
                        break;
                    case "F":
                        scoreF++;
                        break;
                    case "J":
                        scoreJ++;
                        break;
                    case "P":
                        scoreP++;
                        break;
                }
            }
        }

        if(scoreE>scoreI)
        {
            mbtiResult.append("E");
        }
        else
        {
            mbtiResult.append("I");
        }
        if(scoreS>scoreN)
        {
            mbtiResult.append("S");
        }
        else
        {
            mbtiResult.append("N");
        }
        if(scoreT>scoreF)
        {
            mbtiResult.append("T");
        }
        else
        {
            mbtiResult.append("f");
        }
        if(scoreJ>scoreP)
        {
            mbtiResult.append("J");
        }
        else
        {
            mbtiResult.append("P");
        }
        resultTextView.setText("你的 MBTI 类型为: " + mbtiResult.toString());
    }
}
