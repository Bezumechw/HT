package kg.sunrise.hightime.Level.Test;

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

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {

    AnswerAdapter adapter;
    ArrayList<Test> list;

    public TestAdapter(ArrayList<Test> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_test, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Test test = list.get(i);
        viewHolder.textTheTask.setText(test.title);
        if (i != 0){
            viewHolder.textTheTask.setVisibility(View.GONE);
        }
        viewHolder.textOption.setText(test.content);


        viewHolder.recyclerView.setLayoutManager(new LinearLayoutManager(viewHolder.context));
        adapter = new AnswerAdapter(test.answers);//вызов другого ресайкл
        viewHolder.recyclerView.setAdapter(adapter);
        viewHolder.setIsRecyclable(false);
        adapter.notifyDataSetChanged();
    }

    public ArrayList<Test> getTests(){
        return list;
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textTheTask, textOption;
        RecyclerView recyclerView;
        Context context;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTheTask = itemView.findViewById(R.id.textTheTask);
            textOption = itemView.findViewById(R.id.textOption);
            recyclerView = itemView.findViewById(R.id.recyclerView);
            context = itemView.getContext();
        }
    }
}
