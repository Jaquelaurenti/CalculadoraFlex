package br.com.jaquelaurenti.calculadoraflex.utils

import br.com.jaquelaurenti.calculadoraflex.BuildConfig
import br.com.jaquelaurenti.calculadoraflex.R
import com.google.android.gms.tasks.Task
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings

object RemoteConfig {
    fun getFirebaseRemoteConfig(): FirebaseRemoteConfig{
        val mFirebaseRemoteConfig: FirebaseRemoteConfig? = FirebaseRemoteConfig.getInstance()
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setDeveloperModeEnabled(BuildConfig.DEBUG)
            .build()
        mFirebaseRemoteConfig?.setConfigSettings(configSettings)
        mFirebaseRemoteConfig?.setDefaults(R.xml.remote_config_defaults)
        return mFirebaseRemoteConfig!!

    }

    fun remoteConfigFetch(): Task<Void>{
        var cacheExpiration: Long = 720
        if(getFirebaseRemoteConfig().info.configSettings.isDeveloperModeEnabled){
            cacheExpiration = 0
        }
        return getFirebaseRemoteConfig().fetch(cacheExpiration)
    }
}