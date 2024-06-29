package src.html;

public class Color {

    public static Color TRANSPARENT = new Color(0,0,0,0);
    public static Color BLACK = new Color(0,0,0,1);
    public static Color WHITE = new Color(255,255,255,1);

    public final String hex;
    public final int r;
    public final int g;
    public final int b;
    public final double a;

    public Color(int r, int g, int b, double a) {
        assert r >= 0 && r <= 255 : "r must be 0 <= r <= 255";
        assert g >= 0 && g <= 255 : "g must be 0 <= g <= 255";
        assert b >= 0 && b <= 255 : "b must be 0 <= b <= 255";
        assert a >= 0 && a <= 1   : "a must be 0 <= a <= 1";
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
        this.hex = "#"
                + String.format("%02x", r)
                + String.format("%02x", g)
                + String.format("%02x", b);
    }

    public Color(String hex){
        assert hex.length() == 7    : "hex string must be of form #rrggbb";
        assert hex.charAt(0) == '#' : "hex string must be of form #rrggbb";
        hex = hex.substring(1);

        String redComponent = hex.substring(0, 2);
        this.r = Integer.parseInt(redComponent, 16);
        String greenComponent = hex.substring(2, 4);
        this.g = Integer.parseInt(greenComponent, 16);
        String blueComponent = hex.substring(4, 6);
        this.b = Integer.parseInt(blueComponent, 16);
        this.a = 1.0;

        this.hex = hex;
    }

    public String rgba() {
        return "rgba("
                + this.r + ", "
                + this.g + ", "
                + this.b + ", "
                + String.format("%.2f", this.a)
                + ")";
    }

    public Color r(int r){
        assert r >= 0 && r <= 255 : "r must be 0 <= r <= 255";
        return new Color(r, this.g, this.b, this.a);
    }
    public Color g(int g){
        assert g >= 0 && g <= 255 : "g must be 0 <= g <= 255";
        return new Color(this.r, g, this.b, this.a);
    }
    public Color b(int b){
        assert b >= 0 && b <= 255 : "b must be 0 <= b <= 255";
        return new Color(this.r, this.g, b, this.a);
    }
    public Color a(double a){
        assert a >= 0 && a <= 1   : "a must be 0 <= a <= 1";
        return new Color(this.r, this.g, this.b, a);
    }
}
