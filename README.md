## IoT - Simulator

This project allows to simulate systems containing sensors an actuators that will use MQTT for communication.

It's a java project that uses maven for software project management and comprehension tool. Since it’s a maven project, it only requires check out and work with it. It has been included a wrapper in order to make it even easier.

Maven will be responsible of managing all dependencies and the building process.

### Description
The package **com.upm.miot.rlopezv.iotsimulator.control**  contains the implementation of the clients that will process the messages. The current implementation provides an abstract definition of the controller and two aditional controllers has been implemented: one for mqtt and another to act as a gateway for messages obtained from a MQTT broker. Since the implementation is part of the configuration it's easy to add implementations as required. For example, if you require something that uses HTTP only should be required to implement the publication of the message. In case of requiring to treat HTTP request it would be required to include a server like jetty that will allow that. In this repository only the implementation related with MQTT has been included but it can be extended really easy.

**AbstractSystemController**

The base class has been created for all the clients. So the clients only need to implement their own requirements.

This class implements Runnable interface so the implementing classes will be Threads executed in the context of an ExecutorService.

This class handles the connection and reconnection to the MQTT Broker and defines a couple of abstract methods that must be implemented in the child classes in order to provide the required funcionality:

    //Initialize client
    
    protected abstract void init();
    
    //Handles received message
    
    protected abstract void handleMessage(Message message);

**BaseSystemController**

Using sl4j libraries outputs to log the message received.

It provides a measure with the configured period.

It accepts commands from the IoT platform.

**GatewayController**

It provides a way of fowaring the subscribed messages to the configured HTTP URL. It's a sort of gateway for sending messages to an HTTP endpoint through a post method.

### Building

The building process of the application from source code it can be done with maven. The following command must be executed in order to do it:

> mvn clean package

This will generate an uber jar with the application under the target directory of the project. This uber jar contains the application executable and all its dependencies. It also contains a manifest declaring the main class for execution.

### Configuration
The capability of configure its behavior has been taken in consideration. So, it’s possible configure several parameters like the MQTT broker url and the topic. And it also allows to define and configure the actuators and sensors included for the system. 

The configuration of a system is done via a JSON file. And every system configuration contains the following elements:

-   **brokerUrl**, the broker URL to connect to
-   **implClassName**, with the controller implementing class.
-   **sysId**, the name of the system and that will be used in the topics that the client is subscribed or publishes.
-   **additionalProperites,** allows including additional information for configuration. I.e:measureInterval httpUrl for the http gateway.
-   **sensors**, array of elements containing the sensors included in the system
-   **actuators**, array of elements indicating the actuators included in the system.

Every sensor has its own configuration:

-   **type**: name of the sensor
-   **maxValue**: upper bound
-   **minValue**: lower bound.

Every actuator has its own configuration:

-   **type**: name of the sensor
-   **initialValue**: initial value

And example of a system configuration would be:
An example configuration file for the heating system will be:

    {
      "systemId": "res03sys03",
      "implClassName": "com.upm.miot.rlopezv.iotsimulator.control.BaseSystemController",
      "mqttConfig": {
        "brokerUrl": "tcp://83.54.31.83:1883"
      },
      "additionalProperties": {},
      "sensors": [
        {
          "type": "temperature",
          "maxValue": 30,
          "minValue": 0
        }
      ],
      "actuators": [
        {
          "type": "valve",
          "defaultValue": false
        }
      ]
    }

In the case of an irrigation system a configuration file could be:

    {
      "systemId": "res03sys01",
      "implClassName": "com.upm.miot.rlopezv.iotsimulator.control.BaseSystemController",
      "mqttConfig": {
        "brokerUrl": "tcp://83.54.31.83:1883"
      },
      "additionalProperties": {},
      "sensors": [
        {
          "type": "temperature",
          "maxValue": 50,
          "minValue": -10
        },
        {
          "type": "humidity",
          "maxValue": 100,
          "minValue": 0
        },
        {
          "type": "sm",
          "maxValue": 100,
          "minValue": 0
        }
      ],
      "actuators": [
        {
          "type": "valve",
          "defaultValue": false
        }
      ]
    }


### Execution

Although it can be executed inside an IDE. The application can be executed from command line using the following command once the jar is generated:

> java -jar [path_to_jar] [path_to_config_file]

An example of executing the application would be:
java -jar 

An example message for the irrigation system defined previously would be:

    {
      "measures": {
        "temperature": 19.54182460072763,
        "humidity": 11.560311047973858,
        "sm": 99.13634825811233,
        "valve": false
      },
      "systemId": "res01sys01"
    }

### Behavior
Once the system is started will start sending measures and will listen for commands. When using the MQTT implementation the subects used are based on the following convention:

 - **[application]/[system]/data**, for publishing sensors and actuators data.
 - **[application]/[system]/command** for receving commands.

Where **application** and **system** allows to identify the application and system respectively. And the content of the messages contains the data published. An example of a published message its:

The content of the messages sent and received is based on the configuration of the system that contains the sensors and actuators that contains.



