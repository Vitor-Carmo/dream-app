package com.example.dream.model;

import android.util.Log;

import com.example.dream.data.ConnectionHelper;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Client {
    Connection connect;
    private String
            usuario,
            senha,
            nome,
            cpf,
            rg,
            data,
            celular,
            email,
            cep,
            estado,
            cidade,
            bairro,
            endereco,
            numero,
            complemento;

    public String getUsuario() {
        return usuario;
    }



    public Client() {
        ConnectionHelper connectionHelper = new ConnectionHelper();
        this.connect = connectionHelper.connectionClass();
    }

    public void create() throws SQLException {

        String query = String.format("\n" +
            "EXEC\t[dbo].[SP_USUARIO_SET]\n" +
            "\t\t@ID = NULL,\n" +
            "\t\t@NIVEL = 3,\n" +
            "\t\t@USUARIO = \"%s\",\n" +
            "\t\t@SENHA = \"%s\",\n" +
            "\t\t@STATUS = 1;\n" +
            "DECLARE @id int\n" +
            "\n" +
            "SELECT TOP 1 @id = ID FROM TB_USUARIO ORDER BY ID DESC;\n" +
            "\n" +
            "EXEC SP_HOSPEDE_SET @id, \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\";\n" +
            "\n" +
            "EXEC SP_ENDERECO_SET @id, \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", '%s';",
            usuario,
            senha,
            nome,
            cpf,
            rg,
            data,
            celular,
            email,
            cep,
            estado,
            cidade,
            bairro,
            endereco,
            numero,
            complemento
        );
        debugQuery(query);
        if (connect != null) {
            Statement st = connect.createStatement();
            st.execute(query);
        }
    }


    public void read(String id) throws SQLException {
        String query = String.format(
                "SELECT USUARIO, " +
                "NOME, " +
                "CPF, " +
                "RG, " +
                "DATA_NASCIMENTO, " +
                "TELEFONE, " +
                "EMAIL, " +
                "CEP, " +
                "ESTADO, " +
                "CIDADE, " +
                "BAIRRO, " +
                "RUA, " +
                "NUMERO, " +
                "COMPLEMENTO " +
                "FROM TB_USUARIO " +
                "INNER JOIN TB_HOSPEDE ON TB_USUARIO.ID = TB_HOSPEDE.ID " +
                "INNER JOIN  TB_ENDERECO ON TB_ENDERECO.ID = TB_USUARIO.ID " +
                "WHERE TB_USUARIO.ID = %s", id);

        if (connect != null) {
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(query);
            debugQuery(query);
            if(rs.next()) {
                Log.e("USUARIO", rs.getString(1));

                this.usuario = rs.getString(1);
                this.nome = rs.getString(2);
                this.cpf = rs.getString(3);
                this.rg = rs.getString(4);
                this.data = rs.getString(5);
                this.celular = rs.getString(6);
                this.email = rs.getString(7);
                this.cep = rs.getString(8);
                this.estado = rs.getString(9);
                this.cidade = rs.getString(10);
                this.bairro = rs.getString(11);
                this.endereco = rs.getString(12);
                this.numero = rs.getString(13);
                this.complemento = rs.getString(14);
            }
        }
    }

    public void updateUser(){
        String query = "\n" +
            "UPDATE TB_USUARIO SET  \n" +
            "NIVEL = @NIVEL,  \n" +
            "USUARIO = @USUARIO,  \n" +
            "[STATUS] = @STATUS,  \n" +
            "SENHA = ENCRYPTBYKEY(@CHAVE, @SENHA),  \n" +
            "DATA_ULT_ALTERACAO = GETDATE()  \n" +
            "WHERE ID = @ID";
    }

    public void updatePassword(){
        String query = "\n" +
                "UPDATE TB_USUARIO SET  \n" +
                "NIVEL = @NIVEL,  \n" +
                "USUARIO = @USUARIO,  \n" +
                "[STATUS] = @STATUS,  \n" +
                "SENHA = ENCRYPTBYKEY(@CHAVE, @SENHA),  \n" +
                "DATA_ULT_ALTERACAO = GETDATE()  \n" +
                "WHERE ID = @ID";
    }


    public String login(String usuario, String senha) throws SQLException {
        String query = String.format("EXEC SP_LOGIN '%s', '%s'", usuario, senha );
            //debugQuery(rs.getString(1));

            if (connect != null) {
                Statement st = connect.createStatement();
                ResultSet rs = st.executeQuery(query);

                if(rs.next()) {
                    return rs.getString(1);
                }
            }

        return null;
    }

    private void debugQuery(String query){
        Log.e("DATABASE QUERY", query);
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

}




       /*
            while (rs.next()){
                bairro.setText(rs.getString(2)); // coluna
            }
        */