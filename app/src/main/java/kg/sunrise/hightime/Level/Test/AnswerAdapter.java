package kg.sunrise.hightime.Level.Test;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;

import java.util.ArrayList;

import kg.sunrise.hightime.R;

public class AnswerAdapter extends RecyclerView.Adapter<AnswerAdapter.ViewHolder> {

    int status = -1;

    ArrayList<Answer> list;
    public  AnswerAdapter(ArrayList<Answer> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_answer, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Answer answer = list.get(i);
//        if (answers.thumbnail.isEmpty()){
            viewHolder.imageView.setVisibility(View.GONE);
//        }else if (!answers.thumbnail.isEmpty()){
//            Picasso.get().load(answers.thumbnail).into(viewHolder.imageView);
//        }
        viewHolder.radio.setText(answer.title);
        viewHolder.radio.setChecked(i == status);
        viewHolder.radio.setChecked(answer.isStatus());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RadioButton radio;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            radio = itemView.findViewById(R.id.radio);
            imageView = itemView.findViewById(R.id.imageTest);

            View.OnClickListener clickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (Answer answer: list){
                        answer.setStatus(false);
                    }
                    list.get(getAdapterPosition()).setStatus(true);
                    notifyDataSetChanged();
                }
            };
            itemView.setOnClickListener(clickListener);
            radio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (Answer answer: list){
                        answer.setStatus(false);
                    }
                    list.get(getAdapterPosition()).setStatus(true);
                    notifyDataSetChanged();
                }
            });
//            radio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    if (isChecked) {
//                        radio.setTextColor(Color.BLUE);
////                        изменить цвет при нажатии
//                    }
//                }
//            });
        }
    }
}
