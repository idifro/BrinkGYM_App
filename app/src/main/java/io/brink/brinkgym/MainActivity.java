package io.brink.brinkgym;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.brink.brinkgym.Model.Common;
import io.brink.brinkgym.R;
import io.brink.brinkgym.Model.Team;

public class MainActivity extends AppCompatActivity {

    EditText teamname;
    EditText participantname1;
    EditText participantname2;
    EditText rollno1;
    EditText rollno2;
    Button   submit;
    FirebaseDatabase database;
    DatabaseReference drag;
    public int Score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Score = 500;
        database = FirebaseDatabase.getInstance();
        drag = database.getReference("Teams");

        teamname=findViewById(R.id.tname);
        participantname1=findViewById(R.id.pname1);
        participantname2=findViewById(R.id.pname2);
        rollno1=findViewById(R.id.roll1);
        rollno2=findViewById(R.id.roll2);
        submit=findViewById(R.id.submitbutton);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addteam();

            }
        });
    }
    private void addteam(){
        final Team team = new Team(teamname.getText().toString(),
                participantname1.getText().toString(),
                participantname2.getText().toString(),
                rollno1.getText().toString(),
                rollno2.getText().toString(),Score);
        drag.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(team.getTeamname()).exists()){
                    Toast.makeText(MainActivity.this,"Team Already Exists",Toast.LENGTH_SHORT).show();
                }else {
                    drag.child(team.getTeamname()).setValue(team);
                    Common.Teamname = team.getTeamname();
                    Intent intent= new Intent(MainActivity.this,RulesActivity.class);
                    startActivity(intent);
                    finish();

                    Toast.makeText(MainActivity.this,"Team Registerd",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Toast.makeText(this,"Saved",Toast.LENGTH_LONG).show();


    }
}
