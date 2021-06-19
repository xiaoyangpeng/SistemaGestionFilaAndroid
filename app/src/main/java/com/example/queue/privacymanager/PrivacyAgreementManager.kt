package com.example.afasiaapp.privacymanager

import android.content.Context
import android.content.SharedPreferences

class PrivacyAgreementManager(private val context: Context) {

    companion object {

        private const val PRIVACY_SHARED_PREFERENCE = "sharedPreferences_of_privacy_policy"

        private const val AGRREMENTES = "agreements"
    }
    private val prefs: SharedPreferences = context.getSharedPreferences(PRIVACY_SHARED_PREFERENCE, Context.MODE_PRIVATE)

    fun saveStateAgreement() {

        val editor = prefs.edit()
        editor.putBoolean(AGRREMENTES, true)
        editor.apply()
    }

    fun isAceptAgreement(): Boolean {

        return prefs.getBoolean(AGRREMENTES, false)
    }
}
