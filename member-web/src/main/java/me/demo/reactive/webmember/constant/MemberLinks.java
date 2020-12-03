package me.demo.reactive.webmember.constant;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum MemberLinks {
    SIGN_UP(MemberLinks.BASE_PATH + "/sign-up");

    private final static String BASE_PATH = "/members";
    private final String link;

    MemberLinks(final String link) {
        this.link = link;
    }

    public static String getBasePath() {
        return BASE_PATH;
    }
}
