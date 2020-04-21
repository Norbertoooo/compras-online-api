insert into categoria_produto(nome) values ('higiene'), ('alimento'), ('limpeza'), ('bebidas');

insert into usuario(email, senha, admin) values ('audora@gmail.com', '$2y$12$Pq4MjfdvHNF/zN5K8fC/quCcICrS7AQFsOnkOAFYvGx7MKR./LlFy', TRUE);
insert into usuario(email, senha, admin) values ('cliente@gmail.com', '$2y$12$Pq4MjfdvHNF/zN5K8fC/quCcICrS7AQFsOnkOAFYvGx7MKR./LlFy', FALSE);


insert into produto(nome,preco,categoria_id) values ('shampo',10.50,1);
insert into produto(nome,preco,categoria_id) values ('frango', 10.50,2);
insert into produto(nome,preco,categoria_id) values ('sabao', 4.00,3);
insert into produto(nome,preco,categoria_id) values ('coca', 5.00,4);




