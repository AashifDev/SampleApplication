package com.example.sampleapplication.model

import android.widget.EditText
import com.google.firebase.database.Exclude

data class Note(
    val title: String? = null,
    val description: String? = null
)/*{
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "title" to title,
            "description" to description
        )
    }
}*/
