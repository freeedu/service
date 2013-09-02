package org.personal.mason.feop.server.blog.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableUtils {

	public static Pageable getPageable(Pageable pageable, Sort sort) {
		return new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), sort);
	}
}
