package com.swufe.scoresforapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.swufe.scoresforapp.Dao.QuestionDao;
import com.swufe.scoresforapp.entities.Question;

import java.util.List;

public class insertdata extends AppCompatActivity implements View.OnClickListener {

    private EditText et_selectedb;
    private EditText et_selecteda;
    private EditText et_optionA;
    private EditText et_optionB;
    private EditText et_question;
    private QuestionDao questionDao;

    @Override
    protected void onCreate(@Nullable @androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insertdata);

        et_question = findViewById(R.id.et_question);
        et_optionA = findViewById(R.id.et_optionA);
        et_optionB = findViewById(R.id.et_optionB);
        et_selecteda = findViewById(R.id.et_selecteda);
        et_selectedb = findViewById(R.id.et_selectedb);
        questionDao = Myapplication.getInstance().getQuestionDatabase().questionDao();

        //Question que =new Question("当你要外出一整天，你会","计划你要做什么和在什么时候做","说去就去","J","P");



        findViewById(R.id.btn_insert).setOnClickListener(this);
        findViewById(R.id.btn_delete).setOnClickListener(this);
        findViewById(R.id.btn_query).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        String question = et_question.getText().toString().trim();
        String optionA = et_optionA.getText().toString().trim();
        String optionB = et_optionB.getText().toString().trim();
        String selecteda = et_selecteda.getText().toString().trim();
        String selectedb = et_selectedb.getText().toString().trim();



        final Question que = new Question(question, optionA, optionB, selecteda, selectedb);

        switch (v.getId()) {
            case R.id.btn_insert:
                if (question.isEmpty() || optionA.isEmpty() || optionB.isEmpty() || selecteda.isEmpty() || selectedb.isEmpty()) {
                    Toast.makeText(this, "请填写所有字段", Toast.LENGTH_SHORT).show();
                    return;
                }
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            questionDao.insert(que);
                            insertdata.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    insertdata.this.clearFields();
                                }
                            });
                        } catch (Exception e) {
                            Log.e("DB_INSERT", "插入失败", e);
                        }
                    }
                }).start();
                break;
            case R.id.btn_delete:
                questionDao.deleteByQuestionText("T");
                Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_query:

                List<Question> list = questionDao.queryAll();

                if (list == null || list.isEmpty()) {
                    Toast.makeText(this, "数据库为空", Toast.LENGTH_SHORT).show();
                } else {
                    for (Question b : list) {
                        Log.d("QuestionInfo", b.toString());
                    }
                    Toast.makeText(this, "查询成功", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void clearFields() {
        et_question.setText("");
        et_optionA.setText("");
        et_optionB.setText("");
        et_selecteda.setText("");
        et_selectedb.setText("");
    }


}

