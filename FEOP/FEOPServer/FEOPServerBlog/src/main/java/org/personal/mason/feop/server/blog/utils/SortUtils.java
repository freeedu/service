package org.personal.mason.feop.server.blog.utils;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

public class SortUtils {

    public static Sort getSortASC(String... properties) {
        return new Sort(Direction.ASC, properties);
    }

    public static Sort getSortDESC(String... properties) {
        return new Sort(Direction.DESC, properties);
    }

    public static Sort appendSortASC(Sort sort, String property) {
        return sort.and(new Sort(getOrder(property, Direction.ASC)));
    }

    public static Sort appendSortDESC(Sort sort, String property) {
        return sort.and(new Sort(getOrder(property, Direction.DESC)));
    }

    protected static Order getOrder(String property, Direction direction) {
        if (direction == null) {
            direction = Direction.ASC;
        }

        if (property == null || property.isEmpty()) {
            return null;
        }

        return new Order(direction, property);
    }
}
