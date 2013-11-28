package org.personal.mason.feop.oauth.service.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableUtils {

    public static Pageable getPageable(int page, int size) {
        PageRequest request = new PageRequest(page, size);
        return request;
    }

    public static Pageable getPageable(int page, int size, Sort sort) {
        return new PageRequest(page, size, sort);
    }
}
