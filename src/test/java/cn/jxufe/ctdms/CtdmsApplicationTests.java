package cn.jxufe.ctdms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CtdmsApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(CtdmsApplicationTests.class);

	@Test
	public void contextLoads() {
		log.info("info");
		log.error("error");
		log.debug("debug");
	}

}
