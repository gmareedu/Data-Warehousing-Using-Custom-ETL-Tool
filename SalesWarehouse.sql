create database SalesDW;
use SalesDW;
create table DumpTable
(
	t_id varchar(10),
	t_date date,
    cust_id varchar(10),
    cust_name varchar(50),
    cust_gen varchar(20),
    store_id varchar(10),
    store_name varchar(50),
    store_loc varchar(100),
    city varchar(100),
    state varchar(100),
    country varchar(50),
    salesperson_id varchar(10),
    salesperson varchar(50),
    product_id varchar(10),
	product_name varchar(50),
    p_actualcost numeric(19,4),
    p_salescost numeric(19,4),
    quantity int,
    total_amount numeric(19,4)
);
load data local infile 'E:/Giridhar/mini proj/Book1.csv' into table DumpTable fields terminated 
by ',' enclosed by "" escaped by '\\' lines terminated by '\n';
select * from dumptable;
create table DimCustomer
(
CustomerID int primary key auto_increment,
CustomerAltID varchar(10),
CustomerName varchar(50),
Gender varchar(20)
);
insert into DimCustomer (CustomerAltID,CustomerName,Gender)
select cust_id,cust_name,cust_gen 
from dumptable
where not exists (select 1 from DimCustomer where cust_id=CustomerAltID)
group by cust_id;
select * from dimcustomer;
Create table DimStores
(
StoreID int primary key auto_increment,
StoreAltID varchar(10)not null,
StoreName varchar(100),
StoreLocation varchar(100),
City varchar(100),
State varchar(100),
Country varchar(100)
);
insert into DimStores (StoreAltID,StoreName,StoreLocation,City,State,Country)
select store_id,store_name,store_loc,city,state,country 
from dumptable
where not exists (select 1 from dimstores where store_id=StoreAltID)
group by store_id;
select * from dimstores;
Create table DimSalesPerson
(
SalesPersonID int primary key auto_increment,
SalesPersonAltID varchar(10)not null,
SalesPersonName varchar(100),
StoreID varchar(10),
City varchar(100),
State varchar(100),
Country varchar(100)
);
insert into DimSalesPerson (SalesPersonAltID,SalesPersonName,StoreID,City,State,Country)
select salesperson_id,salesperson,store_id,city,state,country 
from dumptable
where not exists (select 1 from dimSalesPerson where salesperson_id=SalesPersonAltID)
group by salesperson_id;
select * from dimsalesperson;
Create table DimProduct
(
ProductKey int primary key auto_increment,
ProductAltKey varchar(10)not null,
ProductName varchar(100),
ProductActualCost numeric(19,4),
ProductSalesCost numeric(19,4)
);
insert into DimProduct (ProductAltKey,ProductName,ProductActualCost,ProductSalesCost)
select product_id,product_name,p_actualcost,p_salescost
from dumptable
where not exists (select 1 from DimProduct where ProductAltKey = product_id)
group by product_id;
select * from dimproduct;
Create Table FactProductSales
(
TransactionId bigint primary key auto_increment,
TransAltID varchar(10) not null,
SalesDate date,
StoreID int not null,
CustomerID int not null,
ProductID int not null,
SalesPersonID int not null,
Quantity float,
SalesTotalCost numeric(19,4),
ProductActualCost numeric(19,4),
Deviation float
);
AlTER TABLE FactProductSales ADD CONSTRAINT
FK_StoreID FOREIGN KEY (StoreID)REFERENCES DimStores(StoreID);
AlTER TABLE FactProductSales ADD CONSTRAINT 
FK_CustomerID FOREIGN KEY (CustomerID)REFERENCES Dimcustomer(CustomerID);
AlTER TABLE FactProductSales ADD CONSTRAINT 
FK_ProductKey FOREIGN KEY (ProductID)REFERENCES Dimproduct(ProductKey);
AlTER TABLE FactProductSales ADD CONSTRAINT 
FK_SalesPersonID FOREIGN KEY (SalesPersonID)REFERENCES Dimsalesperson(SalesPersonID);
insert into factproductsales (TransAltID,SalesDate,StoreID,
CustomerID,ProductID,SalesPersonID,quantity,SalesTotalCost,ProductActualCost,Deviation)
select t_id,t_date,
(select StoreID from dimstores where store_id=StoreAltID),
(select CustomerID from dimcustomer where cust_id=CustomerAltID),
(select ProductKey from dimproduct where product_id=ProductAltKey),
(select SalesPersonID from dimsalesperson where salesperson_id = SalesPersonAltID),
quantity,total_amount,p_actualcost,
total_amount-(quantity*p_actualcost)
from DumpTable
where not exists (select 1 from factproductsales where t_id=TransAltID)
group by t_id;
select * from factproductsales;