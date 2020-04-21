# Compras online api

Api rest de compras online desenvolvida com java, spring-boot, banco de dados h2 em memoria e autenticação por JWT

# Documentação

Para gerar a documentação da api foi utilizado swagger 2.0, existe duas formas de acessar, 
pela interface grafica disponibilizada pelo swagger em 

    http://localhost:8081/swagger-ui.html
    
ou pelo json gerado com toda documentação, que pode ser importado para o postman em

    http://localhost:8081/v2/api-docs

Dois usuarios padrão são criados assim que a aplicação é inicializada, um usuario admin e outro usuario cliente

    Admin: { "email":"audora@gmail.com", "senha":"123456" }
    Cliente: { "email":"cliente@gmail.com", "senha":"123456" }
    
A autenticação é utilizando tokens jwt logo quando uma requisição for feita deve conter no header: 
    
    'Authorization: Bearer <token>'

###Autenticar usuario
Para autenticar um usuario envie uma requisição POST para http://localhost:8081/autenticar
    
    {
         "email": "teste@gmail.com",
         "senha": "123456"
    }
    
###Registrar novo usuario    
Para registrar novo usuario cliente envie uma requisição POST para http://localhost:8081/registrar
    
    {
         "email": "novocliente@gmail.com",
         "senha": "123456"
    }

    

