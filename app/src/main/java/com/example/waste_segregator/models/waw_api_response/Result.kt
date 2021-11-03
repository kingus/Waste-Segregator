package com.example.waste_segregator.models.waw_api_response

data class Result(
    val _links: Links?,
    val fields: List<Field>?,
    val include_total: Boolean?,
    val limit: Int?,
    val records: List<Record>?,
    val records_format: String?,
    val resource_id: String?,
    val total: Int?
)