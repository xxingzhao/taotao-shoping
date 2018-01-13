import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration({"classpath:spring/applicationContext-*.xml"})
public class ItemParamTest {

    @Autowired
    private ItemParamService itemParamService;


    public void getItemParam() {

    }
}
