package io.github.redstoneparadox.tomlfrappe.document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class TomlTable extends TomlElement implements Map<String, TomlElement> {
	private final List<Entry> entries = new ArrayList<>();
	private final boolean inline;

	public TomlTable(boolean inline) {
		this.inline = inline;
	}

	public boolean isInline() {
		return inline;
	}

	@Override
	public int size() {
		return entries.size();
	}

	@Override
	public boolean isEmpty() {
		return entries.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		Objects.requireNonNull(key, "TOML documents prohibit null keys!");

		for (Entry entry: entries) {
			if (entry.key == key) return true;
		}

		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		Objects.requireNonNull(value, "TOML documents prohibit null values!");

		for (Entry entry: entries) {
			if (entry.value == value) return true;
		}

		return false;
	}

	@Override
	public TomlElement get(Object key) {
		Objects.requireNonNull(key, "TOML documents prohibit null keys!");

		for (Entry entry: entries) {
			if (entry.key == key) return entry.value;
		}

		return null;
	}

	@Override
	public TomlElement put(String key, TomlElement value) {
		Objects.requireNonNull(key, "TOML documents prohibit null keys!");
		Objects.requireNonNull(value, "TOML documents prohibit null values!");

		Entry existing = getOrCreateEntry(key);
		return existing.setValue(value);
	}

	public TomlElement put(String key, TomlElement value, String comment, boolean commentIsInline) throws Exception {
		Objects.requireNonNull(key, "TOML documents prohibit null keys!");
		Objects.requireNonNull(value, "TOML documents prohibit null values!");

		if (inline) throw new Exception("Inline table entries can't have comments!");

		Entry existing = getOrCreateEntry(key);

		if (comment != null) {
			if (commentIsInline) existing.setInlineComment(comment);
			else existing.setAboveComment(comment);
		}

		return existing.setValue(value);
	}

	public TomlElement put(String key, TomlElement value, String aboveComment, String inlineComment) throws Exception {
		Objects.requireNonNull(key, "TOML documents prohibit null keys!");
		Objects.requireNonNull(value, "TOML documents prohibit null values!");

		if (inline) throw new Exception("Inline table entries can't have comments!");

		Entry existing = getOrCreateEntry(key);

		if (aboveComment != null) existing.setAboveComment(aboveComment);
		if (inlineComment != null) existing.setInlineComment(inlineComment);

		return existing.setValue(value);
	}

	public void setAboveComment(String key, String aboveComment) {
		if (containsKey(key)) {
			Entry existing = getOrCreateEntry(key);

			if (aboveComment != null) existing.setAboveComment(aboveComment);
		}
	}

	public void setInlineComment(String key, String inlineComment) {
		if (containsKey(key)) {
			Entry existing = getOrCreateEntry(key);

			if (inlineComment != null) existing.setInlineComment(inlineComment);
		}
	}

	@Override
	public TomlElement remove(Object key) {
		Objects.requireNonNull(key, "TOML documents prohibit null keys!");

		Entry toRemove = null;
		TomlElement value = null;

		for (Entry entry: entries) {
			if (entry.key == key) {
				toRemove = entry;
				value = entry.value;
			}
		}

		if (toRemove != null) entries.remove(toRemove);

		return value;
	}

	@Override
	public void putAll(Map<? extends String, ? extends TomlElement> m) {
		for (Map.Entry<? extends String, ? extends TomlElement> entry: m.entrySet()) {
			entries.add(new Entry(entry.getKey(), entry.getValue()));
		}
	}

	@Override
	public void clear() {
		entries.clear();
	}

	@Override
	public Set<String> keySet() {
		Set<String> keys = new HashSet<>();

		for (Entry entry: entries) {
			keys.add(entry.key);
		}

		return keys;
	}

	@Override
	public Collection<TomlElement> values() {
		Collection<TomlElement> values = new HashSet<>();

		for (Entry entry: entries) {
			values.add(entry.value);
		}

		return values;
	}

	@Override
	public Set<Map.Entry<String, TomlElement>> entrySet() {
		return new HashSet<>(entries);
	}

	private Entry getOrCreateEntry(String key) {
		for (Entry entry: entries) {
			if (key.equals(entry.key)) return entry;
		}

		return new Entry(key);
	}

	private static class Entry implements Map.Entry<String, TomlElement> {
		private final String key;
		private String aboveComment;
		private String inlineComment;
		private TomlElement value;

		private Entry(String key) {
			this.key = key;
		}

		private Entry(String key, TomlElement value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public String getKey() {
			return key;
		}

		@Override
		public TomlElement getValue() {
			return value;
		}

		public TomlElement setValue(TomlElement element) {
			TomlElement oldValue = value;
			value = element;
			return oldValue;
		}

		public String getAboveComment() {
			return aboveComment;
		}

		public void setAboveComment(String aboveComment) {
			this.aboveComment = aboveComment;
		}

		public String getInlineComment() {
			return inlineComment;
		}

		public void setInlineComment(String inlineComment) {
			this.inlineComment = inlineComment;
		}
	}
}
