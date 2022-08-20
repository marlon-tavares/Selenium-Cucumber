# Autor:Marlon H. Tavares
# Data:20/06/2022
# Descrição: teste para validar o acesso de login do operador.

Feature: Teste da funcionalidade de login

  Scenario: Verifica login com sucesso
    Given o usuario na tela de login
    When preencher usuario e senha validos
    And clicar no botao de login
    Then devera ser redirecionado para a pagina de usuario logado

  Scenario: Verifica falha no login
    Given o usuario na tela de login
    When preencher usuario ou senha invalidos
    And clicar no botao de login
    Then devera apresentar pop-up de erro no login
