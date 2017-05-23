package ru.bda.utair.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * email: denbelobaba@gmail.com
 *
 * @author Belobaba Denis
 */

public class CloudsDTO {
    @SerializedName("all")
    @Expose
    private Integer all;

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }
}
