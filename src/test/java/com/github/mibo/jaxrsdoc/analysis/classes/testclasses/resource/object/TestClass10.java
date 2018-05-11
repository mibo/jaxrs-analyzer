package com.github.mibo.jaxrsdoc.analysis.classes.testclasses.resource.object;

import com.github.mibo.jaxrsdoc.model.Types;
import com.github.mibo.jaxrsdoc.model.elements.HttpResponse;
import com.github.mibo.jaxrsdoc.builder.HttpResponseBuilder;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class TestClass10 {

    @javax.ws.rs.GET public String method() {
        final StringBuilder builder = new StringBuilder();

        getDailyAccesses().entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .forEach(e -> {
                    builder.append(e.getKey()).append(":\n");
                    e.getValue().entrySet().stream()
                            .sorted(Comparator.comparing(Map.Entry::getKey))
                            .forEach(a -> builder.append(a.getKey()).append(": ").append(a.getValue()).append('\n'));
                    builder.append('\n');
                });

        return builder.toString();
    }

    private Map<String, Map<LocalDate, Integer>> getDailyAccesses() {
        return getAll().entrySet().stream().collect(HashMap::new, (m, e) -> m.put(e.getKey(), calculateDailyAccesses(e.getValue())), Map::putAll);
    }

    public Map<String, Set<Instant>> getAll() {
        return Collections.emptyMap();
    }

    private Map<LocalDate, Integer> calculateDailyAccesses(final Set<Instant> timestamps) {
        return timestamps.stream()
                .map(t -> t.atZone(ZoneId.systemDefault()).toLocalDate())
                .collect(HashMap::new, (m, d) -> m.merge(d, 1, (oldV, newV) -> oldV + newV), Map::putAll);
    }

    public static Set<HttpResponse> getResult() {
        return Collections.singleton(HttpResponseBuilder.newBuilder().andEntityTypes(Types.STRING).build());
    }

}
