package src.html;

public enum HTMXTrigger{
    // KeyDown ("keydown[key=='s']"),
    KeyDown ("keydown[key=='ArrowDown']"),
    KeyLeft ("keydown[key=='ArrowLeft]"),
    KeyRight("keydown[key==39]"),
    KeyUp("keydown[key==38]"),
    Click("click");

    private final String desc;

    HTMXTrigger(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return this.desc;
    }
}
