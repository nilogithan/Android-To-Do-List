package com.example.nilogithan.todolist;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TaskList extends AppCompatActivity {

    ListView listView;
    List<tasks> userlist;
    DatabaseReference databaseuser;

    private FloatingActionButton addButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        addButton = (FloatingActionButton)findViewById(R.id.addBtn);
        databaseuser = FirebaseDatabase.getInstance().getReference("tasks");
        listView = (ListView)findViewById(R.id.taskList);
        userlist = new ArrayList<>();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskList.this, AddTask.class);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                tasks tasks = userlist.get(position);
                showUpdateDialog(tasks.getId(), tasks.getName());
                return false;
            }
        });

    }

    private void showUpdateDialog(final String userId, String userName){
        AlertDialog.Builder dialogBuilder  = new AlertDialog.Builder(TaskList.this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_task, null);
        dialogBuilder.setView(dialogView);

        final EditText updateName = (EditText)dialogView.findViewById(R.id.updateName);
        final EditText updateDiscription = (EditText)dialogView.findViewById(R.id.updateDisc);
        final Spinner updatePriority = (Spinner)dialogView.findViewById(R.id.updateSpinner);
        final EditText updateDate = (EditText)dialogView.findViewById(R.id.updateDate);
        final Button updateButton = (Button)dialogView.findViewById(R.id.updateBtn);
        final Button deleteButton = (Button)dialogView.findViewById(R.id.deleteBtn);
        dialogBuilder.setTitle("Updating  "+ userName);
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = updateName.getText().toString();
                String discription = updateDiscription.getText().toString();
                String priority = updatePriority.getSelectedItem().toString();
                String date = updateDate.getText().toString();

                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(discription) ||TextUtils.isEmpty(date)){
                    Toast.makeText(TaskList.this, "Fields are Empty", Toast.LENGTH_LONG).show();
                    return;
                }
                updateTask(userId,name,discription,priority,date);
                alertDialog.dismiss();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteTask(userId);
                alertDialog.dismiss();
            }
        });
    }

    private void deleteTask(String userId) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("tasks").child(userId);

        databaseReference.removeValue();

        Toast.makeText(TaskList.this,"Task Removed", Toast.LENGTH_LONG).show();
    }

    private boolean updateTask(String id, String name, String discription, String priority, String duedate){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("tasks").child(id);
        tasks tasks = new tasks(id,name,discription,priority,duedate);
        databaseReference.setValue(tasks);
        Toast.makeText(TaskList.this, "Task Updated", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseuser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userlist.clear();
                for(DataSnapshot artistSnapshot : dataSnapshot.getChildren()){

                    tasks user = artistSnapshot.getValue(tasks.class);
                    userlist.add(user);
                }
                UserList adapter = new UserList(TaskList.this, userlist);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}

