package kg.sunrise.hightime.Lessons.Char_lesson.Test;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

//import com.squareup.picasso.Picasso;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import kg.sunrise.hightime.R;

public class AnswersAdapter extends RecyclerView.Adapter<AnswersAdapter.ViewHolder> {

    int status = -1;

    ArrayList<Answers> list;
    public  AnswersAdapter(ArrayList<Answers> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public AnswersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_answer, viewGroup, false);
        return new AnswersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnswersAdapter.ViewHolder viewHolder, int i) {
        Answers answers = list.get(i);
        viewHolder.textContent.setVisibility(View.GONE);
        if (answers.thumbnail.isEmpty()){
            viewHolder.imageView.setVisibility(View.GONE);
        }else if (!answers.thumbnail.isEmpty()){
            Picasso.get().load(answers.thumbnail).into(viewHolder.imageView);
        }

        viewHolder.radio.setText((answers.name).replaceAll("&amp;rsquo;", "")
        .replaceAll("&amp;#039;", "")
        .replaceAll("&amp;ndash", "")
        .replaceAll("&amp;ndash", "")
        .replaceAll("&amp;rdquo", "")
        .replaceAll("&amp;raquo", "")
        .replaceAll("&amp;laquo", "")
        .replaceAll("&amp;ldquo", ""));

        viewHolder.radio.setChecked(i == status);
        viewHolder.radio.setChecked(answers.isStatus());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RadioButton radio;
        TextView textContent;
        ImageView imageView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        radio = itemView.findViewById(R.id.radio);
        imageView = itemView.findViewById(R.id.imageTest);
        textContent = itemView.findViewById(R.id.textContent);
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Answers answers: list){
                    answers.setStatus(false);
                    }
                    list.get(getAdapterPosition()).setStatus(true);
                    notifyDataSetChanged();
                }
            };
        itemView.setOnClickListener(clickListener);
        radio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Answers answers: list){
                    answers.setStatus(false); }
                list.get(getAdapterPosition()).setStatus(true);
                notifyDataSetChanged();
            }
        });
    }
    }
}
