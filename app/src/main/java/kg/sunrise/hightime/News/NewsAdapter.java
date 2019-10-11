package kg.sunrise.hightime.News;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import kg.sunrise.hightime.R;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    OnClickListener onClickListener;

    public interface  OnClickListener {
        void onClickItem(int pos);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    ArrayList<News> list;

    public  NewsAdapter(ArrayList<News> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_news, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        News news = list.get(i);
        Picasso.get().load(news.thumbmailS).into(viewHolder.imageView);
        viewHolder.textName.setText(news.name);
        viewHolder.textShort.setText((news.shortInfo)
                .replaceAll("&amp;laquo;", "")
                .replaceAll("&amp;raquo;", "")
                .replaceAll("&amp;ndash",""));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textName, textShort;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textName = itemView.findViewById(R.id.textName);
            textShort = itemView.findViewById(R.id.textShort);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onClickItem(getAdapterPosition());
                }
            });
        }
    }
}
