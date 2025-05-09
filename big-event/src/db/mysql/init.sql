-- Create database
create database if not exists big_event;
use big_event;

-- User table
create table user (
                      id int unsigned primary key auto_increment comment 'ID',
                      username varchar(20) not null unique comment 'Username',
                      password varchar(32) comment 'Password',
                      nickname varchar(10) default '' comment 'Nickname',
                      email varchar(128) default '' comment 'Email',
                      user_pic varchar(128) default '' comment 'Avatar',
                      create_time datetime not null comment 'Creation Time',
                      update_time datetime not null comment 'Update Time'
) comment 'User Table';

-- Category table
create table category (
                          id int unsigned primary key auto_increment comment 'ID',
                          category_name varchar(32) not null comment 'Category Name',
                          category_alias varchar(32) not null comment 'Category Alias',
                          create_user int unsigned not null comment 'Creator ID',
                          create_time datetime not null comment 'Creation Time',
                          update_time datetime not null comment 'Update Time',
                          constraint fk_category_user foreign key (create_user) references user(id)
) comment 'Category Table';

-- Article table
create table article (
                         id int unsigned primary key auto_increment comment 'ID',
                         title varchar(30) not null comment 'Article Title',
                         content varchar(10000) not null comment 'Article Content',
                         cover_img varchar(128) not null comment 'Cover Image',
                         state varchar(3) default 'Draft' comment 'Article Status: Draft | Published',
                         category_id int unsigned comment 'Category ID',
                         create_user int unsigned not null comment 'Creator ID',
                         create_time datetime not null comment 'Creation Time',
                         update_time datetime not null comment 'Update Time',
                         constraint fk_article_category foreign key (category_id) references category(id),
                         constraint fk_article_user foreign key (create_user) references user(id)
) comment 'Article Table';