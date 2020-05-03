package com.sudirman.aplikasifootball.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseLastEvent{

	@SerializedName("events")
	private List<EventsItem> events;

	public List<EventsItem> getEvents(){
		return events;
	}
}