package com.doubletapp.psbhack.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.doubletapp.psbhack.R
import com.doubletapp.psbhack.main.MainActivity
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthCallback
import com.vk.api.sdk.utils.VKUtils

class AuthActivity : AppCompatActivity(R.layout.activity_auth) {

    private val authPreference by lazy { AuthTokenPreference() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fingerprints = VKUtils.getCertificateFingerprint(this, packageName)

        if (savedInstanceState == null) {
            if (authPreference.hasToken()) {
                val intent = MainActivity.getIntent(this, authPreference.getRequestId())
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                startActivity(intent)
            } else {
                supportFragmentManager.commit {
                    add(R.id.auth_container, AuthFragment(), null)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val callback = object : VKAuthCallback {
            override fun onLogin(token: VKAccessToken) {
                (supportFragmentManager.findFragmentById(R.id.auth_container) as? AuthFragment)?.onVkAuthLogin(token)
            }

            override fun onLoginFailed(errorCode: Int) {
                (supportFragmentManager.findFragmentById(R.id.auth_container) as? AuthFragment)?.onVkAuthDecline()
            }
        }
        if (!VK.onActivityResult(requestCode, resultCode, data, callback)) {
            (supportFragmentManager.findFragmentById(R.id.auth_container) as? AuthFragment)?.onVkAuthDecline()
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}