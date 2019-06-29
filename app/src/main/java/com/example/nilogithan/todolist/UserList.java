package com.example.nilogithan.todolist;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.AsyncLayoutInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class UserList extends ArrayAdapter<tasks> {

    private Activity context;
    private List<tasks> userlist;

    public UserList(Activity context, List<tasks> artistList){

        super(context, R.layout.task_view, artistList);
        this.context = context;
        this.userlist = artistList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View ListViewItem = inflater.inflate(R.layout.task_view, null, true);
        TextView textViewName = (TextView) ListViewItem.findViewById(R.id.viewName);
        TextView textViewDescription = (TextView) ListViewItem.findViewById(R.id.viewDisc);
        TextView textViewPriority = (TextView) ListViewItem.findViewById(R.id.viewPriority);
        TextView textViewDate = (TextView) ListViewItem.findViewById(R.id.viewDate);
        tasks user = userlist.get(position);

        textViewName.setText(user.getName());
        textViewDescription.setText(user.getDiscription());
        textViewPriority.setText(user.getPriority());
        textViewDate.setText(user.getDuedate());

        return ListViewItem;
    }
}
