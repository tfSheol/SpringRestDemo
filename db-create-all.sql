create table account (
  id                            bigint auto_increment not null,
  username                      varchar(255),
  password                      varchar(255),
  email                         varchar(255),
  constraint pk_account primary key (id)
);

create table token (
  token                         varchar(255),
  username                      varchar(255),
  token_type                    varchar(255),
  ttl                           integer not null
);

