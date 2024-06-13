enum Keys {
    UP,
    DOWN,
    LEFT,
    RIGHT,
    CONFIRM,
    BACK,
    MARK,
    DELETE,

    public static Option<Key> from_console_in(final int in) {
        return switch (in) {
            case 107 -> Optional.of(Keys.UP);
            case  65 -> Optional.of(Keys.UP);
            case 106 -> Optional.of(Keys.DOWN);
            case  66 -> Optional.of(Keys.DOWN);
            case 104 -> Optional.of(Keys.LEFT);
            case  68 -> Optional.of(Keys.LEFT);
            case 108 -> Optional.of(Keys.RIGHT);
            case  67 -> Optional.of(Keys.RIGHT);
            case  10 -> Optional.of(Keys.CONFIRM);
            case 127 -> Optional.of(Keys.BACK);
            case  32 -> Optional.of(Keys.MARK);
            case 102 -> Optional.of(Keys.DELETE);
            default -> Optional.empty();
        }
    }
}
