package service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import entity.BaseEntity;
import entity.PastaEntity;

public class RemoteService {

	private static RemoteService instance = null;

	public static RemoteService getInstance() {
		if (instance == null) {
			instance = new RemoteService();
		}

		return instance;
	}

	private RemoteService() {

	}

	public String getSearchResult() throws IOException {
		return executeFromRemote("http://food2fork.com/api/search?key=510b4b833870c160aeb1b8dbb6c10178");
	}

	public String getRecipe(String id) throws IOException {
		return executeFromRemote("http://food2fork.com/api/get?key=510b4b833870c160aeb1b8dbb6c10178&rId="
				+ id);
	}

	private String executeFromRemote(String theUrl) throws IOException {
		HttpURLConnection conn = null;
		try {

			URL url = new URL(theUrl);

			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			conn.addRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");

			conn.connect();

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			StringBuilder builder = new StringBuilder();

			String output;
			while ((output = br.readLine()) != null) {
				builder.append(output);
			}

			return builder.toString();
		} catch (IOException e) {
			throw e;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
	}
	
	public void notifyAmout(final BaseEntity entity) {
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					sendPost(entity);
				} catch (Exception e) {
					// Print to log
				}
				
			}
		}).run();
	}
	
	// HTTP POST request
	private void sendPost(BaseEntity entity) throws Exception {

		String url = "https://localhost:9300";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		
		StringBuilder builder = new StringBuilder();
		builder.append("type=");
		if (entity instanceof PastaEntity) {
			builder.append("pasta");
		}
		else {
			builder.append("sauce");
		}
		builder.append("&key="+entity.getName()+"amount="+entity.getAmount());
		String urlParameters = builder.toString();
//		String urlParameters = "type=pasta&key="+entity.getName()+"amount="+entity.getAmount();

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
/*
		int responseCode = con.getResponseCode();

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());
*/

	}


}
