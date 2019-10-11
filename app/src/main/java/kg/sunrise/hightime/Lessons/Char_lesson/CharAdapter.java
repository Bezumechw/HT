package kg.sunrise.hightime.Lessons.Char_lesson;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

import kg.sunrise.hightime.R;

public class CharAdapter extends RecyclerView.Adapter<CharAdapter.ViewHolder> {

    OnClickListener onClickListener;

    public interface  OnClickListener {
        void onClickItem(int pos);

    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    ArrayList<Videos> list;

    public CharAdapter(ArrayList<Videos> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_char, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Videos videos = list.get(i);
        viewHolder.textName.setText((videos.getName()).replaceAll("Pre ntermatide", ""));

    }

    @Override
    public int getItemCount() {
        return list.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textName;
        ImageView imageButton;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
            imageButton = itemView.findViewById(R.id.imageButton);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onClickItem(getAdapterPosition());
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onClickItem(getAdapterPosition());
                }
            });
        }
    }
}

