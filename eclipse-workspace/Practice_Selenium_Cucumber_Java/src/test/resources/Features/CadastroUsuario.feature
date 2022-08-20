# Autor:Marlon H. Tavares
# Data:25/06/2022
# Descrição: teste para validar o cadastro de um usuário.

Feature: Cadastro de usuario

	Scenario: Cadastro usuario com sucesso
		Given operador logado no sistema
		When acessar a tela de cadastro de usuario
		And preencher todos os dados corretamente
		Then o usuario devera ser cadastrado com sucesso
