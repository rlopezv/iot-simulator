package com.upm.miot.rlopezv.iotsimulator.control;

import java.util.Optional;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.upm.miot.rlopezv.iotsimulator.AppConstants;
import com.upm.miot.rlopezv.iotsimulator.data.Message;
import com.upm.miot.rlopezv.iotsimulator.data.MessageBuilder;
import com.upm.miot.rlopezv.iotsimulator.data.MessageException;
import com.upm.miot.rlopezv.iotsimulator.element.SrActuator;
import com.upm.miot.rlopezv.iotsimulator.model.SrIrrigationPlan;
import com.upm.miot.rlopezv.iotsimulator.model.SrSystemMeassure;

/**
 *
 * @author ramon
 *
 */
public class BaseSystemController extends AbstractSystemController {

	private Logger LOGGER = LoggerFactory.getLogger(BaseSystemController.class);

	@Override
	protected void init() {
		String sInterval = getConfig().getOrDefault(AppConstants.MEASURE_INTERVAL, "1");
		setMeasureInterval(Long.valueOf(sInterval) * 60000);
	}

	@Override
	protected void handleMessage(Message message) {
		LOGGER.debug("Message recevied:{}", message);
		try {
			if (message.getTopic().endsWith(AppConstants.PLAN)) {
				SrIrrigationPlan plan = MessageBuilder.getBuilder().parseObject(message.getMqttMessage().getPayload(),
						SrIrrigationPlan.class);
				LOGGER.info("*******************Irrigation Plan*******************");
				LOGGER.info("{}", plan);
				LOGGER.info("*****************************************************");
			} else {
				String actuatorType = message.getTopic().substring(message.getTopic().lastIndexOf("/") + 1,
						message.getTopic().length());
				Optional<SrActuator> actuator = getSystem().getActuators().stream()
						.filter(p -> actuatorType.equalsIgnoreCase(p.getType())).findFirst();
				if (actuator.isPresent() && "off".equalsIgnoreCase(message.getMqttMessage().toString())) {
					if (actuator.get().getStatus()) {
						actuator.get().setStatus(Boolean.FALSE);
						LOGGER.info("*******************Changing actuator status*******************");
						LOGGER.info("Change actuator status ({}):{}", actuatorType, Boolean.FALSE);
						LOGGER.info("*****************************************************");
					}
				} else if (actuator.isPresent() && "on".equalsIgnoreCase(message.getMqttMessage().toString())) {
					actuator.get().setStatus(Boolean.TRUE);
					LOGGER.info("*******************Changing actuator status*******************");
					LOGGER.info("Change actuator status ({}):{}", actuatorType, Boolean.TRUE);
					LOGGER.info("*****************************************************");
					
				} else {
					LOGGER.warn("Actuator or command not valid ({}):{}", message.getTopic(),
							message.getMqttMessage().toString());
				}
			}
		} catch (MessageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void generateMessage() {
		SrSystemMeassure message = getSystem().getMeasures();
		try {
			getClient().publish(MessageBuilder.getBuilder().buildMessage(message));
			LOGGER.info("*******************Sent message*******************");
			LOGGER.info("message:{}", message);
			LOGGER.info("*****************************************************");

		} catch (MqttException | MessageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
