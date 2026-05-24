package lpoo.stable;

import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author Luís Eduardo Lopes dos Santos
 * Otávio Ferreira Augusto
 * Guilherme Escobar
 */
public class SymbolTable 
{

    private GlobalScope globalScope = new GlobalScope();
    private Class currentClass = null;
    private Method currentMethod = null;
    private Block currentBlock = null;

    public Class addClass(String name) throws DuplicateSymbolException {
        Class novaClasse = new Class(name);
        this.globalScope.addClass(novaClasse);
        return novaClasse;
    }

    public Class findClass(String name) {
        return this.globalScope.findClass(name);
    }

    public void openClass(Class clazz) {
        this.currentClass = clazz;
    }

    public void closeClass() {
        this.currentClass = null;
    }

    public Method addMethod(String name, ParameterList parameters) throws DuplicateSymbolException {
        Method novoMetodo = new Method(name, parameters);
        if(this.currentClass != null){
            this.currentClass.addMethod(novoMetodo);
        }
        return novoMetodo;
    }

    public List<Method> findMethods(String name, Class clazz) {
        List<Method> lista = new ArrayList<>();
        Class atual = clazz;

        while (atual != null) {
            Method m = atual.findMethod(name);
            if (m != null) {
                lista.add(m);
            }
            atual = atual.getSuperClass(); 
        }
        return lista;
    }

    public Method findMethod(String name, List<Type> argumentTypes, Class clazz) {
        Class atual = clazz;
        // Busca ascendente pelo método exato
        while (atual != null) {
            Method m = atual.findMethod(name);
            if (m != null) {
                return m;
            }
            atual = atual.getSuperClass();
        }
        return null;
    }

    public void openMethod(Method method) {
        this.currentMethod = method;
    }

    public void closeMethod() {
        this.currentMethod = null;
    }


    public void openBlock() {
        Block novoBloco = new Block(this.currentBlock);
        this.currentBlock = novoBloco;

    }

    public void closeBlock() {
        if (this.currentBlock != null) {
            this.currentBlock = this.currentBlock.getParentBlock();
        }
    }

    public void print() {
        System.out.println("--- TABELA DE SÍMBOLOS ---");
        if (currentClass != null) System.out.println("Classe ativa: " + currentClass.getName());
        if (currentMethod != null) System.out.println("Método ativo: " + currentMethod.getName());
    }

    // Metodo para o Main conseguir adicionar variáveis nos escopos corretos
    public void addVariable(Variable variable) throws DuplicateSymbolException {
        if (this.currentBlock != null) {
            this.currentBlock.addVariable(variable);
        }
        // Prioridade 2: Estamos num metodo (mas fora de blocos internos)?
        else if (this.currentMethod != null) {
            this.currentMethod.addVariable(variable);
        }
        // Prioridade 3: É um atributo de classe
        else if (this.currentClass != null) {
            this.currentClass.addAttribute(variable);
        }
    }



    public Variable findVariable(String name) {

        // --- 1. BUSCA POR NOME QUALIFICADO ---
        if (name.contains(".")) {
            String[] partes = name.split("\\.");
            String qualificador = partes[0];
            String nomeSimples = partes[1];

            Class classeQualificadora = this.findClass(qualificador);

            if (classeQualificadora != null) {
                return classeQualificadora.findAttribute(nomeSimples);
            }

            return null; // Se tinha ponto e não achou, a busca qualificada morre aqui.
        }

        // 2.A) Procura no bloco atual e sobe para os blocos pais
        Block blocoAtual = this.currentBlock;
        while (blocoAtual != null) {
            Variable v = blocoAtual.findVariable(name);
            if (v != null) return v;
            blocoAtual = blocoAtual.getParentBlock();
        }

        // 2.B) Procura no método corrente (variáveis locais e parâmetros)
        if (this.currentMethod != null) {
            Variable v = this.currentMethod.findVariable(name);
            if (v != null) return v;
        }

        // 2.C) Sobe para a classe corrente e vai subindo pelas superclasses
        Class atual = this.currentClass;
        while (atual != null) {
            Variable v = atual.findAttribute(name);
            if (v != null) return v;
            atual = atual.getSuperClass();
        }

        return null;
    }
}