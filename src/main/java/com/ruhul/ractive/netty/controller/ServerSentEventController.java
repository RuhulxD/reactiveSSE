package com.ruhul.ractive.netty.controller;

import com.ruhul.ractive.netty.common.EventDto;
import com.ruhul.ractive.netty.service.ServerSentEventServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ServerSentEventController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	//
	private final ServerSentEventServiceImpl serverSentEventService;

	public ServerSentEventController(ServerSentEventServiceImpl serverSentEventService) {
		this.serverSentEventService = serverSentEventService;
	}

	@GetMapping(path = "/public/ping/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<EventDto> pingSubscribe() {
		//TODO add check from customerId
		return serverSentEventService.onPingSubscribe();
	}

	@GetMapping(path = "/public/file", produces = MediaType.TEXT_HTML_VALUE)
	public Flux<String> getFile() {
		//TODO add check from customerId
		return serverSentEventService.getFile();
	}
}
