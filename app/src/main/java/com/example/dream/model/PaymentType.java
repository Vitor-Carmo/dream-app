package com.example.dream.model;

import android.util.Log;

import com.example.dream.data.ConnectionHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PaymentType {
    private String id, pagamento_desc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPagamento_desc() {
        return pagamento_desc;
    }

    public void setPagamento_desc(String pagamento_desc) {
        this.pagamento_desc = pagamento_desc;
    }


    public static ArrayList<PaymentType> getPaymentsTypes() throws SQLException {
        ConnectionHelper connectionHelper = new ConnectionHelper();
        Connection connect = connectionHelper.connectionClass();
        String query = "SELECT ID, PAGAMENTO_DESC FROM TB_PAGAMENTO_TIPO ";

        if (connect != null) {
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(query);
            ArrayList<PaymentType> paymentTypes = new ArrayList<PaymentType>();

            while(rs.next()) {
                PaymentType paymentType = new PaymentType();
                paymentType.setId(rs.getString(1));
                paymentType.setPagamento_desc(rs.getString(2));

                Log.e("ID", "" + rs.getString(1));
                Log.e("PAGAMENTO_DESC", "" + rs.getString(2));

                paymentTypes.add(paymentType);
            }
            return paymentTypes;
        }

        return null;
    }
}
