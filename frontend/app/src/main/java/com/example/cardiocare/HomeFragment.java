package com.example.cardiocare;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeFragment extends Fragment implements View.OnClickListener {

    Context context;
    HeartDisease heartdisease;
    TextView textTarget;
    EditText textAge,textSex,textCp,textTrestbps,textChol,textFbs,textRestecg,textThalach,textExang,textOldpeak,textSlope,textCa,textThal;
    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().setTitle("Cardio Disease Prediction");

        heartdisease = new HeartDisease();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        this.context = container.getContext();

        textAge = view.findViewById(R.id.textAge);
        textSex = view.findViewById(R.id.textSex);
        textCp = view.findViewById(R.id.textCp);
        textTrestbps = view.findViewById(R.id.textTrestbps);
        textChol = view.findViewById(R.id.textChol);
        textFbs= view.findViewById(R.id.textFbs);
        textRestecg = view.findViewById(R.id.textRestecg);
        textThalach = view.findViewById(R.id.textThalach);
        textExang = view.findViewById(R.id.textExang);
        textOldpeak = view.findViewById(R.id.textOldpeak);
        textSlope = view.findViewById(R.id.textSlope);
        textCa = view.findViewById(R.id.textCa);
        textThal = view.findViewById(R.id.textThal);
        textTarget = view.findViewById(R.id.textTarget);

        Button btnPredictTarget = view.findViewById(R.id.btnpredicttarget);
        btnPredictTarget.setOnClickListener(this);

        return view;
    }

    private void GetTarget() {

        String url = "http://35.169.164.147/266/predicttarget?";
        url += "age=" + heartdisease.Age + "&";
        url += "sex=" + heartdisease.Sex + "&";
        url += "cp=" + heartdisease.Cp + "&";
        url += "trestbps=" + heartdisease.Trestbps + "&";
        url += "chol=" + heartdisease.Chol + "&";
        url += "fbs=" + heartdisease.Fbs + "&";
        url += "restecg=" + heartdisease.Reatecg + "&";
        url += "thalach=" + heartdisease.Thalach + "&";
        url += "exang=" + heartdisease.Exang + "&";
        url += "oldpeak=" + heartdisease.Oldpeak + "&";
        url += "slope=" + heartdisease.Slope + "&";
        url += "ca=" + heartdisease.Ca + "&";
        url += "thal=" + heartdisease.Thal;

        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(context);

        // make json array request and then extracting data from each json object.
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject responseObj = response.getJSONObject(0);

                    String returnValue = responseObj.getString("target");
//                    textTarget.setText("Target: $" + returnValue);
                    if(returnValue.equals("1")){
                        textTarget.setText("You have heart disease");
                    }else{
                        textTarget.setText("You don't have Disease");
                    }

                    Toast.makeText(context, returnValue, Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonArrayRequest);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnpredicttarget:
                heartdisease.Age =  textAge.getText().toString();
                heartdisease.Sex =  textSex.getText().toString();
                heartdisease.Cp =  textCp.getText().toString();
                heartdisease.Trestbps =  textTrestbps.getText().toString();
                heartdisease.Chol =  textChol.getText().toString();
                heartdisease.Fbs =  textFbs.getText().toString();
                heartdisease.Reatecg =  textRestecg.getText().toString();
                heartdisease.Thalach =  textThalach.getText().toString();
                heartdisease.Exang =  textExang.getText().toString();
                heartdisease.Oldpeak =  textOldpeak.getText().toString();
                heartdisease.Slope =  textSlope.getText().toString();
                heartdisease.Ca =  textCa.getText().toString();
                heartdisease.Thal =  textThal.getText().toString();


                GetTarget();
                textTarget.setText("wait..");
                break;
        }
    }
}