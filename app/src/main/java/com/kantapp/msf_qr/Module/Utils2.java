package com.kantapp.msf_qr.Module;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.Patterns;

import java.util.regex.Pattern;


public class Utils2
{
    Activity activity;
    private ProgressDialog progress;
    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;

    public Utils2(Activity activity)
    {
        this.activity = activity;
        progress=new ProgressDialog(activity);

        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);


    }



    public static boolean isMobileValid(String phone)
    {
        boolean check;
        check = !Pattern.matches("[a-zA-Z.*+]+", phone) && !(phone.length() < 10 || phone.length() > 13);
        return check;
    }

    public static boolean isEmailValid(String email)
    {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    public  void ShowDialog(String title,String message)
    {
        builder = new AlertDialog.Builder(activity);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog=builder.create();
        alertDialog.show();
    }

    public void startDialog(String message)
    {
        progress.setMessage(message);
        progress.show();
    }
    public void endDialog()
    {
        progress.dismiss();
    }



}
