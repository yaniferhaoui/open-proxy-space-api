package com.yferhaoui.open.proxy.space.api.data;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public final class Cascade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2811609238877479223L;

	// -------------------------------------------------------- //

	@Expose(serialize = true, deserialize = true)
	private String ip;

	@Expose(serialize = true, deserialize = true)
	private String country;

	// Getter
	public final String getIp() {
		return this.ip;
	}

	public final String getCountry() {
		return this.country;
	}
}
