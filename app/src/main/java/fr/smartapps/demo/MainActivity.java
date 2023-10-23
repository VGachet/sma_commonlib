package fr.smartapps.demo;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import fr.smartapps.demo.list.MainListActivity;
import fr.smartapps.demo.video.MainVideoActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ZOOMABLE IMAGE VIEW
        Button button1 = (Button) findViewById(R.id.button1);
        if (button1 != null) {
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, ZoomableImageActivity.class);
                    startActivity(intent);
                }
            });
        }

        // PAGER CUSTOM ANIMATION
        Button button2 = (Button) findViewById(R.id.button2);
        if (button2 != null) {
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, PagerAnimationActivity.class);
                    startActivity(intent);
                }
            });
        }

        // PAGER TAB
        Button button3 = (Button) findViewById(R.id.button3);
        if (button3 != null) {
            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, PagerTabActivity.class);
                    startActivity(intent);
                }
            });
        }

        // TAB
        Button button4 = (Button) findViewById(R.id.button4);
        if (button4 != null) {
            button4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, TabActivity.class);
                    startActivity(intent);
                }
            });
        }

        // PAGER BOUNDARIES
        Button button5 = (Button) findViewById(R.id.button5);
        if (button5 != null) {
            button5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, PagerBoundariesActivity.class);
                    startActivity(intent);
                }
            });
        }

        // DIALOG
        Button button6 = (Button) findViewById(R.id.button6);
        if (button6 != null) {
            button6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, DialogActivity.class);
                    startActivity(intent);
                }
            });
        }


        // LIST
        Button button7 = (Button) findViewById(R.id.button7);
        if (button7 != null) {
            button7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, MainListActivity.class);
                    startActivity(intent);
                }
            });
        }


        // MAP VIEW
        Button button8 = (Button) findViewById(R.id.button8);
        if (button8 != null) {
            button8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, MapviewActivity.class);
                    startActivity(intent);
                }
            });
        }

        // VIDEO SIMPLE
        Button button9 = (Button) findViewById(R.id.button9);
        if (button9 != null) {
            button9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, MainVideoActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}
