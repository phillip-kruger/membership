package com.github.phillipkruger.membership.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class SearchCriteria {
    private String key;
    private String operation;
    private Object value;
}
