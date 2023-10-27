package me.emyar.gamesprocessor.controller

import me.emyar.gamesprocessor.dto.request.BalanceRequest
import me.emyar.gamesprocessor.dto.request.BalanceUpdateRequest
import me.emyar.gamesprocessor.dto.request.Request
import me.emyar.gamesprocessor.dto.response.Response
import me.emyar.gamesprocessor.service.BalanceRequestProcessor
import me.emyar.gamesprocessor.service.BalanceUpdateProcessor
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/open-api-games/v1/games-processor")
class Controller(
    private val balanceRequestProcessor: BalanceRequestProcessor,
    private val balanceUpdateProcessor: BalanceUpdateProcessor,
) {

    @GetMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun process(request: Request): Response =
        when (request) {
            is BalanceRequest -> balanceRequestProcessor.getBalance(request)
            is BalanceUpdateRequest -> balanceUpdateProcessor.updateBalance(request)
        }
}