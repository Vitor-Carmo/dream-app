package com.example.dream.model;

import android.util.Log;

import java.util.ArrayList;

public class Reservation {
    private String tipo_pagamento, hospede;
    private int qtd_acompanhante, quarto;
    private double total;
    private ArrayList<Companion> companions;


    public void create(){
        String query = String.format(
                "EXEC\t[dbo].[SP_RESERVA_SET]\n" +
                "\t\t@HOSPEDE = %s,\n" +
                "\t\t@QUARTO = %s,\n" +
                "\t\t@DTENTRADA = NULL,\n" +
                "\t\t@DTSAIDA = NULL,\n" +
                "\t\t@CHECKIN = NULL,\n" +
                "\t\t@CHECKOUT = NULL,\n" +
                "\t\t@TIPOPAGAMENTO = %s,\n" +
                "\t\t@TOTAL = %s,\n" +
                "\t\t@QTDACOMP = %s",
                hospede,
                quarto,
                tipo_pagamento,
                total,
                qtd_acompanhante
        );


        Log.e("Query", query);
        Companion.setCompanion(companions);
    }

    public ArrayList<Companion> getCompanions() {
        return companions;
    }

    public void setCompanions(ArrayList<Companion> companions) {
        this.companions = companions;
    }

    public String getTipo_pagamento() {
        return tipo_pagamento;
    }

    public void setTipo_pagamento(String tipo_pagamento) {
        this.tipo_pagamento = tipo_pagamento;
    }

    public int getQuarto() {
        return quarto;
    }

    public void setQuarto(int quarto) {
        this.quarto = quarto;
    }

    public String getHospede() {
        return hospede;
    }

    public void setHospede(String hospede) {
        this.hospede = hospede;
    }

    public int getQtd_acompanhante() {
        return qtd_acompanhante;
    }

    public void setQtd_acompanhante(int qtd_acompanhante) {
        this.qtd_acompanhante = qtd_acompanhante;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
