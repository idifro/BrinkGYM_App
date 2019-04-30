package io.brink.brinkgym;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import io.brink.brinkgym.Model.Common;
import io.brink.brinkgym.Model.GameName;

public class Main2Activity extends AppCompatActivity {

    Button QRScanner;
    EditText gname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        QRScanner=findViewById(R.id.QR_Button);


        QRScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertdialog = new AlertDialog.Builder(Main2Activity.this);
                alertdialog.setTitle("Enter the Game Name");

                LayoutInflater inflater =Main2Activity.this.getLayoutInflater();
                View code = inflater.inflate(R.layout.game_name,null);

                gname = (EditText)code.findViewById(R.id.gname);

                alertdialog.setView(code);
                alertdialog.setPositiveButton("Go", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final GameName g = new GameName(gname.getText().toString());
                        Common.Gamename=g.getGamename();
                        Intent intent=new Intent(Main2Activity.this,QRscanner.class);
                        startActivity(intent);

                    }
                });
                alertdialog.show();
            }

            private void showw() {


            }
        });
    }

    private void show() {

    }
}
