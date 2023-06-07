# Projeto Final Desenvolvedor Java
Projeto Final do curso Desenvolvedor Java

O projeto é uma aplicação Spring Boot para um sistema de cadastro de produtos. Ele utiliza o Spring MVC para lidar com as requisições HTTP e o Spring Data JPA para a persistência dos dados no banco de dados MySQL.

Aqui está a lista com as tecnologias utilizadas para desenvolver o projeto:

- Java;
- Spring Boot;
- MySQL;
- HTML;
- CSS;
- Bootstrap;
- Thymeleaf;
- JavaScript.

Analise e documentação de cada parte do código.
# Arquivo pom.xml (Maven)
```java
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>3.1.0</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.crud</groupId>
	<artifactId>produto</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>produto</name>
	<description>Projeto Final do curso Dev.Java do Senai df. A proposta do meu projeto é criar uma "loja de roupas" onde sera feito o cadastro de produtos</description>
	<properties>
		<java.version>17</java.version>
	</properties>
	<dependencies>
		<!-- Dependências do projeto -->
	</dependencies>

	<build>
		<!-- Configurações de build -->
	</build>

</project>
```

O arquivo `POM.xml` é o arquivo de configuração do Maven, uma ferramenta de gerenciamento de projetos Java. Nele são especificadas as dependências, plugins e outras configurações do projeto. Neste caso, o arquivo contém o nome, a descrição e a versão do projeto, bem como a versão do Spring Boot utilizada. As dependências do projeto são especificadas dentro da seção `<dependencies>`.

# As depedencias utilizadas no projeto são as seguintes:
   ```
    org.springframework.boot:spring-boot-starter-data-jpa    
    org.springframework.boot:spring-boot-starter-thymeleaf
    org.springframework.boot:spring-boot-starter-web
    org.springframework.boot:spring-boot-devtools
    com.mysql:mysql-connector-j
    org.springframework.boot:spring-boot-starter-test
   ```
   
# Classe ProdutoApplication.java
```java
package com.crud.produto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableWebMvc
@ComponentScan(basePackages = "com.crud.produto")
public class ProdutoApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(ProdutoApplication.class, args);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/css/");

        registry.addResourceHandler("/img/**")
                .addResourceLocations("classpath:/static/img/");
    }
}
```
Esta classe representa a classe principal do projeto, que contém o método `main` para inicializar a aplicação Spring Boot. A anotação `@SpringBootApplication` é uma combinação das anotações `@Configuration`, `@EnableAutoConfiguration` e `@ComponentScan`, que habilitam as configurações do Spring Boot e o escaneamento de componentes.

A anotação `@EnableWebMvc` habilita a configuração do MVC (Model-View-Controller) no Spring. A anotação `@ComponentScan` especifica o pacote base a ser escaneado em busca de componentes.

A classe também implementa a interface `WebMvcConfigurer`, que fornece métodos para configurar recursos estáticos, como arquivos CSS e imagens. O método `addResourceHandlers` é sobrescrito para configurar os locais dos recursos estáticos no classpath.

# Classe DataConfig.java
```java
package com.crud.produto;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class DataConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/db_produto");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
        adapter.setPrepareConnection(true);
        return adapter;
    }
}

```
A classe `DataConfig` é uma classe de configuração do Spring responsável por configurar o acesso a dados e a integração com o banco de dados. Ela utiliza a anotação `@Configuration` para indicar que é uma classe de configuração.

O método `dataSource` cria e configura um `DriverManagerDataSource`, que é um tipo de `DataSource` que utiliza o driver JDBC do MySQL para conexão. Ele define a URL de conexão, o nome de usuário e a senha para acessar o banco de dados.

O método `jpaVendorAdapter` cria e configura um `HibernateJpaVendorAdapter`, que é um adaptador do Hibernate para o JPA (Java Persistence API). Ele define o banco de dados como MySQL, habilita a exibição das consultas SQL, gera automaticamente as instruções DDL, define o dialeto do MySQL e prepara a conexão para ser usada.

