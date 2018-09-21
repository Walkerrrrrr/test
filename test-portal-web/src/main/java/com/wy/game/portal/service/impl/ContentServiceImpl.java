package com.wy.game.portal.service.impl;

import com.wy.game.common.core.JsonUtils;
import com.wy.game.common.core.StrKit;
import com.wy.game.common.jedis.JedisClient;
import com.wy.game.portal.dao.TbContentMapper;
import com.wy.game.portal.pojo.po.TbContent;
import com.wy.game.portal.pojo.po.TbContentExample;
import com.wy.game.portal.service.ContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ContentServiceImpl implements ContentService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TbContentMapper contentMapper;
    @Autowired
    private JedisClient jedisClient;
    @Override
    public List<TbContent> listContentByCid(Long cid) {
        List<TbContent> contentList = null;
        try {
            //缓存中有则从缓存中取值，如无则去数据库搜索
            String jsonStr = jedisClient.hget("CONTENTLIST",cid+"");
            if(StrKit.notBlank(jsonStr)){
                contentList = JsonUtils.jsonToList(jsonStr,TbContent.class);
                return contentList;
            }
            //从数据库中查找
            TbContentExample example = new TbContentExample();
            TbContentExample.Criteria criteria = example.createCriteria();
            criteria.andCategoryIdEqualTo(cid);
            contentList = contentMapper.selectByExample(example);
            //存放一份到缓存服务器
            jedisClient.hset("CONTENTLIST",cid+"",JsonUtils.objectToJson(contentList));
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return contentList;
    }
}
