package net.kingbets.cambista.view.dialogs;


import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import net.kingbets.cambista.view.fragments.BaseFragment;


public abstract class BaseDialog extends DialogFragment {



    protected BaseFragment parent;
    protected LinearLayout layoutContentApostas;
    protected View rootView;
    protected int loader;



    protected void setRootView(View rootView) {
        this.rootView = rootView;
    }

    protected void setLoader(int loader) {
        this.loader = loader;
    }



    protected void setLoaderVisibility(boolean visible) {

        final boolean fnVisible = visible;

        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override public void run() {
                    rootView.findViewById(loader).setVisibility(fnVisible ? View.VISIBLE : View.GONE);
                }
            });
        }
    }



    protected void alert(String message, View.OnClickListener action) {
        if (parent.getActivity() != null && getView() != null) {

            final View.OnClickListener fnAction = action;
            final Snackbar snackbar = Snackbar.make(getView(), message, Snackbar.LENGTH_LONG);

            parent.getActivity().runOnUiThread(new Runnable() {
                @Override public void run() {

                    snackbar.setAction(
                            (fnAction != null) ? "TENTAR NOVAMENTE" : "OK",
                            (fnAction != null) ? fnAction : new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    snackbar.dismiss();
                                }
                            }
                    );

                    snackbar.show();
                }
            });
        }
        else {
            Log.i("ALERT", message);
        }
    }
}
