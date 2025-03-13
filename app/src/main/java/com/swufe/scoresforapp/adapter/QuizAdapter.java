package com.swufe.scoresforapp.adapter;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.swufe.scoresforapp.R;
import com.swufe.scoresforapp.entities.Question;

import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuestionViewHolder> {

    private List<Question> questionList;
    private String[] userAnswers;

    public QuizAdapter(List<Question> questionList) {
        this.questionList = questionList;
        this.userAnswers = new String[questionList.size()];
    }

    @Override
    public QuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question, parent, false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final QuestionViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        final Question question = questionList.get(position);
        holder.questionText.setText(question.getQuestionText());
        holder.optionA.setText(question.getOptionA());
        holder.optionB.setText(question.getOptionB());

        holder.optionA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAnswers[position] = question.getSelecteda();
            }
        });
        holder.optionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userAnswers[position] = question.getSelectedb();
            }
        });
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public String getAnswerForQuestion(int position) {
        return userAnswers[position];
    }

    class QuestionViewHolder extends RecyclerView.ViewHolder {

        TextView questionText;
        Button optionA, optionB;

        public QuestionViewHolder(View itemView) {
            super(itemView);
            questionText = itemView.findViewById(R.id.question_text);
            optionA = itemView.findViewById(R.id.option_a);
            optionB = itemView.findViewById(R.id.option_b);
        }
    }
}
