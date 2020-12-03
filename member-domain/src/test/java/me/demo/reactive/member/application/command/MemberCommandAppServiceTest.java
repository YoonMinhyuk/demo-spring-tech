package me.demo.reactive.member.application.command;

import me.demo.reactive.member.domain.Member;
import me.demo.reactive.member.exception.DuplicateMemberException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static me.demo.reactive.member.fixture.Fixtures.aMemberRecord;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest(properties = "spring.config.location=classpath:/application-member.yml")
@ActiveProfiles("test")
@Transactional
class MemberCommandAppServiceTest {
    @Autowired
    private MemberCommandAppService memberCommandAppService;

    @Test
    @DisplayName("회원가입 테스트")
    void testSignUp() {
        //given when
        final Long newMemberId = memberCommandAppService.signUp(aMemberRecord().build());

        //then
        assertThat(newMemberId).isNotNull();
        assertThat(newMemberId).isGreaterThan(0);
    }

    @Test
    @DisplayName("이미 가입이 되어있는 회원정보로 다시 가입을 진행하면 예외가 발생해야한다")
    void testSignUpFailure() {
        //given
        final Member.MemberRecord memberRecord = aMemberRecord().build();
        memberCommandAppService.signUp(memberRecord);

        //when then
        assertThatThrownBy(() -> memberCommandAppService.signUp(memberRecord))
                .isExactlyInstanceOf(DuplicateMemberException.class);
    }
}