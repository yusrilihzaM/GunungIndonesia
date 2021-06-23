package com.kerjapraktek.gunungindonesia.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class App(
    var namaAplikasi:String,
    var gambarAplikasi:String
): Parcelable
