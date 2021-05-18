package fr.smartapps.demo.list;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.smartapps.demo.R;
import fr.smartapps.commonlib.list.SMADataView;
import fr.smartapps.commonlib.list.SMAListListener;
import fr.smartapps.commonlib.list.SMAListView;

/**
 * Created by vchann on 11/08/2016.
 */
public class RandomReloadActivity extends AppCompatActivity implements SMAListListener {

    protected SMAListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_random_reload);
        setListView();
        initButton();
    }

    protected void setListView() {
        listView = (SMAListView) findViewById(R.id.list);
        if (listView != null) {
            listView.initData(2, getDataViews(), this);
        }
    }

    protected List<SMADataView> getDataViews() {
        List<SMADataView> result = new ArrayList<>();

        // list header
        SMADataView header = new SMADataView(R.layout.list_header);
        header.setImage("pokemon_header.jpg");
        header.setFullWidth(true);

        // list 1
        SMADataView dataList1 = new SMADataView(R.layout.list_card);
        dataList1.setImage("carapuce.png");
        dataList1.setTitle("Carapuce");
        dataList1.setSubtitle("Carapuce est une petite tortue bleue.");

        // list 2
        SMADataView dataList2 = new SMADataView(R.layout.list_grid);
        dataList2.setImage("alakazam.png");

        // list 3
        SMADataView dataList3 = new SMADataView(R.layout.list_title);
        dataList3.setTitle("Evoli");

        // list 4
        SMADataView dataList4 = new SMADataView(R.layout.list_default);
        dataList4.setImage("pikachu.png");
        dataList4.setTitle("Pikachu");
        dataList4.setSubtitle("Pikachu est un petit Pokémon potelé.");
        header.setFullWidth(true);

        // populate with a thousand views
        result.add(header);
        for(int position = 0; position < 1000; position++) {
            Random random = new Random();
            switch (random.nextInt(1000) % 5) {
                case 1:
                    result.add(dataList2);
                    break;
                case 2:
                    result.add(dataList1);
                    break;
                case 3:
                    result.add(dataList4);
                    break;
                case 4:
                    result.add(dataList3);
                    break;
                case 0:
                    result.add(header);
                    break;
            }
        }

        // return list of view to display them in the listView
        return result;
    }

    @Override
    public void onBindViewHolder(View itemView, final SMADataView dataView) {

        ImageView imageView = (ImageView) itemView.findViewById(R.id.row_image);
        TextView titleView = (TextView) itemView.findViewById(R.id.row_title);
        TextView subtitleView = (TextView) itemView.findViewById(R.id.row_subtitle);

        switch (dataView.resourceViewIdx) {
            case R.layout.list_header:
                imageView.setBackground(getDrawableFromAssets(dataView.getImage()));
                break;

            case R.layout.list_card:
                imageView.setBackground(getDrawableFromAssets(dataView.getImage()));
                titleView.setText(dataView.getTitle());
                subtitleView.setText(dataView.getSubtitle());
                break;

            case R.layout.list_grid:
                imageView.setBackground(getDrawableFromAssets(dataView.getImage()));
                break;

            case R.layout.list_title:
                titleView.setText(dataView.getTitle());
                break;

            case R.layout.list_default:
                imageView.setBackground(getDrawableFromAssets(dataView.getImage()));
                titleView.setText(dataView.getTitle());
                subtitleView.setText(dataView.getSubtitle());
                break;
        }
    }

    protected void initButton() {
        Button buttonReload = (Button) findViewById(R.id.button_reload);
        buttonReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listView.reloadData(getDataViews());
            }
        });
    }

    protected Drawable getDrawableFromAssets(String filename) {
        Drawable drawable = null;
        InputStream inputStream = null;
        try {
            inputStream = this.getAssets().open(filename);
            drawable = Drawable.createFromStream(inputStream, null);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return drawable;
    }
}