# Classe Produto.java
```java
package com.crud.produto.models;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Produto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigoProduto;
    private String descricao;
    private double preco;
    private int quantidade;
    private String categoria;
    private String tamanho;
    private String cor;
    private String genero;
    private String marca;
    private Date dataCadastro;
    private Date dataAtualizacao;

    // Métodos para cadastrar, atualizar e excluir produtos

    // Getters e setters
}

```
A classe `Produto` é uma entidade JPA mapeada para uma tabela do banco de dados. A anotação `@Entity` indica que a classe é uma entidade persistente. Os atributos da classe representam as colunas da tabela.

A anotação `@Id` especifica a chave primária da entidade, e a anotação `@GeneratedValue` indica que o valor dessa chave será gerado automaticamente pelo banco de dados.

A classe também implementa a interface `Serializable`, permitindo que os objetos da classe sejam serializados.

A classe possui os seguintes atributos:

- `codigoProduto`: representa o código do produto (chave primária).
- `descricao`: representa a descrição do produto.
- `preco`: representa o preço do produto.
- `quantidade`: representa a quantidade disponível do produto.
- `categoria`: representa a categoria do produto.
- `tamanho`: representa o tamanho do produto.
- `cor`: representa a cor do produto.
- `genero`: representa o gênero do produto.
- `marca`: representa a marca do produto.
- `dataCadastro`: representa a data de cadastro do produto.
- `dataAtualizacao`: representa a data de atualização do produto.

A classe possui os métodos `cadastrarProduto()`, `atualizarProduto()` e `excluirProduto()`, que são apenas declarações de método sem implementação.

# Interface ProdutoRepository.java

```java
package com.crud.produto.repository;

import org.springframework.data.repository.CrudRepository;

import com.crud.produto.models.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, String> {
    Produto findByCodigoProduto(Long codigoProduto);
    Produto deleteByCodigoProduto(Long codigoProduto);
}

```

A interface `ProdutoRepository` é uma interface do Spring Data JPA que estende a interface `CrudRepository`. Ela fornece operações de CRUD (create, read, update, delete) para a entidade `Produto`.

A interface possui dois métodos adicionais além dos métodos herdados:

- `findByCodigoProduto(Long codigoProduto)`: realiza uma consulta pelo código do produto e retorna o produto correspondente.
- `deleteByCodigoProduto(Long codigoProduto)`: exclui o produto correspondente ao código fornecido.

# Classe ProdutoController.java

```java
package com.crud.produto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.crud.produto.models.Produto;
import com.crud.produto.repository.ProdutoRepository;

@Controller
@EnableWebMvc
public class ProdutoController {

    @Autowired
    private ProdutoRepository pr;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/cadastrarProduto", method = RequestMethod.GET)
    public String cadastrarProduto() {
        return "content/cadastrar-produto";
    }

    @RequestMapping(value = "/cadastrarProduto", method = RequestMethod.POST)
    public String cadastrarProduto(@Validated Produto produto, BindingResult result, RedirectAttributes attributes) {
        pr.save(produto);
        return "redirect:/listarProdutos";
    }

    @RequestMapping("/listarProdutos")
    public ModelAndView listarProdutos() {
        ModelAndView mv = new ModelAndView("content/listar-produtos");
        Iterable<Produto> produtos = pr.findAll();
        mv.addObject("produtos", produtos);
        return mv;
    }

    @RequestMapping(value = "/alterarProduto/{codigoProduto}", method = RequestMethod.GET)
    public ModelAndView formAlterarProduto(@PathVariable("codigoProduto") Long codigoProduto) {
        Produto produto = pr.findByCodigoProduto(codigoProduto);
        ModelAndView mv = new ModelAndView("content/atualizar-produto");
        mv.addObject("produto", produto);
        return mv;
    }

    @RequestMapping(value = "/alterarProduto/{codigoProduto}", method = RequestMethod.POST)
    public String alterarProduto(@PathVariable("codigoProduto") Long codigoProduto, @Validated Produto produto,
            BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("mensagem", "Verifique os campos obrigatórios!");
            return "redirect:/alterarProduto/{codigoProduto}";
        }
        pr.save(produto);
        attributes.addFlashAttribute("mensagem", "Produto alterado com sucesso!");
        return "redirect:/listarProdutos";
    }

    @RequestMapping("/excluirProduto/{codigoProduto}")
    public String excluirProduto(@PathVariable("codigoProduto") Long codigoProduto, RedirectAttributes attributes) {
        Produto produto = pr.findByCodigoProduto(codigoProduto);
        pr.delete(produto);
        attributes.addFlashAttribute("mensagem", "Produto excluído com sucesso!");
        return "redirect:/listarProdutos";
    }
}

```

