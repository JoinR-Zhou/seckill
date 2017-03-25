

use seckill;

-- show tables;

-- drop table seckill;

CREATE TABLE seckill(
seckill_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT'主键',
seckill_name VARCHAR(120) NOT NULL COMMENT'商品名称',
number INT NOT NULL COMMENT'库存数量',
start_time TIMESTAMP NOT NULL COMMENT'秒杀开启时间',
end_time TIMESTAMP NOT NULL COMMENT'秒杀结束时间',
create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT'秒杀创建时间',
KEY idx_start_time(start_time),
KEY idx_end_time(end_time),
KEY idx_create_time(create_time)

)ENGINE=INNODB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT'秒杀库存表'

INSERT seckill 
(seckill_name,number,start_time,end_time)
VALUES
('1000元秒杀iPhone6',100,'2017-03-07 00:00:00','2017-03-08 00:00:00'),
('400元秒杀iPad2',200,'2017-03-08 00:00:00','2017-03-16 00:00:00'),
('300元秒杀小米4',300,'2017-03-09 00:00:00','2017-03-20 00:00:00'),
('200元秒杀红米note',400,'2017-03-10 00:00:00','2017-03-30 00:00:00');

-- select * from seckill;

CREATE TABLE success_seckill(
seckill_id BIGINT NOT NULL COMMENT'商品ID',
user_phone BIGINT NOT NULL COMMENT'用户电话号码',
state TINYINT NOT NULL DEFAULT -1 COMMENT'状态标示：-1：无效，0：成功，1：已付款',
create_time TIMESTAMP NOT NULL COMMENT'创建时间',
PRIMARY KEY (seckill_id,user_phone),
KEY idx_create_time(create_time)


)ENGINE=INNODB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT'秒杀成功明细表';

-- select * from success_seckill;

-- delete from success_seckill where seckill_id=1000;

