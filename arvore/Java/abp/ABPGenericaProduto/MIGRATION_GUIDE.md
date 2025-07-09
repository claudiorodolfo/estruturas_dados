# Guia de Migração: Projeto Original → Maven

Este documento descreve as principais diferenças entre o projeto original da ABP Genérica Produto e sua versão Maven.

## Estrutura de Diretórios

### Projeto Original
```
ABPGenericaProduto/
├── bin/                    # Classes compiladas
├── build/                  # JARs gerados
├── doc/                    # Documentação JavaDoc
├── lib/                    # Bibliotecas externas
├── src/                    # Código fonte
├── test/                   # Testes
└── readme                  # Instruções manuais
```

### Projeto Maven
```
ABPGenericaProduto/
├── pom.xml                 # Configuração Maven
├── README.md               # Documentação
├── MIGRATION_GUIDE.md      # Este guia
├── .gitignore              # Arquivos ignorados pelo Git
├── src/
│   ├── main/
│   │   ├── java/           # Código fonte principal
│   │   └── resources/      # Recursos da aplicação
│   └── test/
│       ├── java/           # Testes unitários
│       └── resources/      # Recursos para testes
└── target/                 # Arquivos gerados (ignorado pelo Git)
```

## Comandos Equivalentes

### Compilação

**Original:**
```bash
javac src/*.java -d bin
```

**Maven:**
```bash
mvn compile
```

### Execução

**Original:**
```bash
java -cp .;bin ABPProdutoMainCLI
```

**Maven:**
```bash
mvn exec:java
```

### Testes

**Original:**
```bash
javac -cp .;bin;lib/junit-4.13.2.jar -d test test/ABPProdutoTest.java
java -cp .;bin;test;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar org.junit.runner.JUnitCore ABPProdutoTest
```

**Maven:**
```bash
mvn test
```

### Geração de JAR

**Original:**
```bash
jar cvfe build/Main.jar ABPProdutoMainCLI -C bin .
```

**Maven:**
```bash
mvn package
```

### Documentação

**Original:**
```bash
javadoc -d doc src/*.java
```

**Maven:**
```bash
mvn javadoc:javadoc
```

## Vantagens do Maven

1. **Gerenciamento de Dependências**: As bibliotecas JUnit e Hamcrest são baixadas automaticamente
2. **Padrão de Projeto**: Estrutura padronizada seguindo convenções Maven
3. **Automação**: Comandos simplificados para compilação, teste e empacotamento
4. **Integração**: Fácil integração com IDEs e ferramentas de CI/CD
5. **Reprodutibilidade**: Builds consistentes em diferentes ambientes

## Migração de Dependências

### Bibliotecas Externas

**Original:** JARs manuais em `lib/`
- `junit-4.13.2.jar`
- `hamcrest-core-1.3.jar`

**Maven:** Dependências declaradas no `pom.xml`
```xml
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.13.2</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.hamcrest</groupId>
    <artifactId>hamcrest-core</artifactId>
    <version>1.3</version>
    <scope>test</scope>
</dependency>
```

## Configurações Específicas

### Versão do Java
- **Original:** Dependia da versão do Java instalada
- **Maven:** Configurado para Java 11 no `pom.xml`

### Classpath
- **Original:** Configuração manual com separadores específicos do SO (`;` no Windows, `:` no Linux)
- **Maven:** Gerenciamento automático do classpath

### Plugins Configurados

1. **maven-compiler-plugin**: Compilação Java 11
2. **maven-surefire-plugin**: Execução de testes
3. **maven-jar-plugin**: Geração de JAR executável
4. **maven-javadoc-plugin**: Geração de documentação
5. **exec-maven-plugin**: Execução da aplicação

## Diferenças Específicas do Projeto Produto

### Classe Principal
- **Original:** `ABPProdutoMainCLI`
- **Maven:** `ABPProdutoMainCLI` (mantido)

### Classe de Dados
- **Original:** `Produto` com validações de código de barras
- **Maven:** `Produto` com validações mantidas

### Funcionalidades Específicas
- **Busca por código de barras**: Implementada na classe principal
- **Validação de entrada**: Tratamento de erros para códigos inválidos
- **Interface específica**: Menu adaptado para operações com produtos

## Próximos Passos

Para migrar outros projetos do repositório para Maven:

1. Criar estrutura de diretórios Maven
2. Configurar `pom.xml` com dependências apropriadas
3. Mover código fonte para `src/main/java`
4. Mover testes para `src/test/java`
5. Configurar plugins necessários
6. Testar compilação e execução
7. Documentar mudanças 