package com.apocalypse.tripto.api

import com.apocalypse.tripto.ui.community.CreateGuideSendData
import com.apocalypse.tripto.ui.community.GuideReturnData
import com.apocalypse.tripto.ui.community.SuggestAPlace
import com.apocalypse.tripto.ui.community.SuggestAPlaceResponse
import com.apocalypse.tripto.ui.group.GroupAdd
import com.apocalypse.tripto.ui.group.GroupDataItem
import com.apocalypse.tripto.ui.group.NewGroup
import com.apocalypse.tripto.ui.group.ResponseAfterGroupCreated
import com.apocalypse.tripto.ui.home.PostItem
import com.apocalypse.tripto.ui.home.UploadResponse
import com.apocalypse.tripto.ui.registration.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*


interface Api {
    // Login
    @POST("api/auth/login")
    fun signin(@Body post:LoginSend): Call<LoginResponse>

    //Register New User :)
    @POST("api/auth/register")
    fun register_new_user(@Body post: RegistrationResponse): Call<RegistrationResponse>

    //Home Timeline Feed
    @GET("api/post")
    fun getData(): Call<List<PostItem>>

    //Get Guide According to city
    @GET("api/guide/{city}")
    fun getGuides(@Path("city") city: String): Call<List<GuideReturnData>>

    //Get the list of groups
    @GET("api/group")
    fun getGroup(): Call<List<GroupDataItem>>


    //Create Post
    @POST("api/post")
    fun post_feed(@Body post:PostItem):  Call<PostItem>


    //Create Suggestion for a place , this is used in community tab
    @POST("api/spot")
    fun post_suggestion_of_a_place(@Body post:SuggestAPlace):  Call<SuggestAPlaceResponse>

    //Become A Host
    @POST("api/family")
    fun be_a_host(@Body post: HostResponse): Call<HostResponseFromServer>

    //Create A New Guide
    @POST("api/guide")
    fun be_a_guide(@Body post:CreateGuideSendData) : Call<ResponseAfterGroupCreated>

    //Join a New Group
    @POST("api/group/addrem")
    fun join_group(@Body addrem:GroupAdd):  Call<String>

    //Create A new Group
    @POST("api/group")
    fun join_group(@Body post:NewGroup):  Call<ResponseAfterGroupCreated>

    //This is to fetch community suggested places for that city
    @GET("api/spot/{city}")
    fun getSuggestedPlaces(@Path("city") city: String): Call<List<SuggestedPlacesResponseItem>>

    @Headers("Authorization: Client-ID 04ae4f56bea9845")
@Multipart
@POST("/3/image")
fun uptoimgur(
       // @Header("Authorization: Client-ID 04ae4f56bea9845")

        @Part image: MultipartBody.Part?,
       // @Part("image")image: MultipartBody.Part?

): Call<UploadResponse>

    @Multipart
    @POST("photo")
    fun uptoadi(
        // @Header("Authorization: Client-ID 04ae4f56bea9845")

        @Part a: MultipartBody.Part,
        // @Part("image")image: MultipartBody.Part?

    ): Call<String>


// Imgur api ; its not being used right now
    @Multipart
    @POST("/3/upload")
    suspend fun image_to_imgur(
        @Part image: MultipartBody.Part?,
        @Part("name") name: RequestBody? = null
    ): Response<UploadResponse>

}