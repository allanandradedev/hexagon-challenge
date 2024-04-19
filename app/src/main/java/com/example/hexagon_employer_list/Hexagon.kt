package com.example.hexagon_employer_list

import android.app.Application
import br.com.flexpag.mainappneopay.ui.navigation.NavigationManager
import com.example.hexagon_employer_list.data.source.local.LocalEmployee
import dagger.hilt.android.HiltAndroidApp
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

@HiltAndroidApp
class Hexagon: Application() {
    companion object {
        lateinit var realm: Realm
        lateinit var navigationManager: NavigationManager
    }

    override fun onCreate() {
        super.onCreate()
        navigationManager = NavigationManager()
        realm = Realm.open(
            configuration = RealmConfiguration.create(
                schema = setOf(
                    LocalEmployee::class
                )
            )
        )
    }
}