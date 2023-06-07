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
    public String alterarProduto(@Validated Produto produto, BindingResult result, RedirectAttributes attributes) {
        pr.save(produto);
        return "redirect:/listarProdutos";
    }

    @RequestMapping("/confirmarExclusaoProduto/{codigoProduto}")
    public ModelAndView confirmarExclusaoProduto(@PathVariable("codigoProduto") Long codigoProduto) {
        Produto produto = pr.findByCodigoProduto(codigoProduto);
        ModelAndView mv = new ModelAndView("content/excluir-produto");
        mv.addObject("produto", produto);
        return mv;
    }

    @RequestMapping("/excluirProduto/{codigoProduto}")
    public String excluirProduto(@PathVariable("codigoProduto") Long codigoProduto) {
        Produto produto = pr.findByCodigoProduto(codigoProduto);
        pr.delete(produto);
        return "redirect:/listarProdutos";
        
    }
}