package ru.incoming34.yandex;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class MainClass {

	public static void main(String[] args) throws InterruptedException {

		String host = args[1];
		int interval = Integer.valueOf(args[0]);
		URL url = null;

		try {
			url = new URL(host);
		} catch (MalformedURLException e) {
			System.out.println("URL parsing error");
			System.exit(0);
		}

		while (true) {
			HttpURLConnection httpURLConnection = null;
			int responseCode = 0;

			try {
				httpURLConnection = (HttpURLConnection) url.openConnection();
				responseCode = httpURLConnection.getResponseCode();
				httpURLConnection.disconnect();
			} catch (IOException e) {
				if (Objects.nonNull(httpURLConnection)) {
					httpURLConnection.disconnect();
				}
			}

			if (responseCode == 200) {
				System.out.println("Checking '" + host + "'. Result: OK(200)");
			} else {
				System.out.println("Checking '" + host + "'. Result: ERR(" + responseCode + ")");
			}

			Thread.sleep(interval * 1000);
		}
	}
}
