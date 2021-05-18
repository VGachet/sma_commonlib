package fr.smartapps.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import fr.smartapps.commonlib.pager.SMAViewPager;
import fr.smartapps.commonlib.pager.Transition;

public class PagerAnimationActivity extends AppCompatActivity implements View.OnClickListener {

    SMAViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_animation);

        // pager
        pager = (SMAViewPager) findViewById(R.id.pager);
        pager.fragmentManager(getFragmentManager())
                .setFragments(new Fragment1(), new Fragment2(), new Fragment3())
                .create();

        // button
        Button button1 = (Button) findViewById(R.id.option_1);
        button1.setOnClickListener(this);

        Button button2 = (Button) findViewById(R.id.option_2);
        button2.setOnClickListener(this);

        Button button3 = (Button) findViewById(R.id.option_3);
        button3.setOnClickListener(this);

        Button button4 = (Button) findViewById(R.id.option_4);
        button4.setOnClickListener(this);

        Button button5 = (Button) findViewById(R.id.option_5);
        button5.setOnClickListener(this);

        Button button6 = (Button) findViewById(R.id.option_6);
        button6.setOnClickListener(this);

        Button button7 = (Button) findViewById(R.id.option_7);
        button7.setOnClickListener(this);

        Button button8 = (Button) findViewById(R.id.option_8);
        button8.setOnClickListener(this);

        Button button9 = (Button) findViewById(R.id.option_9);
        button9.setOnClickListener(this);

        Button button10 = (Button) findViewById(R.id.option_10);
        button10.setOnClickListener(this);

        Button button11 = (Button) findViewById(R.id.option_11);
        button11.setOnClickListener(this);

        Button button12 = (Button) findViewById(R.id.option_12);
        button12.setOnClickListener(this);

        Button button13 = (Button) findViewById(R.id.option_13);
        button13.setOnClickListener(this);

        Button button14 = (Button) findViewById(R.id.option_14);
        button14.setOnClickListener(this);

        Button button15 = (Button) findViewById(R.id.option_15);
        button15.setOnClickListener(this);

        Button button16 = (Button) findViewById(R.id.option_16);
        button16.setOnClickListener(this);

        Button button17 = (Button) findViewById(R.id.option_17);
        button17.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.option_1:
                pager.transformer(Transition.ACCORDION);
                break;
            case R.id.option_2:
                pager.transformer(Transition.BACK_TO_FRONT);
                break;
            case R.id.option_3:
                pager.transformer(Transition.CUBE_DOWN);
                break;
            case R.id.option_4:
                pager.transformer(Transition.CUBE_OUT);
                break;
            case R.id.option_5:
                pager.transformer(Transition.DEPTH_PAGE);
                break;
            case R.id.option_6:
                pager.transformer(Transition.FLIP_HORIZONTAL);
                break;
            case R.id.option_7:
                pager.transformer(Transition.FLIP_VERTICAL);
                break;
            case R.id.option_8:
                pager.transformer(Transition.FRONT_TO_BACK);
                break;
            case R.id.option_9:
                pager.transformer(Transition.NULL);
                break;
            case R.id.option_10:
                pager.transformer(Transition.PARALLAX_PAGE);
                break;
            case R.id.option_11:
                pager.transformer(Transition.ROTATE_DOWN);
                break;
            case R.id.option_12:
                pager.transformer(Transition.ROTATE_UP);
                break;
            case R.id.option_13:
                pager.transformer(Transition.STACK);
                break;
            case R.id.option_14:
                pager.transformer(Transition.TABLET);
                break;
            case R.id.option_15:
                pager.transformer(Transition.ZOOM_IN);
                break;
            case R.id.option_16:
                pager.transformer(Transition.ZOOM_OUT_SIDE);
                break;
            case R.id.option_17:
                pager.transformer(Transition.ZOOM_OUT);
                break;
        }
    }
}
