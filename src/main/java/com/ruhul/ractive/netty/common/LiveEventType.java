package com.ruhul.ractive.netty.common;

import com.fasterxml.jackson.annotation.JsonValue;

public enum LiveEventType {
	AGENT_ONLINE,
	AGENT_OFFLINE,
	AGENT_STATUS,
	AGENT_JOINED_QUEUE,
	AGENT_UN_JOINED_QUEUE,
	AGENT_STARTED_SESSION,
	AGENT_ENDED_SESSION,
	VISITORS_JOINED_QUEUE,
	VISITORS_LEAVE_QUEUE,
	VISITORS_ABANDONED_QUEUE,
	SESSION_MOVED_TO_QUEUE,
	SESSION_ENDED,
	QUEUE_STATE,
	PING
	;


	@JsonValue
	public String getValue() {
		return name();
	}
}
