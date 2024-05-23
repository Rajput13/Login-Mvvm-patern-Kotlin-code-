package com.example.apihit.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class LiveCategoriesModel(
    @SerializedName("category_id")
    @Expose
    var categoryId: String? = null,


    @SerializedName("category_name")
    @Expose
    var categoryName: String? = null,

    @SerializedName("parent_id")
    @Expose
    var parentId: Int? = null
) {


}