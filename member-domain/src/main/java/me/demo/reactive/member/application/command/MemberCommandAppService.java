package me.demo.reactive.member.application.command;

import lombok.RequiredArgsConstructor;
import me.demo.reactive.member.domain.Member;
import me.demo.reactive.member.domain.MemberRepository;
import me.demo.reactive.member.exception.DuplicateMemberException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberCommandAppService {
    private final MemberRepository memberRepository;

    @Transactional
    public Long signUp(final Member.MemberRecord memberRecord) {
        if (memberRepository.existsByEmailAndPlatform(memberRecord.getEmail(), memberRecord.getPlatform())) {
            throw new DuplicateMemberException();
        }
        return memberRepository.save(Member.signUp(memberRecord)).getId();
    }
}
