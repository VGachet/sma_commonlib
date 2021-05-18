package fr.smartapps.demo;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import fr.smartapps.commonlib.dialog.SMADialog;
import fr.smartapps.commonlib.dialog.SMADialogListener;
import fr.smartapps.commonlib.dialog.material.SMADialogDefault;

public class DialogActivity extends AppCompatActivity {

    protected DialogActivity getActivity() { return this; }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        Button buttonDialog = (Button) findViewById(R.id.button);
        buttonDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });


        // not working yet
        Button buttonDialogMaterial = (Button) findViewById(R.id.button_material);
        buttonDialogMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "not working yet", Toast.LENGTH_SHORT).show();
                // TODO showDialogDefault();
            }
        })
        ;
    }

    protected void showDialog() {
        new SMADialog(this)
                .cancelableOnTouchOutside(true)
                .setCustomlayout(R.layout.dialog, new SMADialogListener() {
                    @Override
                    public void onCreate(final Dialog dialog) {
                        Button buttonOk = (Button) dialog.findViewById(R.id.button_ok);
                        buttonOk.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.cancel();
                            }
                        });
                    }
                })
                .show();
    }

    protected void showDialogDefault() {
        new SMADialogDefault(this)
                .cancelableOnTouchOutside(true)
                .setDescription("custom description")
                .setRightClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getActivity(), "right click", Toast.LENGTH_SHORT).show();
                    }
                })
                .setLeftClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getActivity(), "left click", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }
}
