package com.bugrahankaramollaoglu.catchflappy_app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder> {

    private List<Integer> scoreList;

    public ScoreAdapter(List<Integer> scoreList) {
        this.scoreList = scoreList;
        Collections.sort(scoreList, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        });
        Collections.reverse(scoreList);

    }

    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        return new ScoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
        Integer score = scoreList.get(position);
        holder.bind(score, position + 1);
    }

    @Override
    public int getItemCount() {
        return scoreList.size();
    }

    public class ScoreViewHolder extends RecyclerView.ViewHolder {

        private TextView scoreTextView;
        private TextView scoreNumberText;

        public ScoreViewHolder(@NonNull View itemView) {
            super(itemView);
            scoreTextView = itemView.findViewById(R.id.scoreText);
            scoreNumberText = itemView.findViewById(R.id.scoreNumberText);
        }

        public void bind(Integer score, int position) {
            scoreTextView.setText(String.valueOf(score));
            scoreNumberText.setText(String.valueOf(position));
        }
    }
}
