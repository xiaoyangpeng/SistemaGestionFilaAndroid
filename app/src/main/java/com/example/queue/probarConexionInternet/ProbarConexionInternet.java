package com.example.queue.probarConexionInternet;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.example.queue.MainActivity;
import com.example.queue.noHayConexion.NohayConexionLoginActivity;
import com.example.queue.noHayConexion.NohayConexionMainActivity;
import com.example.queue.valorFijo.ConexionUrl;


public class ProbarConexionInternet extends BroadcastReceiver {


    private  Activity activity;

    public ProbarConexionInternet(Activity activity){

        this.activity=activity;
    }


    @Override
    public void onReceive(Context context, Intent intent) {

      isConnect(context);

    }

    private  void isConnect(Context context) {
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）


        try {
            ConnectivityManager connectivity = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                // 获取网络连接管理的对象
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info != null&& info.isConnected()) {
                    // 判断当前网络是否已经连接
                    ConexionUrl.Companion.setCONEXION(true);

                    if (info.getType()==ConnectivityManager.TYPE_WIFI) {

                        toast("Conexion con wifi",context);

                    }else if(info.getType()==ConnectivityManager.TYPE_MOBILE){

                        toast("Conexion con datos",context);
                    }
                }else {

                    ConexionUrl.Companion.setCONEXION(false);


                    Intent intent=new Intent();

                  //  if(activity.getClass()==MainActivity.class){

                       // intent.setClass(context, NohayConexionMainActivity.class);

                    //}else{

                        intent.setClass(context, NohayConexionLoginActivity.class);
                 //   }

                    toast("Ha perdido conexion de internet",context);

                    context.startActivity(intent);

                   // activity.finish();
                }
            }
        } catch (Exception e) {

            Log.v("error",e.toString());
        }

    }

    private void toast(String texto,Context context){

        Toast.makeText(context,texto,Toast.LENGTH_LONG).show();

    }



}
