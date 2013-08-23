package org.personal.mason.feop.server.blog.domain.repository;

import org.personal.mason.feop.server.blog.domain.model.MediaInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.lang.String;
import java.util.List;

public interface MediaInfoRepository extends JpaRepository<MediaInfo, Long> {

	List<MediaInfo> findByMediaCode(String mediacode);

	List<MediaInfo> findByMediaOwnerUid(String mediaowneruid);

	List<MediaInfo> findByMediaOwnerUid(String mediaowneruid, Pageable pageable);

	List<MediaInfo> findByMediaOwnerUidAndMediaType(String mediaowneruid, String mediatype, Pageable pageable);
}
