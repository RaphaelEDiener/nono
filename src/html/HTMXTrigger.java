package src.html;

public enum HTMXTrigger{
    KeyDown ("keydown[key=='ArrowDown']"),
    KeyLeft ("keydown[key=='ArrowLeft']"),
    KeyRight("keydown[key=='ArrowRight']"),
    KeyUp("keydown[key=='ArrowUp']"),
    KeyEnter("keydown[key=='Enter']"),
    KeySpace("keydown[key==' ']"),
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
