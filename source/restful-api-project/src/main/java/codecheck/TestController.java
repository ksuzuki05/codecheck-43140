package codecheck;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/test")
public class TestController {
    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @RequestMapping(path="/test", method=RequestMethod.GET)
    public String test() {
        System.out.println("accessed");
        List<Map<String, Object>> list;
        list = jdbcTemplate.queryForList("select * from recipes");
        return list.toString();
    }
}
