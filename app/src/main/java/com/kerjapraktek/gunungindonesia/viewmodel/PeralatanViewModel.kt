package com.kerjapraktek.gunungindonesia.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.kerjapraktek.gunungindonesia.model.App
import com.kerjapraktek.gunungindonesia.model.Peralatan

class PeralatanViewModel:ViewModel() {
    val listPeralatan = MutableLiveData<ArrayList<Peralatan>>()
    fun setPeralatan(){
        val listItems = ArrayList<Peralatan>()
        val db = FirebaseFirestore.getInstance()

        db.collection("peralatan")
            .get()
            .addOnCompleteListener { task ->
                Log.d("peralatan", "Suksess")
                Log.d("peralatan", task.result.toString())
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        val id=document["id"].toString()
                        val nama_peralatan=document["nama_peralatan"].toString()
                        val gambar_peralatan=document["gambar_peralatan"].toString()
                        val jenis_peralatan=document["jenis_peralatan"].toString()
                        val app= Peralatan(
                            id.toInt(),
                            nama_peralatan,
                            gambar_peralatan,
                            jenis_peralatan
                        )
                        listItems.add(app)
                    }
                    Log.d("peralatan", listItems.toString())
                    listPeralatan.postValue(listItems)
                } else {
                    Log.w(ContentValues.TAG, "peralatan", task.exception)
                }
            }
    }
    fun getPeralatan(): LiveData<ArrayList<Peralatan>> {
        return listPeralatan
    }
}