package kg.sunrise.hightime.Reviews.ReviewOne;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import kg.sunrise.hightime.R;

public class ReviewOneAdapter extends RecyclerView.Adapter<ReviewOneAdapter.ViewHolder> {

    ArrayList<ReviewOne> list;

    public  ReviewOneAdapter(ArrayList<ReviewOne> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_review, viewGroup, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ReviewOne reviewOne = list.get(i);
        if (reviewOne.thumbnail != null) {
            Picasso.get().load(reviewOne.getThumbnail()).into(viewHolder.imageAvatar);
        }
        viewHolder.textName.setText(reviewOne.name);
        viewHolder.textDate.setText(reviewOne.date);
        viewHolder.textTime.setText(reviewOne.time);
        if (reviewOne.comment != null) {
            viewHolder.textComment.setText((reviewOne.comment));
            Log.e("TAG", "response: " + reviewOne.comment);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageAvatar;
        TextView textName, textDate, textComment, textTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageAvatar = itemView.findViewById(R.id.imageAvatar);
            textName = itemView.findViewById(R.id.textName);
            textDate = itemView.findViewById(R.id.textDate);
            textTime = itemView.findViewById(R.id.textTime);
            textComment = itemView.findViewById(R.id.textComment);
        }
    }
}
