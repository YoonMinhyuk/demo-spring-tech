package me.demo.reactive.member.domain;

import lombok.*;
import me.demo.reactive.domainglobal.domain.DomainDateTimeAudit;
import me.demo.reactive.member.exception.InvalidEmailException;
import me.demo.reactive.member.exception.UnsupportedPlatformException;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "UNQ_email", columnNames = {"email", "platform"})})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(of = {"email", "platform"}, callSuper = false)
@ToString
public class Member extends DomainDateTimeAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Email email;

    @Enumerated(EnumType.STRING)
    private Platform platform;

    @Column(name = "name")
    private String name;

    private Member(final Email email, final Platform platform, final String name) {
        setEmail(email);
        setPlatform(platform);
        setName(name);
    }

    private Member(final String email, final Platform platform, final String name) {
        this(Email.of(email), platform, name);
    }

    public static Member signUp(final MemberRecord memberRecord) {
        return Optional.ofNullable(memberRecord)
                       .map(mr -> new Member(mr.getEmail(), mr.getPlatform(), mr.getName()))
                       .orElseThrow(() -> new IllegalArgumentException("memberRecord cannot be null"));
    }

    private void setEmail(final Email email) {
        this.email = Optional.ofNullable(email).orElseThrow(InvalidEmailException::new);
    }

    public void setPlatform(final Platform platform) {
        this.platform = Optional.ofNullable(platform).orElseThrow(UnsupportedPlatformException::new);
    }

    private void setName(final String name) {
        this.name = Optional.ofNullable(name)
                            .filter(s -> !s.isEmpty())
                            .filter(s -> !s.isBlank())
                            .orElse("");
    }

    @RequiredArgsConstructor
    @Builder
    @Getter
    @ToString
    public static class MemberRecord {
        private final Long id;
        private final String email;
        private final Platform platform;
        private final String name;

        private MemberRecord(final Member member) {
            this.id = member.getId();
            this.email = member.getEmail().getValue();
            this.platform = member.getPlatform();
            this.name = member.getName();
        }

        public static MemberRecord of(
                final String email,
                final Platform platform,
                final String name
        ) {
            return new MemberRecord(null,email, platform, name);
        }

        public Email getEmail() {
            return Email.of(email);
        }
    }
}
