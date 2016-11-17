package com.example.antreasmertzelos.findthesum;

import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.view.MarginLayoutParamsCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TableLayout tilesLayout;
    private TextView sumResult;
    private static final int TILES = 4;
    private static final String TAG = "info";
    private Handler handler = new Handler();
    private Handler tilesHandler = new Handler();
    private Additional additional = new Additional();
    private int count = 0;
    private int[] setId = new int[TILES * TILES];
    private int ID = 8;
    private int countTest;
    private TextView[] textView = new TextView[TILES * TILES];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        tilesLayout = (TableLayout) findViewById(R.id.tiles);
        sumResult = (TextView) findViewById(R.id.sumResult);

        for (int i = 0; i < TILES * TILES; i++) {

            setId[i] = View.generateViewId();

        }


        populateTiles(TILES);
    }

    public void populateTiles(int tiles) {
        countTest = 0;

        countTest = -1;


        for (int row = 0; row < tiles; row++) {
            TableRow tableRow = new TableRow(this);

            TableRow.LayoutParams paramsExample = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT, 1.0f);

            tilesLayout.addView(tableRow);

            for (int col = 0; col < tiles; col++) {


                countTest++;

                textView[countTest] = new TextView(this);
                textView[countTest].setBackgroundResource(R.drawable.background_tile);
                textView[countTest].setGravity(Gravity.CENTER);
                textView[countTest].setTextAppearance(this, R.style.buttonText);

                textView[countTest].setId(setId[countTest]);
                textView[countTest].setText("" + additional.getRndNumbers(TILES).get(countTest));
                textView[countTest].setOnClickListener(this);


                paramsExample.setMargins(8, 8, 8, 8);

                textView[countTest].setLayoutParams(paramsExample);


                tableRow.addView(textView[countTest]);

                textView[countTest].getLayoutParams().height = 180;
                textView[countTest].getLayoutParams().width = 180;

                Log.v(TAG, "view ID" + textView[countTest].getId());


            }

        }
    }

    @Override
    public void onClick(View v) {

        count++;

        String textInput = (String) ((TextView) v).getText();
        v.setBackgroundResource(R.drawable.state_pressed);
        // button pressed only once.
        v.setClickable(false);


        //sumResult.setText(textInput);
        int numbers = Integer.parseInt(textInput);
        additional.setNumberIn(numbers);


        if (count == TILES) {
            for (int i = 0; i < TILES * TILES; i++) {

                textView[i].findViewById(setId[i]).setClickable(false);
            }

            sumResult.setText(additional.getSum() + "");
            //clears the view elements of the table layout after a certain time.
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    count = 0;
                    tilesLayout.removeAllViews();
                    populateTiles(TILES);
                    additional.clearArray();
                }
            }, 3000);
        }
    }
}
