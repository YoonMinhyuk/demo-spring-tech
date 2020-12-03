package me.demo.reactive.member.domain;

import me.demo.reactive.member.exception.InvalidEmailException;
import me.demo.reactive.member.exception.UnsupportedPlatformException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static me.demo.reactive.member.fixture.Fixtures.aMemberRecord;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberTest {
    @Test
    @DisplayName("member 생성 성공 테스트(signUp 메소드 테스트)")
    void testMemberInstantiationSuccess() {
        //given
        final Member.MemberRecord memberRecord = aMemberRecord().build();

        //when
        final Member member = Member.signUp(memberRecord);

        //then
        assertThat(member).isNotNull();
        assertThat(member.getEmail()).isEqualTo(memberRecord.getEmail());
        assertThat(member.getName()).isEqualTo(memberRecord.getName());
        assertThat(member.getPlatform()).isEqualTo(memberRecord.getPlatform());
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("member 생성 실패 테스트")
    void testMemberInstantiationFailure(
            final Member.MemberRecord memberRecord,
            final Class<?> expectedExceptionType
    ) {
        assertThatThrownBy(() -> Member.signUp(memberRecord)).isExactlyInstanceOf(expectedExceptionType);
    }

    private static Stream<Arguments> testMemberInstantiationFailure() {
        return Stream.of(
                Arguments.arguments(aMemberRecord().email(null).build(), InvalidEmailException.class),
                Arguments.arguments(aMemberRecord().platform(null).build(), UnsupportedPlatformException.class)
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("email 과 platform 이 같으면 동등해야한다")
    void testEqualsAndHashCode(
            final Member firstMember,
            final Member secondMember,
            final boolean expected
    ) {
        assertThat(firstMember.equals(secondMember)).isEqualTo(expected);
        assertThat(firstMember.hashCode() == secondMember.hashCode()).isEqualTo(expected);
    }

    private static Stream<Arguments> testEqualsAndHashCode() {
        final String firstEmail = "first@gmail.com";
        final String secondEmail = "second@gmail.com";
        final Platform google = Platform.GOOGLE;
        final Platform naver = Platform.NAVER;

        final Member firstMember = Member.signUp(aMemberRecord()
                .email(firstEmail)
                .platform(google)
                .build());

        final Member secondMember = Member.signUp(aMemberRecord()
                .email(firstEmail)
                .platform(google)
                .build());

        final Member thirdMember = Member.signUp(aMemberRecord()
                .email(secondEmail)
                .platform(google)
                .build());

        final Member fourthMember = Member.signUp(aMemberRecord()
                .email(firstEmail)
                .platform(naver)
                .build());

        final Member fifthMember = Member.signUp(aMemberRecord()
                .email(secondEmail)
                .platform(naver)
                .build());

        return Stream.of(
                Arguments.arguments(firstMember, secondMember, true),
                Arguments.arguments(firstMember, thirdMember, false),
                Arguments.arguments(firstMember, fourthMember, false),
                Arguments.arguments(firstMember, fifthMember, false)
        );
    }
}