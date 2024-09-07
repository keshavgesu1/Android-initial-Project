package com.example.smarttask.model

import com.google.gson.annotations.SerializedName

data class UserDetails(
    @SerializedName("phone_number")
    var strPhone: String = "",
    @SerializedName("first_name")
    var strFirstName: String = "",
    @SerializedName("last_name")
    var strLastName: String = "",
    @SerializedName("father_name")
    var strFatherName: String = "",
    @SerializedName("mother_name")
    var strMotherName: String = "",
    @SerializedName("gender")
    var strGender: String = "",
    @SerializedName("dob")
    var strDOB: String = "",
    @SerializedName("aadhar_number")
    var strAadharNumber: String = "",
    @SerializedName("occupation")
    var strOccupation: String = "",
    @SerializedName("family_income")
    var strFamilyIncome: String = "",
    @SerializedName("nature_of_business")
    var strNatureOfBusiness: String = "",
    @SerializedName("status_job")
    var strJobStatus: String = "",

    @Transient
    var strHouseNumber: String = "",

    @SerializedName("destination")
    var designation: String? = "",

    var _id: String? = null,
    @Transient
    var strStreet: String = "",
    @Transient
    var strTown: String = "",
    @Transient
    var town: String = "",
    @Transient
    var strPinCode: String = "",
    @Transient
    var strDistrict: String = "",
    @Transient
    var strState: String = "",
    @Transient
    var strPDistrict: String = "",
    @Transient
    var strPVillage: String = "",
    @Transient
    var strPBlock: String = "",
    @SerializedName("sarpanch_name")
    var strPSarpanchName: String? = "",
    @SerializedName("sarpanch_number")
    var strPSarpanchNumber: String? = "",
    @SerializedName("user_image")
    var strProfileImage: String = "",
    @SerializedName("aadhar_image")
    var AadharImage: AadharImage = AadharImage(),

    @Transient
    var otp: String = ""

//    @Transient
//    var confirmPassword:String?=null,
) {

}

data class AadharImage(var front_part: String = "", var back_part: String = "")
