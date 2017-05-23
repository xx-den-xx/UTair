package ru.bda.utair.model.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * email: denbelobaba@gmail.com
 *
 * @author Belobaba Denis
 */

public class TownResultDTO {
    @SerializedName("results")
    @Expose
    private List<TownDTO> towns = null;

    public List<TownDTO> getTowns() {
        return towns;
    }

    public void setTowns(List<TownDTO> towns) {
        this.towns = towns;
    }
}
