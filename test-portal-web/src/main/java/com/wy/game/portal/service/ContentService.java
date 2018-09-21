package com.wy.game.portal.service;

import com.wy.game.portal.pojo.po.TbContent;

import java.util.List;

public interface ContentService {
    List<TbContent> listContentByCid(Long cid);
}
