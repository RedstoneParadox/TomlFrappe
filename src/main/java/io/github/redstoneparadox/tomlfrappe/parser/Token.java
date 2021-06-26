package io.github.redstoneparadox.tomlfrappe.parser;

public record Token(TokenType type, long line, long column, String lexeme, Object literal) { }
