package lpoo.stable;

import java.util.*;

public class Construtor {
    private String name; // O nome do construtor é sempre igual ao da classe
    private ParameterList parameters;
    private TreeMap<String, Variable> localVariables;
    private List<String> modifiers = new ArrayList<>();

    public Construtor(String name, ParameterList parameters) {
        this.name = name;
        this.parameters = parameters;
        this.localVariables = new TreeMap<>();
    }

    public void addVariable(Variable novaVariavel) throws DuplicateSymbolException {
        if (this.localVariables.containsKey(novaVariavel.getName())) {
            throw new DuplicateSymbolException("Erro semântico: Variável local de construtor duplicada.");
        }
        this.localVariables.put(novaVariavel.getName(), novaVariavel);
    }

    public Variable findVariable(String name) {
        return this.localVariables.get(name);
    }

    public String getName() { return this.name; }
    public ParameterList getParameters() { return this.parameters; }

    public void addModifier(String modifier) { this.modifiers.add(modifier); }
    public List<String> getModifiers() { return this.modifiers; }
}