package com.example.dream.model;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.dream.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// spinner estado cidade ibge
public class SpinnerStateCity {
    private final Context context;

    private final RequestQueue mRequest;
    private final RequestQueue mRequest2;
    private final ArrayList<String> statesArray;
    private final ArrayList<String> cityArray;
    private final ArrayList<String> cityIdArray;
    private final Spinner stateSpinner;
    private final Spinner citySpinner;

    private String state;
    private String city;

    public SpinnerStateCity(Context c, Spinner stateSpinner, Spinner citySpinner) {
        this.context = c;
        this.statesArray = new ArrayList<>();
        this.cityIdArray = new ArrayList<>();
        this.cityArray = new ArrayList<>();

        this.stateSpinner = stateSpinner;
        this.citySpinner = citySpinner;

        this.mRequest = Volley.newRequestQueue(this.context);
        this.mRequest2 = Volley.newRequestQueue(this.context);

        this.getStateJson();

        this.stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                state = parent.getItemAtPosition(position).toString();
                getCityJson(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        this.citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                city = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void getStateJson() {
        String URL = "https://servicodados.ibge.gov.br/api/v1/localidades/estados";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            statesArray.clear();
                            cityIdArray.clear();

                            if(state != null) {
                                statesArray.add(state);
                                cityIdArray.add("-1");
                            };

                            for (int i = 0; i < response.length(); i++) {
                                JSONObject estados = response.getJSONObject(i);
                                String nome = estados.getString("nome");
                                String id = estados.getString("id");
                                statesArray.add(nome);
                                cityIdArray.add(id);
                            }
                            stateSpinner.setAdapter(new ArrayAdapter<String>(context, R.layout.spinner_dropdown_item, statesArray));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequest.add(request);

    }

    private void getCityJson(final int position) {
        String URL = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/" + cityIdArray.get(position) + "/municipios";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            cityArray.clear();

                            if(city != null) cityArray.add(city);

                            for (int i = 0; i < response.length(); i++) {
                                JSONObject estados = response.getJSONObject(i);
                                String nome = estados.getString("nome");
                                cityArray.add(nome);
                            }

                            citySpinner.setAdapter(new ArrayAdapter<String>(context, R.layout.spinner_dropdown_item, cityArray));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequest2.add(request);
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public void setValues(String state, String city) {
        this.state = state;
        this.city = city;
        getStateJson();
    }

}
