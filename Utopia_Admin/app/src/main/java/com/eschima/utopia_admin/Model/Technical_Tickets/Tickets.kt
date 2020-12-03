package com.eschima.utopia_admin.Model.Technical_Tickets

data class Solved_Tickets(var data: ArrayList<solve_ticket>)

data class solve_ticket(var id:String , var deviceId:String , var name:String,
                        var date:String , var time:String , var details:String,
                        var executionComment:String , var isFinished:Boolean)


data class solve_technical(var id:Int , var executionComment:String)

