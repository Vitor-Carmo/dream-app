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
    private int id, qtdHospede, qtdCamaSolteiro, qtdCamaCasal, reserva;
    private double valor;

    public int getReserva() {
        return reserva;
    }

    public void setReserva(int reserva) {
        this.reserva = reserva;
    }

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

    public ArrayList<Rate> getComments() throws SQLException {
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connect = connectionHelper.connectionClass();

        String query = String.format(
                "SELECT NOTA_ACOMODACAO, " +
                "OBSERVACAO FROM TB_QUARTO_TIPO " +
                "INNER JOIN TB_QUARTO ON TB_QUARTO_TIPO.ID = TB_QUARTO.ID " +
                "INNER JOIN TB_RESERVA ON TB_QUARTO.ID = TB_RESERVA.QUARTO " +
                "INNER JOIN TB_AVALIACAO_RESERVA ON TB_AVALIACAO_RESERVA.ID = TB_RESERVA.ID " +
                "WHERE TB_QUARTO_TIPO.ID = %s",
                id
        );

        if (connect != null) {
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(query);
            ArrayList<Rate> rates = new ArrayList<>();

            while(rs.next()) {
                Rate rate = new Rate();

                rate.setAcomodacao(rs.getString(1));
                rate.setObservacao(rs.getString(2));
                rates.add(rate);
            }
            return rates;
        }

        return null;
    }

    public static ArrayList<Room> searchRooms(String search) throws SQLException {
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connect = connectionHelper.connectionClass();

        String query = "SELECT ID, NOME, QTD_HOSPEDE,QTD_CAMA_SOLTEIRO,QTD_CAMA_CASAL,FOTO,DESCRICAO,VALOR FROM TB_QUARTO_TIPO WHERE NOME LIKE '%"+ search +"%'";

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

    public static ArrayList<Room> myRooms(String id) throws SQLException {
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connect = connectionHelper.connectionClass();

        String query = "SELECT TB_QUARTO_TIPO.ID, NOME, QTD_HOSPEDE,QTD_CAMA_SOLTEIRO,QTD_CAMA_CASAL,FOTO,DESCRICAO,VALOR,TB_RESERVA.ID FROM TB_QUARTO_TIPO INNER JOIN TB_RESERVA ON QUARTO = TB_QUARTO_TIPO.ID WHERE HOSPEDE = " + id;

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
                room.setReserva(rs.getInt(9));

                Log.e("setId", "" + rs.getInt(1));
                Log.e("setNome", "" + rs.getString(2));
                Log.e("setQtdHospede", "" + rs.getInt(3));
                Log.e("setQtdCamaSolteiro", "" + rs.getInt(4));
                Log.e("setQtdCamaCasal", "" + rs.getInt(5));
                Log.e("setFoto", "" + rs.getString(6));
                Log.e("setDescricao", "" + rs.getString(7));
                Log.e("setValor", "" + rs.getDouble(8));
                Log.e("TB_RESERVA", "" + rs.getString(9));

                //Log.e("id", rooms.toString());

                rooms.add(room);
            }
            return rooms;
        }

        return null;
    }
}

