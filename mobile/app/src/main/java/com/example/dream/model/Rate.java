package com.example.dream.model;

import com.example.dream.data.ConnectionHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Rate {
    private int reserva;
    private String atendimento, acomodacao, recomendacao, observacao;
    private final Connection connect;

    public Rate() {
        ConnectionHelper connectionHelper = new ConnectionHelper();
        this.connect = connectionHelper.connectionClass();
    }


    public void create() throws SQLException {
        String query = String.format("" +
                "INSERT INTO TB_AVALIACAO_RESERVA(\n" +
                    "ID,\n" +
                    "DATA_AVALIACAO,\n" +
                    "NOTA_ATENDIMENTO,\n" +
                    "NOTA_ACOMODACAO,\n" +
                    "NOTA_RECOMENDACAO,\n" +
                    "OBSERVACAO\n" +
                ")VALUES(\n" +
                    "%s,\n" +
                    "GETDATE(),\n" +
                    "%s,\n" +
                    "%s,\n" +
                    "%s,\n" +
                    "'%s'\n" +
                ")",
                String.valueOf(reserva),
                atendimento,
                acomodacao,
                recomendacao,
                observacao
        );
        if (connect != null) {
            Statement st = connect.createStatement();
            st.execute(query);
        }
    }


    public boolean hasRated(String id) throws SQLException {
        String query = String.format(
                "SELECT * FROM TB_AVALIACAO_RESERVA " +
                "INNER JOIN TB_RESERVA " +
                "ON TB_RESERVA.ID = TB_AVALIACAO_RESERVA.ID " +
                "INNER JOIN TB_HOSPEDE ON HOSPEDE = TB_HOSPEDE.ID " +
                "WHERE TB_HOSPEDE.ID = %s  AND TB_RESERVA.ID = %s",
                id,
                reserva
        );

        if (connect != null) {
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(query);

            return rs.next();
        }

        return false;
    }

    public int getReserva() {
        return reserva;
    }

    public void setReserva(int id) {
        this.reserva = id;
    }

    public String getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(String atendimento) {
        this.atendimento = atendimento;
    }

    public String getAcomodacao() {
        return acomodacao;
    }

    public void setAcomodacao(String acomodacao) {
        this.acomodacao = acomodacao;
    }

    public String getRecomendacao() {
        return recomendacao;
    }

    public void setRecomendacao(String recomendacao) {
        this.recomendacao = recomendacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
