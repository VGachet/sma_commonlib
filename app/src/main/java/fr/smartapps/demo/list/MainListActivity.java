package fr.smartapps.demo.list;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.smartapps.demo.R;
import fr.smartapps.commonlib.list.SMADataView;
import fr.smartapps.commonlib.list.SMAListListener;
import fr.smartapps.commonlib.list.SMAListView;

public class MainListActivity extends AppCompatActivity implements SMAListListener {

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

        // title
        SMADataView dataTitle = new SMADataView(R.layout.list_title);
        dataTitle.setTitle("SMAListView Showcase");

        // text
        SMADataView dataText = new SMADataView(R.layout.list_text);
        dataText.setTitle("Welcome to SMAListView showcase. Here you can see every possibilities this library can offer with a very easy implementation.");

        // options 1
        SMADataView dataOptionGrid = new SMADataView(R.layout.list_button);
        dataOptionGrid.setTitle("GridView");
        dataOptionGrid.setId(1);

        // options 2
        SMADataView dataOptionList = new SMADataView(R.layout.list_button);
        dataOptionList.setTitle("ListView");
        dataOptionList.setId(2);

        // options 3
        SMADataView dataOptionAsyncList = new SMADataView(R.layout.list_button);
        dataOptionAsyncList.setTitle("Asynchronous ListView");
        dataOptionAsyncList.setId(3);

        // options 4
        SMADataView dataOptionRefreshList = new SMADataView(R.layout.list_button);
        dataOptionRefreshList.setTitle("Refresh ListView");
        dataOptionRefreshList.setId(4);

        // options 5
        SMADataView dataOptionSearchList = new SMADataView(R.layout.list_button);
        dataOptionSearchList.setTitle("Search ListView");
        dataOptionSearchList.setId(5);

        // options 6
        SMADataView dataOptionExpandableList = new SMADataView(R.layout.list_button);
        dataOptionExpandableList.setTitle("Expandable ListView");
        dataOptionExpandableList.setId(6);

        // options 7
        SMADataView dataOptionRandomReloadList = new SMADataView(R.layout.list_button);
        dataOptionRandomReloadList.setTitle("Random Reload ListView");
        dataOptionRandomReloadList.setId(7);

        // add each view
        result.add(dataTitle);
        result.add(dataText);
        result.add(dataOptionGrid);
        result.add(dataOptionList);
        result.add(dataOptionAsyncList);
        result.add(dataOptionRefreshList);
        result.add(dataOptionSearchList);
        result.add(dataOptionExpandableList);
        result.add(dataOptionRandomReloadList);

        // return list of view to display them in the listView
        return result;
    }

    @Override
    public void onBindViewHolder(View itemView, final SMADataView dataView) {
        // title
        TextView titleView = (TextView) itemView.findViewById(R.id.row_title);
        if (titleView != null && dataView.getTitle() != null) {
            titleView.setText(dataView.getTitle());
        }

        // options
        if (dataView.resourceViewIdx == R.layout.list_button) {
            Button buttonGo = (Button) itemView.findViewById(R.id.row_button);

            if (buttonGo == null)
                return;

            // define each GO button onClickListener thX to ID
            buttonGo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (dataView.getId()) {
                        case 1:
                            Intent intentGrid = new Intent(getApplicationContext(), GridActivity.class);
                            startActivity(intentGrid);
                            break;
                        case 2:
                            Intent intentList = new Intent(getApplicationContext(), ListActivity.class);
                            startActivity(intentList);
                            break;
                        case 3:
                            Intent intentAsyncList = new Intent(getApplicationContext(), AsyncListActivity.class);
                            startActivity(intentAsyncList);
                            break;
                        case 4:
                            Intent intentRefreshList = new Intent(getApplicationContext(), RefreshListActivity.class);
                            startActivity(intentRefreshList);
                            break;
                        case 5:
                            Intent intentSearchList = new Intent(getApplicationContext(), SearchActivity.class);
                            startActivity(intentSearchList);
                            break;
                        case 6:
                            Intent intentExpandableList = new Intent(getApplicationContext(), ExpandListActivity.class);
                            startActivity(intentExpandableList);
                            break;
                        case 7:
                            Intent intentRandomReloadList = new Intent(getApplicationContext(), RandomReloadActivity.class);
                            startActivity(intentRandomReloadList);
                            break;
                    }
                }
            });
        }
    }
}
