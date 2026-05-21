package lpoo.stable;
import java.util.*;

public class Variable {
    private String name;
    private Type type; // tipo de var
    private List<String> modifiers = new ArrayList<>();
    public void addModifier(String modifier) {
        this.modifiers.add(modifier);
    }

    public List<String> getModifiers() {
        return this.modifiers;
    }

    public Variable(String name, Type type) {
        this.name = name;
        this.type = type;
    }

        public String getName(){
            return this.name;}


    public Type getType() {
        return this.type;
    }
}
