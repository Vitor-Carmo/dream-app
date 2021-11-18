package com.example.dream.model;

import android.util.Log;

import com.example.dream.data.ConnectionHelper;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Companion {
    private static Connection connect;
    private String nome, cpf;

    public Companion() {
        ConnectionHelper connectionHelper = new ConnectionHelper();
        connect = connectionHelper.connectionClass();
    }

    public static void setCompanion(ArrayList<Companion> companions) throws SQLException {
        StringBuilder query = new StringBuilder(
                "\nDECLARE @id int\n" +
                "SELECT TOP 1 @id = ID FROM TB_RESERVA ORDER BY ID DESC\n"
         );
        for (int i = 0; i < companions.size(); i++) {
            query.append(String.format("INSERT INTO TB_ACOMPANHANTE ( NOME, CPF, RESERVA ) VALUES( '%s', '%s', @id);\n",
                    companions.get(i).getNome(),
                    companions.get(i).getCpf()
            ));
        }

        if (connect != null) {
            Statement st = connect.createStatement();
            st.execute(query.toString());
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

}
