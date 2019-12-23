package net.kingbets.cambista.view.dialogs;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.kingbets.cambista.R;
import net.kingbets.cambista.model.contracts.CambistaContract;
import net.kingbets.cambista.model.Cambista;
import net.kingbets.cambista.http.models.Sistema.Cartao;
import net.kingbets.cambista.http.models.Sistema.Cliente;
import net.kingbets.cambista.http.responses.CartaoResponse;
import net.kingbets.cambista.utils.DateTime;
import net.kingbets.cambista.utils.Str;
import net.kingbets.cambista.utils.URL;
import net.kingbets.cambista.view.fragments.BaseFragment;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class DialogCliente extends BaseDialog {



    public static final String TAG = "DIALOG_CLIENTE";
    public static final String ASTERISCOS = "* * * * * *";



    private Cliente cliente;
    private BaseFragment parent;

    private TextView txvNome;
    private TextView txvContato;
    private TextView txvCodigo;
    private ImageButton imbMostrarCodigo;
    private ImageButton imbEsconderCodigo;



    private Callback callbackCartao = new Callback() {
        @Override public void onFailure(@NonNull Call call, @NonNull IOException e) {
            Log.e(TAG, e.getMessage());
        }

        @Override public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
            proccessCartoes(response);
        }
    };



    public static void display(BaseFragment parent, FragmentManager manager, Cliente cliente) {

        DialogCliente dialog = new DialogCliente();
        dialog.setCliente(cliente);
        dialog.setParent(parent);

        if (manager != null) {
            dialog.show(manager, TAG);
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.AppTheme);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialog_cliente, container, false);

        setRootView(view);
        setLoader(R.id.content_loader);

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

        txvNome = rootView.findViewById(R.id.txv_nome);
        txvContato = rootView.findViewById(R.id.txv_contato);
        txvCodigo = rootView.findViewById(R.id.txv_codigo);
        imbMostrarCodigo = rootView.findViewById(R.id.imb_mostrar_codigo);
        imbEsconderCodigo = rootView.findViewById(R.id.imb_esconder_codigo);

        showData();
        addInputControls();
        requestCartoes();
    }


    @Override
    public void onDismiss(DialogInterface dialog) {

        if (parent != null) {
            parent.request();
        }

        super.onDismiss(dialog);
    }



    private void requestCartoes() {

        setLoaderVisibility(true);

        Cambista cambista = new CambistaContract(getContext()).first();
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(URL.CARTOES + "listar/" + cambista.getWebToken() + "/" + cliente.id)
                .build();

        client.newCall(request).enqueue(callbackCartao);
    }



    private void proccessCartoes(@NonNull Response response) throws IOException {
        if (response.isSuccessful()) {

            setLoaderVisibility(false);

            if (response.body() != null) {
                CartaoResponse cartaoResponse = CartaoResponse.receive(response.body().string());
                mostrarCartaoAtual(cartaoResponse.body.atual);
            }
        }
        else {
            setLoaderVisibility(false);
        }
    }



    private void showData() {
        txvNome.setText(cliente.nome);
        txvContato.setText(Str.mask(cliente.contato, "(##) # ####-####"));
        txvCodigo.setText(ASTERISCOS);
    }



    private void mostrarCartaoAtual(@Nullable Cartao atual) {

        final Cartao fnAtual = atual;

        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override public void run() {

                    TextView txvSemCartaoAtual = rootView.findViewById(R.id.txv_no_current_card);
                    LinearLayout layoutCartaoAtual = rootView.findViewById(R.id.layout_cartao_atual);

                    if (fnAtual != null) {

                        txvSemCartaoAtual.setVisibility(View.GONE);
                        layoutCartaoAtual.setVisibility(View.VISIBLE);

                        TextView txvSaldoAtual = rootView.findViewById(R.id.txv_saldo_atual);
                        TextView txvValidade = rootView.findViewById(R.id.txv_validade);
                        TextView txvSaldoInicial = rootView.findViewById(R.id.txv_saldo_inicial);
                        TextView txvQuantBilhetes = rootView.findViewById(R.id.txv_quant_bilhetes);

                        txvSaldoAtual.setText(Str.getCurrency(fnAtual.valorAtual));
                        txvValidade.setText(DateTime.inverse(fnAtual.validadoAte, "/"));
                        txvSaldoInicial.setText(Str.getCurrency(fnAtual.valorInicial));

                        txvQuantBilhetes.setText(
                            (fnAtual.quantBilhetses <= 0)
                                ? "NENHUM BILHETE"
                                : (fnAtual.quantBilhetses > 1)
                                    ? fnAtual.quantBilhetses + " BILHETS"
                                    : fnAtual.quantBilhetses + " BILHETE"
                        );
                    }
                    else {
                        txvSemCartaoAtual.setVisibility(View.VISIBLE);
                        layoutCartaoAtual.setVisibility(View.GONE);
                    }
                }
            });
        }
    }



    private void addInputControls() {

        imbMostrarCodigo.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                txvCodigo.setText(cliente.codigo);
                imbMostrarCodigo.setVisibility(View.GONE);
                imbEsconderCodigo.setVisibility(View.VISIBLE);
            }
        });

        imbEsconderCodigo.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                txvCodigo.setText(ASTERISCOS);
                imbEsconderCodigo.setVisibility(View.GONE);
                imbMostrarCodigo.setVisibility(View.VISIBLE);
            }
        });
    }



    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }



    public void setParent(BaseFragment parent) {
        this.parent = parent;
    }
}
