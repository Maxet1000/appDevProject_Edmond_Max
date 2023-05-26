package com.example.tracsit

import android.location.Address
import android.os.Parcel
import android.os.Parcelable

class TravelInformation(
    var fromLocation: Address?,
    var toLocation: Address?,
    var travelTime: String?) : Parcelable {
    //parcelable can be used later to pass an object from fragments instead of parts of the fragment
    companion object {
        @JvmField
        val CREATOR = object : Parcelable.Creator<TravelInformation> {
            override fun createFromParcel(parcel: Parcel) = TravelInformation(parcel)
            override fun newArray(size: Int) = arrayOfNulls<TravelInformation>(size)
        }
    }

    private constructor(parcel: Parcel) : this(
        fromLocation = parcel.readParcelable(Address::class.java.classLoader),
        toLocation = parcel.readParcelable(Address::class.java.classLoader),
        travelTime = parcel.readString()
    )

    constructor(travelInformation: TravelInformation) : this(travelInformation.fromLocation , travelInformation.toLocation, travelInformation.travelTime)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(fromLocation, flags)
        parcel.writeParcelable(toLocation, flags)
        parcel.writeString(travelTime)
    }

    override fun describeContents() = 0
}