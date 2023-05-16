create database if not exists metrics_db;
use metrics_db;
create table if not exists metrics (
    id int not null AUTO_INCREMENT,
    system varchar(256) not null,
    name varchar(256) not null,
    date int not null,
    value int not null
 );