package com.crossman;

import com.crossman.config.RootConfiguration;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Comparator;
import java.util.List;

public class HealthTrioTestApplication implements Runnable {
	private static final ObjectMapper objectMapper = new ObjectMapper();
	private static final Yaml yaml = new Yaml(new Constructor(RootConfiguration.class));

	private final RootConfiguration rootConfiguration;
	private final HttpClient client;

	public HealthTrioTestApplication(RootConfiguration rootConfiguration) {
		this.rootConfiguration = rootConfiguration;
		this.client = HttpClient.newBuilder().build();
	}

	@Override
	public void run() {
		try {
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(rootConfiguration.getServer().getUrl() + "?source=" + rootConfiguration.getServer().getSource()))
					.build();

			HttpResponse<String> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).join();

			List<HTRecord> results = objectMapper.readValue(response.body(), new TypeReference<List<HTRecord>>() {
			});

			results.stream()
					.filter(record -> record.getPeriod() != null && record.getPeriod() == 2014)
					.filter(record -> record.getRegion() != null)
					.filter(record -> record.getPct_hospitals_basic_ehr_notes() != null)
					.sorted(((Comparator<HTRecord>) (o1, o2) -> {
						final double d1 = Double.parseDouble(o1.getPct_hospitals_basic_ehr_notes());
						final double d2 = Double.parseDouble(o2.getPct_hospitals_basic_ehr_notes());
						return Double.compare(d1, d2);
					}).reversed())
					.forEach(record -> {
						System.out.println(record.getRegion() + " -> " + record.getPct_hospitals_basic_ehr_notes());
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		try (InputStream is = HealthTrioTestApplication.class.getClassLoader().getResourceAsStream("application.yaml")) {
			RootConfiguration config = yaml.load(is);
			new HealthTrioTestApplication(config).run();
		}
	}
}
