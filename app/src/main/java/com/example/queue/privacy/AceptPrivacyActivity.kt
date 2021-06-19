package com.example.queue.privacy

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.afasiaapp.privacymanager.PrivacyAgreementManager
import com.example.queue.R
import com.example.queue.login.LoginActivity
import com.example.queue.valorFijo.ConexionUrl

class AceptPrivacyActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var textAceptPrivacy: TextView
    private lateinit var textUseCondition: TextView
    private var isCheckPrivacy = false
    private var isCheckCondition = false
    private lateinit var checkBoxPrivacy: CheckBox
    private lateinit var checkBoxCondition: CheckBox
    private lateinit var buttonAceptAgreement: Button

    companion object {

        const val TYPE_AGREEMEMTS = "tipo_de_acuerdo"

        const val AGREEMETS_PRIVACY = "acuerdo_politica"

        const val AGREEMETS_CONDITION = "condicion_uso"

        private const val START_TEXT_PRIVACY = 10

        private const val START_TEXT_CONDITION = 12
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()

        if (PrivacyAgreementManager(this).isAceptAgreement()) {

            startActivity(Intent(this,LoginActivity::class.java))

            this.finish()
        } else {
            setContentView(R.layout.activity_acept_privacy)
            initView()
        }
    }

    private fun initView() {

        textAceptPrivacy = findViewById(R.id.textAceptPrivacy)

        textUseCondition = findViewById(R.id.textUseCondition)

        checkBoxCondition = findViewById(R.id.checkBoxUseCondition)

        checkBoxPrivacy = findViewById(R.id.checkBoxAceptPrivacy)

        buttonAceptAgreement = findViewById(R.id.buttonAceptAgreement)

        setTextPrivacy()
        setTextCondition()
    }

    private fun setTextPrivacy() {

        val privacyBuilder = SpannableStringBuilder("Acepto la política de privacidad")

        privacyBuilder.setSpan(
            PrivacyClicText(this),
            START_TEXT_PRIVACY,
            privacyBuilder.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        textAceptPrivacy.text = privacyBuilder

        textAceptPrivacy.movementMethod = LinkMovementMethod.getInstance()

        textAceptPrivacy.highlightColor = Color.TRANSPARENT
    }

    private fun setTextCondition() {

        val conditionBuilder = SpannableStringBuilder("He leído las condiciones de uso")

        conditionBuilder.setSpan(
            ConditionClicText(this),
            START_TEXT_CONDITION,
            conditionBuilder.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        textUseCondition.text = conditionBuilder

        textUseCondition.movementMethod = LinkMovementMethod.getInstance()

        textUseCondition.highlightColor = Color.TRANSPARENT
    }

    override fun onClick(v: View?) {

        when (v?.id) {

            R.id.checkBoxAceptPrivacy -> {
                isCheckPrivacy = checkBoxPrivacy.isChecked
                setButtonBackground()
                clearError()
            }
            R.id.checkBoxUseCondition -> {
                isCheckCondition = checkBoxCondition.isChecked
                setButtonBackground()
                clearError()
            }
            R.id.buttonAceptAgreement ->
                {
                    toNextActivity()
                }
            R.id.cancelAgreement ->
                this.finish()
        }
    }

    private fun toNextActivity() {

        if (canToNextActivity()) {


            dialogPutIpUrl()

        } else {

            if (!isCheckCondition) {
                checkBoxCondition.error = "No está marcado"
            }
            if (!isCheckPrivacy) {

                checkBoxPrivacy.error = "No está marcado"
            }
        }
    }



    private fun dialogPutIpUrl(){

            val inputEditText = EditText(this)

            val builder = AlertDialog.Builder(this)

            builder.setTitle("Pon Url de Api")
                    .setView(inputEditText)
            builder.setPositiveButton(
                    "Aceptar",
                    DialogInterface.OnClickListener {

                        _, _ ->

                        ConexionUrl.IPWEB=inputEditText.text.toString()+".ngrok.io"

                        ConexionUrl.OLVIDARCONTRASENA="http://${ConexionUrl.IPWEB}:${ConexionUrl.PORTWEB}/proyectoFinalEntrada/pages/recuperacontrasena.jsp"


                        ConexionUrl.BASE_URL="http://${ConexionUrl.IPWEB}:${ConexionUrl.PORTWEB}/"

                        ConexionUrl.HTTPJSONINFORMACION="http://${ConexionUrl.IPWEB}:${ConexionUrl.PORTWEB}/proyectoFinalEntrada/mandarinformaciondeproducto?idusuario=";


                        ConexionUrl.HTTPJSONMANDACUENTA= "http://${ConexionUrl.IPWEB}:${ConexionUrl.PORTWEB}/proyectoFinalEntrada/mandarcuenta?idproducto="

                        PrivacyAgreementManager(this).saveStateAgreement()

                        startActivity(Intent(this, LoginActivity::class.java))

                        this.finish()



                    }
            ).setCancelable(false)

            builder.show()

    }


    private fun clearError() {

        if (isCheckPrivacy) {

            checkBoxPrivacy.error = null
        }

        if (isCheckCondition) {

            checkBoxCondition.error = null
        }
    }

    private fun setButtonBackground() {

        if (canToNextActivity()) {

            buttonAceptAgreement.setBackgroundColor( Color.parseColor("#397BB7"))
        } else {

            buttonAceptAgreement.setBackgroundColor( Color.parseColor("#dddddd"))
        }
    }

    private fun canToNextActivity(): Boolean {

        return isCheckCondition && isCheckPrivacy
    }

    class ConditionClicText(var activity: Activity) : ClickableSpan() {

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)

            ds.color = Color.parseColor("#397BB7")

            ds.isUnderlineText = false
        }

        override fun onClick(widget: View) {

            val intent = Intent(activity, DetailPrivacyActivity::class.java)
            intent.putExtra(TYPE_AGREEMEMTS, AGREEMETS_CONDITION)
            activity.startActivity(intent)
        }
    }

    class PrivacyClicText(var activity: Activity) : ClickableSpan() {

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)

            ds.color = Color.parseColor("#397BB7")

            ds.isUnderlineText = false
        }

        override fun onClick(widget: View) {

            val intent = Intent(activity, DetailPrivacyActivity::class.java)
            intent.putExtra(TYPE_AGREEMEMTS, AGREEMETS_PRIVACY)

            activity.startActivity(intent)
        }
    }
}
