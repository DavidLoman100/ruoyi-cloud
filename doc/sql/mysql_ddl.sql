-- 用户信息表 ---------------------------------------------------------------------
create table sys_user
(
    user_id     bigint auto_increment comment '用户ID'
        primary key,
    dept_id     bigint                    null comment '部门ID',
    user_name   varchar(30)               not null comment '用户账号',
    nick_name   varchar(30)               not null comment '用户昵称',
    user_type   varchar(2)   default '00' null comment '用户类型（00系统用户）',
    email       varchar(50)  default ''   null comment '用户邮箱',
    phonenumber varchar(11)  default ''   null comment '手机号码',
    sex         char         default '0'  null comment '用户性别（0男 1女 2未知）',
    avatar      varchar(100) default ''   null comment '头像地址',
    password    varchar(100) default ''   null comment '密码',
    status      char         default '0'  null comment '帐号状态（0正常 1停用）',
    is_deleted  char         default '0'  null comment '删除标志（0代表存在 1代表删除）',
    login_ip    varchar(128) default ''   null comment '最后登录IP',
    login_date  datetime                  null comment '最后登录时间',
    create_by   varchar(64)  default ''   null comment '创建者',
    create_time datetime                  null comment '创建时间',
    update_by   varchar(64)  default ''   null comment '更新者',
    update_time datetime                  null comment '更新时间',
    remark      varchar(500)              null comment '备注'
) comment '用户信息表';

-- 菜单表 ---------------------------------------------------------------------
create table sys_menu
(
    menu_id     bigint auto_increment comment '菜单ID'
        primary key,
    menu_name   varchar(50)              not null comment '菜单名称',
    parent_id   bigint       default 0   null comment '父菜单ID',
    order_num   int          default 0   null comment '显示顺序',
    path        varchar(200) default ''  null comment '路由地址',
    component   varchar(255)             null comment '组件路径',
    query       varchar(255)             null comment '路由参数',
    route_name  varchar(50)  default ''  null comment '路由名称',
    is_frame    int          default 1   null comment '是否为外链（0是 1否）',
    is_cache    int          default 0   null comment '是否缓存（0缓存 1不缓存）',
    menu_type   char         default ''  null comment '菜单类型（M目录 C菜单 F按钮）',
    visible     char         default '0' null comment '菜单状态（0显示 1隐藏）',
    status      char         default '0' null comment '菜单状态（0正常 1停用）',
    perms       varchar(100)             null comment '权限标识',
    icon        varchar(100) default '#' null comment '菜单图标',
    create_by   varchar(64)  default ''  null comment '创建者',
    create_time datetime                 null comment '创建时间',
    update_by   varchar(64)  default ''  null comment '更新者',
    update_time datetime                 null comment '更新时间',
    remark      varchar(500) default ''  null comment '备注',
    is_deleted  char         default '0' null comment '删除标志（0代表存在 1代表删除）'
) comment '菜单权限表';

-- 角色表 ---------------------------------------------------------------------
create table sys_role
(
    role_id             bigint auto_increment comment '角色ID'
        primary key,
    role_name           varchar(30)             not null comment '角色名称',
    role_key            varchar(100)            not null comment '角色权限字符串',
    role_sort           int                     not null comment '显示顺序',
    data_scope          char        default '1' null comment '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
    menu_check_strictly tinyint(1)  default 1   null comment '菜单树选择项是否关联显示',
    dept_check_strictly tinyint(1)  default 1   null comment '部门树选择项是否关联显示',
    status              char                    not null comment '角色状态（0正常 1停用）',
    is_deleted          char        default '0' null comment '删除标志（0代表存在 1代表删除）',
    create_by           varchar(64) default ''  null comment '创建者',
    create_time         datetime                null comment '创建时间',
    update_by           varchar(64) default ''  null comment '更新者',
    update_time         datetime                null comment '更新时间',
    remark              varchar(500)            null comment '备注'
) comment '角色信息表';

