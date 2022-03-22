package io.github.redstoneparadox.tomlfrappe.parser;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
	private char[] source = new char[]{'\u0000'};
	private List<Token> tokens = new ArrayList<>();
	private int index = 0;
	private int row = 0;
	private int column = 0;


	public List<Token> lex(String toml) {
		source = toml.toCharArray();

		var c =  ' ';

		while (c != '\u0000') {
			consumeWhitespace();
			c = next();
			switch (c) {
				case '"':
					break;
				default:
					if (Character.isLetter(c)) {
						identifier(c);
					} else if (Character.isDigit(c)) {

					}
					break;
			}
		}

		var lexed = tokens;
		tokens = new ArrayList<>();
		return lexed;
	}

	private void consumeWhitespace() {
		while (Character.isWhitespace(peek(0))) {
			switch (next()) {
				case ' ':
				case '\r':
				case '\t':
					break;
				case '\n':
					row++;
					column = 0;
				default:
					return;
			}
		}
	}

	private void identifier(char first) {
		StringBuilder wordBuilder = new StringBuilder();

		wordBuilder.append(first);

		while (Character.isLetter(peek(0)) || Character.isLetter(peek(0))) {
			wordBuilder.append(next());
		}

		String word = wordBuilder.toString();

		switch (word) {
			case "true" -> tokens.add(new Token(TokenType.TRUE, 0, 0, null, false));
			case "false" -> tokens.add(new Token(TokenType.FALSE, 0, 0, null, false));
			default -> tokens.add(new Token(TokenType.IDENTIFIER, 0, 0, word, null));
		}
	}

	private char next() {
		if (index < source.length) return source[index++];
		return '\u0000';
	}

	private char peek(int count) {
		if (index + count < source.length) return source[index + count];
		return '\u0000';
	}
}
