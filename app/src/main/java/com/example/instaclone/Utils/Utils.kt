package com.example.instaclone.Utils

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import java.util.UUID

fun uploadImage(uri: Uri, folderName: String, callback: (String?) -> Unit) {
    var imageurl: String? = null
    FirebaseStorage.getInstance().getReference(folderName).child(UUID.randomUUID().toString())
        .putFile(uri)
        .addOnSuccessListener {
            it.storage.downloadUrl.addOnSuccessListener {
                imageurl = it.toString()
                callback(imageurl)
            }
        }
}


fun uploadVideo(
    uri: Uri,
    folderName: String,
    progressDialog: ProgressDialog,
    callback: (String?) -> Unit
) {
    var imageurl: String? = null
    progressDialog.setTitle("Uploading...")
    progressDialog.show()
    FirebaseStorage.getInstance().getReference(folderName).child(UUID.randomUUID().toString())
        .putFile(uri)
        .addOnSuccessListener {
            it.storage.downloadUrl.addOnSuccessListener {
                imageurl = it.toString()
                progressDialog.dismiss()
                callback(imageurl)
            }
        }
        .addOnProgressListener {
            val progress = (100.0 * it.bytesTransferred / it.totalByteCount).toInt()
            progressDialog.setMessage("Uploaded $progress%")
        }
}