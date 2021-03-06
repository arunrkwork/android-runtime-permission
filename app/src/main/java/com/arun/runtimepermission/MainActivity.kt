package com.arun.runtimepermission

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    val REQUEST_CAMERA: Int = 101
    val REQUEST_STORAGTE: Int = 102
    val REQUEST_CONTACTS: Int = 103
    val TXT_CAMERA: Int = 1
    val TXT_STORAGTE: Int = 2
    val TXT_CONTACTS: Int = 3

    var permissionUtils: PermissionUtils? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        permissionUtils = PermissionUtils(this)

        btnAllPermission.setOnClickListener(this)
        btnCameraPermission.setOnClickListener(this)
        btnWritePermission.setOnClickListener(this)
        btnReadContactsPermission.setOnClickListener(this)

    }

    override fun onClick(v: View?) {

        when(v!!.id) {
            R.id.btnAllPermission -> {

            }
            R.id.btnCameraPermission -> {
                openCamera()
            }
            R.id.btnWritePermission -> {
                openStorage()
            }
            R.id.btnReadContactsPermission -> {
                openContacts()
            }
        }
    }

    private fun checkPermission(permission: Int): Int {
        var status: Int = PackageManager.PERMISSION_DENIED
        status = when(permission) {
            TXT_CAMERA -> ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            )
            TXT_STORAGTE -> ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            TXT_CONTACTS -> ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CONTACTS
            )
            else -> PackageManager.PERMISSION_DENIED
        }
        return status
    }

    private fun requestPermission(permission: Int) {
        when(permission) {
            TXT_CAMERA -> {
                ActivityCompat
                    .requestPermissions(this,
                        arrayOf(Manifest.permission.CAMERA),
                        REQUEST_CAMERA
                    )
            }
            TXT_STORAGTE -> {
                ActivityCompat
                    .requestPermissions(this,
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        REQUEST_STORAGTE
                    )
            }
            TXT_CONTACTS -> {
                ActivityCompat
                    .requestPermissions(this,
                        arrayOf(Manifest.permission.READ_CONTACTS),
                        REQUEST_CONTACTS
                    )
            }
        }
    }

    private fun showPermissionExplanation(permission: Int) {
        var builder =  AlertDialog.Builder(this)
        when(permission) {
            TXT_CAMERA -> {
                builder.setMessage("This app request camera access, Please allow")
                builder.setTitle("Camera Permission")
            }
            TXT_STORAGTE -> {
                builder.setMessage("This app request storage access, Please allow")
                builder.setTitle("Storage Permission")
            }
            TXT_CONTACTS -> {
                builder.setMessage("This app request contacts access, Please allow")
                builder.setTitle("Contacts Permission")
            }
        }

        builder.setPositiveButton("Allow") { dialog, which ->

            when(permission){
                TXT_CAMERA -> requestPermission(TXT_CAMERA)
                TXT_STORAGTE -> requestPermission(TXT_STORAGTE)
                TXT_CONTACTS -> requestPermission(TXT_CONTACTS)
            }
        }

        builder.setNegativeButton("Cancel   ") { dialog, which ->
            dialog.dismiss()
        }

        var alertDialog: AlertDialog = builder.create()
        alertDialog.show()

    }

    fun openCamera() {
        if(checkPermission(TXT_CAMERA) != PackageManager.PERMISSION_GRANTED) {
          if (ActivityCompat.shouldShowRequestPermissionRationale(this,
              Manifest.permission.CAMERA))
              showPermissionExplanation(TXT_CAMERA)
          else if(!permissionUtils!!.checkPermissionPreference("camera")) {
              requestPermission(TXT_CAMERA)
              permissionUtils!!.updatePermissionPreference("camera")
          } else {
                Toast.makeText(this,
                    "Please allow camera permission in your app settings",
                    Toast.LENGTH_LONG).show()
                var intent = Intent()
              intent.apply {
                action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                  val uri =
                      Uri.fromParts("package", packageName, null)
                  data = uri
                  startActivity(intent)
              }
          }
        } else {
            Toast.makeText(this, "You have camera permission", Toast.LENGTH_LONG).show()
            var intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("message", "You have camera permission")
            startActivity(intent)
        }
    }

    fun openStorage() {
        if(checkPermission(TXT_STORAGTE) != PackageManager.PERMISSION_GRANTED) {
          if (ActivityCompat.shouldShowRequestPermissionRationale(this,
              Manifest.permission.WRITE_EXTERNAL_STORAGE))
              showPermissionExplanation(TXT_STORAGTE)
          else if(!permissionUtils!!.checkPermissionPreference("storage")) {
              requestPermission(TXT_STORAGTE)
              permissionUtils!!.updatePermissionPreference("storage")
          } else {
                Toast.makeText(this,
                    "Please allow storage permission in your app settings",
                    Toast.LENGTH_LONG).show()
                var intent = Intent()
              intent.apply {
                action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                  val uri =
                      Uri.fromParts("package", packageName, null)
                  data = uri
                  startActivity(intent)
              }
          }
        } else {
            Toast.makeText(this, "You have storage permission", Toast.LENGTH_LONG).show()
            var intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("message", "You have storage permission")
            startActivity(intent)
        }
    }

    fun openContacts() {
        if(checkPermission(TXT_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
          if (ActivityCompat.shouldShowRequestPermissionRationale(this,
              Manifest.permission.READ_CONTACTS))
              showPermissionExplanation(TXT_CONTACTS)
          else if(!permissionUtils!!.checkPermissionPreference("contacts")) {
              requestPermission(TXT_CONTACTS)
              permissionUtils!!.updatePermissionPreference("contacts")
          } else {
                Toast.makeText(this,
                    "Please allow contacts permission in your app settings",
                    Toast.LENGTH_LONG).show()
                var intent = Intent()
              intent.apply {
                action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                  val uri =
                      Uri.fromParts("package", packageName, null)
                  data = uri
                  startActivity(intent)
              }
          }
        } else {
            Toast.makeText(this, "You have contacts permission", Toast.LENGTH_LONG).show()
            var intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("message", "You have contacts permission")
            startActivity(intent)
        }
    }

}
