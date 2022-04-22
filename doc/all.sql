create database wiki;

use wiki;

drop table if exists `test`;
create table `test`
(
    `id`       bigint not null comment 'id',
    `name`     varchar(50) comment '名称',
    `password` varchar(50) comment '密码',
    primary key (`id`)
) engine innodb
  default charset = utf8mb4 comment ='测试';

drop table if exists `demo`;
create table `demo`
(
    `id`   bigint not null comment 'id',
    `name` varchar(50) comment '名称',
    primary key (`id`)
) engine innodb
  default charset = utf8mb4 comment ='测试';

insert into demo (id, name)
values (1, '测试');

# 电子书表
drop table if exists `tb_book`;
create table `tb_book`
(
    `id`           bigint     not null comment 'id',
    `name`         varchar(50) comment '名称',
    `category1_id` bigint comment '分类1',
    `category2_id` bigint comment '分类2',
    `description`  varchar(200) comment '描述',
    `cover`        varchar(200) comment '封面',
    `doc_count`    int comment '文档数',
    `view_count`   int comment '阅读数',
    `vote_count`   int comment '点赞数',
    `is_delete`    TINYINT(1) NOT NULL DEFAULT 0 comment '逻辑删除',
    `gmt_create`   DATETIME   NOT NULL DEFAULT now() comment '创建时间',
    `gmt_update`   DATETIME   NOT NULL DEFAULT now() comment '修改时间',
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4 comment '分类';

# 分类表
drop table if exists `tb_category`;
create table `tb_category`
(
    `id`           bigint not null comment 'id',
    `parent`       bigint not null default 0 comment '父id',
    `name`         varchar(50) not null comment '名称',
    `sort`         int comment '顺序',
    `is_delete`    TINYINT(1) NOT NULL DEFAULT 0 comment '逻辑删除',
    `gmt_create`   DATETIME   NOT NULL DEFAULT now() comment '创建时间',
    `gmt_update`   DATETIME   NOT NULL DEFAULT now() comment '修改时间',
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4 comment '电子书';

insert into `tb_category` (`id`,`parent` ,`name`, `sort`)
VALUES (100, 000, '前端开发', 100),
       (101, 100, 'vue', 101),
       (102, 100, 'css', 102),
       (200, 000, '后端开发', 200),
       (201, 200, 'java', 201),
       (202, 200, 'springboot', 202),
       (300, 000, 'Python', 300),
       (301, 300, '基础应用', 301),
       (302, 300, '进阶框架', 302),
       (400, 000, '数据库', 400),
       (401, 400, 'mysql', 401),
       (500, 000, '其他', 500),
       (501, 500, '服务器', 501),
       (502, 500, '开发工具', 502),
       (503, 500, '热门服务器语言', 503);

insert into `tb_book` (`id`, `name`, `description`)
VALUES (1, 'SpringBoot 入门教程', '零基础入门 java 开发，企业级应用开发最佳首选框架'),
       (2, 'Vue 入门教程', '零基础入门 vue 开发，企业级应用开发最佳首选框架'),
       (3, 'Python 入门教程', '零基础入门 python 开发，企业级应用开发最佳首选框架'),
       (4, 'Mysql 入门教程', '零基础入门 mysql 开发，企业级应用开发最佳首选框架'),
       (5, 'Oracle 入门教程', '零基础入门 oracle 开发，企业级应用开发最佳首选框架');
