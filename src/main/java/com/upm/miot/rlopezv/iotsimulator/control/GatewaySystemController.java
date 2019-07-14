package com.upm.miot.rlopezv.iotsimulator.control;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.upm.miot.rlopezv.iotsimulator.AppConstants;
import com.upm.miot.rlopezv.iotsimulator.data.Message;
import com.upm.miot.rlopezv.iotsimulator.utils.JSONUtils;

import okio.Buffer;

/**
 *
 * @author ramon
 *
 */
public class GatewaySystemController extends AbstractSystemController {

	private Logger LOGGER = LoggerFactory.getLogger(GatewaySystemController.class);

	private String httpURL = null;
	@Override
	protected void init() {
		String sInterval = getConfig().getOrDefault(AppConstants.MEASURE_INTERVAL, "1");
		setMeasureInterval(Long.valueOf(sInterval) * 60000);
		httpURL = getConfig().getOrDefault(AppConstants.HTTP_URL, "http://localhost:1880");

	}

	@Override
	protected void handleMessage(Message message) {
		LOGGER.info("Message received:{}", message);
		if (JSONUtils.isJSONValid(message.getMqttMessage().getPayload())) {
			MediaType JSON = MediaType.parse("application/json; charset=utf-8");
			String payload = new String(message.getMqttMessage().getPayload(), StandardCharsets.UTF_8);
			RequestBody body = RequestBody.create(JSON, payload);

			OkHttpClient client = new OkHttpClient();

			Request request = new Request.Builder().url(httpURL).post(body).build();

			Call call = client.newCall(request);

			call.enqueue(new Callback() {
				@Override
				public void onFailure(Request request, IOException e) {
					String requestContent = null;
					final Buffer buffer = new Buffer();
					try {
						request.body().writeTo(buffer);
					} catch (IOException e1) {
						LOGGER.warn("Could not process request body", e1);
					}
					requestContent = buffer.readUtf8();
					LOGGER.error("onFailure() Request was:({}/{})", request.urlString(), request.method());
					LOGGER.error("Content:{}", requestContent);
					LOGGER.error("Error", e);
				}

				@Override
				public void onResponse(Response response) throws IOException {
					String result = response.body().string();
					LOGGER.info("message processed: {}", response.code(), result);

				}
			});
		} else {
			LOGGER.warn("Discarded message {}", message);
		}

	}

	@Override
	protected void generateMessage() {
		// DOES NOTHING
	}

}
