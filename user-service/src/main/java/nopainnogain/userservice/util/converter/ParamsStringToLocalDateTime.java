package nopainnogain.userservice.util.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

@Component
public class ParamsStringToLocalDateTime implements Converter<String, LocalDateTime> {
    @Override
    public LocalDateTime convert(String source) {


        return LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(source)),
                TimeZone.getDefault().toZoneId());
    }
}
