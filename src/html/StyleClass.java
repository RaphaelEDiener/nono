package src.html;

public sealed interface StyleClass
permits DivStyles {
    String className();
    String classDef();
    String style();
}
