package com.kerjapraktek.gunungindonesia.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Gunung(
    var id :String,
    var nama_gunung :String,
    var deskripsi_gunung :String,
    var gambar_gunung :String,
    var jalur_pendakian :String,
    var informasi_gunung :String,
    var estimasi_pendakian :String,
    var lokasi_gunung :String
):Parcelable