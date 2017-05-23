package ru.bda.utair.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * email: denbelobaba@gmail.com
 *
 * @author Belobaba Denis
 */

public class RainDTO {
    @SerializedName("3h")
    @Expose
    private Double _3h;

    public Double get3h() {
        return _3h;
    }

    public void set3h(Double _3h) {
        this._3h = _3h;
    }
}
