package com.example.model;

import java.util.List;

public class Devices {

	private List<Device> devices;
	private String group;

	public Devices() {
		super();
	}

	public Devices(List<Device> devices, String group) {
		super();
		this.devices = devices;
		this.group = group;
	}

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}
}
