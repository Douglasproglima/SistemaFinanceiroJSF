create table usuario (
   nome_usuario varchar(15) not null primary key,
   senha varchar(15) not null
);

create table permissao_usuario (
   nome_usuario	varchar(15) not null,
   nome_permissao varchar(15) not null, 
   primary key (nome_usuario, nome_permissao),
   foreign key (nome_usuario) references usuario (nome_usuario)
);

insert into usuario values ('dflima', 'dflima');
insert into usuario values ('admin', 'admin');
insert into usuario values ('funcionario', 'funcionario');

insert into permissao_usuario values ('funcionario', 'cadastro');
insert into permissao_usuario values ('dflima', 'consulta');
insert into permissao_usuario values ('admin', 'cadastro');
insert into permissao_usuario values ('admin', 'consulta');