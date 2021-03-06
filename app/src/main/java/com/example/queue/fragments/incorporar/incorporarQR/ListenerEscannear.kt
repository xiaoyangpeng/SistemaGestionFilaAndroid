package com.example.queue.fragments.incorporar.incorporarQR

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import com.example.queue.MainActivity
import com.example.queue.comunicacionQR.QRActivity
import com.example.queue.fragments.incorporar.IncorporarFragment
import com.example.queue.zxing.activity.CaptureActivity


class ListenerEscannear(val view: View, val fragmet: Fragment) : View.OnClickListener{

    companion object{

        var yaHapulsado=true

    }


    override fun onClick(v: View?) {

        if(yaHapulsado){

            yaHapulsado=false


            if (ContextCompat.checkSelfPermission(view.context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.mainActivity, arrayOf(Manifest.permission.CAMERA), 1)

               yaHapulsado= true

            } else {

               val i =  Intent(view.getContext(), CaptureActivity::class.java);


                fragmet.startActivity(i)


            }



        }

    }
}