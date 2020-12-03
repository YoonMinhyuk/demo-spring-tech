package me.demo.reactive.member.fixture;

import me.demo.reactive.member.domain.Member;
import me.demo.reactive.member.domain.Platform;

public abstract class Fixtures {
    public static Member.MemberRecord.MemberRecordBuilder aMemberRecord() {
        return Member.MemberRecord.builder()
                                  .email("member@gmail.com")
                                  .name("memberName")
                                  .platform(Platform.GOOGLE);
    }
}
