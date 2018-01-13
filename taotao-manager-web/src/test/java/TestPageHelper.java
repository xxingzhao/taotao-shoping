import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestPageHelper {

    @Test
    public void testPage() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        TbItemMapper itemMapper = (TbItemMapper)ac.getBean("tbItemMapper");
        TbItemExample example = new TbItemExample();
        PageHelper.startPage(1, 10);
        List<TbItem> list = itemMapper.selectByExample(example);

        for(TbItem item : list) {
            System.out.println(item.getTitle());
        }

        PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);

        int pageSize = pageInfo.getPageSize();
        long taotal = pageInfo.getTotal();
        System.out.println(pageSize);
        System.out.println(taotal);
    }
}