A classe `ProdutoController` é um controlador do Spring MVC responsável por lidar com as requisições relacionadas aos produtos. A anotação `@Controller` indica que a classe é um controlador.

A anotação `@Autowired` é usada para injetar a dependência do `ProdutoRepository` no controlador.

Os métodos mapeados com a anotação `@RequestMapping` são responsáveis por lidar com as requisições HTTP e retornar as visualizações correspondentes.

- O método `index()` mapeia a rota principal ("/") e retorna a visualização "index".
- O método `cadastrarProduto()` mapeia a rota "/cadastrarProduto" para exibir o formulário de cadastro de produto.
- O método `cadastrarProduto(Produto produto, BindingResult result, RedirectAttributes attributes)` mapeia a rota "/cadastrarProduto" para processar o formulário de cadastro de produto. Ele recebe o objeto `Produto` preenchido pelo formulário, realiza a validação, salva o produto no banco de dados e redireciona para a rota "/listarProdutos".
- O método `listarProdutos()` mapeia a rota "/listarProdutos" para exibir a lista de produtos cadastrados.
- O método `formAlterarProduto(Long codigoProduto)` mapeia a rota "/alterarProduto/{codigoProduto}" para exibir o formulário de atualização de produto. Ele recebe o código do produto a ser alterado como parâmetro na URL.
- O método `alterarProduto(Long codigoProduto, Produto produto, BindingResult result, RedirectAttributes attributes)` mapeia a rota "/alterarProduto/{codigoProduto}" para processar o formulário de atualização de produto. Ele recebe o código do produto a ser alterado e o objeto `Produto` preenchido pelo formulário. Realiza a validação, salva as alterações no banco de dados e redireciona para a rota "/listarProdutos".
- O método `excluirProduto(Long codigoProduto, RedirectAttributes attributes)` mapeia a rota "/excluirProduto/{codigoProduto}" para excluir um produto. Ele recebe o código do produto a ser excluído como parâmetro na URL. Realiza a exclusão no banco de dados e redireciona para a rota "/listarProdutos".

# Front-End

# Página 1: index.html

## Descrição

Este arquivo HTML é a página inicial do site "Art Estampa". Ele contém um cabeçalho, uma barra de navegação, uma imagem principal e uma galeria de imagens.

## Funcionalidades

- Exibe o logotipo e o nome do site "Art Estampa" no cabeçalho.
- Apresenta uma breve descrição do site no cabeçalho.
- Possui uma barra de navegação com links para as seções "Home", "Cadastrar Produto", "Listar Produto" e "Contato".
- Exibe uma imagem principal com informações sobre uma arte urbana específica.
- Apresenta uma galeria de imagens com obras de arte urbana, cada uma com seu título, data de realização e um botão para mais detalhes.
- Inclui um rodapé com ícones de redes sociais e informações de direitos autorais.

## Dependências

- Bootstrap v5.0.1 (arquivo CSS e JavaScript)
- Bootstrap Icons v1.5.0 (arquivo CSS)
- Arquivo CSS personalizado (styles.css)
- Imagens (favicon.ico, art_logo.svg)

# Página 2: listarProdutos.html

## Descrição

Este arquivo HTML exibe uma lista de produtos cadastrados. Ele é usado para listar os detalhes de cada produto, como código, descrição, preço, quantidade, categoria, tamanho, marca, data de cadastro e data de atualização.

## Funcionalidades

