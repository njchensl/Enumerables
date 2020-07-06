package me.nanjingchj.test.math.enumerability;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

@FunctionalInterface
public interface ImmutableStreamSet<E> extends Set<E> {
    @Override
    Stream<E> stream();

    @Override
    default int size() {
        return (int) stream().limit(Integer.MAX_VALUE).count();
    }
    @Override
    default boolean contains(Object o) {
        return stream().anyMatch(e -> Objects.equals(e, o));
    }
    @Override
    default boolean containsAll(Collection<?> c) {
        return this == c || c.stream().allMatch(this::contains);
    }
    @Override
    default boolean isEmpty() {
        return stream().findAny().isEmpty();
    }
    @Override
    default <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }
    @Override
    default Object[] toArray() {
        //noinspection SimplifyStreamApiCallChains
        return stream().toArray();
    }
    @Override
    default Spliterator<E> spliterator() {
        return stream().spliterator();
    }
    @Override
    default Iterator<E> iterator() {
        return stream().iterator();
    }
    @Override
    default Stream<E> parallelStream() {
        return stream().parallel();
    }
    @Override
    default void forEach(Consumer<? super E> action) {
        //noinspection SimplifyStreamApiCallChains
        stream().forEach(action);
    }
    // We are immutable
    @Override
    default boolean removeIf(Predicate<? super E> filter) {
        throw new UnsupportedOperationException();
    }
    @Override
    default void clear() {
        throw new UnsupportedOperationException();
    }
    @Override
    default boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }
    @Override
    default boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }
    @Override
    default boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }
    @Override
    default boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }
    @Override
    default boolean add(E e) {
        throw new UnsupportedOperationException();
    }
}