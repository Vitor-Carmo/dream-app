package com.example.dream.model;

import android.content.Context;
import android.util.Log;

import com.example.dream.data.ConnectionHelper;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Room implements Serializable {
    private String nome, foto, descricao;
    private int id, qtdHospede, qtdCamaSolteiro, qtdCamaCasal;
    private double valor;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQtdHospede() {
        return qtdHospede;
    }

    public void setQtdHospede(int qtdHospede) {
        this.qtdHospede = qtdHospede;
    }

    public int getQtdCamaSolteiro() {
        return qtdCamaSolteiro;
    }

    public void setQtdCamaSolteiro(int qtdCamaSolteiro) {
        this.qtdCamaSolteiro = qtdCamaSolteiro;
    }

    public int getQtdCamaCasal() {
        return qtdCamaCasal;
    }

    public void setQtdCamaCasal(int qtdCamaCasal) {
        this.qtdCamaCasal = qtdCamaCasal;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public static ArrayList<Room> getRooms() throws SQLException {
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connect = connectionHelper.connectionClass();

        String query = "SELECT ID, NOME, QTD_HOSPEDE,QTD_CAMA_SOLTEIRO,QTD_CAMA_CASAL,FOTO,DESCRICAO,VALOR FROM TB_QUARTO_TIPO";

        if (connect != null) {
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(query);
            ArrayList<Room> rooms = new ArrayList<Room>();

            while(rs.next()) {
                Room room = new Room();
                room.setId(rs.getInt(1));
                room.setNome(rs.getString(2));
                room.setQtdHospede(rs.getInt(3));
                room.setQtdCamaSolteiro(rs.getInt(4));
                room.setQtdCamaCasal(rs.getInt(5));
                room.setFoto(rs.getString(6));
                room.setDescricao(rs.getString(7));
                room.setValor(rs.getDouble(8));

                Log.e("setId", "" + rs.getInt(1));
                Log.e("setNome", "" + rs.getString(2));
                Log.e("setQtdHospede", "" + rs.getInt(3));
                Log.e("setQtdCamaSolteiro", "" + rs.getInt(4));
                Log.e("setQtdCamaCasal", "" + rs.getInt(5));
                Log.e("setFoto", "" + rs.getString(6));
                Log.e("setDescricao", "" + rs.getString(7));
                Log.e("setValor", "" + rs.getDouble(8));

                //Log.e("id", rooms.toString());

                rooms.add(room);
            }
            return rooms;
        }

        return null;
    }
}

