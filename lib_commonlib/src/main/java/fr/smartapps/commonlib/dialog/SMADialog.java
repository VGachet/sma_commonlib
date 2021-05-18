package fr.smartapps.commonlib.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.util.Log;
import android.view.Window;

/**
 * Created by vchann on 22/08/2016.
 */
public class SMADialog {

    private String TAG = "SMADialog";
    protected Dialog dialogInstance;
    protected SMADialogListener dialogListener;
    protected Activity context;

    public SMADialog(Activity context) {
        this.context = context;
        dialogInstance = new Dialog(context);
        dialogInstance.requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    public SMADialog cancelable(boolean cancelable) {
        dialogInstance.setCancelable(cancelable);
        return this;
    }

    public SMADialog cancelableOnTouchOutside(boolean cancelable) {
        dialogInstance.setCanceledOnTouchOutside(cancelable);
        return this;
    }

    public SMADialog setCustomlayout(int customLayout, SMADialogListener dialogListener) {
        dialogInstance.setContentView(customLayout);
        this.dialogListener = dialogListener;
        return this;
    }

    public SMADialog cancel() {
        dialogInstance.cancel();
        return this;
    }

    public SMADialog show() {
        if (context == null) {
            Log.e(TAG, "context activity is null");
            return this;
        }

        if (context.isFinishing()) {
            Log.e(TAG, "context activity is finishing");
            return this;
        }

        dialogInstance.show();
        if (dialogListener != null) {
            dialogListener.onCreate(dialogInstance);
        }
        return this;
    }
}
