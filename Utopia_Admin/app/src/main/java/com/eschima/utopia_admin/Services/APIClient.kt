package com.eschima.utopia_admin.Services

import com.eschima.utopia_admin.Model.IT_Model.device_inventory_post
import com.eschima.utopia_admin.Model.IT_Model.examination_model
import com.eschima.utopia_admin.Model.IT_Model.examination_post
import com.eschima.utopia_admin.Model.IT_Model.inventory_model
import com.eschima.utopia_admin.Model.Login.current_user
import com.eschima.utopia_admin.Model.Login.login
import com.eschima.utopia_admin.Model.Technical_Tickets.Solved_Tickets
import com.eschima.utopia_admin.Model.Technical_Tickets.assigned
import com.eschima.utopia_admin.Model.Technical_Tickets.solve_technical
import com.eschima.utopia_admin.Model.Technical_Tickets.submit_perform_model
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface APIClient {

    @Headers(
        "Content-Type: application/json",
        "Accept:application/json"
    )
    @POST("Login")
    fun log_in(
        @Header("email") email: String,
        @Header("password") password : String
    ): Call<login>

    @GET("CurrentUser")
    fun get_current_user_info(
        @Header("token") token: String
    ) : Call<current_user>



    // IT Unit

    @GET("AllDevices")
    fun Get_Devices_Inventory(
        @Header("token") token: String
    ) : Call<inventory_model>

    @GET("AllDevicesExamination")
    fun Get_Devices_Examination(
        @Header("token") token: String
    ) : Call<examination_model>

    @Headers(
        "Content-Type: application/json",
        "Accept:application/json"
    )
    @POST("Devices")
    fun add_Device(
        @Body device_body: device_inventory_post,
        @Header("token") token : String
    ): Call<ResponseBody>

    @Headers(
        "Content-Type: application/json",
        "Accept:application/json"
    )
    @POST("PostNewExamination")
    fun add_Device_Examination(
        @Body device_body: examination_post,
        @Header("token") token : String
    ): Call<ResponseBody>

    // assigned and finished tasks
    @GET("AllAssignedITTasks")
    fun Get_Assigned_Tasks(
        @Header("token") token: String
    ) : Call<assigned>

    @Headers(
        "Content-Type: application/json",
        "Accept:application/json"
    )
    @POST("PerFormItTask")
    fun Perform_Assigned_Task(
        @Body submit_perform_model: submit_perform_model,
        @Header("token") token : String
    ): Call<ResponseBody>


    @GET("AllFinishedITTasks")
    fun Get_Finished_Tasks(
        @Header("token") token: String
    ) : Call<assigned>


    // requested technical tickets
    @GET("SolvedTechnicalTickets")
    fun Get_Solved_technical_tickets(
        @Header("token") token: String
    ) : Call<Solved_Tickets>


    @Headers(
        "Content-Type: application/json",
        "Accept:application/json"
    )
    @POST("SolveTechnicalIssue")
    fun Solve_Technical_Task(
        @Body solve_technical: solve_technical,
        @Header("token") token : String
    ): Call<ResponseBody>



}