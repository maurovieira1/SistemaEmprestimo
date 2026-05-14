#  LabManager Pro — Sistema Inteligente de Gestão e Inventário para Laboratórios

> Sistema desktop desenvolvido em Java com foco em automação de laboratórios, persistência real de dados e arquitetura profissional utilizando MySQL + JDBC.

---

#  Sobre o Projeto

O **LabManager Pro** foi desenvolvido para resolver um problema comum em laboratórios acadêmicos: a falta de organização no cadastro de usuários e no controle de equipamentos.

O sistema permite:
-  Cadastro de alunos
-  Controle de estoque de equipamentos
-  Gerenciamento de disponibilidade
-  Atualização dinâmica em tempo real
-  Persistência real no banco de dados MySQL

Diferente de projetos básicos CRUD, este sistema foi estruturado utilizando conceitos profissionais de engenharia de software, arquitetura em camadas e sincronização automática entre interface e banco de dados.

---

#  Fluxo Inteligente do Sistema

O projeto foi pensado com um fluxo contínuo e guiado, simulando aplicações reais de mercado.

##  1. Cadastro de Alunos
O usuário inicia no `FormularioAluno`, responsável pela entrada e validação dos dados.

###  Recursos
- Validação de campos
- Persistência automática
- Integração com banco de dados
- Tratamento de entradas inválidas

---

##  2. Visualização Instantânea
Após o cadastro, o sistema redireciona automaticamente para a `TelaListaAlunos`.

###  Recursos
- Exibição dinâmica com `JTable`
- Atualização automática
- Confirmação visual da persistência no banco

---

##  3. Gestão Completa de Inventário
Na `TelaLaboratorio`, ocorre o gerenciamento dos equipamentos.

###  Recursos
- Controle de quantidade
- Alteração de disponibilidade
- Atualização em tempo real
- Sincronização automática com MySQL

---

#  Diferenciais Técnicos (Ponto Forte do Projeto)

##  JTable Inteligente com Atualização Automática

A `JTable` do sistema não é apenas visual.  
Ela funciona como uma planilha inteligente integrada diretamente ao banco de dados.

###  Implementação Técnica
Foi utilizado um `TableModelListener` para detectar alterações feitas pelo usuário diretamente nas células da tabela.

Quando uma quantidade é alterada:
1. O sistema identifica a mudança
2. Executa automaticamente um `UPDATE`
3. Sincroniza instantaneamente com o MySQL

###  Por que isso é avançado?
Muitos projetos acadêmicos utilizam botões separados para atualizar dados.  
Neste sistema, a atualização ocorre automaticamente, simulando comportamento de softwares profissionais de gestão.

###  Benefícios
- Melhor experiência do usuário
- Redução de cliques
- Atualização em tempo real
- Maior produtividade
- Integridade dos dados

---

##  Arquitetura Profissional com DAO

O projeto segue rigorosamente o padrão **DAO (Data Access Object)**.

###  Separação de Responsabilidades
O sistema foi dividido em camadas:
- `view/` → Interface gráfica
- `model/` → Entidades do sistema
- `dao/` → Regras SQL
- `database/` → Gerenciamento de conexões

###  Por que isso é importante?
Essa arquitetura desacopla totalmente a interface do banco de dados.

Ou seja:
- É possível trocar a interface desktop por Web futuramente
- A lógica de persistência continua funcionando
- O código fica mais organizado e reutilizável

###  Benefícios
- Manutenção facilitada
- Escalabilidade
- Código limpo
- Organização profissional

---

##  Persistência Real com JDBC + MySQL

O sistema trabalha com persistência real de dados.

Todas as ações realizadas pelo usuário são refletidas imediatamente no banco MySQL.

###  Recursos Técnicos Utilizados
- JDBC
- PreparedStatement
- ResultSet
- Connection
- Try-With-Resources

###  Diferencial Técnico
O uso de `try-with-resources` garante fechamento automático das conexões, evitando:
- Memory leaks
- Conexões ociosas
- Travamentos do banco

Esse é um padrão utilizado em aplicações profissionais.

---

#  Tecnologias Utilizadas

| Tecnologia | Função |
|---|---|
| Java JDK 17 | Linguagem principal |
| Java Swing | Interface gráfica |
| MySQL 8.0 | Banco de dados |
| JDBC | Comunicação com banco |
| Maven | Gerenciamento do projeto |

---

#  Estrutura do Projeto

```bash
src/
│
├── view/
│   ├── FormularioAluno.java
│   ├── TelaListaAlunos.java
│   └── TelaLaboratorio.java
│
├── dao/
│   ├── AlunoDAO.java
│   └── EquipamentoDAO.java
│
├── model/
│   ├── Aluno.java
│   └── Equipamento.java
│
├── database/
│   └── ConnectionFactory.java
│
└── Main.java
```

---

#  Banco de Dados

## Script SQL

```sql
CREATE DATABASE laboratorio_db;
USE laboratorio_db;

CREATE TABLE aluno (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    matricula VARCHAR(20) NOT NULL
);

CREATE TABLE equipamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    quantidade INT DEFAULT 1,
    disponivel TINYINT(1) DEFAULT 1
);
```

---

#  Como Executar

##  Clone o Projeto

```bash
git clone https://github.com/seu-usuario/labmanager-pro.git
```

---

##  Configure o Banco

Adicione usuário e senha do MySQL na `ConnectionFactory`.

---

##  Execute

### Via Maven

```bash
mvn clean install
mvn exec:java
```

### Ou pela IDE
- IntelliJ IDEA
- Eclipse
- NetBeans

---

#  Melhorias Futuras

- Sistema de login
- Controle de permissões
- Dashboard administrativo
- Relatórios PDF
- Histórico de movimentações
- Integração Web/API
- Dark Mode

---

#  Conceitos Aplicados

Este projeto aplica diversos conceitos importantes da Engenharia de Software:

- Programação Orientada a Objetos (POO)
- Arquitetura em Camadas
- DAO Pattern
- JDBC
- CRUD Completo
- Persistência de Dados
- Eventos com Swing
- Programação Reativa
- Clean Code

---

#  Autor

**Mauro Vieira**

Projeto acadêmico desenvolvido para demonstrar conhecimentos em:
- Desenvolvimento Desktop com Java
- Banco de Dados Relacional
- Arquitetura de Software
- Persistência de Dados
- Engenharia de Software