-- 角色和菜单表 ---------------------------------------------------------------------
create table sys_role_menu
(
    role_id bigint not null comment '角色ID',
    menu_id bigint not null comment '菜单ID',
    primary key (role_id, menu_id)
) comment '角色和菜单关联表';

-- 部门表 ---------------------------------------------------------------------
create table sys_dept
(
    dept_id     bigint auto_increment comment '部门id'
        primary key,
    parent_id   bigint      default 0   null comment '父部门id',
    ancestors   varchar(50) default ''  null comment '祖级列表',
    dept_name   varchar(30) default ''  null comment '部门名称',
    order_num   int         default 0   null comment '显示顺序',
    leader      varchar(20)             null comment '负责人',
    phone       varchar(11)             null comment '联系电话',
    email       varchar(50)             null comment '邮箱',
    status      char        default '0' null comment '部门状态（0正常 1停用）',
    is_deleted  char        default '0' null comment '删除标志（0代表存在 1代表删除）',
    create_by   varchar(64) default ''  null comment '创建者',
    create_time datetime                null comment '创建时间',
    update_by   varchar(64) default ''  null comment '更新者',
    update_time datetime                null comment '更新时间'
) comment '部门表';

-- 角色和部门表 ---------------------------------------------------------------------
create table
(
    role_id bigint not null comment '角色ID',
    dept_id bigint not null comment '部门ID',
    primary key (role_id, dept_id)
) comment '角色和部门关联表';
-- 生活日表 ---------------------------------------------------------------------
create table sys_life_day
(
    id                   bigint auto_increment comment '主键id'
        primary key,
    it_ms                int  default 0 null comment 'it面试',
    it_skill             int  default 0 null comment 'it技能',
    it_project           int  default 0 null comment 'it项目',
    it_mysql             int  default 0 null comment 'itSQl题',
    it_algorithm         int  default 0 null comment 'it算法',
    sport                int  default 0 null comment '运动',
    finance_skill        int  default 0 null comment '金融学习',
    equity_mkt_interpret int  default 0 null comment '解读股市',
    eng_word             int  default 0 null comment '英语单词',
    photography_skill    int  default 0 null comment '摄影技能',
    life_date            date             not null comment '生活时间',
    create_by            varchar(64) null comment '创建者',
    create_time          datetime null comment '创建时间',
    update_by            varchar(64) null comment '更新者',
    update_time          datetime null comment '更新时间',
    remark               varchar(500) null comment '备注',
    is_deleted           char default '0' not null comment '删除标志（0代表存在 1代表删除）',
    constraint un_life_date
        unique (life_date)
) comment '生活日表';

-- 用电日表 ----------------------------------------------------------------
create table sys_electricity_cost
(
    id            bigint auto_increment comment '主键id'
        primary key,
    price_per_kwh decimal(6, 2)    not null comment '每千瓦时费用',
    total_kwh     decimal(6, 2)    not null comment '总千瓦时 单位1000',
    cost          decimal(12, 2)   not null comment '费用合计',
    life_date     date             not null comment '生活时间',
    create_by     varchar(64)      null comment '创建者',
    create_time   datetime         null comment '创建时间',
    update_by     varchar(64)      null comment '更新者',
    update_time   datetime         null comment '更新时间',
    remark        varchar(500)     null comment '备注',
    is_deleted    char default '0' not null comment '删除标志（0代表存在 1代表删除）',
    constraint un_life_date
        unique (life_date)
) comment '用电支出';

-- 字典表 -
create table sys_dict
(
    id          bigint primary key auto_increment comment '主键',
    dict_code   varchar(100)             not null comment '字典类型（唯一标识）',
    dict_name   varchar(100)             not null comment '字典名称',
    create_by   varchar(64)  default null comment '创建者',
    create_time datetime     default null comment '创建时间',
    update_by   varchar(64)  default null comment '更新者',
    update_time datetime     default null comment '更新时间',
    remark      varchar(500) default null comment '备注',
    is_deleted  char         default '0' not null comment '删除标志（0代表存在 1代表删除）',
    constraint un_dict_code
        unique (dict_code)
) comment '字典表';
