package net.kingbets.cambista.printer;


import android.content.Context;
import android.content.res.Resources;

import net.kingbets.cambista.R;
import net.kingbets.cambista.bluetooth.StreamManager;
import net.kingbets.cambista.model.remote.apostas.Aposta;
import net.kingbets.cambista.utils.DateTime;
import net.kingbets.cambista.utils.Str;

import java.util.Locale;


public class Printer {



    private Context context;
    private StreamManager streamManager;



    public Printer(Context context, StreamManager streamManager) {
        this.context = context;
        this.streamManager = streamManager;
    }



    public void setupDevice(String deviceName) {
        Setup.set(
                context, deviceName.equals("RP80-A") ? Setup.Config.LARGE : Setup.Config.NORMAL
        );
    }



    public void printHeader() {

        Resources res = context.getResources();
        String header = res.getString(R.string.app_name_upper);
        String info = res.getString(R.string.cupom_aposta_info);

        streamManager.printText(header, Commands.Alignment.CENTER, Commands.FontSize.F16, 0, false);

        streamManager.printDashes(0, false);
        streamManager.printText(info, Commands.Alignment.CENTER, 0, false);
        streamManager.printDashes(0, false);
    }



    public void printCodigo(String codigo) {
        Resources res = context.getResources();

        String codLB = res.getString(R.string.cupom_codigo);
        byte[] spacesBTcod = Utils.getSpaceCount(codLB, codigo);

        streamManager.printText(codLB, Commands.Alignment.LEFT, 0, true);
        streamManager.printText(spacesBTcod, Commands.Alignment.LEFT, 0);
        streamManager.printText(codigo, Commands.Alignment.LEFT, 1, false);
    }



    public void printDataHoraAposta(String dataHora) {
        Resources res = context.getResources();

        String dataLB = res.getString(R.string.cupom_data_hora);
        byte[] spacesBTdata = Utils.getSpaceCount(dataLB, dataHora);

        streamManager.printText(dataLB, Commands.Alignment.LEFT, 0, true);
        streamManager.printText(spacesBTdata, Commands.Alignment.LEFT, 0);
        streamManager.printText(dataHora, Commands.Alignment.LEFT, 1, false);
    }



    public void printCambistaInfo(String nome, String tell) {
        Resources res = context.getResources();

        String nomeLB = res.getString(R.string.cupom_cambista);
        byte[] spacesBTnome = Utils.getSpaceCount(nomeLB, nome);

        String tellLB = res.getString(R.string.cupom_contato);
        byte[] spacesBTtell = Utils.getSpaceCount(tellLB, tell);

        streamManager.printText(nomeLB, Commands.Alignment.LEFT, 0, true);
        streamManager.printText(spacesBTnome, Commands.Alignment.LEFT, 0);
        streamManager.printText(nome, Commands.Alignment.LEFT, 1, false);

        streamManager.printText(tellLB, Commands.Alignment.LEFT, 0, true);
        streamManager.printText(spacesBTtell, Commands.Alignment.LEFT, 0);
        streamManager.printText(tell, Commands.Alignment.LEFT, 1, false);
    }



    public void printApostador(String apostador) {
        Resources res = context.getResources();

        String apostadorLB = res.getString(R.string.cupom_apostador);
        byte[] spacesBTapostador = Utils.getSpaceCount(apostadorLB, apostador);

        streamManager.printText(apostadorLB, Commands.Alignment.LEFT, 0, true);
        streamManager.printText(spacesBTapostador, Commands.Alignment.LEFT, 0);
        streamManager.printText(apostador, Commands.Alignment.LEFT, 1, false);

        String apostaLB = res.getString(R.string.cupom_aposta);
        String cotacaoLB = res.getString(R.string.cupom_aposta_cotacao);
        byte[] spacesBTaposta = Utils.getSpaceCount(apostaLB, cotacaoLB);

        streamManager.printDashes(0, false);
        streamManager.printText(apostaLB, Commands.Alignment.LEFT, 0, true);
        streamManager.printText(spacesBTaposta, Commands.Alignment.LEFT, 0);
        streamManager.printText(cotacaoLB, Commands.Alignment.LEFT, 1, false);
        streamManager.printDashes(0, false);
    }






    public void printAposta(Aposta aposta, String futebol, String campeonato, String equipes) {

        Resources res = context.getResources();

        streamManager.printText(futebol, Commands.Alignment.LEFT, 0, true);
        streamManager.printText(campeonato, Commands.Alignment.LEFT, 1, true);
        streamManager.printText(equipes, Commands.Alignment.LEFT, 1, false);

        String cotacao = String.format(Locale.getDefault(), "%.2f", aposta.cotacao);
        byte[] spacesBTcotacao = Utils.getSpaceCount(aposta.titulo, cotacao);

        streamManager.printText(aposta.titulo, Commands.Alignment.LEFT, 0, true);
        streamManager.printText(spacesBTcotacao, Commands.Alignment.LEFT, 0);
        streamManager.printText(cotacao, Commands.Alignment.LEFT, 1, false);

        streamManager.printDashes(0, false);
    }






    public void printQuantJogos(String quantJogos) {
        Resources res = context.getResources();

        String quantLB = res.getString(R.string.cupom_quant_jogos);
        byte[] spacesBTvalor = Utils.getSpaceCount(quantLB, quantJogos);

        streamManager.printText(quantLB, Commands.Alignment.LEFT, 0, true);
        streamManager.printText(spacesBTvalor, Commands.Alignment.LEFT, 0);
        streamManager.printText(quantJogos, Commands.Alignment.LEFT, 1, false);
    }



    public void printCotacao(String cotacao) {
        Resources res = context.getResources();

        String cotacaoLB = res.getString(R.string.cupom_cotacao);
        byte[] spacesBTvalor = Utils.getSpaceCount(cotacaoLB, cotacao);

        streamManager.printText(cotacaoLB, Commands.Alignment.LEFT, 0, true);
        streamManager.printText(spacesBTvalor, Commands.Alignment.LEFT, 0);
        streamManager.printText(cotacao, Commands.Alignment.LEFT, 1, false);
    }



    public void printTotalApostado(String valor) {
        Resources res = context.getResources();

        String valorLB = res.getString(R.string.cupom_total_apostado);
        byte[] spacesBTvalor = Utils.getSpaceCount(valorLB, valor);

        streamManager.printText(valorLB, Commands.Alignment.LEFT, 0, true);
        streamManager.printText(spacesBTvalor, Commands.Alignment.LEFT, 0);
        streamManager.printText(valor, Commands.Alignment.LEFT, 1, false);
    }



    public void printPossivelRetorno(String valor) {
        Resources res = context.getResources();

        String retornoLB = res.getString(R.string.cupom_possivel_retorno);
        byte[] spacesBTvalor = Utils.getSpaceCount(retornoLB, valor);

        streamManager.printText(retornoLB, Commands.Alignment.LEFT, 0, true);
        streamManager.printText(spacesBTvalor, Commands.Alignment.LEFT, 0);
        streamManager.printText(valor, Commands.Alignment.LEFT, 1, false);
    }



    public void printRodape() {
        streamManager.printDashes(1, false);
        streamManager.printText("REGRAS - SUGESTOES - RECLAMACOES", Commands.Alignment.CENTER, 1, false);
        streamManager.printText("ACESSE: WWW.KINGBETS.NET", Commands.Alignment.CENTER, 2, false);
    }





    private String getStatus(String status) {

        switch (status) {

            case "G":
                return "Ganhou";

            case "P":
                return "Perdeu";

            case "N":
                return "Anulada";

            default:
                return "Aguardando";
        }
    }
}
