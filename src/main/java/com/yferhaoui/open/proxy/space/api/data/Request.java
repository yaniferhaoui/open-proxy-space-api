package com.yferhaoui.open.proxy.space.api.data;

import java.util.Arrays;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;

public final class Request {

	private final static String DEFAULT_REQUEST = "https://api.openproxy.space/premium/json?apiKey=";

	// -------------------------------------------------------------------- //

	private final String request;

	public Request(final String request) {
		this.request = request;
	}

	public final String getRequest() {
		return this.request;
	}

	// -------------------------------------------------------------------- //

	public final static class RequestBuilder {

		private final String key;
		private final TreeMap<String, String> attributes;

		public RequestBuilder(final String key) {
			this.key = key;
			this.attributes = new TreeMap<String, String>();
		}

		// Select amount of proxies equal to value.
		public final RequestBuilder amount(final int amount) {

			this.attributes.put("amount", String.valueOf(amount));

			return this;
		}

		// Select proxies with uptime percent more or equal to value.
		// Allowed value between 1 and 100.
		public final RequestBuilder uptime(final int uptime) {

			this.attributes.put("uptime", String.valueOf(uptime));

			return this;
		}

		// Select proxies by count of successful checks in a row more or equal to value.
		// Available only for alive proxies.
		// Allowed value between 1 and 100.
		public final RequestBuilder streak(final int streak) {

			this.attributes.put("streak", String.valueOf(streak));

			return this;
		}

		// Select proxes by current status.
		// Allowed values: 1,2,3.
		// 1 - Alive.
		// 2 - Dead.
		// 3 - All (alive and dead).
		public final RequestBuilder status(final int status) {

			this.attributes.put("status", String.valueOf(status));

			return this;
		}

		// Will filter multiple proxies with same output (real) IPs and extract only
		// best one proxy.
		// Allowed value: 1.
		public final RequestBuilder smart(final int smart) {

			this.attributes.put("smart", String.valueOf(smart));

			return this;
		}

		// Select proxies by response time of the last checking, less or equal to value,
		// in milliseconds.
		// Allowed value between 1 and 10000.
		public final RequestBuilder timeout(final int timeout) {

			this.attributes.put("timeout", String.valueOf(timeout));

			return this;
		}

		// Select proxies by average response time of the last 10 checkings, less or
		// equal to value, in milliseconds.
		// Allowed value between 1 and 10000.
		public final RequestBuilder timeoutAverage(final int timeoutAverage) {

			this.attributes.put("timeoutAverage", String.valueOf(timeoutAverage));

			return this;
		}

		// Select proxies by stability gradation of the last connection.
		// Allowed values: [0,1,2].
		// 0 - High.
		// 1 - Medium.
		// 2 - Low.
		// Example: 1,2.
		public final RequestBuilder stable(final int[] stable) {

			final String value = Arrays.stream(stable)//
					.mapToObj(String::valueOf)//
					.collect(Collectors.joining(","));
			this.attributes.put("stable", value);

			return this;
		}

		// Select proxies by average stability gradation of the last 10 connections.
		// Allowed values: [0,1,2].
		// 0 - High.
		// 1 - Medium.
		// 2 - Low.
		// Example: 1,2.
		public final RequestBuilder stableAverage(final int[] stableAverage) {

			final String value = Arrays.stream(stableAverage)//
					.mapToObj(String::valueOf)//
					.collect(Collectors.joining(","));
			this.attributes.put("stableAverage", value);

			return this;
		}

		// Select proxies by protocols.
		// Allowed values: [1,2,3].
		// 1 - Https.
		// 2 - Socks4.
		// 3 - Socks5.
		// Example: 1,3.
		public final RequestBuilder protocols(final int[] protocols) {

			final String value = Arrays.stream(protocols)//
					.mapToObj(String::valueOf)//
					.collect(Collectors.joining(","));
			this.attributes.put("protocols", value);

			return this;
		}

		// Select proxies by country.
		// Allowed all country codes available at the moment.
		// Example: BD,ID,US.
		public final RequestBuilder countries(final String[] countries) {

			final String value = Arrays.stream(countries)//
					.collect(Collectors.joining(","));
			this.attributes.put("countries", value);

			return this;
		}

		// Will exclude proxies with these countries.
		// Allowed all country codes available at the moment.
		// Example: BD,ID,US.
		public final RequestBuilder excludeCountries(final String[] excludeCountries) {

			final String value = Arrays.stream(excludeCountries)//
					.collect(Collectors.joining(","));
			this.attributes.put("excludeCountries", value);

			return this;
		}

		// Select proxies by ports.
		// Allowed values between 1 and 65535.
		// Example: 80,8080,3128.
		public final RequestBuilder ports(final int[] ports) {

			final String value = Arrays.stream(ports)//
					.mapToObj(String::valueOf)//
					.collect(Collectors.joining(","));
			this.attributes.put("ports", value);

			return this;
		}

		// Select proxies by calculated quality score from: streak, average stable of 10
		// last checks, average timeout of 10 last checks, uptime. More or equal to
		// value.
		// Allowed value between 1 and 100.
		public final RequestBuilder quality(final int quality) {

			this.attributes.put("quality", String.valueOf(quality));

			return this;
		}

		// Select proxies by total checks, more or equal to value. To determine old
		// proxies in database.
		// Allowed value between 1 and 10000.
		public final RequestBuilder checksMore(final int checksMore) {

			this.attributes.put("checksMore", String.valueOf(checksMore));

			return this;
		}

		// Select proxies by total checks, less or equal to value. To determine new
		// proxies in database.
		// Allowed value between 1 and 10000.
		public final RequestBuilder checksLess(final int checksLess) {

			this.attributes.put("checksLess", String.valueOf(checksLess));

			return this;
		}

		// If specified will download a file instead of plain text response.
		// Allowed charsets: a-z A-Z 0-9 _ - (space).
		// File extension will sets automatically.
		// Example: "save me".
		// Will be saved as "save me.txt" or "save me.json".
		public final RequestBuilder filename(final String filename) {

			this.attributes.put("filename", filename);

			return this;
		}

		public final Request build() {

			// Create the basic Request
			final StringBuilder request = new StringBuilder(Request.DEFAULT_REQUEST)//
					.append(this.key);

			// Add every attributes
			for (final Entry<String, String> e : this.attributes.entrySet()) {

				request.append("&")//
						.append(e.getKey())//
						.append("=")//
						.append(e.getValue());
			}

			return new Request(request.toString());
		}
	}
}
