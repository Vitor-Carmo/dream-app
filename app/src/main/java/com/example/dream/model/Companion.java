package com.example.dream.model;

import android.util.Log;

import java.util.ArrayList;

public class Companion {
    private String nome, cpf;
    private int reserva;

    public static void setCompanion(ArrayList<Companion> companions){
        StringBuilder query = new StringBuilder("SELECT TOP 1 @id = ID FROM TB_RESERVA ORDER BY ID DESC\n");
        for (int i = 0; i < companions.size(); i++) {
            query.append(String.format("INSERT INTO TB_ACOMPANHANTE ( NOME, CPF, RESERVA ) VALUES( '%s', '%s', @id);\n",
                    companions.get(i).getNome(),
                    companions.get(i).getCpf()
            ));
        }

        Log.e("Query Acompanhante", query.toString());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getReserva() {
        return reserva;
    }

    public void setReserva(int reserva) {
        this.reserva = reserva;
    }
}