- Exibe o logotipo e o nome do site "Art Estampa" no cabeçalho.
- Apresenta uma breve descrição do site no cabeçalho.
- Possui uma barra de navegação com links para as seções "Home", "Cadastrar Produto", "Listar Produto" e "Contato".
- Apresenta uma lista de produtos cadastrados, cada um com seus detalhes, incluindo código, descrição, preço, quantidade, categoria, tamanho, marca, data de cadastro e data de atualização.
- Para cada produto, há botões para editar e excluir o produto.
- Inclui um rodapé com ícones de redes sociais e informações de direitos autorais.

## Dependências

- Bootstrap v5.3.0 (arquivo CSS e JavaScript)
- Bootstrap Icons v1.5.0 (arquivo CSS)
- Arquivo CSS personalizado (styles.css)
- Arquivo JavaScript personalizado
- Thymeleaf 

# Página 3: excluirProduto.html

## Descrição

Este arquivo HTML é usado para confirmar a exclusão de um produto. Ele exibe uma mensagem de confirmação com o nome e código do produto e fornece botões para confirmar ou cancelar a exclusão.

## Funcionalidades

- Exibe o logotipo e o nome do site "Art Estampa" no cabeçalho.
- Apresenta uma breve descrição do site no cabeçalho.
- Possui uma barra de navegação com links para as seções "Home", "Cadastrar Produto", "Listar Produto" e "Contato".
- Exibe uma mensagem de confirmação para excluir o produto, incluindo o nome e código do produto.
- Inclui botões "Sim" e "Não" para confirmar ou cancelar a exclusão.

## Dependências

- Bootstrap v5.3.0 (arquivo CSS e JavaScript)
- Bootstrap Icons v1.5.0 (arquivo CSS)
- Arquivo CSS personalizado (styles.css)
- Arquivo JavaScript personalizado
- Thymeleaf 

# Página 4: atualizarProduto.html

## Descrição

Esta página é usada para atualizar os detalhes de um produto específico. Ela exibe um formulário com campos preenchidos com as informações atuais do produto. Os usuários podem editar esses campos para atualizar os dados do produto.

## Funcionalidades

- Exibe o logotipo e o nome do site "Art Estampa" no cabeçalho.
- Apresenta uma breve descrição do site no cabeçalho.
- Possui uma barra de navegação com links para as seções "Home", "Cadastrar Produto", "Listar Produto" e "Contato".
- Exibe o título "Atualizar o Produto" seguido pela descrição do produto.
- Apresenta um formulário para atualizar os detalhes do produto.
- O formulário contém campos para editar a descrição, preço, quantidade, categoria, tamanho, cor, gênero, marca, data de cadastro e data de atualização do produto.
- O valor atual de cada campo é exibido como um valor padrão nos campos de entrada.
- Inclui um botão "Alterar dados" para enviar o formulário de atualização.
- Exibe uma imagem ilustrativa à direita do formulário.
- Inclui um rodapé com ícones de redes sociais e informações de direitos autorais.

## Dependências

- Bootstrap v5.3.0 (arquivo CSS e JavaScript)
- Bootstrap Icons v1.5.0 (arquivo CSS)
- Arquivo CSS personalizado (styles.css)
- Arquivo JavaScript personalizado

# Considerações Finais 

O projeto é uma aplicação Spring Boot para um sistema de cadastro de produtos. Ele utiliza o Spring MVC para lidar com as requisições HTTP e o Spring Data JPA para a persistência dos dados no banco de dados MySQL.

A estrutura do projeto segue as convenções do Spring Boot, com os arquivos Java organizados em pacotes separados e as visualizações (páginas HTML) armazenadas na pasta "resources/templates".

O banco de dados MySQL é configurado no arquivo `application.properties`, que deve conter as informações de conexão, como a URL, nome de usuário e senha.

As classes `DataConfig` e `ProdutoRepository` são responsáveis pela configuração da conexão com o banco de dados e pelas operações de CRUD, respectivamente.

O controlador `ProdutoController` define os métodos para lidar com as requisições relacionadas aos produtos, como cadastrar, listar, alterar e excluir.

