package org.dargor.productmsreactive.app.messagging.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.dargor.productmsreactive.app.dto.ProductRequestDtoWrapper;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

import static org.dargor.productmsreactive.app.messagging.Constants.PRODUCTS_TOPIC;

@Component
public class ObjectDeserializer implements Deserializer<Object> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Object deserialize(String topic, byte[] data) {
        try {
            if (data == null) {
                return null;
            }
            if (topic.equals(PRODUCTS_TOPIC))
                return objectMapper.readValue(new String(data, StandardCharsets.UTF_8), ProductRequestDtoWrapper.class);
            else
                throw new SerializationException("Error when deserializing Kafka DTO to byte[]. Could not find matching topic.");

        } catch (Exception e) {
            throw new SerializationException("Error when deserializing Kafka DTO to byte[]");
        }
    }
}
