package fr.smartapps.demo.list;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import fr.smartapps.demo.R;
import fr.smartapps.commonlib.list.SMADataView;
import fr.smartapps.commonlib.list.SMAListListener;
import fr.smartapps.commonlib.list.SMAListView;

/**
 * Created by vchann on 11/08/2016.
 */
public class ListActivity extends AppCompatActivity implements SMAListListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        setListView();
    }

    protected void setListView() {
        SMAListView listView = (SMAListView) findViewById(R.id.list);
        if (listView != null) {
            listView.initData(1, getDataViews(), this);
        }
    }

    protected List<SMADataView> getDataViews() {
        List<SMADataView> result = new ArrayList<>();

        // grid 1
        SMADataView dataList1 = new SMADataView(R.layout.list_default);
        dataList1.setImage("carapuce.png");
        dataList1.setTitle("Carapuce");
        dataList1.setSubtitle("Carapuce est une petite tortue bipède de couleur bleue.");

        // grid 2
        SMADataView dataList2 = new SMADataView(R.layout.list_default);
        dataList2.setImage("alakazam.png");
        dataList2.setTitle("Alakazam");
        dataList2.setSubtitle("Alakazam a des moustaches plus longues que sa pré-évolution et il a 2 cuillères.");

        // grid 3
        SMADataView dataList3 = new SMADataView(R.layout.list_default);
        dataList3.setImage("evoli.png");
        dataList3.setTitle("Evoli");
        dataList3.setSubtitle("Évoli est un Pokémon mammalien et quadrupède avec une fourrure principalement brune.");

        // grid 4
        SMADataView dataList4 = new SMADataView(R.layout.list_default);
        dataList4.setImage("pikachu.png");
        dataList4.setTitle("Pikachu");
        dataList4.setSubtitle("Pikachu est un rongeur au corps dodu.");

        // grid 5
        SMADataView dataList5 = new SMADataView(R.layout.list_default);
        dataList5.setImage("roocool.png");
        dataList5.setTitle("Roucool");
        dataList5.setSubtitle("Roucool est un petit Pokémon aviaire au corps dodu.");

        // populate with a thousand views
        for(int position = 0; position < 1000; position++) {
            switch (position % 5) {
                case 1:
                    result.add(dataList1);
                    break;
                case 2:
                    result.add(dataList2);
                    break;
                case 3:
                    result.add(dataList3);
                    break;
                case 4:
                    result.add(dataList4);
                    break;
                case 5:
                    result.add(dataList5);
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
            imageView.setImageDrawable(getDrawableFromAssets(dataView.getImage()));
        }

        // title
        TextView titleView = (TextView) itemView.findViewById(R.id.row_title);
        if (titleView != null && dataView.getTitle() != null) {
            titleView.setText(dataView.getTitle());
        }

        // title
        TextView subtitleView = (TextView) itemView.findViewById(R.id.row_subtitle);
        if (subtitleView != null && dataView.getSubtitle() != null) {
            subtitleView.setText(dataView.getSubtitle());
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
