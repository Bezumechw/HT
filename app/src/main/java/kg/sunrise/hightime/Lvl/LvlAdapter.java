package kg.sunrise.hightime.Lvl;

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


public class LvlAdapter extends RecyclerView.Adapter<LvlAdapter.ViewHolder> {

    OnClickListener onClickListener;

    public interface  OnClickListener {
        void onClickItem(int pos);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
    ArrayList<Lvl> list;
    public LvlAdapter(ArrayList<Lvl> list){
        this.list = list;
    }
    @NonNull
    @Override
    public LvlAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_person, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LvlAdapter.ViewHolder viewHolder, int i) {
        Lvl lvl = list.get(i);
        Picasso.get().load(lvl.thumbnail).into(viewHolder.imageView);
        viewHolder.textView.setText(lvl.name);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageLevel);
            textView = itemView.findViewById(R.id.textLevel);
            itemView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onClickItem(getAdapterPosition());
                }
            });

        }
    }

}
