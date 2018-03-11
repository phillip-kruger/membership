package com.github.phillipkruger.membership;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class Link {
    private final String url;
    private final String description;
}