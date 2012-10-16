package com.ubs.gtp.data.resource.mappers;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Custom format for outputting {@link java.util.Date}s.
 *
 * @author Jakub D Kozlowski
 * @since 0.3
 */
public final class CustomDateSerializer extends JsonSerializer<Timestamp> {

    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    /**
     * {@inheritDoc}
     */
    @Override
    public void serialize(final Timestamp value, final JsonGenerator jgen, final SerializerProvider provider)
            throws IOException {
        try {
            final SimpleDateFormat f = new SimpleDateFormat(DATE_FORMAT);
            final String formatted = f.format(value);
            final long timestamp = f.parse(formatted).getTime();
            jgen.writeNumber(value.getTime());
        } catch (final ParseException e) {
            // Ignore, this is a hack.
        }
    }

    @Override
    public Class<Timestamp> handledType() {
        return Timestamp.class;
    }
}
