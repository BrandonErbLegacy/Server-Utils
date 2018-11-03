package nightwraid.utils.general;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;

public class DiscordHelper {
	public static String default_discord = "https://discordapp.com/api/webhooks/508074427863269377/0c6JBaNErWBckDQVFm8v16rP7u1J6_ihl_WInIAP3CTaQvOXikZFdPSamJEu8Keo28wH";
    private final static String USER_AGENT = "Mozilla/5.0 (X11; Linux x86_64; rv:49.0) Gecko/20100101 Firefox/49.0";

	public static void postMessage(String message) throws IOException {
		URL url = new URL(default_discord);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("User-Agent", USER_AGENT);
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestProperty("Content-Length", String.valueOf(message.length()));
		connection.setDoOutput(true);
		connection.setDoInput(true);
		DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
		outputStream.writeBytes("{\"content\": \""+message+"\"}");
		outputStream.flush();
		outputStream.close();
		connection.connect();
		
		InputStream stream = connection.getInputStream();
		StringWriter writer = new StringWriter();
		IOUtils.copy(stream, writer, "UTF-8");
		String theString = writer.toString();
		System.out.println(theString);
	}
}
