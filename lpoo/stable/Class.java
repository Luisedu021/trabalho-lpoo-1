package lpoo.stable;
import java.util.*;

/**
 *
 * @author Luís Eduardo Lopes dos Santos
 * Otávio Ferreira Augusto
 * Guilherme Escobar
 */
public class Class
{
    private String name;
    private Class superClass;

    //fazendo o escopo interno da classe:uma arvore avl para guardar os métodos dela
    private TreeMap<String, Method > methods;

    //fazendo uma nova arv para guardar os atributos da classe(variaveis da variable.java)
    private TreeMap<String, Variable> attributes;

    // A lista fica declarada aqui, junto com os outros atributos da classe!
    private List<String> modifiers = new ArrayList<>();
    private List<Construtor> constructors = new ArrayList<>();
    // Construtor (Agora sem variáveis "presas" na mesma linha)
    public Class(String name){
        this.name = name;
        this.methods = new TreeMap<>();
        this.attributes = new TreeMap<>(); // ajuste para nao dar null pointer exception
    }

    //metodo para adicionar um metodo na árvore da classe
    public void addMethod(Method novoMetodo) throws DuplicateSymbolException{
        if(this.methods.containsKey(novoMetodo.getName())){
            throw new DuplicateSymbolException();
        }
        this.methods.put(novoMetodo.getName(),novoMetodo);
    }

    public Method findMethod(String name){
        return this.methods.get(name);
    }

    //metodos de modificadores
    public void addModifier(String modifier) {
        this.modifiers.add(modifier);
    }

    public List<String> getModifiers() {
        return this.modifiers;
    }

    //metodo p add atributo na classe
    public void addAttribute(Variable novaVariavel) throws DuplicateSymbolException{
        if(this.attributes.containsKey(novaVariavel.getName())){
            throw new DuplicateSymbolException();
        }
        this.attributes.put(novaVariavel.getName(), novaVariavel);
    }

    public void addConstructor(Construtor constructor) {
        this.constructors.add(constructor);
    }

    //metodo para procurar atributo em classe
    public Variable findAttribute(String name){
        return this.attributes.get(name);
    }

    // novos metódos adicionados para a symbol table a partir dessa linha

    //busca ascendente para conseguir subir para o pai
    public Class getSuperClass() {
        return this.superClass;
    }

    //para definir a herança na main
    public void setSuperClass(Class superClass) {
        this.superClass = superClass;
    }

    public String getName() {return this.name ; }

};