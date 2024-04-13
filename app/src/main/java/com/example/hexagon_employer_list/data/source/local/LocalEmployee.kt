package com.example.hexagon_employer_list.data.source.local

import android.net.Uri
import io.realm.kotlin.types.RealmObject
import org.mongodb.kbson.ObjectId
import java.time.LocalDate

class LocalEmployee: RealmObject {
    var _id: ObjectId = ObjectId()
    var name: String = ""
    var birthDate: String = ""
    var city: String = ""
    var document: String = ""
    var active: Boolean = false
    var profilePicture: String = ""
}