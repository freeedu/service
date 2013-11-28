package org.personal.mason.feop.server.blog.domain.service;

import org.personal.mason.feop.server.blog.domain.model.MediaInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MediaInfoService {

    void save(MediaInfo mediaInfo);

    MediaInfo update(MediaInfo mediaInfo);

    void delete(MediaInfo mediaInfo);

    void delete(Long id);

    MediaInfo findById(Long id);

    MediaInfo findByMediaCode(String mediacode);

    Page<MediaInfo> findByUid(String uid, Pageable pageable);

    Page<MediaInfo> findByUidAndType(String uid, String type, Pageable pageable);

    List<MediaInfo> findByUidAndType(String uid, String type);
}
