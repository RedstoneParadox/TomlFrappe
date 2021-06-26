package io.github.redstoneparadox.tomlfrappe.document;

public class TomlPrimitive extends TomlElement {
	private final Object value;

	public TomlPrimitive(Object value) {
		if (value instanceof Character) {
			this.value = "" + value;
		} else if (value instanceof String) {
			this.value = value;
		} else if (value instanceof Integer) {
			this.value = value;
		} else if (value instanceof Float) {
			this.value = value;
		} else if (value instanceof Boolean) {
			this.value = value;
		} else {
			throw new IllegalArgumentException();
		}
	}

	public String asString(String defaultValue) {
		if (value instanceof String) return (String) value;
		return defaultValue;
	}

	public int asInt(int defaultValue) {
		if (value instanceof Integer) return (int) value;
		return defaultValue;
	}

	public float asFloat(float defaultValue) {
		if (value instanceof Float) return (float) value;
		return defaultValue;
	}

	public boolean asBoolean(boolean defaultValue) {
		if (value instanceof Boolean) return (boolean) value;
		return defaultValue;
	}
}
