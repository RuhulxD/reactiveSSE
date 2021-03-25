package com.ruhul.ractive.netty.service;

import com.ruhul.ractive.netty.common.EventDto;
import reactor.core.publisher.Flux;

public interface ServerSentEventService {
	Flux<EventDto> onPingSubscribe();

	Flux<String> getFile();
}
