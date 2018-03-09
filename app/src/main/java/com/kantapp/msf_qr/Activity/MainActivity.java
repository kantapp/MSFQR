package com.kantapp.msf_qr.Activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.kantapp.msf_qr.Module.B2T;
import com.kantapp.msf_qr.Module.Utils2;
import com.kantapp.msf_qr.Pojo.CustomerPojo;
import com.kantapp.msf_qr.R;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Activity {

    private Utils2 utils2;
    private Gson gson;
    private LinearLayout qrCard;
    private LinearLayout welcomeCard;
    private TextView name,mobile,email,amount;
    private TextView messageStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        utils2=new Utils2(this);
        Intent intent=getIntent();
        gson=new Gson();
        String qrData=intent.getStringExtra("data");
        qrCard= (LinearLayout) findViewById(R.id.qrCard);
        welcomeCard=findViewById(R.id.welcomeCard);
        name=findViewById(R.id.name);
        mobile=findViewById(R.id.mobile);
        email=findViewById(R.id.email);
        amount=findViewById(R.id.amount);
        messageStatus=findViewById(R.id.messageStatus);

//        Toast.makeText(this, ""+B2T.Base64ToText(qrData), Toast.LENGTH_SHORT).show();

//        Toast.makeText(this, ""+IsBase64Encoded(qrData)+B2T.Base64ToText(qrData), Toast.LENGTH_SHORT).show();
//

        Button scanActivity=findViewById(R.id.scanActivity);
        scanActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Scanner.class));
            }
        });

        if(qrData!=null)
        {
            if(IsBase64Encoded(qrData))
            {
//            Toast.makeText(this, ""+B2T.Base64ToText(qrData), Toast.LENGTH_SHORT).show();
                VerifyUser(B2T.Base64ToText(qrData));
            }
            else
            {

                Toast.makeText(this, "Invalid User", Toast.LENGTH_SHORT).show();
            }
        }

    }
    public boolean IsBase64Encoded(String s)
    {
        try
        {
//            byte[] data= android.util.Base64.decode(s.getBytes(), android.util.Base64.DEFAULT);

            return s.replace(" ","").length()%4==0;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    public void VerifyUser(final String id)
    {

        utils2.startDialog("Verifying Customer....");
        String URL_CustCheck="https://kantapp.com/Zeist/MSF/check_cust.php";
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_CustCheck, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
//                Toast.makeText(MainActivity.this, ""+response, Toast.LENGTH_LONG).show();
                Log.d("Arvind", "onResponse: "+response);
                CustomerPojo customerPojo=gson.fromJson(response,CustomerPojo.class);
                if(!customerPojo.getError())
                {
                    qrCard.setVisibility(View.GONE);
                    welcomeCard.setVisibility(View.VISIBLE);
                    name.setText(customerPojo.getData().getName());
                    mobile.setText("Mobile : "+customerPojo.getData().getMobile());
                    email.setText("Email : "+customerPojo.getData().getEmail());
                    amount.setText("Amount : ₹ "+customerPojo.getData().getAmount());
                    messageStatus.setText(customerPojo.getMessage());
//                    Toast.makeText(MainActivity.this, ""+customerPojo.getResult().getName(), Toast.LENGTH_SHORT).show();
                }
                else
                {
//                    Toast.makeText(getApplicationContext(), ""+customerPojo.getMessage(), Toast.LENGTH_LONG).show();
                    utils2.ShowDialog("Message",""+customerPojo.getMessage());
                }
                Log.d("Arvind", "onResponse: "+response);
                utils2.endDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, ""+error, Toast.LENGTH_SHORT).show();
                utils2.endDialog();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                return params;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
