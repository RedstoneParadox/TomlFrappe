package io.github.redstoneparadox.tomlfrappe;

import io.github.redstoneparadox.tomlfrappe.parser.Lexer;

@SuppressWarnings("unused")
public class Toml {
	private final Lexer lexer;

	public Toml() {
		lexer = new Lexer();
	}

	public void load(String source) {
		var tokens = lexer.lex(source);
	}
}
