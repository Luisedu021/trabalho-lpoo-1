package lpoo.stable;

import java.util.TreeMap;

public class Block {
    private Block parentBlock; // Referência para o bloco de fora (se existir)
    private TreeMap<String, Variable> localVariables;

    // Construtor
    public Block(Block parentBlock) {
        this.parentBlock = parentBlock;
        this.localVariables = new TreeMap<>();
    }

    // Adiciona variável local ao bloco
    public void addVariable(Variable novaVariavel) throws DuplicateSymbolException {
        if (this.localVariables.containsKey(novaVariavel.getName())) {
            throw new DuplicateSymbolException("Erro semântico: Variável de bloco duplicada.");
        }
        this.localVariables.put(novaVariavel.getName(), novaVariavel);
    }

    // Procura variável apenas neste bloco específico
    public Variable findVariable(String name) {
        return this.localVariables.get(name);
    }

    // Método para a SymbolTable conseguir voltar ao bloco pai
    public Block getParentBlock() {
        return this.parentBlock;
    }
}