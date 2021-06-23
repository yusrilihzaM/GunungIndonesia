package com.kerjapraktek.gunungindonesia.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Peralatan(
    var id :String,
    var nama_peralatan :String,
    var gambar_peralatan :String,
    var jenis_peralatan :String,
): Parcelable
