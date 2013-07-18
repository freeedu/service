package org.personal.mason.feop.server.blog.spi;

import java.util.List;

import org.personal.mason.feop.server.blog.domain.Category;
import org.personal.mason.feop.server.blog.domain.Sery;
import org.springframework.data.domain.Sort;

public interface SeryService {

	void save(Sery sery);

	Sery update(Sery sery);

	Sery findById(Long id);

	void delete(Sery sery);

	void delete(Long id);

	List<Sery> findByCategory(Category category);

	List<Sery> findByCategory(Category category, int page, int size);

	List<Sery> findByCategory(Category category, Sort sort);

	List<Sery> findByName(String name, int page, int size);
}
