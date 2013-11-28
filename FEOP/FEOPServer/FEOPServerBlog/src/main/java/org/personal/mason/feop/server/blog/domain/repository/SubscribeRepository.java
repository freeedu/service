package org.personal.mason.feop.server.blog.domain.repository;

import org.personal.mason.feop.server.blog.domain.model.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {

    List<Subscribe> findBySubscriberUid(String subscriberuid);
}
