package net.kingbets.cambista.view.widgets;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.local.apostas.Aposta;
import net.kingbets.cambista.model.local.apostas.Single;
import net.kingbets.cambista.utils.DateTime;
import net.kingbets.cambista.view.dialogs.CriaCupomDialog;


public class WidgetCupom {



    private CriaCupomDialog parent;
    private View rootView;
    private Widget widget;

    private TextView txvFutebol;
    private TextView txvCampeonato;
    private TextView txvEquipes;
    private TextView txvTituloAposta;
    private TextView txvApostaCotacao;
    private TextView txvApostaStatus;
    private ImageView btnDeleteAposta;



    public WidgetCupom(Context context, Widget widget) {

        this.rootView = LayoutInflater.from(context).inflate(R.layout.item_cupom_aposta, null, false);
        this.widget = widget;

        txvFutebol = rootView.findViewById(R.id.txv_cupom_futebol);
        txvCampeonato = rootView.findViewById(R.id.txv_cupom_campeonato);
        txvEquipes = rootView.findViewById(R.id.txv_cupom_equipes);
        txvTituloAposta = rootView.findViewById(R.id.txv_cupom_titulo_aposta);
        txvApostaCotacao = rootView.findViewById(R.id.txv_cupom_aposta_cotacao);
        txvApostaStatus = rootView.findViewById(R.id.txv_cupom_aposta_status);
        btnDeleteAposta = rootView.findViewById(R.id.btn_delete_aposta);
    }



    public WidgetCupom build() {

        Aposta aposta = widget.getAposta();

        setFutebol(aposta.partida.data, aposta.partida.inicio);
        setCampeonato(aposta.partida.campeonato);
        setEquipes(aposta.partida.casa, aposta.partida.fora);
        setTituloAposta(widget.getTitulo());
        setApostaCotacao(widget.getTextCotacao());
        setApostaStatus(aposta.status);

        btnDeleteAposta.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Single.instance().removeAposta(widget);
                parent.mostrarApostas();
                parent.refresh();
            }
        });

        return this;
    }



    public void setParent(CriaCupomDialog parent) {
        this.parent = parent;
    }



    public View getRootView() {
        return rootView;
    }



    private void setFutebol(String data, String hora) {

        data = DateTime.getInlineDate( DateTime.getDateFromString(data), "dd/MM/yyyy" );
        hora = DateTime.compact(hora);

        String futebol = "Futebol - " + data + "  às  " + hora;
        txvFutebol.setText(futebol);
    }



    private void setCampeonato(String campeonato) {
        txvCampeonato.setText(campeonato);
    }



    private void setEquipes(String casa, String fora) {
        String equipes = casa + " x " + fora;
        txvEquipes.setText(equipes);
    }



    private void setTituloAposta(String titulo) {
        txvTituloAposta.setText(titulo);
    }



    private void setApostaCotacao(String cotacao) {
        txvApostaCotacao.setText(cotacao);
    }



    private void setApostaStatus(String status) {
        txvApostaStatus.setText(status);
    }



    public void hideStatus() {
        getRootView().findViewById(R.id.layout_status).setVisibility(View.GONE);
    }
}
