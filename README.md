# Trabalho-de-LPOO-1
**Project focused in make an symbols table to the the subject of LPOO**

## 👥 Autores
* Luís Eduardo Lopes dos Santos
* Otávio Ferreira Augusto
* Guilherme Escobar

---

## Atividades Feitas

### Modelagem de Símbolos
* Criação de classes para representar os símbolos: `Class`, `Method`, `Construtor`, `Variable` e `Type`.
* Implementação de suporte a modificadores: `public`, `private`, `protected`, `static`, `abstract` e `final`.

### Gerenciamento de Escopos
* Implementação de escopo global com o `GlobalScope` para guardar as classes.
* Implementação de escopo da classe ativa, método ativo e blocos locais internos aninhados por árvore AVL.
* Métodos de `openClass`/`closeClass`, `openMethod`/`closeMethod` e `openBlock`/`closeBlock` funcionando perfeitamente.

### Mecanismo de busca
* Procura de variáveis com busca ascendente: vai de bloco local  para blocos pais para método atual para
classe atual para superclasse(Conceito de herança)
  * Implementação e validação de busca por nome qualificado,
  capaz de isolar o escopo da classe qualificadora e buscar o atributo

### Classe Principal e Testes(Main):
*Arquivo Main.java implementando uma sequência de testes que valida
com sucesso todas as buscas nos escopos, herança e nomes qualificados,
retornando sucesso ("Achou!") e finalizando com Exit Code 0.
---

## 🔗 Link do Vídeo
* **Link:** 