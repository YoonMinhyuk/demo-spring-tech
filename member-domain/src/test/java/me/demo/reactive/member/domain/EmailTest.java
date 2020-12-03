package me.demo.reactive.member.domain;

import me.demo.reactive.member.exception.InvalidEmailException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EmailTest {
    @Test
    @DisplayName("email 생성 성공 테스트")
    void testEmailInstantiation() {
        //given when
        final Email email = Email.of("email@gmail.com");

        //then
        assertThat(email).isNotNull();
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("email 생성 실패 테스트")
    void testEmailInstantiationFailure(final String email) {
        //given when then
        assertThatThrownBy(() -> Email.of(email)).isExactlyInstanceOf(InvalidEmailException.class);
    }

    private static Stream<Arguments> testEmailInstantiationFailure() {
        return Stream.of(
                Arguments.arguments("email"),
                Arguments.arguments("emailgmail.com"),
                Arguments.arguments("email@"),
                Arguments.arguments("email@gmail"),
                Arguments.arguments("email@gmail."),
                Arguments.arguments("email@gmail.co.")
        );
    }

    @Test
    void testEqualsAndHasCode() {
        //given
        final String firstEmailValue = "first@gmail.com";
        final String secondEmailValue = "second@gmail.com";

        //when then
        final Email firstEmail = Email.of(firstEmailValue);
        final Email secondEmail = Email.of(firstEmailValue);
        final Email thirdEmail = Email.of(secondEmailValue);

        assertThat(firstEmail).isEqualTo(secondEmail);
        assertThat(firstEmail.hashCode()).isEqualTo(secondEmail.hashCode());

        assertThat(firstEmail).isNotEqualTo(thirdEmail);
        assertThat(firstEmail.hashCode()).isNotEqualTo(thirdEmail.hashCode());
    }
}