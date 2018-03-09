package com.kantapp.msf_qr.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Arvind Kant on 2/28/2018.
 */

public class CustomerPojo
{
    @SerializedName("error")
    @Expose
    private Boolean error;

    @SerializedName("data")
    @Expose
    private CustResult data;

    @SerializedName("message")
    @Expose
    private String message;


    public CustomerPojo(Boolean error, CustResult data, String message) {
        this.error = error;
        this.data = data;
        this.message = message;
    }

    public CustomerPojo() {
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public CustResult getData() {
        return data;
    }

    public void setData(CustResult data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
