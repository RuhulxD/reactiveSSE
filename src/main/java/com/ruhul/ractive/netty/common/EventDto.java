package com.ruhul.ractive.netty.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public class EventDto extends LiveDTO {
	private LiveEventType event;
	private Object data;

	public EventDto(UUID id, Long epoch, String time, LiveEventType event, Object data) {
		super(id, epoch, time);
		this.event = event;
		this.data = data;
	}

	public EventDto(LiveEventType event, Object data) {
		super();
		this.event = event;
		this.data = data;
	}
}
