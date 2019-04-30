package io.brink.brinkgym;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

public class RulesActivity extends AppCompatActivity {

    Button start;
    ScrollView scroll_rules;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        start=findViewById(R.id.Button_start);
        scroll_rules=findViewById(R.id.Scroll_rules);




        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RulesActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });

    }
}
