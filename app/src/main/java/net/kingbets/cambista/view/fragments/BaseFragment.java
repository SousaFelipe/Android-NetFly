package net.kingbets.cambista.view.fragments;


import android.content.Context;
import android.opengl.Visibility;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import net.kingbets.cambista.R;


public abstract class BaseFragment extends Fragment {



    protected Context context;
    protected View view;
    protected int loader;



    protected void setLoaderVisibility(boolean visible) {
        if (getActivity() != null) {

            final boolean fnVisible = visible;

            getActivity().runOnUiThread(new Runnable() {
                @Override public void run() {
                    view.findViewById(loader).setVisibility( fnVisible ? View.VISIBLE : View.GONE );
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
