package com.example.hexagon_employer_list

import android.app.Application
import com.example.hexagon_employer_list.data.source.local.LocalEmployee
import dagger.hilt.android.HiltAndroidApp
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

@HiltAndroidApp
class Hexagon: Application() {
    companion object {
        lateinit var realm: Realm
    }

    override fun onCreate() {
        super.onCreate()
        realm = Realm.open(
            configuration = RealmConfiguration.create(
                schema = setOf(
                    LocalEmployee::class
                )
            )
        )
    }
}