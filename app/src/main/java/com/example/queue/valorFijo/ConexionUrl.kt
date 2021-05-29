package com.example.queue.valorFijo

class ConexionUrl {



    companion object{

        var CONEXION = false

       val IP:String="6.tcp.ngrok.io"

       //  val IP:String="10.0.2.2"

       //val PORT:Int=8888

       val PORT:Int=18928


         //  val IPWEB:String="10.0.2.2"

          val IPWEB:String="ea04013c69e8.ngrok.io"


       // val PORTWEB=8088
        val PORTWEB=80



        var OLVIDARCONTRASENA:String="http://${IPWEB}:${PORTWEB}/proyectoFinalEntrada/pages/recuperacontrasena.jsp"


        val BASE_URL="http://${IPWEB}:${PORTWEB}/"


        val HTTPJSON="http://${IPWEB}:${PORTWEB}/proyectoFinalEntrada/mandaListaporqr"


        val  HTTPJSONINFORMACION="http://${IPWEB}:${PORTWEB}/proyectoFinalEntrada/mandarinformaciondeproducto?idusuario=";


        val HTTPINFORMACIONTIENDA="http://${IPWEB}:${PORTWEB}/proyectoFinalEntrada/mandarcuenta"

        var HTTPJSONMIPRODUCTO = "http://${IPWEB}:${PORTWEB}/proyectoFinalEntrada/mandarlistaproductousuario?idcola="

        var HTTPJSONMANDACUENTA = "http://${IPWEB}:${PORTWEB}/proyectoFinalEntrada/mandarcuenta?idproducto="

    }

}