# Passos para configuração do keycloak

Acessar http://localhost:18080/, clicar em "Administrator Console" e digitar as credenciais preenchidas no docker-compose, que por padrão são:

```yml
      KEYCLOAK_USER: admin
      KEYCLOAK_PASSWORD: Pa55w0rd
```

Com o mouse por cima do "Master" selecionar a opção "Add realm"

![img_5.png](img_5.png)

Selecionar a opção "Select file" e selecionar o arquivo "realm-export.json" disponível na raiz desse projeto e então clicar em "create"

![img_6.png](img_6.png)

No Realm Api-ecommerce clicar na aba users e em Add user

![img_2.png](img_2.png)

Preencher os dados do novo usuário

![img_3.png](img_3.png)

Na aba credencials escolher uma senha para esse usuário

![img_4.png](img_4.png)