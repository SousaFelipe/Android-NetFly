package net.kingbets.cambista.view.odds;


import android.content.Context;
import android.view.View;

import net.kingbets.cambista.view.fragments.BaseFragment;


public abstract class BaseOddsView {



    private Context context;
    private View rootView;



    protected BaseOddsView(View rootView) {
        this.rootView = rootView;
    }



    public abstract BaseOddsView create(BaseFragment parent);



    public abstract BaseOddsView build();



    protected Context getContext() {
        return context;
    }



    protected void setContext(Context context) {
        this.context = context;
    }



    public View getRootView() {
        return rootView;
    }
}
