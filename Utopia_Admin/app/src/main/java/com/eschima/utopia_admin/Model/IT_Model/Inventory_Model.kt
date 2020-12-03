package com.eschima.utopia_admin.Model.IT_Model

data class inventory_model(var data :ArrayList<device_inventory>)

data class device_inventory(var id : String , var name:String , var code :String,
                            var location :String , var buyingDate:String ,
                            var price:String , var guaranteeCertificate:String,
                            var specificationsDetails:String)

data class device_inventory_post( var name:String , var code :String,
                            var location :String , var buyingDate:String ,
                            var price:String , var guaranteeCertificate:String,
                            var specificationsDetails:String)