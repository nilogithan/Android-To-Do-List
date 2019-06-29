package com.example.nilogithan.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTask extends AppCompatActivity {
    private EditText Name;
    private EditText Discription;
    private Spinner Priority;
    private EditText DueDate;
    private Button submitBtn;

    DatabaseReference databaseuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        Name = (EditText)findViewById(R.id.addName);
        Discription = (EditText)findViewById(R.id.addDesc);
        Priority = (Spinner) findViewById(R.id.prioritySpinner);
        DueDate = (EditText)findViewById(R.id.dueDate);
        submitBtn = (Button)findViewById(R.id.submitBtn);

        databaseuser = FirebaseDatabase.getInstance().getReference("tasks");

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addtask();

            }
        });

    }

     public void addtask(){
        String name;
        String discription;
        String priority;
        String duedate;

        name = Name.getText().toString();
        discription = Discription.getText().toString();
        priority = Priority.getSelectedItem().toString();
        duedate = DueDate.getText().toString();



        if(!TextUtils.isEmpty(name)|| !TextUtils.isEmpty(discription)|| !TextUtils.isEmpty(duedate)){
            String id = databaseuser.push().getKey();
            tasks tasks = new tasks(id,name,discription,priority,duedate);
            databaseuser.child(id).setValue(tasks);
            Intent intent = new Intent(AddTask.this, TaskList.class);
            startActivity(intent);
        }else{
            Toast.makeText(AddTask.this, "Fields are Empty", Toast.LENGTH_SHORT).show();
        }

    }
}
