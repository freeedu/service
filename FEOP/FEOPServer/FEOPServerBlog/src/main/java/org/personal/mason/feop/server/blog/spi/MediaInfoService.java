package org.personal.mason.feop.server.blog.spi;

import java.util.List;

import org.personal.mason.feop.server.blog.domain.MediaInfo;

public interface MediaInfoService {

	void save(MediaInfo mediaInfo);

	MediaInfo update(MediaInfo mediaInfo);

	void delete(MediaInfo mediaInfo);

	void delete(Long id);

	MediaInfo findById(Long id);

	MediaInfo findByMediaCode(String mediacode);

	List<MediaInfo> findByUid(String uid, int page, int size);

	List<MediaInfo> findByUidAndType(String uid, String type, int page, int size);
}
