package com.azamat.nycschoolforartech.model.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class School(
    @SerializedName("dbn") val dbn: String,
    @SerializedName("school_name") val schoolName: String?,
    @SerializedName("overview_paragraph") val overviewParagraph: String?,
    @SerializedName("location") val location: String?,
    @SerializedName("phone_number") val phoneNumber: String?,
    @SerializedName("fax_number") val faxNumber: String?,
    @SerializedName("school_email") val schoolEmail: String?,
    @SerializedName("website") val website: String?,
    @SerializedName("latitude") val latitude: String?,
    @SerializedName("longitude") val longitude: String?,
) : Parcelable {
    companion object {
        fun empty() = School(dbn = "--", schoolName = "--", overviewParagraph = "--", location = "--", phoneNumber = "--", faxNumber = "--", schoolEmail = "--", website = "--", latitude = "--", longitude = "--")
    }
}
