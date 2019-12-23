package net.kingbets.cambista.http.responses;


import android.support.annotation.NonNull;

import net.kingbets.cambista.http.models.Sistema.Cliente;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ClienteResponse extends BaseResponse {



    public List<Cliente> body;



    private ClienteResponse() {
        this.code = 0;
        this.body = new ArrayList<>();
    }



    public static ClienteResponse receive(@NonNull String bodyString) {

        ClienteResponse response = new ClienteResponse();

        try {

            JSONObject json = new JSONObject(bodyString);
            JSONArray body = json.getJSONArray("body");
            response.code = json.getInt("code");

            if (response.code == 200) {

                for (int i = 0; i < body.length(); i++) {

                    Cliente cliente = new Cliente();

                    cliente.id = body.getJSONObject( i ).getLong("id");
                    cliente.cambista = body.getJSONObject( i ).getLong("cambista");
                    cliente.nome = body.getJSONObject( i ).getString("nome");
                    cliente.contato = body.getJSONObject( i ).getString("contato");
                    cliente.codigo = body.getJSONObject( i ).getString("codigo");
                    cliente.token = body.getJSONObject( i ).getString("token");
                    cliente.bloqueado = ("S".equals(body.getJSONObject( i ).getString("bloqueado")));

                    response.body.add(cliente);
                }
            }
        }
        catch (JSONException e) {
            response.code = 512;
        }

        return response;
    }
}
