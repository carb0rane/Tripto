package com.apocalypse.tripto.ui.home
// Move to Bottom !!
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.ContentResolver
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.Toast
import com.apocalypse.tripto.R
import com.apocalypse.tripto.api.Api
import com.apocalypse.tripto.api.info
import com.apocalypse.tripto.ui.registration.Profile

import kotlinx.android.synthetic.main.alert_dialog_choose_image.*
import kotlinx.android.synthetic.main.alert_dialog_choose_image.view.*
import kotlinx.android.synthetic.main.fragment_create_post.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.ByteArrayOutputStream
import java.io.OutputStreamWriter
import java.net.URL
import javax.net.ssl.HttpsURLConnection

lateinit var inflater:LayoutInflater
lateinit var api: Api
lateinit var contentResolver:ContentResolver
       var linkOfImage="imgur.com"


class createPost : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Toast.makeText(requireActivity().applicationContext,"Indk", Toast.LENGTH_SHORT).show()
        return inflater.inflate(R.layout.fragment_create_post, container, false)
    }

    override fun onStart() {
        super.onStart()

        btnimgUpload.setOnClickListener {
            submitPostButton.visibility= GONE
pickImageFromGallery()
        }
       // val postDescription=etMail.text.toString()
       // Toast.makeText(requireActivity(),postDescription,Toast.LENGTH_SHORT).show()
        submitPostButton.setOnClickListener {
            val postDescription=etMail.text.toString()
           // Toast.makeText(requireActivity(),postDescription,Toast.LENGTH_SHORT).show()
           // Toast.makeText(requireActivity(), linkOfImage,Toast.LENGTH_SHORT).show()
            postfeed(linkOfImage, postDescription)
        }
    }

