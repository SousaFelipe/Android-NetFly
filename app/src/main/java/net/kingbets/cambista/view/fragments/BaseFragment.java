package net.kingbets.cambista.view.fragments;


import android.content.Context;
import android.graphics.Color;
import android.opengl.Visibility;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.kingbets.cambista.R;


public abstract class BaseFragment extends Fragment {



    protected Context context;
    protected View view;


    protected int info;
    protected int loader;



    protected CardView lastWidget;



    protected void selecionarWidget(CardView widget) {

        if (lastWidget != null) {
            lastWidget.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
            ((TextView)lastWidget.getChildAt(0)).setTextColor(Color.parseColor("#263238"));
        }

        widget.setCardBackgroundColor(Color.parseColor("#FF5252"));
        ((TextView)widget.getChildAt(0)).setTextColor(Color.parseColor("#FFFFFF"));

        lastWidget = widget;
    }



    protected void setInfoVisibility(boolean visible) {
        if (getActivity() != null) {

            final boolean fnVisible = visible;

            getActivity().runOnUiThread(new Runnable() {
                @Override public void run() {
                    view.findViewById( info ).setVisibility( fnVisible ? View.VISIBLE : View.GONE );
                }
            });
        }
    }

    protected void setLoaderVisibility(boolean visible) {
        if (getActivity() != null) {

            final boolean fnVisible = visible;

            getActivity().runOnUiThread(new Runnable() {
                @Override public void run() {
                    view.findViewById( loader ).setVisibility( fnVisible ? View.VISIBLE : View.GONE );
                }
            });
        }
    }



    public void setContext(Context context) {
        this.context = context;
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setLoader(int loader) {
        this.loader = loader;
    }

    public void setInfo(int info) {
        this.info = info;
    }



    protected void alert(int resource) {
        alert(getResources().getString(resource) );
    }

    protected void alert(String text) {
        if (getActivity() != null) {

            final String fnText = text;

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, fnText, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    protected void alertByCode(int code) {

        int resourse = 0;

        switch (code) {

            case 0:
                resourse = R.string.alert_http_000;
                break;

            case 404:
                resourse = R.string.alert_http_404;
                break;

            case 401:
                resourse = R.string.alert_http_401;
                break;

            case 512:
                resourse = R.string.alert_http_512;
                break;

            default:
                alert("Erro: " + code);
                break;

        }

        if (resourse != 0) {
            alert(resourse);
        }
    }
}
