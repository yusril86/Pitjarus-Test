package com.yusril.pitjarustest.ui.store

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.yusril.pitjarustest.adapter.StoreAdapter
import com.yusril.pitjarustest.data.db.StoreDao
import com.yusril.pitjarustest.data.db.StoreDatabase
import com.yusril.pitjarustest.data.model.Stores
import com.yusril.pitjarustest.databinding.ActivityStoreBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StoreActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap
    private lateinit var binding: ActivityStoreBinding

    private var storedStores : List<Stores> = ArrayList()
    private lateinit var storeDatabase: StoreDatabase
    private lateinit var storeDao: StoreDao

    private var adapter : StoreAdapter = StoreAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mapView = binding.mapView
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
        storeDatabase = StoreDatabase.getDatabase(this)
        storeDao = storeDatabase.storeDao()

        binding.rvStore.adapter = adapter
        val layoutManager = LinearLayoutManager(this)
        binding.rvStore.layoutManager = layoutManager


        CoroutineScope(Dispatchers.IO).launch {
             storedStores = storeDao.getAllStores()

            withContext(Dispatchers.Main) {
                adapter.updateData(storedStores)
            }

        }

    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        for (item in storedStores){
            val latitude: Double = item.latitude!!.toDouble()
            val longitude: Double = item.longitude!!.toDouble()
            val pointLatLang = LatLng(latitude,longitude)
            googleMap.addMarker(MarkerOptions().position(pointLatLang).title(item.storeName))
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pointLatLang, 10f))
        }
        googleMap.uiSettings.isZoomControlsEnabled = true
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

}
