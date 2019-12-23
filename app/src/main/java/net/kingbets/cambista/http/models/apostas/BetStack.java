package net.kingbets.cambista.http.models.apostas;


import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import net.kingbets.cambista.model.apostas.Cupom;


public class BetStack {



    private Cupom cupom;
    private List<Bet> stack;
    private View btnShowBets;

    private int lastSize = 0;
    private static BetStack instance = null;



    private BetStack() {
        cupom = new Cupom();
    }



    public static synchronized BetStack instance() {
        if (instance == null) {
            instance = new BetStack();
        }
        return instance;
    }



    public Cupom getCupom() {
        return cupom;
    }

    public List<Bet> getStack() {
        return stack;
    }



    public void setBtnShowBets(View btnShowBets) {
        this.btnShowBets = btnShowBets;
    }



    public void load(JSONArray bets, int min) {

        stack = new ArrayList<>();

        try {

            Bet bet;

            for (int i = 0; i < bets.length(); i++) {

                bet = new Bet(bets.getJSONObject(i));

                if ( ! stack.contains(bet)) {
                    stack.add(bet);
                }
            }

            update(min);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void update(int min) {

        if (stack.size() >= min) {
            btnShowBets.setVisibility(View.VISIBLE);
        }
        else {
            btnShowBets.setVisibility(View.GONE);
        }

        cupom.updateCotacao();
        cupom.updatePossivelRetorno();
    }

    public void clear() {

        cupom = new Cupom();

        stack.clear();
        stack = new ArrayList<>();

        lastSize = 0;
    }



    public boolean findMatch(long partidaId) {
        boolean findMatch = false;

        for (Bet in: stack) {
            if (in.partida.id == partidaId) {
                findMatch = true;
                break;
            }
        }

        return findMatch;
    }

    public boolean isSameOdd(Bet bet) {
        boolean isSameOdd = false;

        for (Bet in: stack) {
            if (in.odd == bet.odd && in.tipo.equals(bet.tipo) && in.titulo.equals(bet.titulo) && in.sentenca.equals(bet.sentenca)) {
                isSameOdd = true;
                break;
            }
        }

        return isSameOdd;
    }
}
