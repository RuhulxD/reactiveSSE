package com.ruhul.ractive.netty.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.FluxSink;

import java.time.ZoneId;
import java.util.function.Consumer;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LiveConsumer {
	protected int id;
	//NOTE: if zoneid changed during live stream it will not have any affect on this
	protected ZoneId zoneId;

	protected Consumer<EventDto> consumer;

	protected FluxSink<EventDto> emitter;

	public LiveConsumer(int id, Consumer<EventDto> consumer, FluxSink<EventDto> emitter) {
		this.id = id;
		this.zoneId = ZoneId.systemDefault();
		this.consumer = consumer;
		this.emitter = emitter;
	}
}