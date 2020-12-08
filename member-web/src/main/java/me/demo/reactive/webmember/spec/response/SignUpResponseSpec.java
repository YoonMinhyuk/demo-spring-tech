package me.demo.reactive.webmember.spec.response;

import lombok.Getter;
import lombok.ToString;
import me.demo.reactive.webmember.constant.MemberLinks;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.RepresentationModel;

import java.net.URI;
import java.util.Objects;

@Getter
@ToString
public class SignUpResponseSpec extends RepresentationModel<SignUpResponseSpec> {
    private final Long id;

    public SignUpResponseSpec(final Long id) {
        if (Objects.isNull(id)) throw new IllegalArgumentException("id cannot be null");
        this.id = id;
        initLinks(id);
    }

    public static SignUpResponseSpec of(final Long id) {
        return new SignUpResponseSpec(id);
    }

    public URI getSelfLinkUri() {
        return getRequiredLink(IanaLinkRelations.SELF).toUri();
    }

    private void initLinks(final Long memberId) {
        add(
                Link.of(MemberLinks.SIGN_UP.getLink()).withSelfRel(),
                Link.of(MemberLinks.QUERY_A_MEMBER.getLink()).withRel(LinkRelation.of("query-a-member")).expand(memberId)
        );
    }
}