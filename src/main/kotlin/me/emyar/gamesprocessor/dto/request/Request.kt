@file:OptIn(ExperimentalSerializationApi::class)

package me.emyar.gamesprocessor.dto.request

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonClassDiscriminator

@JsonClassDiscriminator("api")
@Serializable
sealed class Request