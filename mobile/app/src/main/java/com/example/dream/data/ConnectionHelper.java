package com.example.dream.data;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {
    //configurar sql server tcpip
    ConnectionHelper con;
    String username, password, ip, port, database;

    @SuppressLint("NewApi")
    public Connection connectionClass() {
        ip = "192.168.1.100";
        database = "DreamHotel";
        username="keye";
        password="123456789";
        port="1433";
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;

        String ConnectionURL;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL= "jdbc:jtds:sqlserver://"+ ip + ":"+ port+";"+ "databasename="+ database+";user="+username+";password="+password+";useNTLMv2=true;";
            connection = DriverManager.getConnection(ConnectionURL);

        }catch (Exception exception){
            Log.e("Error Connection ", exception.getMessage());
            exception.printStackTrace();
        }
        return  connection;
    }
}
