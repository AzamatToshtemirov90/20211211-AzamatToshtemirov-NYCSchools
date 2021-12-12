package com.azamat.nycschoolforartech.model.remote.response

import com.google.gson.annotations.SerializedName

data class Score(
    @SerializedName("dbn") val dbn: String,
    @SerializedName("num_of_sat_test_takers") val testTakersNo: String?,
    @SerializedName("sat_critical_reading_avg_score") val readingAvgScore: String?,
    @SerializedName("sat_math_avg_score") val mathAvgScore: String?,
    @SerializedName("sat_writing_avg_score") val writingAvgScore: String?,
    @SerializedName("school_name") val schoolName: String?
) {
    companion object {
        fun empty() = Score(dbn = "--", testTakersNo = "--", readingAvgScore = "--", mathAvgScore = "--", writingAvgScore = "--", schoolName = "--")
    }
}
