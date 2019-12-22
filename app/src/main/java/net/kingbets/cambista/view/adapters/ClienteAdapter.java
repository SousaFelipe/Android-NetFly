package net.kingbets.cambista.view.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.http.models.Sistema.Cliente;
import net.kingbets.cambista.utils.Str;
import net.kingbets.cambista.view.dialogs.DialogCliente;
import net.kingbets.cambista.view.fragments.BaseFragment;

import java.util.List;


public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ViewHolder> {



    private Context context;
    private BaseFragment parent;
    private List<Cliente> clientes;
    private FragmentManager fragmentManager;



    public ClienteAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cliente, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Cliente cliente = clientes.get(position);

        holder.showNome(cliente.nome);
        holder.showContato(cliente.contato);
        holder.setClickData(parent, fragmentManager, cliente);
    }


    @Override
    public int getItemCount() {
        return clientes.size();
    }



    public void setParent(BaseFragment parent) {
        this.parent = parent;
    }



    public void setDataList(List<Cliente> clientes) {
        this.clientes = clientes;
    }



    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }



    public void clear() {
        if (clientes != null) {
            clientes.clear();
            notifyDataSetChanged();
        }
    }



    static class ViewHolder extends RecyclerView.ViewHolder {


        private FrameLayout contentClick;
        private TextView txvNome;
        private TextView txvContato;


        ViewHolder(View view) {
            super(view);

            contentClick = view.findViewById(R.id.content_click);
            txvNome = view.findViewById(R.id.txv_nome);
            txvContato = view.findViewById(R.id.txv_contato);
        }


        void showNome(String nome) {
            txvNome.setText(nome);
        }


        void showContato(String contato) {
            txvContato.setText(Str.mask(contato, "(##) # ####-####"));
        }


        void setClickData(BaseFragment parent, FragmentManager fragmentManager, Cliente cliente) {

            final BaseFragment fnParent = parent;
            final FragmentManager fnFragmentManager = fragmentManager;
            final Cliente fnCliente = cliente;

            contentClick.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    DialogCliente.display(fnParent, fnFragmentManager, fnCliente);
                }
            });
        }
    }
}
