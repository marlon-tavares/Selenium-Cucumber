# Autor:Marlon H. Tavares
# Data:20/06/2022
# Empresa: Infox Tecnologia
# Produto: FWCARD -> Base HOM
# Descrição: teste para validar venda.

Feature: Realizar venda manual

  Scenario: Venda de produto carne
    Given operador logado deseje realizar uma venda manual
    When acessar a tela de venda
    And preencher os dados da venda 
    And clicar em finalizar
    Then a venda devera ser efetuada e apresentado o cupom