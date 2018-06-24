package com.nick.simpleTest;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeoutException;

public class Send {
    private final static String QUEUE_NAME = "hello";
    public  static Logger logger =  LoggerFactory.getLogger(Send.class);

    public static void main(String[] argv)
            throws java.io.IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.5.136");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello World!";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        logger.info(" [x] Sent '{}'" ,message );

        channel.close();
        connection.close();
        System.exit(0);
    }

}