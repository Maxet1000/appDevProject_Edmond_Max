package com.example.tracsit

import android.os.Parcel
import android.os.Parcelable

data class TravelInformation(var fromLocation: String?, var toLocation: String?, var travelTime: String?) :
    Parcelable {
    //parcelable can be used later to pass an object from fragments instead of parts of the fragment
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    constructor(travelInformation: TravelInformation) : this(travelInformation.fromLocation, travelInformation.toLocation, travelInformation.travelTime)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(fromLocation)
        parcel.writeString(toLocation)
        parcel.writeString(travelTime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TravelInformation> {
        override fun createFromParcel(parcel: Parcel): TravelInformation {
            return TravelInformation(parcel)
        }

        override fun newArray(size: Int): Array<TravelInformation?> {
            return arrayOfNulls(size)
        }
    }
}