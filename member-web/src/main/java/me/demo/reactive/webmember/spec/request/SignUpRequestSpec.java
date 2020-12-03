package me.demo.reactive.webmember.spec.request;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import me.demo.reactive.member.domain.Member;
import me.demo.reactive.member.domain.Platform;

@RequiredArgsConstructor
@Builder
@Getter
@ToString
public class SignUpRequestSpec {
    private final String email;
    private final Platform platform;
    private final String name;

    public Member.MemberRecord toMemberRecord() {
        return Member.MemberRecord.builder()
                                  .email(email)
                                  .platform(platform)
                                  .name(name)
                                  .build();
    }
}
