use yuapi;
-- 接口信息表
create table if not exists yuapi.`interface_info`
(
    `username` varchar(256) not null comment '用户名',
    `id` bigint not null auto_increment comment '主键' primary key,
    `name` varchar(256) not null comment '名称',
    `requestHeader` text null comment '请求头',
    `responseHeader` text null comment '响应头',
    `desciption` varchar(256) null comment '描述',
    `method` varchar(256) not null comment '请求类型',
    `status` int default 0 not null comment '接口状态（0-关闭 1-开启）',
    `userId` bigint not null comment '用户名',
    `url` varchar(512) not null comment '请求地址',
    `updateTime` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `createTime` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `isDelete` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)'
    ) comment '接口信息表';

insert into yuapi.`interface_info` (`username`, `name`, `requestHeader`, `responseHeader`, `desciption`, `method`, `userId`, `url`) values ('龙子涵', '高钰轩', '黄鑫磊', '傅健柏', '赵瑾瑜', '何炫明', 18018468, 'www.adolph-keebler.biz');
insert into yuapi.`interface_info` (`username`, `name`, `requestHeader`, `responseHeader`, `desciption`, `method`, `userId`, `url`) values ('陆子涵', '方子轩', '钟明', '姚胤祥', '钟苑博', '莫鹭洋', 226779882, 'www.fredrick-yost.biz');
insert into yuapi.`interface_info` (`username`, `name`, `requestHeader`, `responseHeader`, `desciption`, `method`, `userId`, `url`) values ('戴越彬', '蒋瑞霖', '陈智渊', '夏晟睿', '苏伟泽', '吕锦程', 2121563, 'www.gregorio-halvorson.io');
insert into yuapi.`interface_info` (`username`, `name`, `requestHeader`, `responseHeader`, `desciption`, `method`, `userId`, `url`) values ('尹烨霖', '阎明', '郝驰', '傅烨霖', '卢聪健', '雷伟宸', 6805, 'www.ryan-wintheiser.com');
insert into yuapi.`interface_info` (`username`, `name`, `requestHeader`, `responseHeader`, `desciption`, `method`, `userId`, `url`) values ('何睿渊', '龙健柏', '孔志强', '武瑾瑜', '王鹭洋', '叶彬', 379811, 'www.domingo-wyman.name');
insert into yuapi.`interface_info` (`username`, `name`, `requestHeader`, `responseHeader`, `desciption`, `method`, `userId`, `url`) values ('刘智渊', '李晓啸', '雷瑾瑜', '郝晓啸', '龙鑫鹏', '傅鑫磊', 29, 'www.frankie-koss.com');
insert into yuapi.`interface_info` (`username`, `name`, `requestHeader`, `responseHeader`, `desciption`, `method`, `userId`, `url`) values ('梁嘉熙', '雷潇然', '阎雨泽', '傅鸿煊', '谢雨泽', '严志泽', 4669357230, 'www.lettie-gerlach.net');
insert into yuapi.`interface_info` (`username`, `name`, `requestHeader`, `responseHeader`, `desciption`, `method`, `userId`, `url`) values ('傅晟睿', '孙鹭洋', '冯鸿煊', '汪熠彤', '丁烨霖', '余浩', 358428, 'www.kristeen-stoltenberg.biz');
insert into yuapi.`interface_info` (`username`, `name`, `requestHeader`, `responseHeader`, `desciption`, `method`, `userId`, `url`) values ('高金鑫', '马炎彬', '余烨伟', '张伟诚', '萧天宇', '韩志泽', 3302, 'www.robbie-runte.org');
insert into yuapi.`interface_info` (`username`, `name`, `requestHeader`, `responseHeader`, `desciption`, `method`, `userId`, `url`) values ('董嘉懿', '姚峻熙', '于瑞霖', '罗晋鹏', '吕锦程', '郑荣轩', 74, 'www.pamela-renner.com');
insert into yuapi.`interface_info` (`username`, `name`, `requestHeader`, `responseHeader`, `desciption`, `method`, `userId`, `url`) values ('余子轩', '覃志泽', '段博涛', '邹思聪', '蒋煜城', '史晓博', 2635073, 'www.alvin-bahringer.biz');
insert into yuapi.`interface_info` (`username`, `name`, `requestHeader`, `responseHeader`, `desciption`, `method`, `userId`, `url`) values ('黄靖琪', '马天翊', '唐弘文', '吕鑫磊', '田正豪', '魏伟祺', 503, 'www.laurie-okeefe.net');
insert into yuapi.`interface_info` (`username`, `name`, `requestHeader`, `responseHeader`, `desciption`, `method`, `userId`, `url`) values ('彭弘文', '田浩轩', '陆君浩', '曾峻熙', '雷伟祺', '范天磊', 4270, 'www.kyong-pagac.io');
insert into yuapi.`interface_info` (`username`, `name`, `requestHeader`, `responseHeader`, `desciption`, `method`, `userId`, `url`) values ('潘鸿煊', '龚明轩', '王耀杰', '阎烨磊', '谢晟睿', '傅志强', 204, 'www.dulce-bergstrom.com');
insert into yuapi.`interface_info` (`username`, `name`, `requestHeader`, `responseHeader`, `desciption`, `method`, `userId`, `url`) values ('韩峻熙', '苏浩然', '罗鹭洋', '蔡炎彬', '许昊焱', '姚越彬', 709, 'www.otha-stiedemann.io');
insert into yuapi.`interface_info` (`username`, `name`, `requestHeader`, `responseHeader`, `desciption`, `method`, `userId`, `url`) values ('许烨伟', '曾笑愚', '萧致远', '雷煜城', '丁明哲', '陆弘文', 3249704867, 'www.cheryle-wuckert.com');
insert into yuapi.`interface_info` (`username`, `name`, `requestHeader`, `responseHeader`, `desciption`, `method`, `userId`, `url`) values ('尹嘉懿', '蒋彬', '谭彬', '彭凯瑞', '龚子涵', '任黎昕', 84315, 'www.joya-paucek.info');
insert into yuapi.`interface_info` (`username`, `name`, `requestHeader`, `responseHeader`, `desciption`, `method`, `userId`, `url`) values ('刘振家', '崔立诚', '曾子轩', '金烨磊', '何晟睿', '崔子轩', 578, 'www.rosario-walker.net');
insert into yuapi.`interface_info` (`username`, `name`, `requestHeader`, `responseHeader`, `desciption`, `method`, `userId`, `url`) values ('徐展鹏', '邱明哲', '白皓轩', '郭楷瑞', '顾聪健', '方明', 27, 'www.noemi-dare.net');

insert into yuapi.`interface_info` (`username`, `name`, `requestHeader`, `responseHeader`, `desciption`, `method`, `userId`, `url`) values ('孔鹏', '白风华', '吕笑愚', '蔡鹏涛', '汪荣轩', '徐梓晨', 3304, 'www.janet-klocko.biz');
