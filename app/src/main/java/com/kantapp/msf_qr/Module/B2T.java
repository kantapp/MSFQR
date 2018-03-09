package com.kantapp.msf_qr.Module;

/**
 * Created by Arvind Kant on 2/26/2018.
 */
import android.util.Base64;

public class B2T
{
    public static String Base64ToText(String value)
    {
        byte[] decodeValue=Base64.decode(value.getBytes(),Base64.DEFAULT);

        return new String(decodeValue);
    }
}
