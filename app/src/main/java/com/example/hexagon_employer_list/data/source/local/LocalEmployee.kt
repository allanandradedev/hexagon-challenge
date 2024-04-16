package com.example.hexagon_employer_list.data.source.local

import io.realm.kotlin.types.RealmObject
import org.mongodb.kbson.ObjectId

class LocalEmployee: RealmObject {
    var id: ObjectId = ObjectId()
    var name: String = ""
    var birthDate: String = ""
    var city: String = ""
    var document: String = ""
    var active: Boolean = false
    var profilePicture: String = ""
}