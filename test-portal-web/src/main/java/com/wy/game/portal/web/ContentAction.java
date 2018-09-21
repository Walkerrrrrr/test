package com.wy.game.portal.web;

import com.wy.game.common.core.PropKit;
import com.wy.game.portal.pojo.po.TbContent;
import com.wy.game.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ContentAction {
    @Autowired
    private ContentService contentService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String findContentLunBo(HttpServletRequest request){
        Long cid = PropKit.use("index.properties").getLong("lunbo.cid");
        List<TbContent> contentList = contentService.listContentByCid(cid);
        request.setAttribute("contentList",contentList);
        return "index1";
    }

    @RequestMapping(value = "/1",method = RequestMethod.GET)
    public String findContentLunBo1(HttpServletRequest request){
        Long cid = PropKit.use("index.properties").getLong("lunbo.cid");
        List<TbContent> contentList = contentService.listContentByCid(cid);
        request.setAttribute("contentList",contentList);
        return "index";
    }
}
