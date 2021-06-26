package io.github.redstoneparadox.tomlfrappe.document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

public class TomlArray extends TomlElement implements List<TomlElement> {
	private final List<TomlElement> entries = new ArrayList<>();

	@Override
	public int size() {
		return entries.size();
	}

	@Override
	public boolean isEmpty() {
		return entries.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		Objects.requireNonNull(o, "TOML documents prohibit null values!");
		if (!(o instanceof TomlElement)) throw new ClassCastException();

		return entries.contains(o);
	}

	@Override
	public Iterator<TomlElement> iterator() {
		return entries.iterator();
	}

	@Override
	public Object[] toArray() {
		return entries.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return entries.toArray(a);
	}

	@Override
	public boolean add(TomlElement tomlElement) {
		return entries.add(tomlElement);
	}

	@Override
	public boolean remove(Object o) {
		Objects.requireNonNull(o, "TOML documents prohibit null values!");
		if (!(o instanceof TomlElement)) throw new ClassCastException();

		return entries.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object o: c) {
			Objects.requireNonNull(o, "TOML documents prohibit null values!");
			if (!(o instanceof TomlElement)) throw new ClassCastException();
		}

		return entries.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends TomlElement> c) {
		for (Object o: c) {
			Objects.requireNonNull(o, "TOML documents prohibit null values!");
		}

		return entries.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends TomlElement> c) {
		for (Object o: c) {
			Objects.requireNonNull(o, "TOML documents prohibit null values!");
		}

		return entries.addAll(index, c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		for (Object o: c) {
			Objects.requireNonNull(o, "TOML documents prohibit null values!");
			if (!(o instanceof TomlElement)) throw new ClassCastException();
		}

		return entries.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		for (Object o: c) {
			Objects.requireNonNull(o, "TOML documents prohibit null values!");
			if (!(o instanceof TomlElement)) throw new ClassCastException();
		}

		return entries.retainAll(c);
	}

	@Override
	public void clear() {
		entries.clear();
	}

	@Override
	public TomlElement get(int index) {
		return entries.get(index);
	}

	@Override
	public TomlElement set(int index, TomlElement element) {
		Objects.requireNonNull(element, "TOML documents prohibit null values!");

		return entries.set(index, element);
	}

	@Override
	public void add(int index, TomlElement element) {
		Objects.requireNonNull(element, "TOML documents prohibit null values!");

		entries.add(index, element);
	}

	@Override
	public TomlElement remove(int index) {
		return entries.remove(index);
	}

	@Override
	public int indexOf(Object o) {
		Objects.requireNonNull(o, "TOML documents prohibit null values!");
		if (!(o instanceof TomlElement)) throw new ClassCastException();

		return entries.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		Objects.requireNonNull(o, "TOML documents prohibit null values!");
		if (!(o instanceof TomlElement)) throw new ClassCastException();

		return entries.lastIndexOf(0);
	}

	@Override
	public ListIterator<TomlElement> listIterator() {
		return entries.listIterator();
	}

	@Override
	public ListIterator<TomlElement> listIterator(int index) {
		return entries.listIterator(index);
	}

	@Override
	public List<TomlElement> subList(int fromIndex, int toIndex) {
		return entries.subList(fromIndex, toIndex);
	}
}
