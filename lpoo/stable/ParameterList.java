package lpoo.stable;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luís Eduardo Lopes dos Santos
 * Otávio Ferreira Augusto
 * Guilherme Escobar
 */
public final class ParameterList{
  private List<Type> parameters = new ArrayList<>();

  // O método permite ir adicionando os tipos dos parâmetros
  public void  addParameter(Type type) {
    this.parameters.add(type);
  }

  public List<Type> getParameters() {
    return this.parameters;
  }
} // ParameterList
