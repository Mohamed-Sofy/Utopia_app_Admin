package com.eschima.utopia_admin.Model.IT_Model

data class examination_model(var data : ArrayList<examination>)

data class examination(var id :String , var deviceId:String , var troubleshootingName:String,
                       var troubleshootingReason:String , var troubleshootingDate:String,
                       var troubleshootingTime:String , var itTroubleshootingNotes:String,
                       var deviceResponsiblee:String)

data class examination_post(var deviceId:String , var troubleshootingName:String,
                       var troubleshootingReason:String , var troubleshootingDate:String,
                       var troubleshootingTime:String , var itTroubleshootingNotes:String,
                       var deviceResponsiblee:String)