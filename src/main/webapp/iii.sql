use project;
delimiter //
create procedure proc_showHistoryOrderByUser(id int)
begin
    select orders.orderId as orderId,orders.userId as userId,
           orderDetail.productId as productId,product.productName as productName,
           orderDetail.price as price, orderDetail.quantity as quantity, orders.dateBuy as date from
        orders join orderdetail on orders.orderId = orderDetail.orderId
               join product on orderDetail.productId = product.productId where orders.userId = id;
end;
drop procedure proc_showHistoryOrderByUser;

call proc_showAllOrders;
call proc_showHistoryOrderByUser(4);
delimiter //
create procedure proc_getAllOrders()
begin
    select * from orders;
end //;


delimiter //
create trigger setProductStatus
    after update on product for each row
    begin
        update product set status = 0 where OLD.quantity = 0;
    end //;

call proc_updateUser(4,'1994-3-3','0972534731','Nghe An');

update user set status = 1 where userId = 4;


delimiter //
create procedure PROC_createCatalog(IN name varchar(50), IN newDescription text,
                                    IN newCountry varchar(50), IN newImage text)

begin
    insert into catalog(catalogName, description, country,image) VALUES (name,newDescription,newCountry,newImage);
end //;

delimiter //
create procedure PROC_updateCatalog(IN idUpdate int, IN upName varchar(50), IN upDescription text,
                                                          IN upCountry varchar(50), IN upImage text)
begin
    update catalog set catalogName = upName,description = upDescription,country = upCountry,image = upImage where catalogId = idUpdate;
end //;

delimiter //
create procedure searchProductByCatalogId(id int)
begin
    select * from product where catalogId = id;
end //;

call searchProductByCatalogId(5);

delete from product where productId = 1;


delimiter //
create trigger delete_Product
    before delete on product for each row
    begin
        delete from cartitem where cartitem.productId = OLD.productId;
        delete from orderdetail where orderdetail.productId = OLD.productId;
    end //;

delete from catalog where catalogId = 1;

delimiter //
create procedure proc_getCountOrders()
begin
    select count(*) as count from orders where status = 0;
end //;
call proc_getCountOrders;
update orders set status = 1 where orderId = 18;
delimiter //
create procedure proc_getTotalInMonth()
begin
select sum(total) as sumMonth from orders where month(dateBuy) = month(now()) and orders.status = 1;
end //;
drop procedure proc_getAllOrders;
delimiter //
create procedure proc_getTotal()
begin
select sum(total) as sum from orders where orders.status = 1;
end //;
delimiter //
create procedure proc_getAllOrders()
begin
    select * from orders where status = 1 and total <> 0;
end //;

delimiter //
create procedure getAllOrders()
begin
    select * from orders where status = 0 and total <> 0;
end //;
call proc_getAllOrders;
delimiter //
create procedure showOrder(id int)
begin
    select orders.orderId as orderId,orders.userId as userId,
           orderDetail.productId as productId,product.productName as productName,
           orderDetail.price as price, orderDetail.quantity as quantity, orders.dateBuy from
        orders join orderdetail on orders.orderId = orderDetail.orderId
               join product on orderDetail.productId = product.productId where
                orders.status = 0 and orders.orderId = id and orders.total <> 0;
end //;
drop procedure proc_getAllOrders;
delimiter //
create procedure setOrderStatus(id int)
begin
    update orders set status = 1 where orderId = id;
end //;
delimiter //
create procedure proc_showAllOrders()
begin
    select orders.orderId as orderId,orders.userId as userId,
           orderDetail.productId as productId,product.productName as productName,
           orderDetail.price as price, orderDetail.quantity as quantity, orders.dateBuy from
        orders join orderdetail on orders.orderId = orderDetail.orderId
               join product on orderDetail.productId = product.productId where
            orders.status = 1 and orders.total <> 0;
end //;

drop procedure proc_showAllOrders;

delete from orders;
delimiter //
create procedure proc_createOrders(IN uIdNew int, OUT newOrderId int)
begin
    insert into orders(userId)
    values (uIdNew);
    select  Distinct last_insert_id() into newOrderId from orders;
end //;

alter table orders modify total float default 0;
drop procedure proc_createOrders;

delimiter //
create procedure setTotal(id int,newTotal float)
begin
    update orders set total = newTotal where orderId = id;
end //;

update user set status = 1 where userId = 4;

call proc_createOrders(4,@orderId);
select * from orders;

call proc_createOrderDetail(25,7,'Jim Beam Kentucky Straight',69,10);
insert into orderdetail( orderId, productId, productName, price, quantity) values
    (25,7,'Jim Beam Kentucky Straight',69,10);
use project;

update orders set total = 690 where orderId = 25;

call showOrder(25);
delimiter //
create trigger setQuantity
    after insert
    on orderdetail
    for each row
begin
    update product set quantity = quantity - NEW.quantity where product.productId=NEW.productId;
end //;

alter table product modify heart int default 0;

delimiter //
create procedure setHeartProduct(id int)
begin
    update product set heart = heart + 1 where productId = id;
end //;

call setHeartProduct(8);

update product set heart = 44 where productId = 14;
update product set heart = 20 where productId = 8;
update product set heart = 32 where productId = 9;
update product set heart = 56 where productId = 10;
update product set heart = 27 where productId = 11;
update product set heart = 8 where productId = 12;
update product set heart = 14 where productId = 13;

delimiter //
create procedure getProduct()
begin
    select * from product order by heart desc limit 8;
end //;

call getProduct();

delimiter //
create trigger changeStatus
    after update on product for each row
    begin
        update product set status = 0 where quantity = 0;
    end //;

call getAllOrders;
delimiter //
create procedure proc_getCountOrders()
begin
    select count(*) as count from orders where status = 0 and total <> 0;
end //;
drop procedure proc_getCountOrders;

alter table cart add foreign key (userId) references user(userId);
alter table cartitem add foreign key (userId) references user(userId);
alter table cartitem add foreign key (productId) references product(productId);
alter table cartitem add primary key (userId,productId);
delimiter //
create procedure proc_showHistoryOrderByUser(IN id int)
begin
    select orders.orderId as orderId,orders.userId as userId,
           orderDetail.productId as productId,product.productName as productName,
           orderDetail.price as price, orderDetail.quantity as quantity, orders.dateBuy as date from
        orders join orderdetail on orders.orderId = orderDetail.orderId
               join product on orderDetail.productId = product.productId where orders.userId = id;
end //;
drop procedure proc_showHistoryOrderByUser;

update product set quantity = 50 where productId = 13;

delimiter //
create procedure PROC_blockUser(IN idBlock int)
begin
    update user set status = !status where userId = idBlock;
end //;

drop procedure PROC_blockUser;

update user set status = !status where userId = 19;

delimiter //
create trigger delete_orders
    before delete on orders for each row
    begin
        delete from orderdetail where orderdetail.orderId = OLD.orderId;
 end //;

insert into orders(orderId, userId, total, dateBuy, status) values
    (6,15,8000,'2022-2-2',true);

update product set quantity = 50 where productId = 11;