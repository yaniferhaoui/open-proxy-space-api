package com.yferhaoui.open.proxy.space.api;

import java.io.IOException;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.yferhaoui.open.proxy.space.api.data.Request;
import com.yferhaoui.open.proxy.space.api.data.Proxy;

public final class OpenProxySpaceAPI {

	private final HttpClient client = HttpClient.newBuilder()//
			.connectTimeout(Duration.ofSeconds(60))//
			.followRedirects(Redirect.NEVER)//
			.version(Version.HTTP_2)//
			.proxy(ProxySelector.of(null))//
			.build();

	private final Gson gson = new Gson();

	public final List<Proxy> getProxies(final Request request) throws IOException, InterruptedException {

		// Create Http Request
		final HttpRequest httpRequest = HttpRequest.newBuilder()//
				.uri(URI.create(request.getRequest()))//
				.GET()//
				.build();

		// Get Response
		final HttpResponse<String> response = this.client.send(httpRequest, BodyHandlers.ofString());
		if (response.statusCode() != 200) {
			throw new IOException("HTTP Status Code: " + response.statusCode() + " => " + response.body());
		}

		// Return Servers
		return Arrays.asList(this.gson.fromJson(response.body(), Proxy[].class));
	}

	public final List<Proxy> getRandomizedProxies(final Request request) throws IOException, InterruptedException {

		final List<Proxy> proxies = this.getProxies(request);
		Collections.shuffle(proxies);

		return proxies;
	}
}
