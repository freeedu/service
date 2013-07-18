package org.personal.mason.feop.server.blog.repository;

import org.personal.mason.feop.server.blog.domain.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import java.lang.String;
import java.util.List;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {

	List<Subscribe> findBySubscriberUid(String subscriberuid);
}
