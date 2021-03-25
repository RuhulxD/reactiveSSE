package com.ruhul.ractive.netty.service;

import com.ruhul.ractive.netty.common.EventDto;
import com.ruhul.ractive.netty.common.FileReaderReactive;
import com.ruhul.ractive.netty.common.LiveConsumer;
import com.ruhul.ractive.netty.common.LiveEventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

@Service
public class ServerSentEventServiceImpl implements ServerSentEventService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final AtomicInteger counter;

	private final FileReaderReactive fileReaderReactive;

	public ServerSentEventServiceImpl(FileReaderReactive fileReaderReactive) {
		this.fileReaderReactive = fileReaderReactive;
		counter = new AtomicInteger(0);
	}

	private List<LiveConsumer> pingLiveConsumers = new CopyOnWriteArrayList<>();

	@Override
	public Flux<EventDto> onPingSubscribe() {
		logger.info("[SSE] subscribed for ping");
		Flux<EventDto> flux = Flux.create(emitter -> {
			Consumer<EventDto> consumer = emitter::next;
			registerPing(consumer, emitter);
			emitter.onDispose(() -> unRegisterForPing(consumer));
		}, FluxSink.OverflowStrategy.LATEST);
		return flux
				//.doOnEach(eventDtoSignal -> logger.info("[FLUX]do on each"))
				.doOnError(throwable -> logger.error("[FLUX] error: {}", throwable.getMessage(), throwable))
				.doOnComplete(() -> logger.info("[FLUX]: completed."))
				.doFinally(signalType -> logger.info("[FLUX]: Closing sse connection"))
				;
	}

	@Async
	@Scheduled(fixedRate = 2000)
	private void ping() {
		if (pingLiveConsumers.size() > 0) {
			pingLiveConsumers.forEach(liveConsumer -> {
						EventDto eventDto = new EventDto(LiveEventType.PING, " id->" + liveConsumer.getId() + " total consumer->:" + pingLiveConsumers.size());
						liveConsumer.getConsumer().accept(eventDto);
					}
			);
		}
	}

	private void unRegisterForPing(Consumer<EventDto> consumer) {
		pingLiveConsumers.removeIf(liveConsumer -> liveConsumer.getConsumer().equals(consumer));
	}

	private void registerPing(Consumer<EventDto> consumer, FluxSink<EventDto> emitter) {
		pingLiveConsumers.add(new LiveConsumer(counter.incrementAndGet(), consumer, emitter));
	}

	@Override
	public Flux<String> getFile(){
		return fileReaderReactive.getFile();
	}

}
