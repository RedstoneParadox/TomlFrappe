package xyz.redstoneparadox.tomlfrappe.test;

import io.github.redstoneparadox.tomlfrappe.Toml;
import org.junit.jupiter.api.Test;
import xyz.redstoneparadox.tomlfrappe.test.util.InputStreamUtil;

import java.io.IOException;

public class ParserTest {
	private final Toml toml = new Toml();
	private final String source = InputStreamUtil.readFromInputStream(ParserTest.class.getResourceAsStream("/test.toml"));

	public ParserTest() throws IOException {
	}

	@Test
	void basic() {
		var document = toml.load(source);
	}
}