/** Does nothing fancy , just show the dialog to pick gallery or camera and handle the click
 Pending Camera work , rn only gallery is available */
    private fun uploadimage() {

        val builder = AlertDialog.Builder(requireActivity())

        val dialogview =
            LayoutInflater.from(requireActivity()).inflate(R.layout.alert_dialog_choose_image, null)
        builder.setPositiveButton("Cancel") { dialogInterface, which ->
            // Toast.makeText(requireActivity(), "No Image Picked! ", Toast.LENGTH_SHORT).show()
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(true)
        alertDialog.setView(dialogview)
        alertDialog.show()
        dialogview.btn_chosen_camera.setOnClickListener {

        }
        dialogview.btn_chosen_gallery.setOnClickListener {
            pickImageFromGallery()


        }


    }

    /** It is using the deprecated startactivityforresult , so change it
     * it also just starts an intent
     */
    fun pickImageFromGallery() {
        val choosePhotoIntent =
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(choosePhotoIntent, 1234)

    }

    /**
     * Gets the image in bitmap format and then call function to get in base 64
     * which then is passed on in the body of http to send as a  request to imgur api
     */
    private fun uploadImageToImgur(image: Bitmap) {
        lateinit var linkOfImg:String
        getBase64Image(image, complete = { base64Image ->
            GlobalScope.launch(Dispatchers.Default) {
                val url = URL("https://api.imgur.com/3/upload")

                val boundary = "Boundary-${System.currentTimeMillis()}"

                val httpsURLConnection =
                    withContext(Dispatchers.IO) { url.openConnection() as HttpsURLConnection }
                httpsURLConnection.setRequestProperty("Authorization", "Client-ID "+info.CLIENT_ID)
                httpsURLConnection.setRequestProperty(
                    "Content-Type",
                    "multipart/form-data; boundary=$boundary"
                )

                httpsURLConnection.requestMethod = "POST"
                httpsURLConnection.doInput = true
                httpsURLConnection.doOutput = true

                var body = ""
                body += "--$boundary\r\n"
                body += "Content-Disposition:form-data; name=\"image\""
                body += "\r\n\r\n$base64Image\r\n"
                body += "--$boundary--\r\n"


                val outputStreamWriter = OutputStreamWriter(httpsURLConnection.outputStream)
                withContext(Dispatchers.IO) {
                    outputStreamWriter.write(body)
                    outputStreamWriter.flush()
                }
                val response = httpsURLConnection.inputStream.bufferedReader()
                    .use { it.readText() }  // defaults to UTF-8
                val jsonObject = JSONTokener(response).nextValue() as JSONObject
                val data = jsonObject.getJSONObject("data")
                linkOfImage=data.getString("link")
              //  Toast.makeText(requireActivity(), linkOfImage,Toast.LENGTH_SHORT).show()
                Log.d("TAG", "Link is : ${data.getString("link")}")
                submitPostButton.visibility= VISIBLE
//                Toast.makeText(
//                    requireActivity(),
//                    "Link is : ${data.getString("link")}",
//                    Toast.LENGTH_LONG
//                ).show()

                // ...

            }
        })

    }

    /**
     * Gets the image in bitmap and returns the base64 version just as the name says
     */
    private fun getBase64Image(image: Bitmap, complete: (String) -> Unit) {
        GlobalScope.launch {
            val outputStream = ByteArrayOutputStream()
            image.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            val b = outputStream.toByteArray()
            complete(Base64.encodeToString(b, Base64.DEFAULT))
        }
    }
    private fun unimupload( tes:Bitmap){
        val feed_Builder_object =
            Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(info.imgurURL)
                .build()
                .create(Api::class.java)
       val pop= MultipartBody.Part.createFormData("image",getBase64Imag(tes))
        feed_Builder_object.uptoimgur(pop).enqueue(object : Callback<UploadResponse?> {
            override fun onResponse(
                call: Call<UploadResponse?>,
                response: Response<UploadResponse?>
            ) {
                val ft=response.body()!!
              linkOfImage=ft.data.link.toString()
                Log.d("MainActivity", "Successfully Uploaded Image :" + response)
                submitPostButton.visibility= VISIBLE


            }

            override fun onFailure(call: Call<UploadResponse?>, t: Throwable) {
              //  Toast.makeText(requireActivity(), t.message, Toast.LENGTH_SHORT).show()
                Log.d("MainActivity", "onFailure :" + t.message)
                // Toast.makeText(this@MainActivity, "Error in API", Toast.LENGTH_SHORT).show()
            }
        })

    }
    private fun getBase64Imag(image: Bitmap):String {
      lateinit  var tess:String

            val outputStream = ByteArrayOutputStream()
            image.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            val b = outputStream.toByteArray()
            tess = Base64.encodeToString(b, Base64.DEFAULT)
            Log.d("Base 64 Is : ", "   :  " + tess)

            return tess

    }

    /**
     * Some Refatoring is needed as it is deprecated and uses a slower way to upload image, it calls uploadImageToImgur internally
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 1234 && data != null) {
            val imguri = data.data


               // val respUL = uploadFile(imguri!!, "Just Fun")

            val imgbitmap = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, imguri)
//            Toast.makeText(requireActivity(), "Uri is : " + imguri, Toast.LENGTH_LONG).show()
//
//            Toast.makeText(requireActivity(),"some success : "+respUL,Toast.LENGTH_LONG).show()
            unimupload(imgbitmap)
        } else {
            Toast.makeText(requireActivity(), "Failed to get image ! ", Toast.LENGTH_SHORT).show()
        }
    }


    /**
     * As the name says , it posts feed getting it from text view, right now
     * it is using hardcoded values, change it to get from textboxes
     */
    private fun postfeed( imgurl:String, desc:String) {

        Log.d("Description Of Image is : "," : "+imgurl)
        Log.d("Post description is : "," : "+desc)

        val feed_Builder_object =
            Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(info.mainURl)
                .build()
                .create(Api::class.java)

//        Toast.makeText(requireActivity(),imgurl,Toast.LENGTH_LONG).show()
//        Toast.makeText(requireActivity(),desc,Toast.LENGTH_LONG).show()
        val arrayList1 = ArrayList<String>(5)

        arrayList1.add("Deepak")//Adding object in arraylist
        arrayList1.add("Riya")
        arrayList1.add("Biswajit")
        arrayList1.add("Anurag")
        arrayList1.add("Aditya")
        var ess=skdjf(Profile._id,Profile.user_city,Profile.descrption,Profile.email,Profile.gender,Profile.number
        ,Profile.name,Profile.profile_image,Profile.username)
        ess._id=Profile._id

        val teste=PostItem(null,desc,imgurl,arrayList1,ess)

        // val poust = PostItem("andro", postDescription)
        feed_Builder_object.post_feed(teste).enqueue(object : Callback<PostItem?> {
            override fun onResponse(
                call: Call<PostItem?>,
                response: Response<PostItem?>
            ) {

                Toast.makeText(requireActivity(), response.toString(), Toast.LENGTH_SHORT).show()


            }

            override fun onFailure(call: Call<PostItem?>, t: Throwable) {
                Toast.makeText(requireActivity(), t.message, Toast.LENGTH_SHORT).show()
                Log.d("MainActivity", "onFailure :" + t.message)
                // Toast.makeText(this@MainActivity, "Error in API", Toast.LENGTH_SHORT).show()
            }
        })
    }

}

/** Shift to Background Thread ! Make a calss and use co routines !
 *
 */
//class gettheimgup(uri:Uri):ViewModel(){
//    fun getlgn(uri: Uri){
//        viewModelScope.launch(Dispatchers.IO) {
//            createPost.uploadFile(uri, "hey")
//        }
//    }
//}





