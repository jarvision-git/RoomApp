package com.example.roomyt

import android.app.Application

class ContactApplication:Application() {
    val db by lazy {
        ContactDatabase.getInstance(this)
    }

}