package com.example.rampagegg.model.useraccount;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MmrEstimate {

    @SerializedName("estimate")
    @Expose
    public Long estimate;

    public Long getEstimate() {
        return estimate;
    }
}
