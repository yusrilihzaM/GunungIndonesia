package com.kerjapraktek.gunungindonesia.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.kerjapraktek.gunungindonesia.model.App

class InfoViewModel:ViewModel() {
    val listInfoDetail = MutableLiveData<ArrayList<App>>()

    fun setInfoDetail(){
        val listItems = ArrayList<App>()
        val db = FirebaseFirestore.getInstance()

        db.collection("aplikasi")
            .get()
            .addOnCompleteListener { task ->
                Log.d("aplikasi", "Suksess")
                Log.d("aplikasi", task.result.toString())
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        val gambarAplikasi=document["logo_aplikasi"].toString()
                        val namaAplikasi=document["nama_aplikasi"].toString()

                        val app= App(
                            namaAplikasi,
                            gambarAplikasi
                        )
                        listItems.add(app)
                    }
                    Log.d("aplikasi", listItems.toString())
                    listInfoDetail.postValue(listItems)
                } else {
                    Log.w(ContentValues.TAG, "aplikasi", task.exception)
                }
            }

    }
    fun getAppInfo(): LiveData<ArrayList<App>> {
        return listInfoDetail
    }
}