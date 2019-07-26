package net.kingbets.cambista.model.remote.apostas;


import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;

public class Single extends Cupom {



    private static Single instance = null;



    private CardView displayButton;
    private int lastSize = 0;



    private Single() {
        super();
    }



    public static synchronized Single instance() {
        if (instance == null) {
            instance = new Single();
        }
        return instance;
    }



    public void setDisplayButton(CardView displayButton) {
        this.displayButton = displayButton;
    }



    public void update(int min) {

        if (widgets.size() >= min && lastSize <= (min - 1)) {
            displayButton.setVisibility(View.VISIBLE);
        }
        else if (widgets.size() < min && lastSize >= min) {
            displayButton.setVisibility(View.GONE);
        }

        lastSize = widgets.size();
    }
}
