package lpoo.stable;

import java.util.*;

/**
 *
 * @author Luís Eduardo Lopes dos Santos
 * Otávio Ferreira Augusto
 * Guilherme Escobar
 */
public class GlobalScope
{

  private TreeMap<String, Class> classes;

  public GlobalScope(){
    this.classes = new TreeMap<>();
  }
  public void addClass(Class novaClasse) throws DuplicateSymbolException {

    if(this.classes.containsKey(novaClasse.getName())){
      //se existir
      throw new DuplicateSymbolException();
    }
    //se não existir,coloca na avl
  this.classes.put(novaClasse.getName(), novaClasse);

}
//Método p/ achar classe pelo nome

  public Class findClass(String name) {
    return this.classes.get(name);

  }

  
}; // GlobalScope
