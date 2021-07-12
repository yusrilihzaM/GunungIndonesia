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

    //arraylist->mutablelivedata->livedata
    //simpan data dari firebase buat nampilkan data
    val listGunung = MutableLiveData<ArrayList<Gunung>>()//2

    //mengambil narik data dari firebase
    fun setGunung(lokasi:String){
        val listItems = ArrayList<Gunung>()//1
        //koneksi firebase
        val db = FirebaseFirestore.getInstance()
        //ngambil data
        db.collection("gunung")//from nama tabel
            .whereEqualTo("lokasi_gunung",lokasi)// select * from gunung where lokasi_gunung=lokasi
            .get()
            .addOnCompleteListener { task ->
                Log.d("gunung", "Suksess")
                Log.d("gunung", task.result.toString())
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        //tarik data
                        val id= document["id"].toString()
                        val nama_gunung=document["nama_gunung"].toString()
                        val deskripsi_gunung=document["deskripsi_gunung"].toString()
                        val gambar_gunung=document["gambar_gunung"].toString()
                        val jalur_pendakian=document["jalur_pendakian"].toString()
                        val informasi_gunung=document["informasi_gunung"].toString()
                        val estimasi_pendakian=document["estimasi_pendakian"].toString()
                        val lokasi_gunung=document["lokasi_gunung"].toString()
                        val harga_tiket=document["harga_tiket"].toString()
                        val app= Gunung(
                            id.toInt(),
                            nama_gunung,
                            deskripsi_gunung,
                            gambar_gunung,
                            jalur_pendakian,
                            informasi_gunung,
                            estimasi_pendakian,
                            lokasi_gunung,
                            harga_tiket
                        )
                        listItems.add(app)// masukan data pperitem
                    }
                    Log.d("gunung", listItems.toString())
                    listGunung.postValue(listItems)
                } else {
                    Log.w(ContentValues.TAG, "gunung", task.exception)
                }
            }
    }
    //3 siap ditampilkan
    fun getGunung(): LiveData<ArrayList<Gunung>> {
        return listGunung
    }
}