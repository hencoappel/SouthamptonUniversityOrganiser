package net.cbaines.suo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class OrganiserActivity extends Activity implements TestCredentials {

	private static final String TAG = "OrganiserActivity";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		DefaultHttpClient httpclient = new DefaultHttpClient();
		try {
			HttpGet httpget = new HttpGet("https://www.adminservices.soton.ac.uk/adminweb/servlet/login");

			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();

			Log.i(TAG, "Login form get: " + response.getStatusLine());
			// EntityUtils.consume(entity);
			if (entity != null) {
				try {
					entity.consumeContent();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			Log.i(TAG, "Initial set of cookies:");
			List<Cookie> cookies = httpclient.getCookieStore().getCookies();
			if (cookies.isEmpty()) {
				System.out.println("None");
			} else {
				for (int i = 0; i < cookies.size(); i++) {
					Log.i(TAG, "- " + cookies.get(i).toString());
				}
			}

			HttpPost httpost = new HttpPost("https://www.adminservices.soton.ac.uk/adminweb/servlet/login");

			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("user", username));
			nvps.add(new BasicNameValuePair("pwd", password));

			httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

			response = httpclient.execute(httpost);
			entity = response.getEntity();

			Log.i(TAG, "Login form get: " + response.getStatusLine());
			// EntityUtils.consume(entity);
			final String responseText = EntityUtils.toString(response.getEntity());
			if (responseText.contains("<title>Login Failed<title>")) {
				Log.i(TAG, "Login failed");
			} else {
				Log.i(TAG, "Login success");
			}

			Log.i(TAG, "Post logon cookies:");
			cookies = httpclient.getCookieStore().getCookies();
			if (cookies.isEmpty()) {
				System.out.println("None");
			} else {
				for (int i = 0; i < cookies.size(); i++) {
					Log.i(TAG, "- " + cookies.get(i).toString());
				}
			}

			/*
			 * HttpGet csvhttpost = new HttpGet(
			 * "https://www.adminservices.soton.ac.uk/adminweb/timetables/ttdownload/.csv"
			 * );
			 * 
			 * response = httpclient.execute(csvhttpost); entity =
			 * response.getEntity();
			 * 
			 * Log.i(TAG, "Csv get: " + response.getStatusLine()); //
			 * EntityUtils.consume(entity); if (entity != null) { if
			 * (entity.isStreaming()) { InputStream instream =
			 * entity.getContent(); if (instream != null) { instream.close(); }
			 * } }
			 */

		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// When HttpClient instance is no longer needed,
			// shut down the connection manager to ensure
			// immediate deallocation of all system resources
			httpclient.getConnectionManager().shutdown();
		}

		/*
		 * Log.i(TAG, "Begining stuff"); HttpClient client = new
		 * DefaultHttpClient(); HttpPost httppost = new HttpPost(); try {
		 * httppost.setURI(new
		 * URI("https://www.adminservices.soton.ac.uk/adminweb/servlet/login"));
		 * List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
		 * nameValuePairs.add(new BasicNameValuePair("user", username));
		 * nameValuePairs.add(new BasicNameValuePair("pwd", password));
		 * httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		 * httppost.setHeader("Host","www.adminservices.soton.ac.uk");
		 * httppost.setHeader("User-Agent",
		 * "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_6_6; en-us) AppleWebKit/533.19.4 (KHTML, like Gecko) Version/5.0.3 Safari/533.19.4"
		 * ); httppost.setHeader("Cookie","www.adminservices.soton.ac.uk");
		 * 
		 * 
		 * HttpResponse response = client.execute(httppost);
		 * 
		 * Header[] locationHeaders = response.getAllHeaders();
		 * 
		 * for (Header header : locationHeaders) { Log.i(TAG, "location: " +
		 * header.getName() + " : " + header.getValue()); }
		 * 
		 * Log.i(TAG, "Finished stuff");
		 * 
		 * } catch (URISyntaxException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); } catch (ClientProtocolException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } catch (IOException
		 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 * 
		 * new Thread() { public void run() { URL url; try { url = new
		 * URL("https://www.adminservices.soton.ac.uk/adminweb/servlet/login");
		 * URLConnection uc = url.openConnection(); String userpass = "cb15g11"
		 * + ":" + "Aqc9gamt"; String basicAuth = "Basic " +
		 * Base64.encodeToString(userpass.getBytes(), Base64.URL_SAFE);
		 * uc.setRequestProperty("Authorization", basicAuth); InputStream in =
		 * uc.getInputStream(); BufferedReader br = new BufferedReader(new
		 * InputStreamReader(in));
		 * 
		 * StringBuilder builder = new StringBuilder(); String str; while ((str
		 * = br.readLine()) != null) { builder.append(str); Log.i(TAG, str); }
		 * 
		 * url = new URL(
		 * "https://www.adminservices.soton.ac.uk/adminweb/timetables/ttdownload/.csv"
		 * ); uc = url.openConnection(); uc.setRequestProperty("Authorization",
		 * basicAuth); in = uc.getInputStream(); br = new BufferedReader(new
		 * InputStreamReader(in));
		 * 
		 * while ((str = br.readLine()) != null) { Log.i(TAG, str); }
		 * 
		 * } catch (MalformedURLException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); } catch (IOException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } } };
		 */
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.organiser_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.menu_preferences:
			Intent i = new Intent(OrganiserActivity.this, PreferencesActivity.class);
			startActivityForResult(i, 0);
			return true;
		default:
			Log.e(TAG, "No known menu option selected");
			return super.onOptionsItemSelected(item);
		}
	}

	class URLOpener {

		void open() {

		}

	}
}