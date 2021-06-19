package com.example.queue.privacy

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.queue.R
import com.joanzapata.pdfview.PDFView
import com.joanzapata.pdfview.listener.OnPageChangeListener
import kotlinx.android.synthetic.main.activity_detail_privacy.*

class DetailPrivacyActivity : AppCompatActivity(), OnPageChangeListener {

    lateinit var pdfView: PDFView

    lateinit var text: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)

        setContentView(R.layout.activity_detail_privacy)

        pdfView = findViewById(R.id.pdfView)

        text = findViewById(R.id.pdfText)

        val bundle = intent.extras

        val typeAgreement = bundle?.get(AceptPrivacyActivity.TYPE_AGREEMEMTS)

        val filename = if (typeAgreement == AceptPrivacyActivity.AGREEMETS_CONDITION) {

            "terminos-condiciones-app.pdf"
        } else {

            "Politica-de-Privacidad.pdf"
        }

        display(filename)
    }

    private fun display(filename: String) {

        pdfView.fromAsset(filename)
            .enableSwipe(true)
            .swipeVertical(true)
            .onPageChange(this)
            .load()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onPageChanged(page: Int, pageCount: Int) {

        pdfText.text = "$page/$pageCount"
    }
}
