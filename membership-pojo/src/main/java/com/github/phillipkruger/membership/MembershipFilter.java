package com.github.phillipkruger.membership;

import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class MembershipFilter {
    private Optional<Type> type = Optional.empty();
    private Optional<String> name = Optional.empty();
    private Optional<String> surnameContains = Optional.empty();
}
