/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.aop;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link SampleAopApplication}.
 *
 * @author Dave Syer
 * @author Phillip Webb
 */
@ExtendWith(OutputCaptureExtension.class)
class SampleAopApplicationTests {

	private String profiles;

	@BeforeEach
	public void init() {
		this.profiles = System.getProperty("spring.profiles.active");
	}

	@AfterEach
	public void after() {
		if (this.profiles != null) {
			System.setProperty("spring.profiles.active", this.profiles);
		}
		else {
			System.clearProperty("spring.profiles.active");
		}
	}

	@Test
	void testDefaultSettings(CapturedOutput capturedOutput) throws Exception {
		SampleAopApplication.main(new String[0]);
		assertThat(capturedOutput).contains("Hello Phil");
	}

	@Test
	void testCommandLineOverrides(CapturedOutput capturedOutput) throws Exception {
		SampleAopApplication.main(new String[] { "--name=Gordon" });
		assertThat(capturedOutput).contains("Hello Gordon");
	}

}
