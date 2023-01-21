package get.a.big.head.newNRG;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class NewNrgClient {

	public static void main(String[] args) {
		new SpringApplicationBuilder(NewNrgClient.class)
				.headless(false)
				.web(WebApplicationType.NONE)
				.run(args);
	}
}
