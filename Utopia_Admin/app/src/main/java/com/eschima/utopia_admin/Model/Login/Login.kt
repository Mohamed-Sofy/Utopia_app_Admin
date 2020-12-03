package com.eschima.utopia_admin.Model.Login

data class Login_Model(var id :String , var email :String , var phone :String ,
                       var imagePath:String , var token:String , var accountType:String ,
                       var classRoomId :String , var courseId:String)

data class login(var data : Login_Model)

data class current_user(var data :current)
data class current(var user :Login_Model)
