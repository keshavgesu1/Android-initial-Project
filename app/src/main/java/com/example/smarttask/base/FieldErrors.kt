package com.app.pravasiuttrakhandi.base
/*
 Created by "Jayant Sharma" on 20/05/20.
*/
class FieldErrors {


    var errorMobileNumber: String?=null
    var errorOTP: String?=null
    var errorFirstName: String = ""
    var errorEmail: String = ""
    var errorSubject: String = ""
    var errorDescription: String = ""
    var errorLastName: String = ""
    var errorFatherName: String = ""
    var errorMotherName: String = ""
    var errorGender: String = ""
    var errorDOB: String = ""
    var errorAadharNumber: String = ""
    var errorOccupation: String = ""
    var errorDesignation: String = ""
    var errorFamilyIncome: String = ""
    var errorNatureOfBusiness: String = ""
    var errorJobStatus: String = ""
    var errorHouseNumber: String = ""
    var errorState: String = ""
    var errorStreet: String = ""
    var errorTown: String = ""
    var errorStreetName: String = ""
    var errorSector: String = ""
    var errorPinCode: String = ""
    var errorDistrict: String = ""
    var errorPAddress: String = ""
    var errorPDisrict: String = ""
    var errorPBlock: String = ""
    var errorVillage: String = ""
    var errorField: String = ""
    var errorCategory: String = ""
    var errorHelpDescription: String = ""

    var errorPSarpanchName: String = ""
    var errorPSarpanchNumber: String = ""

}
enum class ValidationsError {
   ERROREMPTY, ERRORMOBILE, ERROROTP,NONE
}