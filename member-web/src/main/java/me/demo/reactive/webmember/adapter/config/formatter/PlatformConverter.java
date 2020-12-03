package me.demo.reactive.webmember.adapter.config.formatter;

import me.demo.reactive.member.domain.Platform;
import org.springframework.core.convert.converter.Converter;

public class PlatformConverter implements Converter<String, Platform> {
    @Override
    public Platform convert(final String source) {
        return Platform.valueOf(source);
    }
}
