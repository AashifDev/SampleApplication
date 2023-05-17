package com.example.sampleapplication.model

import com.google.firebase.firestore.DocumentId

data class Note(
    @DocumentId
    val id: String? = null,
    val title: String? = null,
    val description: String? = null
)
