package net.kingbets.cambista.view.adapters;


import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.remote.apostas.Cupom;
import net.kingbets.cambista.utils.Str;
import net.kingbets.cambista.view.dialogs.VerCupomDialog;

import java.util.List;
import java.util.Locale;


public class CupomAdapter extends RecyclerView.Adapter<CupomAdapter.ViewHolder> {



    private List<Cupom> cupons;
    private FragmentManager fragmentManager;



    public CupomAdapter(List<Cupom> cupons) {
        this.cupons = cupons;
    }



    @NonNull
    @Override
    public CupomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cupom_card, parent, false);
        return new CupomAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CupomAdapter.ViewHolder holder, int position) {

        Cupom cupom = cupons.get( position );
        String resumo = Str.getCurrency(cupom.valorApostado) + " em " + cupom.quantApostas + ((cupom.quantApostas > 1) ? "Jogos" : "Jogo" );

        holder.txvNomeApostador.setText(cupom.apostador);
        holder.txvResumo.setText(resumo);
        holder.txvCotacao.setText(String.format(Locale.getDefault(), "%.2f", cupom.cotacao));
        holder.txvPossivelRetorno.setText(Str.getCurrency(cupom.possivelRetorno));

        addItemClick(holder, cupom.codigo);
    }



    private void addItemClick(CupomAdapter.ViewHolder holder, String codigo) {

        final String fnCodigo = codigo;

        holder.contentClick.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                VerCupomDialog.display(fragmentManager, fnCodigo);
            }
        });
    }


    @Override
    public int getItemCount() {
        return cupons.size();
    }



    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }



    public void clear() {
        if (cupons != null) {
            cupons.clear();
            notifyDataSetChanged();
        }
    }



    static class ViewHolder extends RecyclerView.ViewHolder {


        FrameLayout contentClick;
        TextView txvNomeApostador;
        TextView txvResumo;
        TextView txvCotacao;
        TextView txvPossivelRetorno;


        ViewHolder(View view) {
            super(view);

            contentClick = view.findViewById(R.id.content_click);
            txvNomeApostador = view.findViewById(R.id.txv_cupom_apostador);
            txvResumo = view.findViewById(R.id.txv_cupom_resumo);
            txvCotacao = view.findViewById(R.id.txv_cupom_cotacao);
            txvPossivelRetorno = view.findViewById(R.id.txv_cupom_possivel_retorno);
        }
    }
}
