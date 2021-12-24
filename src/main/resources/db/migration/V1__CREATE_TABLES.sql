
CREATE TABLE user (
id BIGINT NOT NULL AUTO_INCREMENT,
username varchar(64) not null unique,
password varchar(64) not null,
money varchar(64) not null,
PRIMARY KEY (id)
)engine=MyISAM;

CREATE TABLE item (
name varchar(200) not null unique,
price varchar(64) not null,
PRIMARY KEY (name)
)engine=MyISAM;

CREATE TABLE basket (
id BIGINT NOT NULL AUTO_INCREMENT,
total_price varchar(64),
PRIMARY KEY (id)
) engine=MyISAM;

CREATE TABLE item_list (
id BIGINT NOT NULL AUTO_INCREMENT,
quantity BIGINT NOT NULL,
total_price varchar(64),
PRIMARY KEY (id)
)engine=MyISAM;


CREATE TABLE order_history (
id BIGINT NOT NULL AUTO_INCREMENT,
timestamp DATETIME not null,
order_price double not null,
PRIMARY KEY (id)
) engine=MyISAM;
