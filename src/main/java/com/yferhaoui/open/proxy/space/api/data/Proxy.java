package com.yferhaoui.open.proxy.space.api.data;

import java.io.Serializable;
import java.util.Arrays;

import com.google.gson.annotations.Expose;

public final class Proxy implements Serializable, Comparable<Proxy> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2941701356519611948L;

	public enum Protocol {

		HTTPS(1), SOCKS4(2), SOCKS5(3);

		private final int value;

		private Protocol(final int value) {
			this.value = value;
		}

		// Getter
		public final int getValue() {
			return this.value;
		}
	}

	// -------------------------------------------------------- //

	@Expose(serialize = true, deserialize = true)
	private int[] protocols;

	@Expose(serialize = true, deserialize = true)
	private int uptime;

	@Expose(serialize = true, deserialize = true)
	private String ip;

	@Expose(serialize = true, deserialize = true)
	private int port;

	@Expose(serialize = true, deserialize = true)
	private int quality;

	@Expose(serialize = true, deserialize = true)
	private int timeout;

	@Expose(serialize = true, deserialize = true)
	private String country;

	@Expose(serialize = true, deserialize = true)
	private Object cascade;

	@Expose(serialize = true, deserialize = true)
	private int stable;

	@Expose(serialize = true, deserialize = true)
	private int streak;

	@Expose(serialize = true, deserialize = true)
	private long updated;
	
	// Basic Constructor
	public Proxy(final String ip, final int port) {
		
		this.ip = ip;
		this.port = port;
	}

	// Getter
	public final boolean contains(final Protocol protocol) {
		return Arrays.stream(this.protocols).anyMatch(i -> i == protocol.getValue());
	}

	public final int[] getProtocols() {
		return this.protocols;
	}

	public final int getUptime() {
		return this.uptime;
	}

	public final String getIp() {
		return this.ip;
	}

	public final int getPort() {
		return this.port;
	}

	public final int getQuality() {
		return this.quality;
	}

	public final int getTimeout() {
		return this.timeout;
	}

	public final String getCountry() {
		return this.country;
	}

	public final Object getCascade() {
		return this.cascade;
	}

	public final int getStable() {
		return this.stable;
	}

	public final int getStreak() {
		return this.streak;
	}

	public final long getUpdated() {
		return this.updated;
	}

	@Override
	public final int compareTo(final Proxy server) {
		return this.ip.compareTo(server.ip);
	}

}
