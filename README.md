# Plataforma Target

![Heroku](http://heroku-badge.herokuapp.com/?app=facilit)

API Restfull controle de carrinho de compras de e-commerce


Git flow
Iniciar uma feature:
    git flow feature start endpoint-api

Agora vamos publicar essa branch para que tenha uma cópia do repositório remoto, assim mais pessoas podem trabalhar nela:
    git flow feature publish endpoint-api

Para realizar um pull e atualizar uma feature publicada no repositório remoto:
    git flow feature pull endpoint-api

A medida que for desenvolvendo, vá adicionando ao git:
    git add endpoint.txt
    git commit -m "inserindo novo endpoint"
Ao final de cada período (almoço, fim do dia e etc), envie para o repositório:
    git flow feature publish endpoint-api
Finalizou a codificação da sua api? testou localmente e está ok? chegou a hora de finalizar a feature branch:
    git flow feature finish endpoint-api
Com esse comando, o git flow faz o merge de tudo que desenvolveu com a branch develop, deleta a branch que foi criada (endpoint-api)
tanto local quanto remoto