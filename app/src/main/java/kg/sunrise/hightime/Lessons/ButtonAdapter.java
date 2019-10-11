package kg.sunrise.hightime.Lessons;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import kg.sunrise.hightime.R;

public class ButtonAdapter extends RecyclerView.Adapter<ButtonAdapter.ViewHolder> {

    OnClickListener onClickListener;

    public interface  OnClickListener {
        void onClickItem(int pos);
    }
    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    ArrayList<ButtonNames> list;
    public ButtonAdapter(ArrayList<ButtonNames> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_button, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ButtonNames buttonNames = list.get(i);
        viewHolder.button.setText(buttonNames.getName().replaceAll("Урок", "")
                .replaceAll("Заключение", "Total")
                .replaceAll("Подтверждение уроков", "Total"));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        Button button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onClickItem(getAdapterPosition());
                }
            });

        }
    }
}
