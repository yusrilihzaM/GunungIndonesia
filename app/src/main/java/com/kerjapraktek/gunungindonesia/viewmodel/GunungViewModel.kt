package com.kerjapraktek.gunungindonesia.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.kerjapraktek.gunungindonesia.model.Gunung
import com.kerjapraktek.gunungindonesia.model.Peralatan

class GunungViewModel:ViewModel() {
    val listGunung = MutableLiveData<ArrayList<Gunung>>()
    fun setGunung(lokasi:String){
        val listItems = ArrayList<Gunung>()
        val db = FirebaseFirestore.getInstance()

        db.collection("gunung")
            .whereEqualTo("lokasi_gunung",lokasi)
            .get()
            .addOnCompleteListener { task ->
                Log.d("gunung", "Suksess")
                Log.d("gunung", task.result.toString())
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        val id=document["id"].toString()
                        val nama_gunung=document["nama_gunung"].toString()
                        val deskripsi_gunung=document["deskripsi_gunung"].toString()
                        val gambar_gunung=document["gambar_gunung"].toString()
                        val jalur_pendakian=document["jalur_pendakian"].toString()
                        val informasi_gunung=document["informasi_gunung"].toString()
                        val estimasi_pendakian=document["estimasi_pendakian"].toString()
                        val lokasi_gunung=document["lokasi_gunung"].toString()
                        val app= Gunung(
                            id,
                            nama_gunung,
                            deskripsi_gunung,
                            gambar_gunung,
                            jalur_pendakian,
                            informasi_gunung,
                            estimasi_pendakian,
                            lokasi_gunung
                        )
                        listItems.add(app)
                    }
                    Log.d("gunung", listItems.toString())
                    listGunung.postValue(listItems)
                } else {
                    Log.w(ContentValues.TAG, "gunung", task.exception)
                }
            }
    }
    fun getGunung(): LiveData<ArrayList<Gunung>> {
        return listGunung
    }
}