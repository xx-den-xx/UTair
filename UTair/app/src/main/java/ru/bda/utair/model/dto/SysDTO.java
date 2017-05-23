package ru.bda.utair.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * email: denbelobaba@gmail.com
 *
 * @author Belobaba Denis
 */

public class SysDTO {

    @SerializedName("pod")
    @Expose
    private String pod;

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }
}
