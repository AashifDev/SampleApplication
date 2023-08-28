package com.example.sampleapplication.model

data class LocationBean(
    var description: String? = null,
    var main_text: String? = "main text",
    var structured_formatting: LocationBean? = null
)
