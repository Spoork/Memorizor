package com.example.boss.leitnersystem;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CardQuestion extends AppCompatActivity {

    private TextView QuestionText;
    private final static String KEY_INDEX = "index";
    private final static String CORRECT_KEY = "correct";
    private int currentIndex = 0;
    private int correctCounter = 0;

    private  Question[] QuestionBank;

    private String subject = "";


    //history bank
    private Question[] HisQuestionBank = new Question[]{

            new Question(R.string.question_ww2, false, "His"),
            new Question(R.string.question_ww1, true, "His"),
            new Question(R.string.question_france, true, "His")

    };

    //biology bank
    private Question[] BioQuestionBank = new Question[] {

            new Question(R.string.question_Bio1, false, "Bio"),
            new Question(R.string.question_Bio2,false,"Bio"),
            new Question(R.string.question_Bio3,true,"Bio")


    };

    //techno bank
    private Question[] TechnoQuestionBank = new Question[] {

            new Question(R.string.question_IT1, false, "Tech"),
            new Question(R.string.question_IT2,true,"Tech"),
            new Question(R.string.question_IT3,true,"Tech")


    };

    //geography bank
    private Question[] GeoQuestionBank = new Question[] {

            new Question(R.string.question_americas, true, "Geo"),
            new Question(R.string.question_africa,true,"Geo"),
            new Question(R.string.question_asia,false,"Geo")


    };

    //Sociologyy bank
    private Question[] SocQuestionBank = new Question[] {

            new Question(R.string.question_soc1, true, "Soc"),
            new Question(R.string.question_soc2,true,"Soc"),
            new Question(R.string.question_soc3,false,"Soc")

    };

    //Astronomy bank
    private Question[] AstroQuestionBank = new Question[] {

            new Question(R.string.question_astro1, false, "Astro"),
            new Question(R.string.question_astro2,false,"Astro"),
            new Question(R.string.question_astro3,true,"Astro")

    };

    //back button pressed
    @Override
    public void onBackPressed() {
        Intent backIntent = new Intent(CardQuestion.this, CardPickActivity.class);
        backIntent.putExtra(Intent.EXTRA_TEXT,"back");
        setResult(Activity.RESULT_OK, backIntent);
        finish();
    }

    protected void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_INDEX, currentIndex);
        savedInstanceState.putInt(CORRECT_KEY, correctCounter);
    }

    //on create
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_question);

        if (savedInstanceState != null){
            currentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            correctCounter = savedInstanceState.getInt(CORRECT_KEY, 0);
        }

        //question
        QuestionText =  findViewById(R.id.Question);

        Bundle b = getIntent().getExtras();
        String value = b.getString(Intent.EXTRA_TEXT);

        //history questions
        if (value.equals("His")) {

            QuestionBank = HisQuestionBank;
            int question = QuestionBank[currentIndex].getquestionIndex();
            QuestionText.setText(question);
            subject = value;
        }
        //biology questions
        else if (value.equals("Bio")) {

            QuestionBank = BioQuestionBank;
            int question = QuestionBank[currentIndex].getquestionIndex();
            QuestionText.setText(question);
            subject = value;
        }
        //technology questions
        else if (value.equals("Tech")) {

            QuestionBank = TechnoQuestionBank;
            int question = QuestionBank[currentIndex].getquestionIndex();
            QuestionText.setText(question);
            subject = value;
        }
        //geography questions
        else if (value.equals("Geo")) {

            QuestionBank = GeoQuestionBank;
            int question = QuestionBank[currentIndex].getquestionIndex();
            QuestionText.setText(question);
            subject = value;
        }

        //sociology questions
        else if (value.equals("Soc")) {

            QuestionBank = SocQuestionBank;
            int question = QuestionBank[currentIndex].getquestionIndex();
            QuestionText.setText(question);
            subject = value;
        }

        //astronomy questions
        else if (value.equals("Astro")) {

            QuestionBank = AstroQuestionBank;
            int question = QuestionBank[currentIndex].getquestionIndex();
            QuestionText.setText(question);
            subject = value;
        }
    }




    //true button pressed
    public void answerTrue(View view) {

        Boolean chosenAnswer = true;
        if(QuestionBank[currentIndex].isAnswer() == chosenAnswer){
            Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show();
            correctCounter++;}
        else Toast.makeText(this, "Incorrect Answer", Toast.LENGTH_SHORT).show();

        if(currentIndex == 2){
            check(currentIndex);
        }
        currentIndex++;
        QuestionText.setText(QuestionBank[currentIndex].getquestionIndex());
    }

    //false button pressed
    public void answerFalse(View view) {

        Boolean chosenAnswer = false;
        if(QuestionBank[currentIndex].isAnswer()==chosenAnswer){
            Toast.makeText(this, "CORRECT ANSWER", Toast.LENGTH_SHORT).show();
            correctCounter++;}
        else Toast.makeText(this, "INCORRECT ANSWER", Toast.LENGTH_SHORT).show();

        if(currentIndex == 2){
            check(currentIndex);
        }
        currentIndex++;
        QuestionText.setText(QuestionBank[currentIndex].getquestionIndex());

    }

    //update question

    void check(int counter) {
        Intent intent = new Intent(CardQuestion.this, CardPickActivity.class);

        switch (subject){
            case "His":
                intent.putExtra(Intent.EXTRA_TEXT,"His");
                break;
            case "Bio":
                intent.putExtra(Intent.EXTRA_TEXT,"Bio");
                break;
            case "Tech":
                intent.putExtra(Intent.EXTRA_TEXT,"Tech");
                break;
            case "Geo":
                intent.putExtra(Intent.EXTRA_TEXT,"Geo");
                break;
            case "Soc":
                intent.putExtra(Intent.EXTRA_TEXT,"Soc");
                break;
            case "Astro":
                intent.putExtra(Intent.EXTRA_TEXT,"Astro");
                break;
        }
        if (counter == 2 && correctCounter == 3) {

          //  Toast.makeText(this, "WELL DONE", Toast.LENGTH_SHORT).show();
            currentIndex = 0;
            correctCounter = 0;

            setResult(Activity.RESULT_OK, intent);
            finish();

        }
            else if (counter == 2 && correctCounter < 3){

                 //   Toast.makeText(this, "TRY AGAIN LATER", Toast.LENGTH_SHORT).show();
                    currentIndex = 0;
                    correctCounter = 0;

                    setResult(Activity.RESULT_CANCELED, intent);
                    finish();
            }
    }

}
