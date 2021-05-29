package com.example.queue.modificardato

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.queue.MainActivity
import com.example.queue.R
import com.example.queue.guardarDatoSharedPre.guardarDatoCuenta.api.LeerDatoCuenta
import com.example.queue.guardarDatoSharedPre.guardarDatoCuenta.api.Usuario
import com.example.queue.modificardato.apimandacuenta.LlamaApiModifica
import java.math.BigDecimal

class ModificaActivity : AppCompatActivity() {

    private var sexo: TextView? = null
    private var email: EditText? = null
    private var textTelefono: EditText? = null
    private var gurdarcambio=false
    private  var nombre:EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modifica)

        // añadir en el toolbar la flecha hacia atras

        // añadir en el toolbar la flecha hacia atras
        val actionBar = this.supportActionBar
        actionBar!!.title = "Modificar dato cuenta"
        actionBar.setDisplayHomeAsUpEnabled(true)

        sexo = findViewById<View>(R.id.sexocuenta) as TextView


        email = findViewById<View>(R.id.emailcuenta) as EditText

        textTelefono =findViewById<View>(R.id.telefonocuenta) as EditText

        nombre = findViewById<View>(R.id.nombrecuenta) as EditText

        sexo!!.setOnClickListener { ShowChoise() }



        var leer= LeerDatoCuenta(this)


        var user=leer.datoEnShare()


        println(user.email)
        println(user.nombre)

        println(user.telefono)


        nombre?.setText(user.nombre)

        sexo?.setText(user.sexo)

        textTelefono?.setText(user.telefono.toString())

        email?.setText(leer.email)


    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        val id = item.itemId // cuando pulsa los opciones del menu coge su id

        if (id == android.R.id.home) { // en caso id es flecha es decir vuelve atras
            // en caso id es flecha es decir vuelve atras

            // para vertificar que el usuario ha guardao su cambio o no
            // cuando pulsa guaradarcambio el boolena de guaradarcambio
            // en el viewModelComida va dejar como true
            // y si cambio cantidad de producto dejara como false
            if (gurdarcambio) {

                finish()

            } else {
                dialogoSalirSeguro()
            }

            return true

        }
        return super.onOptionsItemSelected(item)
    }

    private fun ShowChoise() {
        val builder = AlertDialog.Builder(this, android.R.style.Theme_Holo_Light_Dialog)
        //builder.setIcon(R.drawable.ic_launcher);
        builder.setTitle("Elige su Sexo")
        //    指定下拉列表的显示数据
        val cities = arrayOf("Mujer", "Hombre", "Prefiero no decir")
        //    设置一个下拉的列表选择项
        builder.setItems(cities) { dialog, which -> sexo?.setText(cities[which]) }
        builder.show()
    }

    // en caso no ha guarda los cambios
    fun dialogoSalirSeguro() {
        val dialogo = AlertDialog.Builder(this)
        val acvitarCuenta = DialogInterface.OnClickListener { dialog, which -> finish() }
        dialogo.setMessage("No has guardado los cambio seguro quieres salir ?")
        dialogo.setPositiveButton("Aceptar", acvitarCuenta)
        dialogo.setNegativeButton("Cancelar", null)
        dialogo.show()
    }
    fun modificar(view: View){

        var user=Usuario()


        user.telefono= BigDecimal(textTelefono?.text.toString())

        user.sexo=sexo?.text.toString()

        user.nombre=nombre?.text.toString()

        user.email=email?.text.toString()

        var llama=LlamaApiModifica(this)

        llama.crear(user)

        llama.start()

        llama.join()

        startActivity(Intent(this, MainActivity::class.java))

        finish()
    }



}