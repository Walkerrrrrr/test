package test;

import com.wy.game.common.core.JsonUtils;
import com.wy.game.common.core.StrKit;
import com.wy.game.common.jedis.JedisClient;
import com.wy.game.portal.dao.TbContentMapper;
import com.wy.game.portal.pojo.po.TbContent;
import com.wy.game.portal.pojo.po.TbContentExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-jedis.xml"})
public class ContentTest {

    @Autowired
    private JedisClient jedisClient;
    @Test
    public void lunboTest(){
        System.out.println(jedisClient.get("name"));
    }
}
