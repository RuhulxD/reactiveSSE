package com.ruhul.ractive.netty.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Data
@AllArgsConstructor
public abstract class LiveDTO implements Serializable {
	protected UUID id;

	protected Long epoch;

	protected String time;

	LiveDTO(UUID id, ZoneId zoneId) {
		setId(id);
		Instant now = Instant.now();
		setEpoch(now.getEpochSecond());
		setTime(now.atZone(zoneId)
						.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
	}

	public LiveDTO() {
		this(UUID.randomUUID(),ZoneId.systemDefault());
	}
}
