package io.github.redstoneparadox.tomlfrappe;

import io.github.redstoneparadox.tomlfrappe.document.TomlElement;
import io.github.redstoneparadox.tomlfrappe.parser.Lexer;

@SuppressWarnings("unused")
public class Toml {
	private final Lexer lexer;

	public Toml() {
		lexer = new Lexer();
	}

	public TomlElement load(String source) {
		var tokens = lexer.lex(source);

		return null;
	}
}
