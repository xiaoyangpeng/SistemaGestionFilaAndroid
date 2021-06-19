package com.example.queue.valorFijo

class ConexionUrl {



    companion object{

        var CONEXION = false

       val IP:String="2.tcp.ngrok.io"

       //  val IP:String="10.0.2.2"

       //val PORT:Int=8888

       val PORT:Int=13657


        //   var IPWEB:String="10.0.2.2"

        var IPWEB:String="sdf.ngrok.io"


       // val PORTWEB=8088

        val PORTWEB=80

        var OLVIDARCONTRASENA:String="http://${IPWEB}:${PORTWEB}/proyectoFinalEntrada/pages/recuperacontrasena.jsp"


        var BASE_URL="http://${IPWEB}:${PORTWEB}/"


        var  HTTPJSONINFORMACION="http://${IPWEB}:${PORTWEB}/proyectoFinalEntrada/mandarinformaciondeproducto?idusuario=";


        var HTTPJSONMANDACUENTA = "http://${IPWEB}:${PORTWEB}/proyectoFinalEntrada/mandarcuenta?idproducto="

    }

}