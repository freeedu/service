package org.personal.mason.feop.server.blog.domain.service.impl;

import org.personal.mason.feop.server.blog.domain.model.MediaInfo;
import org.personal.mason.feop.server.blog.domain.repository.MediaInfoRepository;
import org.personal.mason.feop.server.blog.domain.service.MediaInfoService;
import org.personal.mason.feop.server.blog.utils.PageableUtils;
import org.personal.mason.feop.server.blog.utils.SortUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MediaInfoServiceImpl implements MediaInfoService {

    private MediaInfoRepository mediaInfoRepository;

    @Autowired
    public void setMediaInfoRepository(MediaInfoRepository mediaInfoRepository) {
        this.mediaInfoRepository = mediaInfoRepository;
    }

    @Override
    @Transactional
    public void save(MediaInfo mediaInfo) {
        mediaInfoRepository.save(mediaInfo);
    }

    @Override
    @Transactional
    public MediaInfo update(MediaInfo mediaInfo) {
        return mediaInfoRepository.saveAndFlush(mediaInfo);
    }

    @Override
    @Transactional
    public void delete(MediaInfo mediaInfo) {
        mediaInfoRepository.delete(mediaInfo);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        mediaInfoRepository.delete(id);
    }

    @Override
    @Transactional
    public MediaInfo findByMediaCode(String mediacode) {
        List<MediaInfo> mediaInfos = mediaInfoRepository.findByMediaCode(mediacode);
        return mediaInfos.size() > 0 ? mediaInfos.get(0) : null;
    }

    @Override
    @Transactional
    public MediaInfo findById(Long id) {
        return mediaInfoRepository.findOne(id);
    }

    @Override
    public Page<MediaInfo> findByUid(String uid, Pageable pageable) {
        return mediaInfoRepository.findByMediaOwnerUid(uid, PageableUtils.getPageable(pageable, SortUtils.getSortDESC("mediaAddTime")));
    }

    @Override
    public List<MediaInfo> findByUidAndType(String uid, String type) {
        return mediaInfoRepository.findByMediaOwnerUidAndMediaType(uid, type);
    }

    @Override
    public Page<MediaInfo> findByUidAndType(String uid, String type, Pageable pageable) {
        return mediaInfoRepository.findByMediaOwnerUidAndMediaType(uid, type,
                PageableUtils.getPageable(pageable, SortUtils.getSortDESC("mediaAddTime")));
    }
}
