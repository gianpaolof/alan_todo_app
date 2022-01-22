package com.alan.todo.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.alan.todo.Models.TodoItem;
import com.alan.todo.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    private Context context;
    private List<TodoItem> todoItems;

    public TodoAdapter(Context context, List<TodoItem> todoItems) {
        this.context = context;
        this.todoItems = todoItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.todo_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TodoItem todoItem = todoItems.get(position);
        holder.titleTV.setText(todoItem.getTitle());
        holder.descriptionTV.setText(todoItem.getDescription());
        if (todoItem.isComplete())
            holder.todoCheck.setChecked(true);

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm", Locale.getDefault());
        String date = format.format(new Date(todoItem.getTimeStamp()));
        holder.timeStampTV.setText(date);
    }

    @Override
    public int getItemCount() {
        return todoItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView todoCard;
        public CheckBox todoCheck;
        public TextView titleTV;
        public TextView descriptionTV;
        public TextView timeStampTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            todoCard = itemView.findViewById(R.id.todo_card);
            todoCheck = itemView.findViewById(R.id.todo_check);
            titleTV = itemView.findViewById(R.id.todo_title_tv);
            descriptionTV = itemView.findViewById(R.id.description_tv);
            timeStampTV = itemView.findViewById(R.id.timestamp_tv);
        }
    }
}
