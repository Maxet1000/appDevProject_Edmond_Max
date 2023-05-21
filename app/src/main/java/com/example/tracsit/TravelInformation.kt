package com.example.tracsit

data class TravelInformation(var fromLocation: String,var toLocation: String, var travelTime: String) {

    constructor(travelInformation: TravelInformation) : this(travelInformation.fromLocation, travelInformation.toLocation, travelInformation.travelTime)
}