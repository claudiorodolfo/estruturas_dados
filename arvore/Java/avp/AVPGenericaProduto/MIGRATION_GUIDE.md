# Guia de Migração - AVPGenericaProduto para Maven

Este documento descreve as mudanças realizadas para converter o projeto AVPGenericaProduto de uma estrutura manual para uma estrutura Maven padronizada.

## 🔄 Mudanças Realizadas

### 1. Estrutura de Diretórios

**Antes:**
```
AVPGenericaProduto/
├── src/
│   ├── *.java
│   └── *.class
├── test/
│   └── *.java
├── bin/
├── build/
├── doc/
├── lib/
└── readme
```

**Depois:**
```
AVPGenericaProduto/
├── src/
│   ├── main/java/
│   │   └── *.java
│   └── test/java/
│       └── *.java
├── target/          # Gerado pelo Maven
├── pom.xml
├── .gitignore
├── README.md
└── MIGRATION_GUIDE.md
```

### 2. Arquivos Movidos

#### Código Fonte
- `src/Arborizavel.java` → `src/main/java/Arborizavel.java`
- `src/AVP.java` → `src/main/java/AVP.java`
- `src/AVPProdutoMainCLI.java` → `src/main/java/AVPProdutoMainCLI.java`
- `src/Cor.java` → `src/main/java/Cor.java`
- `src/NoTriplo.java` → `src/main/java/NoTriplo.java`
- `src/Produto.java` → `src/main/java/Produto.java`

#### Testes
- `test/AVPProdutoTest.java` → `src/test/java/AVPProdutoTest.java`

### 3. Arquivos Removidos

- Arquivos `.class` compilados
- Diretórios `bin/`, `build/`, `doc/`, `lib/` (legados)
- Arquivo `readme` (substituído por `README.md`)

### 4. Novos Arquivos Criados

#### `pom.xml`
Configuração Maven com:
- Java 25
- JUnit 4.13.2
- Hamcrest 1.3
- Plugins para compilação, testes, JAR e documentação

#### `.gitignore`
Exclui arquivos desnecessários do controle de versão:
- `target/` (build Maven)
- Arquivos de IDE
- Arquivos compilados
- Logs

#### `README.md`
Documentação completa do projeto com:
- Descrição da implementação
- Instruções de uso
- Documentação das classes
- Exemplos de comandos

## 🚀 Como Usar o Novo Projeto

### Compilação
```bash
# Antes
javac -cp . src/*.java

# Depois
mvn clean compile
```

### Execução de Testes
```bash
# Antes
javac -cp . test/*.java
java -cp .:junit-4.13.2.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore AVPProdutoTest

# Depois
mvn test
```

### Execução da Aplicação
```bash
# Antes
java -cp . AVPProdutoMainCLI

# Depois
mvn exec:java
```

### Geração de JAR
```bash
# Antes
jar cvf AVPGenericaProduto.jar src/*.class

# Depois
mvn package
java -jar target/avp-generica-produto-1.0.0.jar
```

## 📋 Benefícios da Migração

### 1. Gerenciamento de Dependências
- Dependências declaradas no `pom.xml`
- Resolução automática de dependências
- Controle de versões

### 2. Build Padronizado
- Comandos Maven padronizados
- Build reproduzível
- Integração com IDEs

### 3. Testes Automatizados
- Execução automática de testes
- Relatórios de cobertura
- Integração contínua

### 4. Documentação
- JavaDoc automático
- README estruturado
- Guias de migração

### 5. Controle de Versão
- `.gitignore` apropriado
- Estrutura limpa
- Arquivos desnecessários excluídos

## 🔧 Configurações Específicas

### Java 25
O projeto foi configurado para usar Java 25:
```xml
<maven.compiler.source>25</maven.compiler.source>
<maven.compiler.target>25</maven.compiler.target>
```

### JUnit 4.13.2
Mantida a versão do JUnit conforme solicitado:
```xml
<junit.version>4.13.2</junit.version>
```

### Plugins Maven
- **maven-compiler-plugin**: Compilação Java 25
- **maven-surefire-plugin**: Execução de testes
- **maven-jar-plugin**: Geração de JAR executável
- **maven-javadoc-plugin**: Documentação JavaDoc
- **exec-maven-plugin**: Execução da aplicação

## 🧪 Verificação da Migração

Para verificar se a migração foi bem-sucedida:

1. **Compilação**: `mvn clean compile`
2. **Testes**: `mvn test`
3. **Execução**: `mvn exec:java`
4. **JAR**: `mvn package`

Todos os comandos devem executar sem erros.

## 📚 Recursos Adicionais

- [Documentação Maven](https://maven.apache.org/guides/)
- [JUnit 4 User Guide](https://junit.org/junit4/)
- [Java 25 Documentation](https://docs.oracle.com/en/java/javase/25/)

---

**Nota**: Esta migração mantém toda a funcionalidade original do projeto, apenas reorganizando a estrutura para seguir as melhores práticas do Maven. 