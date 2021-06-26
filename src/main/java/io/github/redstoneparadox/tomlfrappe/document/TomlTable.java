package io.github.redstoneparadox.tomlfrappe.document;

import java.util.ArrayList;
import java.util.Collection;
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

		if (containsKey(key)) {
			Entry existing = getEntry(key);
			assert existing != null;
			TomlElement previous = existing.value;
			existing.value = value;

			return previous;
		}

		Entry entry = new Entry(key);
		entry.setValue(value);

		return null;
	}

	@Override
	public TomlElement remove(Object key) {
		Objects.requireNonNull(key, "TOML documents prohibit null keys!");

		Entry toRemove = null;
		TomlElement value = null;

		for (Entry entry: entries) {
			if (entry.key == key) {
				value = entry.value;
			}
		}

		if (toRemove != null) entries.remove(toRemove);

		return null;
	}

	@Override
	public void putAll(Map<? extends String, ? extends TomlElement> m) {

	}

	@Override
	public void clear() {

	}

	@Override
	public Set<String> keySet() {
		return null;
	}

	@Override
	public Collection<TomlElement> values() {
		return null;
	}

	@Override
	public Set<Map.Entry<String, TomlElement>> entrySet() {
		return null;
	}

	private Entry getEntry(String key) {
		for (Entry entry: entries) {
			if (key.equals(entry.key)) return entry;
		}

		return null;
	}

	private class Entry {
		private final String key;
		private String aboveComment;
		private String inlineComment;
		private TomlElement value;

		private Entry(String key) {
			this.key = key;
		}

		private TomlElement setValue(TomlElement element) {
			TomlElement oldValue = value;
			value = element;
			return oldValue;
		}
	}
}
