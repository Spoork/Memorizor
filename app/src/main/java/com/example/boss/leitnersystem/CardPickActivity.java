package com.example.boss.leitnersystem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class CardPickActivity extends AppCompatActivity {

    //cardviews
    private CardView historyCard;
    private CardView biologyCard;
    private CardView technologyCard;
    private CardView geographyCard;
    private CardView sociologyCard;
    private CardView astronomyCard;

//    timers
   private TextView historyTimer;
   private TextView biologyTimer;
   private TextView technoTimer;
   private TextView geographyTimer;
   private TextView sociologyTimer;
   private TextView astronomyTimer;

//    progress bars
   private ProgressBar hisBar;
   private ProgressBar bioBar;
   private ProgressBar techBar;
   private ProgressBar geoBar;
   private ProgressBar socBar;
   private ProgressBar astroBar;


    //card score
    private int  hisScore = 0;
    private int  bioScore= 0;
    private int  techScore= 0;
    private int  geoScore= 0;
    private int  socScore= 0;
    private int  astroScore= 0;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    //save instance
    @Override
    protected void onSaveInstanceState (Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_pick);

        pref = getApplicationContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE); // 0 - for private mode
        editor = pref.edit();

        //cards
        historyCard = findViewById(R.id.historyCard);
        biologyCard = findViewById(R.id.biologyCard);
        technologyCard = findViewById(R.id.techCard);
        geographyCard = findViewById(R.id.geoCard);
        sociologyCard = findViewById(R.id.socCard);
        astronomyCard = findViewById(R.id.astroCard);

        // progress bars
        hisBar= findViewById(R.id.HisPB);
        bioBar= findViewById(R.id.BioPB);
        techBar= findViewById(R.id.TechPB);
        socBar= findViewById(R.id.SocPB);
        astroBar= findViewById(R.id.AstPB);
        geoBar= findViewById(R.id.GeoPB);

        //session dates
        historyTimer   = findViewById(R.id.historyTimer);
        biologyTimer   = findViewById(R.id.bioTimer);
        technoTimer    = findViewById(R.id.technoTimer);
        geographyTimer = findViewById(R.id.geoTimer);
        sociologyTimer = findViewById(R.id.socTimer);
        astronomyTimer = findViewById(R.id.astroTimer);


        //card listeners
        //history card
        historyCard.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
             Intent intent = new Intent(CardPickActivity.this, CardQuestion.class);
             intent.putExtra(Intent.EXTRA_TEXT,"His");
             startActivityForResult(intent, 1);

          }
        });

        //biology card
        biologyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CardPickActivity.this, CardQuestion.class);
                intent.putExtra(Intent.EXTRA_TEXT,"Bio");
                startActivityForResult(intent, 1);
            }
        });

        //technology card
        technologyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CardPickActivity.this, CardQuestion.class);
                intent.putExtra(Intent.EXTRA_TEXT,"Tech");
                startActivityForResult(intent, 1);
            }
        });

        //geography card
        geographyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CardPickActivity.this, CardQuestion.class);
                intent.putExtra(Intent.EXTRA_TEXT,"Geo");
                startActivityForResult(intent, 1);
            }
        });

        //sociology card
        sociologyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CardPickActivity.this, CardQuestion.class);
                intent.putExtra(Intent.EXTRA_TEXT,"Soc");
                startActivityForResult(intent, 1);
            }
        });

        //astronomy card
        astronomyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CardPickActivity.this, CardQuestion.class);
                intent.putExtra(Intent.EXTRA_TEXT,"Astro");
                startActivityForResult(intent, 1);
            }
        });


    }

    protected void onPause(){
        super.onPause();
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putInt("History Score", hisScore);
        editor.putInt("Biology Score", bioScore);
        editor.putInt("Technology Score", techScore);
        editor.putInt("Geology Score", geoScore);
        editor.putInt("Sociology Score", socScore);
        editor.putInt("Astronomy Score", astroScore);
        editor.commit();

    }


    public void onResume(){
        super.onResume();

        hisScore = pref.getInt("History Score", 0);
        bioScore = pref.getInt("Biology Score", 0);
        techScore= pref.getInt("Technology Score", 0);
        geoScore = pref.getInt("Geology Score", 0);
        socScore = pref.getInt("Sociology Score", 0);
        astroScore = pref.getInt("Astronomy Score", 0);


        hisBar.setMax(20);
        bioBar.setMax(20);
        techBar.setMax(20);
        geoBar.setMax(20);
        socBar.setMax(20);
        astroBar.setMax(20);


        hisBar.setProgress(hisScore);
        bioBar.setProgress(bioScore);
        techBar.setProgress(techScore);
        geoBar.setProgress(geoScore);
        socBar.setProgress(socScore);
        astroBar.setProgress(astroScore);

    }

    //on activity result
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bundle b = data.getExtras();
        String s = b.getString(Intent.EXTRA_TEXT);


        if (requestCode == 1) {

            if(resultCode == Activity.RESULT_OK){
                switch (s) {
                    case "back":
                        break;

                    case "His": {

                        hisScore += 5;
                        hisBar.setMax(20);
                        hisBar.setProgress(hisScore);
                        editor.putInt("History Score", hisScore);
                        editor.commit();

                        if (hisScore == 20) {
                            historyCard.setClickable(false);
                            historyTimer.setText("Card Memorized");
                            Toast toast = Toast.makeText(getApplicationContext(), "You memorized all the history questions good job keep it up", Toast.LENGTH_SHORT);
                            toast.show();
                        } else {
                            new CountDownTimer(30000, 1000) {

                                public void onTick(long millisUntilFinished) {
                                    historyTimer.setText("seconds remaining: " + millisUntilFinished / 1000);
                                    historyCard.setClickable(false);
                                }

                                public void onFinish() {
                                    historyTimer.setText("Click me!");
                                    historyCard.setClickable(true);
                                }
                            }.start();
                        }
                    }
                    break;
                    case "Bio":
                        bioScore += 5;
                        bioBar.setMax(20);
                        bioBar.setProgress(bioScore);
                        editor.putInt("Biology Score", bioScore);
                        editor.commit();
                        if (bioScore == 20) {
                            biologyCard.setClickable(false);
                            biologyTimer.setText("Card Memorized");
                            Toast toast = Toast.makeText(getApplicationContext(), "You memorized all the biology questions good job keep it up", Toast.LENGTH_SHORT);
                            toast.show();
                        } else {
                            new CountDownTimer(30000, 1000) {

                                public void onTick(long millisUntilFinished) {
                                    biologyTimer.setText("seconds remaining: " + millisUntilFinished / 1000);
                                    biologyCard.setClickable(false);
                                }

                                public void onFinish() {
                                    biologyTimer.setText("Click me!");
                                    biologyCard.setClickable(true);
                                }
                            }.start();
                        }

                        break;
                    case "Tech":
                        techScore += 5;
                        techBar.setMax(20);
                        techBar.setProgress(techScore);
                        editor.putInt("Technology Score", techScore);
                        editor.commit();

                        if (techScore == 20) {
                            technologyCard.setClickable(false);
                            technoTimer.setText("Card Memorized");
                            Toast toast = Toast.makeText(getApplicationContext(), "You memorized all the technology questions good job keep it up", Toast.LENGTH_SHORT);
                            toast.show();
                        } else {
                            new CountDownTimer(30000, 1000) {

                                public void onTick(long millisUntilFinished) {
                                    technoTimer.setText("seconds remaining: " + millisUntilFinished / 1000);
                                    technologyCard.setClickable(false);
                                }

                                public void onFinish() {
                                    technoTimer.setText("Click me!");
                                    technologyCard.setClickable(true);
                                }
                            }.start();
                        }

                        break;
                    case "Geo":
                        geoScore += 5;
                        geoBar.setMax(20);
                        geoBar.setProgress(geoScore);
                        editor.putInt("Geology Score", geoScore);
                        editor.commit();

                        if (geoScore == 20) {
                            geographyCard.setClickable(false);
                            geographyTimer.setText("Card Memorized");
                            Toast toast = Toast.makeText(getApplicationContext(), "You memorized all the geography questions good job keep it up", Toast.LENGTH_SHORT);
                            toast.show();
                        } else {
                            new CountDownTimer(30000, 1000) {

                                public void onTick(long millisUntilFinished) {
                                    geographyTimer.setText("seconds remaining: " + millisUntilFinished / 1000);
                                    geographyCard.setClickable(false);
                                }

                                public void onFinish() {
                                    geographyTimer.setText("Click me!");
                                    geographyCard.setClickable(true);
                                }
                            }.start();
                        }
                        break;

                    case "Soc":
                        socScore += 5;
                        socBar.setMax(20);
                        socBar.setProgress(socScore);
                        editor.putInt("Sociology Score", socScore);
                        editor.commit();

                        if (socScore == 20) {
                            sociologyCard.setClickable(false);
                            sociologyTimer.setText("Card Memorized");
                            Toast toast = Toast.makeText(getApplicationContext(), "You memorized all the sociology questions good job keep it up", Toast.LENGTH_SHORT);
                            toast.show();
                        } else {
                            new CountDownTimer(30000, 1000) {

                                public void onTick(long millisUntilFinished) {
                                    sociologyTimer.setText("seconds remaining: " + millisUntilFinished / 1000);
                                    sociologyCard.setClickable(false);
                                }

                                public void onFinish() {
                                    sociologyTimer.setText("Click me!");
                                    sociologyCard.setClickable(true);
                                }
                            }.start();
                        }
                        break;

                    case "Astro":
                        astroScore += 5;
                        astroBar.setMax(20);
                        astroBar.setProgress(astroScore);
                        editor.putInt("Astronomy Score", astroScore);
                        editor.commit();

                        if(astroScore==20){
                            astronomyCard.setClickable(false);
                            astronomyTimer.setText("Card Memorized");
                            Toast toast=Toast.makeText(getApplicationContext(),"You memorized all the Astronomy questions good job keep it up",Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        else{
                        new CountDownTimer(30000, 1000) {

                            public void onTick(long millisUntilFinished) {
                                astronomyTimer.setText("seconds remaining: " + millisUntilFinished / 1000);
                                astronomyCard.setClickable(false);
                            }

                            public void onFinish() {
                                astronomyTimer.setText("Click me!");
                                astronomyCard.setClickable(true);
                            }
                        }.start(); }
                         break;
                }

            }

            if (resultCode == Activity.RESULT_CANCELED) {
                Toast toast = Toast.makeText(getApplicationContext(),"Try again next time!",Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
   public void reset(View view){

       hisBar.setProgress(0);
       bioBar.setProgress(0);
       techBar.setProgress(0);
       geoBar.setProgress(0);
       socBar.setProgress(0);
       astroBar.setProgress(0);

       historyCard.setClickable(true);
       biologyCard.setClickable(true);
       technologyCard.setClickable(true);
       geographyCard.setClickable(true);
       sociologyCard.setClickable(true);
       astronomyCard.setClickable(true);

       historyTimer.setText("Click me!");
       biologyTimer.setText("Click me!");
       technoTimer.setText("Click me!");
       geographyTimer.setText("Click me!");
       sociologyTimer.setText("Click me!");
       astronomyTimer.setText("Click me!");

       hisScore = 0;
       bioScore= 0;
       techScore= 0;
       geoScore= 0;
       socScore= 0;
       astroScore= 0;

       editor.putInt("History Score", 0);
       editor.putInt("Biology Score", 0);
       editor.putInt("Technology Score", 0);
       editor.putInt("Geology Score", 0);
       editor.putInt("Sociology Score", 0);
       editor.putInt("Astronomy Score", 0);
       editor.commit();

       Toast toast = Toast.makeText(getApplicationContext(), "Scores Reset", Toast.LENGTH_SHORT);
       toast.show();
   }
}
