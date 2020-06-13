package com.roadmap.stackoverclone;

import com.roadmap.stackoverclone.configuration.ProfileConstants;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(ProfileConstants.TEST)
class StackovercloneApplicationTests {

	@Test
	void contextLoads() {
	}

}
