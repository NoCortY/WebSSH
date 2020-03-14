package cn.liaojl;

import cn.objectspace.webssh.util.EncryptUtil;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PattenTest {
    private static final Logger log = LoggerFactory.getLogger(PattenTest.class);
    Pattern pattern = null;
    String sql = null;

    @Before
    public void init() {
        pattern = Pattern.compile("select (.*?) from (\\w*)(\\s)*(where)?(\\s)*(.*?)", Pattern.CASE_INSENSITIVE);
        sql = "select CONVERT(LOG_CONTENT USING utf8),LOG_NAME from TB_SQL_UPDATE_LOG WHERE LOG_TIME>'2010-01-15 15:24:55'";
    }

    @Test
    public void test1() {
        Matcher matcher = pattern.matcher(sql);
        if (matcher.matches()) {
            int count = matcher.groupCount();
            for (int i = 0; i <= count; i++) {
                log.info("{}=>{}", i, matcher.group(i));
            }
        } else {
            log.error("false");
        }
    }

    @Test
    public void test2() {
        try {
            log.info(EncryptUtil.encryptStringWithSHA1("123456"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
