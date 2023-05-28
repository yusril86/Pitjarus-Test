package com.yusril.pitjarustest.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "stores")
data class Stores(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @SerializedName("store_id")
    var storeId: String? = null,
    @SerializedName("store_code")
    var storeCode: String? = null,
    @SerializedName("store_name")
    var storeName: String? = null,
    @SerializedName("address")
    var address: String? = null,
    @SerializedName("dc_id")
    var dcId: String? = null,
    @SerializedName("dc_name")
    var dcName: String? = null,
    @SerializedName("account_id")
    var accountId: String? = null,
    @SerializedName("account_name")
    var accountName: String? = null,
    @SerializedName("subchannel_id")
    var subchannelId: String? = null,
    @SerializedName("subchannel_name")
    var subchannelName: String? = null,
    @SerializedName("channel_id")
    var channelId: String? = null,
    @SerializedName("channel_name")
    var channelName: String? = null,
    @SerializedName("area_id")
    var areaId: String? = null,
    @SerializedName("area_name")
    var areaName: String? = null,
    @SerializedName("region_id")
    var regionId: String? = null,
    @SerializedName("region_name")
    var regionName: String? = null,
    @SerializedName("latitude")
    var latitude: String? = null,
    @SerializedName("longitude")
    var longitude: String? = null
)
