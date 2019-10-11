package kg.sunrise.hightime.Lessons.Char_lesson.Test;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import kg.sunrise.hightime.R;

public class LessonTestAdapter extends  RecyclerView.Adapter<LessonTestAdapter.ViewHolder> {

    AnswersAdapter adapter;
    ArrayList<Tests> list;

    public LessonTestAdapter(ArrayList<Tests> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_lessons_tests, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Tests tests = list.get(i);
        viewHolder.textName.setText(tests.getName());
        if (i != 0){
            viewHolder.textName.setVisibility(View.GONE);
        }
        viewHolder.textContent.setText(tests.getContent().replaceAll("&amp;amp;nbsp;", "")
                .replaceAll("&amp;amp;hellip;", "")
                .replaceAll("&amp;amp;ndash;", "")
                .replaceAll("&amp;amp;mdash;", "")
                .replaceAll("&amp;amp;rsquo;", "")
                .replaceAll("&amp;amp;rdquo;", "")
                .replaceAll("&amp;amp;idquo;", "")
                .replaceAll("&amp;amp;lsquo;", "")
                .replaceAll("&amp;amp;quot;", "")
                .replaceAll("&amp;amp;ldquo;", "")
                .replaceAll("&amp;amp;laquo;", "")
                .replaceAll("&amp;amp;raquo;", "")
                .replaceAll("&amp;amp;shy;", "")
                .replaceAll("&amp;amp;eth;", "")
                .replaceAll("&amp;amp;theta;", "")
                .replaceAll("&amp;amp;delta;", "")
                .replaceAll("&amp;amp;#39;", ""));


        viewHolder.recyclerView.setLayoutManager(new LinearLayoutManager(viewHolder.context));
        adapter = new AnswersAdapter(tests.answers);
        viewHolder.recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    public ArrayList<Tests> getTests(){
        return list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textName, textContent;
        RecyclerView recyclerView;
        Context context;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
            textContent = itemView.findViewById(R.id.textContent);
            recyclerView = itemView.findViewById(R.id.recyclerView);
            context = itemView.getContext();
        }
    }
}