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

	private char next() {
		if (index < source.length) return source[index++];
		return '\u0000';
	}

	private char peek(int count) {
		if (index + count < source.length) return source[index + count];
		return '\u0000';
	}
}
