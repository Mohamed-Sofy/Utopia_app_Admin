package com.eschima.utopia_admin.Model.Technical_Tickets

data class assigned(var data :ArrayList<assign>)

data class assign( var id:String, var name:String , var date:String,
                   var startTime:String, var endTime:String , var description:String,
                   var executionComment:String, var isFinished:Boolean)

data class submit_perform_model(var id : Int , var evaluation:String,
                                var executionComment :String)


