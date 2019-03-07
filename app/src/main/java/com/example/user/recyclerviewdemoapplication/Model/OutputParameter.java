package com.example.user.recyclerviewdemoapplication.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created on 07-03-2019.
 * @author Priyanka Gole
 * Model class of response.
 */

public class OutputParameter {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("rows")
    @Expose
    private List<RowModel> rowModels = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<RowModel> getRowModels() {
        return rowModels;
    }

    public void setRowModels(List<RowModel> rowModels) {
        this.rowModels = rowModels;
    }
}
