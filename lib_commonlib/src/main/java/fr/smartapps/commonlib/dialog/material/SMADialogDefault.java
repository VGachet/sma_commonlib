package fr.smartapps.commonlib.dialog.material;

import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import fr.smartapps.commonlib.R;

/**
 * Created by vchann on 22/08/2016.
 */
public class SMADialogDefault {

    protected Dialog dialogInstance;
    protected Context context;
    protected Button rightButton;
    protected Button leftButton;
    protected TextView titleView;
    protected TextView descriptionView;

    public SMADialogDefault(Context context) {
        this.context = context;
        dialogInstance = new Dialog(context);
        dialogInstance.requestWindowFeature(Window.FEATURE_NO_TITLE);
        rightButton = (Button) dialogInstance.findViewById(R.id.button_ok);
        rightButton.setVisibility(View.GONE);
        leftButton = (Button) dialogInstance.findViewById(R.id.button_cancel);
        leftButton.setVisibility(View.GONE);
    }

    public SMADialogDefault cancelable(boolean cancelable) {
        dialogInstance.setCancelable(cancelable);
        return this;
    }

    public SMADialogDefault cancelableOnTouchOutside(boolean cancelable) {
        dialogInstance.setCanceledOnTouchOutside(cancelable);
        return this;
    }

    public SMADialogDefault setRightClick(View.OnClickListener onClickListener) {
        if (onClickListener != null) {
            rightButton = (Button) dialogInstance.findViewById(R.id.button_ok);
            rightButton.setVisibility(View.VISIBLE);
            rightButton.setOnClickListener(onClickListener);
        }
        return this;
    }

    public SMADialogDefault setRightClick(View.OnClickListener onClickListener, ColorStateList colorStateList) {
        setRightClick(onClickListener);
        if (onClickListener != null) {
            rightButton.setTextColor(colorStateList);
        }
        return this;
    }

    public SMADialogDefault setLeftClick(View.OnClickListener onClickListener) {
        if (onClickListener != null) {
            leftButton = (Button) dialogInstance.findViewById(R.id.button_cancel);
            leftButton.setVisibility(View.VISIBLE);
            leftButton.setOnClickListener(onClickListener);
        }
        return this;
    }

    public SMADialogDefault setLeftClick(View.OnClickListener onClickListener, ColorStateList colorStateList) {
        setLeftClick(onClickListener);
        if (onClickListener != null) {
            leftButton.setTextColor(colorStateList);
        }
        return this;
    }

    public SMADialogDefault setTitle(String title) {
        if (title != null) {
            titleView = (TextView) dialogInstance.findViewById(R.id.title);
            titleView.setText(title);
        }
        return this;
    }

    public SMADialogDefault setTitle(String title, ColorStateList colorStateList) {
        setTitle(title);
        if (colorStateList != null) {
            titleView.setTextColor(colorStateList);
        }
        return this;
    }

    public SMADialogDefault setDescription(String description) {
        if (description != null) {
            descriptionView = (TextView) dialogInstance.findViewById(R.id.description);
            titleView.setText(description);
        }
        return this;
    }

    public SMADialogDefault setDescription(String description, ColorStateList colorStateList) {
        setDescription(description);
        if (colorStateList != null) {
            descriptionView.setTextColor(colorStateList);
        }
        return this;
    }

    public SMADialogDefault cancel() {
        dialogInstance.cancel();
        return this;
    }

    public SMADialogDefault show() {
        dialogInstance.setContentView(R.layout.dialog_material_design_2_button);
        dialogInstance.show();
        return this;
    }
}
