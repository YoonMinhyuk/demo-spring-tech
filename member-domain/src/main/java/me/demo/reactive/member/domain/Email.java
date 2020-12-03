package me.demo.reactive.member.domain;

import lombok.*;
import me.demo.reactive.member.exception.InvalidEmailException;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Optional;
import java.util.regex.Pattern;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
@ToString
public class Email {
    private static final Pattern EMAIL_REG_EX = Pattern.compile("([a-zA-Z0-9]+)@([a-z]+)(\\.[a-z]{2,})(\\.[a-z]{2,})?");

    @Column(name = "email")
    private String value;

    private Email(final String value) {
        setValue(value);
    }

    public static Email of(final String email) {
        return new Email(email);
    }

    private void setValue(final String email) {
        this.value = Optional.ofNullable(email)
                             .filter(s -> !s.isEmpty())
                             .filter(s -> !s.isBlank())
                             .filter(s -> EMAIL_REG_EX.matcher(s).matches())
                             .orElseThrow(InvalidEmailException::new);
    }
}