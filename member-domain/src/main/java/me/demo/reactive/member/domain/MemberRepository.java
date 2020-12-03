package me.demo.reactive.member.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByEmailAndPlatform(final Email email, final Platform platform);
}
