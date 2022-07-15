package com.apocalypse.tripto.ui.registration

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.apocalypse.tripto.MainActivity
import com.apocalypse.tripto.R
import com.apocalypse.tripto.api.Api
import com.apocalypse.tripto.api.info
import kotlinx.android.synthetic.main.fragment_register_next_page.*
import kotlinx.android.synthetic.main.fragment_register_page.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

var ProfileImage = "https://i.imgur.com/GBDCzmc.png"

class register_next_page : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_register_next_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imgbtnUserProfilePic.setOnClickListener {
            pickImageFromGallery()
        }
        btnFinalRegister.setOnClickListener {
            val login_Builder_object =
                Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(info.mainURl)
                    .build()
                    .create(Api::class.java)
            Profile.profile_image = ProfileImage
            Profile.descrption = etUserDescription.text.toString()
            Profile.user_city =
                etUserGender.text.toString() // actually this is not gender , instead it is the user location . But i don't wanna change name right now
            val final = RegistrationResponse(
                Profile.email,
                Profile.gender,
                Profile.number,
                Profile.name,
                Profile.password,
                Profile.username,
                Profile.profile_image,
                Profile.descrption,
                Profile.user_city
            )
            val Login_Data = login_Builder_object.register_new_user(final)
            Login_Data.enqueue(object : Callback<RegistrationResponse> {
                override fun onResponse(
                    call: Call<RegistrationResponse>,
                    response: Response<RegistrationResponse>
                ) {

                    //val ff_data = response.body()!!
                    Log.d("Called from login", " Name: " + response)


                    //  Toast.makeText(this@MainActivity, "Hello Sir", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<RegistrationResponse>, t: Throwable) {

                    Log.d("Hello from Sign In Page", "onFailure :" + t.message)
                    // Toast.makeText(this@MainActivity, "Error in API", Toast.LENGTH_SHORT).show()
                }

            })
            val i=Intent(requireContext(),MainActivity::class.java)
            startActivity(i)

        }
    }

    fun pickImageFromGallery() {
        val choosePhotoIntent =
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(choosePhotoIntent, 1234)

    }

    /**
     * Some Refatoring is needed as it is deprecated and uses a slower way to upload image, it calls uploadImageToImgur internally
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 1234 && data != null) {
            val imguri = data.data


            // val respUL = uploadFile(imguri!!, "Just Fun")

            val imgbitmap =
                MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, imguri)
//            Toast.makeText(requireActivity(), "Uri is : " + imguri, Toast.LENGTH_LONG).show()
//
//            Toast.makeText(requireActivity(),"some success : "+respUL,Toast.LENGTH_LONG).show()
            uploadImageToImgur(imgbitmap)
        } else {
            Log.d("Failed to get image ! ", "Try Again")
        }
    }

    /**
     * Gets the image in bitmap format and then call function to get in base 64
     * which then is passed on in the body of http to send as a  request to imgur api
     */
    private fun uploadImageToImgur(image: Bitmap) {
        lateinit var linkOfImg: String
        getBase64Image(image, complete = { base64Image ->
            GlobalScope.launch(Dispatchers.Default) {
                val url = URL("https://api.imgur.com/3/image")

                val boundary = "Boundary-${System.currentTimeMillis()}"

                val httpsURLConnection =
                    withContext(Dispatchers.IO) { url.openConnection() as HttpsURLConnection }
                httpsURLConnection.setRequestProperty(
                    "Authorization",
                    "Client-ID " + info.CLIENT_ID
                )
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
                ProfileImage = data.getString("link")
                Log.d("TAG", "Link is : ${data.getString("link")}")
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
}