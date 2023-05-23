create table orders(
orderId int primary key auto_increment,
userId int,
total float,
dateBuy datetime default now(),
status bit default 0
);


create table orderDetail(
orderId int,
productId int,
productName varchar(50),
price float,
quantity int
);

alter table orders add foreign key (userId) references user(userId);
alter table orderdetail add foreign key (orderId) references orders(orderId);
alter table orderdetail add foreign key (productId) references product(productId);


drop procedure proc_createOrders;
call proc_createOrders(4,500);
delimiter //
create procedure proc_createOrderDetail(nOrderId int,nProductId int,nProductName varchar(50),nPrice float,nQuantity int)
begin
insert into orderdetail( orderId, productId, productName, price, quantity) values
(nOrderId,nProductId,nProductName,nPrice,nQuantity);
end //;

delimiter //
create procedure proc_updateUser(id int,nBirthday date,nPhoneNumber varchar(50),nAddress text)
begin
update user set birthday = nBirthday,
phoneNumber = nPhoneNumber,address = nAddress where userId = id;
end //;
use project;
drop procedure proc_updateUser;



DELIMITER //
create procedure proc_createOrders
(in uIdNew int,
in totalNew float,
OUT newOrderId int
)
begin
insert into orders(userId, total)
values (uIdNew,totalNew);
select  Distinct last_insert_id() into newOrderId from orders;
end //;


DELIMITER //
create procedure proc_checkLastId
(in name varchar(50),
in mail varchar(50),
in pass varchar(50),
OUT lastId int
)
begin
insert into user(userName, email,password)
values (name,mail,pass);
select  Distinct last_insert_id() into lastId from user;
end //;

call proc_checkLastId('bb','cc','ee',@id);
select @id;
delimiter //
create procedure proc_getLastUserId(out userId int)
begin
select Distinct last_insert_id() into userId from user;
end //;
drop procedure proc_getLastUserId;
insert into user(userName, email, password) values ('aa','aaaa','aaaa');
call proc_getLastUserId(@id);
select @id;

delete from orders;


delimiter //
create trigger setQuantity
after insert on orderdetail for each row
begin
update product set quantity = quantity - NEW.quantity;
end //;

delimiter //
create trigger deleteProduct
before delete on product for each row
begin
delete from cartitem where cartitem.productId = OLD.productId;
end //;

delimiter //
create trigger deleteCatalog
before delete on catalog for each row
begin
delete from product where product.catalogId = OLD.catalogId;
end //;
call PROC_createProduct('aa',90,40,'aaaaa','aaa',1,1);
