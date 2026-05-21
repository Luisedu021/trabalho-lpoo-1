import lpoo.stable.*;

public final class Main {
    public static void main(String[] args) {
        try {
            SymbolTable st = new SymbolTable();

            //tipos básicos que as variáveis vão precisar
            Type intType = new Type("int");
            Type stringType = new Type("String");

            System.out.println("Adicionando String...");
            st.addClass("String");

            System.out.println("Adicionando e abrindo Test...");
            lpoo.stable.Class clazz = st.addClass("Test");
            st.openClass(clazz);

            //TESTE DE ATRIBUTO DE CLASSE
            //adicionando uma variável direto na classe
            System.out.println("Adicionando atributo de classe 'instanciaId'...");
            Variable attrId = new Variable("instanciaId", intType);
            st.addVariable(attrId);

            System.out.println("Adicionando e abrindo método main...");
            ParameterList params = new ParameterList();
            Method method = st.addMethod("main", params);
            st.openMethod(method);

            //TESTE DE VARIÁVEL LOCAL DE MÉTODO
            //adicionando uma variável local no método main
            System.out.println("Adicionando variável local 'x' no método...");
            Variable varX = new Variable("x", intType);
            st.addVariable(varX);

            //TESTE DEW ESCOPO DE BLOCO EX: UM IF OU WHILE
            System.out.println("Abrindo um bloco local interno...");
            st.openBlock();

            System.out.println("Adicionando variável local 'y' no bloco...");
            Variable varY = new Variable("y", stringType);
            st.addVariable(varY);

            //EXECUTANDO AS BUSCAS (validando a busca ascendente)
            System.out.println("\n--- Realizando buscas na Tabela de Símbolos ---");

            //Procura 'y' (deve achar no bloco atual)
            Variable findY = st.findVariable("y");
            System.out.println("Busca por 'y' (escopo do bloco): " + (findY != null ? "Achou! Tipo: " + findY.getType().getTypeName() : "Não achou"));

            //Procura 'x' (deve achar no bloco atual)
            Variable findX = st.findVariable("x");
            System.out.println("Busca por 'x' (escopo do método pai): " + (findX != null ? "Achou! Tipo: " + findX.getType().getTypeName() : "Não achou"));

            //Procura 'instanciaId' (nao está no método, deve subir até a classe)
            Variable findAttr = st.findVariable("instanciaId");
            System.out.println("Busca por 'instanciaId' (atributo da classe): " + (findAttr != null ? "Achou! Tipo: " + findAttr.getType().getTypeName() : "Não achou"));

            System.out.println("\nFechando o bloco local...");
            st.closeBlock();

            //Fim do método
            st.closeMethod();

            //Fim da classe Test
            System.out.println("Fechando a classe Test...");
            st.closeClass();

            //Teste de herança (busca ascendente na superclasse)
            System.out.println("\n--- Testando Herança ---");
            System.out.println("Criando classe SubTest que herda de Test...");
            lpoo.stable.Class clazzSub = st.addClass("SubTest");
            clazzSub.setSuperClass(clazz); // Define Test como superclasse de SubTest

            System.out.println("Abrindo a classe SubTest...");
            st.openClass(clazzSub);

            // Tenta buscar o atributo 'instanciaId' de dentro da subclasse (deve achar na mãe)
            Variable findHerdado = st.findVariable("instanciaId");
            System.out.println("Busca por 'instanciaId' na SubTest (herdado da Test): " + (findHerdado != null ? "Achou com sucesso!" : "Não achou"));

            st.closeClass();

            System.out.println("\nTeste concluido com sucesso! Classes adicionadas na Tabela.");

            System.out.println("\n--- Testando Nome Qualificado ---");


            Variable varQualificada = st.findVariable("Test.instanciaId");
            System.out.println("Busca qualificada 'Test.instanciaId': " +
                    (varQualificada != null ? " Tipo: " + varQualificada.getType().getTypeName() : "Não achou"));

        } catch (DuplicateSymbolException e) {
            System.out.println("Erro semântico: Símbolo Duplicado!");
        }
    }
}