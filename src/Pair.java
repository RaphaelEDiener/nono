package src;

final class Pair<T,U> {
    final T first;
    final U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    Pair(final Pair<T,U> old) {
        return new Pair<T,U>(old.first.clone(), old.second.clone());
    }
}
