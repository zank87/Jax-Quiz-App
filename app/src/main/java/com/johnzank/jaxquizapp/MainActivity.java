package com.johnzank.jaxquizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_INDEX = "index";

    private TextView mQuestionTV;
    private TextView mAnswerTV;
    private int mCurrentIndex = 0;
    private int[] mQuestionBank = new int[] {
            R.string.question_text1, R.string.question_text2, R.string.question_text3, R.string.question_text4
    };
    private boolean[] mAnswerBank = new boolean[] {
            true, false, true, false
    };

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQuestionTV = (TextView)findViewById(R.id.question_textView);
        if( savedInstanceState != null ) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX);
            mQuestionTV.setText(mQuestionBank[mCurrentIndex]);
        } else {
            mQuestionTV.setText(R.string.question_text1);
        }

        mAnswerTV = (TextView)findViewById(R.id.answer_textView);
        mAnswerTV.setText(R.string.answer_text);


        Button trueButton = (Button) findViewById(R.id.true_button);
        trueButton.setText(R.string.true_button);
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnswerBank[mCurrentIndex]) {
                    mAnswerTV.setText(R.string.correct_text);
                } else {
                    mAnswerTV.setText(R.string.incorrect_text);
                }
            }
        });

        Button falseButton = (Button) findViewById(R.id.false_button);
        falseButton.setText(R.string.false_button);
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnswerBank[mCurrentIndex]) {
                    mAnswerTV.setText(R.string.incorrect_text);
                } else {
                    mAnswerTV.setText(R.string.correct_text);
                }
            }
        });

        Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setText(R.string.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCurrentIndex < 3) {
                    mCurrentIndex++;
                    mQuestionTV.setText(mQuestionBank[mCurrentIndex]);
                    mAnswerTV.setText(R.string.answer_text);
                } else {
                    mCurrentIndex = 0;
                    mQuestionTV.setText(mQuestionBank[mCurrentIndex]);
                    mAnswerTV.setText(R.string.answer_text);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
