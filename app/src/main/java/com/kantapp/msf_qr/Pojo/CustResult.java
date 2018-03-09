package com.kantapp.msf_qr.Pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Arvind Kant on 2/28/2018.
 */

public class CustResult
{
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("mobile")
    @Expose
    private String mobile;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("amb_id")
    @Expose
    private String amb_id;

    @SerializedName("amount")
    @Expose
    private String amount;

    public CustResult(String id, String name, String mobile, String email, String amb_id, String amount) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.amb_id = amb_id;
        this.amount = amount;
    }

    public CustResult() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAmb_id() {
        return amb_id;
    }

    public void setAmb_id(String amb_id) {
        this.amb_id = amb_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
