package com.example.queue.valorFijo

class ConexionUrl {



    companion object{


        var CONEXION = false

        val IP:String="192.168.1.136"

        val PORT:Int=8888

        val PORTWEB=8088

        val BASE_URL="http://${IP}:${PORTWEB}/"


       // val IP=""

        val HTTPJSON="http://${IP}:${PORTWEB}/proyectoFinalEntrada/mandaListaporqr?qr="


        val  HTTPJSONINFORMACION="http://${IP}:${PORTWEB}/proyectoFinalEntrada/mandarinformaciondeproducto?idusuario=";


        val HTTPINFORMACIONTIENDA="http://${IP}:${PORTWEB}/proyectoFinalEntrada/mandarcuenta"

        var HTTPJSONMIPRODUCTO = "http://${IP}:${PORTWEB}/proyectoFinalEntrada/mandarlistaproductousuario?idcola="

        var HTTPJSONMANDACUENTA = "http://${IP}:${PORTWEB}/proyectoFinalEntrada/mandarcuenta"

    }

}