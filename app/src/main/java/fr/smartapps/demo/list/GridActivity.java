package fr.smartapps.demo.list;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import fr.smartapps.demo.R;
import fr.smartapps.commonlib.list.SMADataView;
import fr.smartapps.commonlib.list.SMAListListener;
import fr.smartapps.commonlib.list.SMAListView;

public class GridActivity extends AppCompatActivity implements SMAListListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        setListView();
    }

    protected void setListView() {
        SMAListView listView = (SMAListView) findViewById(R.id.list);
        if (listView != null) {
            listView.initData(3, getDataViews(), this);
        }
    }

    protected List<SMADataView> getDataViews() {
        List<SMADataView> result = new ArrayList<>();

        // grid 1
        SMADataView dataGrid1 = new SMADataView(R.layout.list_grid);
        dataGrid1.setImage("carapuce.png");

        // grid 2
        SMADataView dataGrid2 = new SMADataView(R.layout.list_grid);
        dataGrid2.setImage("alakazam.png");

        // grid 3
        SMADataView dataGrid3 = new SMADataView(R.layout.list_grid);
        dataGrid3.setImage("evoli.png");

        // grid 4
        SMADataView dataGrid4 = new SMADataView(R.layout.list_grid);
        dataGrid4.setImage("pikachu.png");

        // grid 5
        SMADataView dataGrid5 = new SMADataView(R.layout.list_grid);
        dataGrid5.setImage("roocool.png");

        // populate with a thousand views
        for(int position = 0; position < 1000; position++) {
            switch (position % 5) {
                case 1:
                    result.add(dataGrid1);
                    break;
                case 2:
                    result.add(dataGrid2);
                    break;
                case 3:
                    result.add(dataGrid3);
                    break;
                case 4:
                    result.add(dataGrid4);
                    break;
                case 5:
                    result.add(dataGrid5);
                    break;
            }
        }

        // return list of view to display them in the listView
        return result;
    }

    @Override
    public void onBindViewHolder(View itemView, final SMADataView dataView) {
        // image
        ImageView imageView = (ImageView) itemView.findViewById(R.id.row_image);
        if (imageView != null && dataView.getImage() != null) {
            imageView.setBackground(getDrawableFromAssets(dataView.getImage()));
        }
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
