package org.personal.mason.feop.server.blog.mvc.rest;

import org.personal.mason.feop.server.blog.domain.model.MediaInfo;
import org.personal.mason.feop.server.blog.domain.service.MediaInfoService;
import org.personal.mason.feop.server.blog.mvc.model.MediaInfoModel;
import org.personal.mason.feop.server.blog.mvc.utils.AuthenticationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("api")
public class MediaInfoApi {
    private MediaInfoService mediaInfoService;

    @Autowired
    public void setMediaInfoService(MediaInfoService mediaInfoService) {
        this.mediaInfoService = mediaInfoService;
    }

    /**
     * @param mediaInfoModel
     * @param request
     * @return
     */
    @RequestMapping(value = "/media/save", method = RequestMethod.POST)
    @ResponseBody
    public MediaInfoModel saveMedia(MediaInfoModel mediaInfoModel, HttpServletRequest request) {
        MediaInfo mediaInfo = MediaInfoModel.convert(mediaInfoModel);
        mediaInfo.setMediaOwnerUid(AuthenticationUtils.getUid(request));
        mediaInfoService.save(mediaInfo);
        return MediaInfoModel.revert(mediaInfo);
    }

    /**
     * @param mediaInfoModel
     * @return
     */
    @RequestMapping(value = "/media/update", method = RequestMethod.PUT)
    @ResponseBody
    public MediaInfoModel updateMedia(MediaInfoModel mediaInfoModel) {
        MediaInfo mediaInfo = MediaInfoModel.convert(mediaInfoModel);
        MediaInfo updateMediaInfo = mediaInfoService.update(mediaInfo);
        return MediaInfoModel.revert(updateMediaInfo);
    }

    /**
     * @param page
     * @param size
     * @param type
     * @param request
     * @return
     */
    @RequestMapping(value = "/media/list", method = RequestMethod.GET)
    @ResponseBody
    public List<MediaInfoModel> findMyMedia(Pageable pageable, @RequestParam(value = "t", required = false) String type, HttpServletRequest request) {

        String uid = AuthenticationUtils.getUid(request);

        Page<MediaInfo> mediaInfos = null;
        if (type == null || type.isEmpty()) {
            mediaInfos = mediaInfoService.findByUidAndType(uid, type, pageable);
        } else {

            mediaInfos = mediaInfoService.findByUid(uid, pageable);
        }
        List<MediaInfoModel> models = new ArrayList<>();
        if (mediaInfos != null) {
            for (MediaInfo mediaInfo : mediaInfos) {
                MediaInfoModel model = MediaInfoModel.revert(mediaInfo);
                models.add(model);
            }
        }
        return models;
    }

    /**
     * @param id
     */
    @RequestMapping(value = "/media/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@RequestParam("id") Long id) {
        mediaInfoService.delete(id);
    }
}
