# Check Point 4 Domain Driven Design

## Gabarito Perguntas - 1 a 12:

### Aluno: Enzo Amá Fatobene RM: 562138

**1)** O que é uma **Entidade** em DDD?

a) Uma classe que só guarda dados, sem comportamento
b) Um objeto que possui identidade única (um "id") que se mantém ao longo do tempo, mesmo que seus outros atributos mudem
c) Uma tabela do banco de dados
d) Um método estático usado para criar objetos

Resposta: b)
---

**2)** Qual a principal diferença entre uma **Entidade** e um **Value Object** (Objeto de Valor)?

a) Value Object tem id único, Entidade não
b) Não existe diferença, são sinônimos
c) Entidade é definida pela sua identidade; Value Object é definido pelos seus atributos/valores (dois Value Objects com os mesmos valores são considerados iguais)
d) Value Object só pode existir dentro de um banco relacional

Resposta: c)
--- 

**3)** O que é um **Repositório** (Repository) em DDD?

a) Uma pasta do Git onde o código fica salvo
b) Uma abstração que permite ao domínio buscar e salvar entidades sem saber os detalhes de como os dados são persistidos (banco, arquivo, memória etc.)
c) O nome da tabela principal do banco de dados
d) Uma classe que contém apenas métodos `main`

Resposta: b)
---

**4)** Na estrutura de pastas usada na aula (`dominio`, `infraestrutura`, `aplicacao`), por que a classe `Cliente.java` não tem nenhuma linha de SQL?

a) Porque SQL não funciona em Java
b) Porque o domínio deve conter as regras de negócio, independente de como os dados são armazenados — isso é responsabilidade da infraestrutura
c) Porque esqueceram de escrever
d) Porque `Cliente` não é usado no banco de dados

Resposta: b)
---

**5)** O que é um **Agregado** (Aggregate) em DDD?

a) Um conjunto de entidades e value objects tratados como uma unidade única, com uma "raiz" (Aggregate Root) responsável por garantir as regras de consistência do grupo
b) Uma função de agregação do SQL, como `SUM()` ou `COUNT()`
c) Um tipo de índice de banco de dados
d) Um padrão de projeto exclusivo do Java

Resposta: a)
---

**6)** Por que em DDD normalmente definimos `ClienteRepositorio` como uma **interface**, deixando a implementação real (com MySQL, por exemplo) em outra classe?

a) É só um costume sem motivo real
b) Interfaces são obrigatórias em Java
c) Para permitir trocar a forma de persistência (MySQL, PostgreSQL, banco em memória para testes) sem alterar o restante do sistema
d) Porque classes concretas não podem ter métodos

Resposta: c)
---

## Parte 2 — Coleções em Java (List, Set, Map, Array/ArrayList, LinkedHashSet)

**7)** Qual a principal diferença entre um **Array** e um **ArrayList** em Java?

a) Array tem tamanho fixo definido na criação; ArrayList pode crescer ou diminuir dinamicamente
b) ArrayList só aceita números
c) Array é mais rápido em qualquer situação e por isso deve ser sempre preferido
d) Não existe diferença

Resposta: a)
---

**8)** Qual estrutura você usaria para armazenar uma lista de nomes de clientes, permitindo repetições e mantendo a ordem de inserção?

a) `Set<String>`
b) `Map<String, String>`
c) `List<String>` (por exemplo, `ArrayList<String>`)
d) `HashSet<String>`

Resposta: c)
---

**9)** O que caracteriza um **Set** em Java (por exemplo, `HashSet`)?

a) Permite elementos duplicados
b) Não permite elementos duplicados
c) Armazena pares chave-valor
d) Só pode conter números inteiros

Resposta: b)
---

**10)** Qual a diferença entre `HashSet` e `LinkedHashSet`?

a) `LinkedHashSet` não existe em Java
b) Ambos são idênticos em todos os aspectos
c) `HashSet` não garante ordem de iteração; `LinkedHashSet` mantém a ordem de inserção dos elementos
d) `HashSet` permite duplicados e `LinkedHashSet` não

Resposta: c)
---

**11)** Para representar a relação "um cliente tem um e-mail único associado ao seu id", qual estrutura combina melhor com um cenário de busca rápida por id?

a) `List<Cliente>`
b) `Map<Integer, Cliente>` (id do cliente como chave)
c) `Set<Cliente>`
d) `int[] clientes`

Resposta: b)
---

**12)** No trecho abaixo, o que acontece ao tentar adicionar `"ana@exemplo.com"` duas vezes num `HashSet<String>`?

```java
Set<String> emails = new HashSet<>();
emails.add("ana@exemplo.com");
emails.add("ana@exemplo.com");
System.out.println(emails.size());
```

a) Imprime `2`, porque HashSet permite duplicados
b) Imprime `1`, porque HashSet ignora a segunda inserção de um valor já existente
c) Lança uma exceção
d) Não compila

Resposta: b)

1. Por que usamos `Optional<Produto>` no método `buscarPorId` em vez de simplesmente retornar `Produto` (que poderia ser `null`)?

Para evitarmos uma NullpointerException ou que nosso objeto seja nulo de qualquer forma, afinal de contas, o método de busca por ID não necessáriamente encontrará um produto, assim, idealmente temos que lidar com essa possível exceção.

2. Por que `listarCategorias()` retorna um `Set` e não uma `List`?

Pois categorias devem ser únicas, no sentido de que não podem existir duas categorias "VERDURA" por exemplo, logo, para contornar o problema, usamos SET, uma estrutura de dados que não permite a inserção de objetos iguais.

3. Se no futuro quisermos trocar `ProdutoRepositorioMemoria` por uma versão que usa MySQL (como fizemos na aula com `Cliente`), o que precisa mudar na classe `Main`? E no `Produto`?

Na main, mudaria apenas a declaração do objeto de persistência, o `ProdutoRepositorioMemoria`, afinal de contas, o repository já assumi essa função mais esclável. Enquanto o Produto, mudaria para ser a representação de uma tabela no banco de dados, com as verificações das regras de negócio mais dedicadas as outras camadas da aplicação.

4. Qual estrutura de coleção vocês usaram dentro de `ProdutoRepositorioMemoria` para guardar os produtos, e por quê?

HashMap, pois é mais fácil é difundido guardar essas informações em uma lógica de chave, valor. Pois a consulta, inserção, atualização e elimição de dados ocorrem de maneira mais eficiente (O(1)).