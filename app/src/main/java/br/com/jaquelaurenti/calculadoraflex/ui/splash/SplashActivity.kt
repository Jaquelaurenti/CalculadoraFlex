package br.com.jaquelaurenti.calculadoraflex.ui.splash

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import br.com.jaquelaurenti.calculadoraflex.R
import br.com.jaquelaurenti.calculadoraflex.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_splash.*


class SplashActivity : AppCompatActivity() {

    private val TEMPO_AGUARDANDO_SPLASHSCREEM = 3500L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val preferences = getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
        val isFirstOne = preferences.getBoolean("open_first", true)
        if(isFirstOne){
            markAppAlreadyOpen(preferences)
            showSplash()

        }else{
            showLogin()
        }
    }

    private fun showSplash() {
        val anim = AnimationUtils.loadAnimation(this, R.anim.animacao_splash)
        anim.reset()
        ivLogo.clearAnimation()
        ivLogo.startAnimation(anim)
    }

    private fun markAppAlreadyOpen(preferences: SharedPreferences) {
        val editor = preferences.edit()
        editor.putBoolean("open_first",false)
        editor.apply()
    }

    private fun showLogin() {
        val nextScreen = Intent(this@SplashActivity, LoginActivity::class.java)
        startActivity(nextScreen)
        finish()
    }

    private fun carregar() {
        val anim = AnimationUtils.loadAnimation(this, R.anim.animacao_splash)
        anim.reset()
        ivLogo.clearAnimation()
        ivLogo.startAnimation(anim)
        Handler().postDelayed({
            showLogin()
        }, TEMPO_AGUARDANDO_SPLASHSCREEM)
    }


}
