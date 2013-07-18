package org.personal.mason.feop.server.blog.repository;

import org.personal.mason.feop.server.blog.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import java.lang.String;
import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

	List<Tag> findByTagName(String tagname);
	
	List<Tag> findByTagNameLike(String tagname);
}
