package io.brink.brinkgym;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.Map;

import io.brink.brinkgym.Interface.ScoreCallBack;
import io.brink.brinkgym.Model.Common;
import io.brink.brinkgym.Model.ScoreBoard;
import io.brink.brinkgym.Model.Team;
import io.brink.brinkgym.Model.TeamScore;

import static java.lang.Integer.valueOf;


public class QRscanner extends AppCompatActivity {

    SurfaceView surfaceView;
    CameraSource cameraSource;
    TextView textResult;
    BarcodeDetector barcodeDetector;

    FirebaseDatabase database;
    DatabaseReference teamscore,scoreBoard;
    int sum=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);

        surfaceView = findViewById(R.id.cameraPreview);
        textResult = findViewById(R.id.textresult);
        database = FirebaseDatabase.getInstance();
        teamscore = database.getReference("TeamScore");
        scoreBoard = database.getReference("ScoreBoard");




        barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE).build();

        cameraSource = new CameraSource.Builder(this, barcodeDetector)
                .setRequestedPreviewSize(640, 480)
                .setAutoFocusEnabled(true)
                .build();

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {



                    return;
                }
                try {
                    cameraSource.start(holder);

                }catch (IOException e){
                    e.printStackTrace();
                }



            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrcodes = detections.getDetectedItems();


                if(qrcodes.size()!=0) textResult.post(new Runnable() {
                    @Override
                    public void run() {
                        Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(1000);
                        textResult.setText(qrcodes.valueAt(0).displayValue);
                        final String text = qrcodes.valueAt(0).displayValue;

                        teamscore.child(String.format("%s_%s",Common.Teamname,Common.Gamename))
                                .setValue(new TeamScore(
                                        Common.Teamname,
                                        Common.Gamename,
                                        text));



                        cameraSource.stop();
                        updateScore(Common.Teamname, new ScoreCallBack<ScoreBoard>() {
                            @Override
                            public void callBack(ScoreBoard scoreboard) {
                                scoreBoard.child(scoreboard.getTeamName())
                                        .setValue(scoreboard);
                            }
                        });


                    }
                });
            }
        });

    }

    private void updateScore(final String teamName, final ScoreCallBack<ScoreBoard> callback) {
        teamscore.orderByChild("teamname").equalTo(teamName)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot data:dataSnapshot.getChildren())
                        {
                            TeamScore ques = data.getValue(TeamScore.class);
                            sum+= Integer.parseInt(ques.getScore());
                        }
                        //after summing all score, we need to process sum variable here.
                        // Firebase has async DB

                        ScoreBoard ranking = new ScoreBoard(teamName,sum);
                        callback.callBack(ranking);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

    }
}
