--TM_STORE_HOUSE----仓库表
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_STORE_HOUSE]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_STORE_HOUSE]
GO

CREATE TABLE [dbo].[TM_STORE_HOUSE] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[HOUSE_CODE] [varchar] (10) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[HOUSE_NAME] [varchar] (10) COLLATE Chinese_PRC_CI_AS NULL ,
	[HOUSE_REMARK] [varchar] (1000) COLLATE Chinese_PRC_CI_AS NULL ,
	[ISVALID] [int] NULL ,
	[ISMIXED] [int] NULL 
) ON [PRIMARY]
GO


--TM_CAR_STATION_TYPE----车型工位类型
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_CAR_STATION_TYPE]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_CAR_STATION_TYPE]
GO

CREATE TABLE [dbo].[TM_CAR_STATION_TYPE] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[STATION_CODE] [varchar] (10) COLLATE Chinese_PRC_CI_AS NULL ,
	[STATION_REMARK] [varchar] (1000) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
GO


--TM_WORKING_HOUR_PRICE----工时单价表
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_WORKING_HOUR_PRICE]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_WORKING_HOUR_PRICE]
GO

CREATE TABLE [dbo].[TM_WORKING_HOUR_PRICE] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[PRICE] [numeric](12, 2) NULL ,
	[PRICE_REMARK] [varchar] (1000) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
GO

--TM_CAR_MODEL_TYPE----车辆类型
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_CAR_MODEL_TYPE]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_CAR_MODEL_TYPE]
GO

CREATE TABLE [dbo].[TM_CAR_MODEL_TYPE] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[MODEL_CODE] [varchar] (10) COLLATE Chinese_PRC_CI_AS NULL ,
	[MODEL_NAME] [varchar] (10) COLLATE Chinese_PRC_CI_AS NULL ,
	[CAR_STATION_ID] [int] NULL ,
	[WORKING_HOUR_PRICE_ID] [int] NULL 
) ON [PRIMARY]
GO

--TM_PART_TYPE----配件类型
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_PART_TYPE]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_PART_TYPE]
GO

CREATE TABLE [dbo].[TM_PART_TYPE] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[TYPE_CODE] [varchar] (10) COLLATE Chinese_PRC_CI_AS NULL ,
	[TYPE_NAME] [varchar] (10) COLLATE Chinese_PRC_CI_AS NULL ,
	[TYPE_REMARK] [varchar] (1000) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
GO

--TM_UNIT----计量单位
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_UNIT]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_UNIT]
GO

CREATE TABLE [dbo].[TM_UNIT] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[UNIT_NAME] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[UNIT_REMARK] [varchar] (1000) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
GO

--TM_CUSTOMER_TYPE--客户类型
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_CUSTOMER_TYPE]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_CUSTOMER_TYPE]
GO

CREATE TABLE [dbo].[TM_CUSTOMER_TYPE] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[TYPE_NAME] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,
	[TYPE_REMARK] [varchar] (1000) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
GO

--TM_DEPARTMENT--部门
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_DEPARTMENT]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_DEPARTMENT]
GO

CREATE TABLE [dbo].[TM_DEPARTMENT] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[DEPT_CODE] [varchar] (10) COLLATE Chinese_PRC_CI_AS NULL ,
	[DEPT_NAME] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
GO


--TM_WORK_TYPE--工种
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_WORK_TYPE]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_WORK_TYPE]
GO

CREATE TABLE [dbo].[TM_WORK_TYPE] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[WORK_CODE] [varchar] (10) COLLATE Chinese_PRC_CI_AS NULL ,
	[WORK_NAME] [varchar] (10) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
GO

--TM_FIX_TYPE-- 修理类型
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_FIX_TYPE]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_FIX_TYPE]
GO

CREATE TABLE [dbo].[TM_FIX_TYPE] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[FIX_TYPE] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,
	[BALANCE_ID] [int] NULL 
) ON [PRIMARY]
GO


--TB_CUSTOMER--客户信息
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TB_CUSTOMER]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TB_CUSTOMER]
GO

CREATE TABLE [dbo].[TB_CUSTOMER] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[CUSTOMER_CODE] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,
	[CUSTOMER_NAME] [varchar] (10) COLLATE Chinese_PRC_CI_AS NULL ,
	[PINYIN_CODE] [varchar] (10) COLLATE Chinese_PRC_CI_AS NULL ,
	[ADDRESS] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[PHONE] [char] (8) COLLATE Chinese_PRC_CI_AS NULL ,
	[TELEPHONE] [char] (11) COLLATE Chinese_PRC_CI_AS NULL ,
	[ZIP_CODE] [char] (6) COLLATE Chinese_PRC_CI_AS NULL ,
	[COMPANY_ACCOUNT_CODE] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[COMPANY_TAX_CODE] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[LAW_PRESENT] [varchar] (10) COLLATE Chinese_PRC_CI_AS NULL ,
	[CONTRACT_PERSON] [varchar] (10) COLLATE Chinese_PRC_CI_AS NULL ,
	[CUSTOMER_PROPERTY] [int] NULL ,
	[CUSTOMER_TYPE_ID] [int] NULL ,
	[SOLE_TYPE_ID] [int] NULL 
) ON [PRIMARY]
GO

--TB_CAR_INFO--车辆信息

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TB_CAR_INFO]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TB_CAR_INFO]
GO

CREATE TABLE [dbo].[TB_CAR_INFO] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[LICENSE_CODE] [varchar] (10) COLLATE Chinese_PRC_CI_AS NULL ,
	[CHASSIS_CODE] [varchar] (30) COLLATE Chinese_PRC_CI_AS NULL ,
	[INSURE_CARD_CODE] [varchar] (30) COLLATE Chinese_PRC_CI_AS NULL ,
	[FACTORY] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,
	[MODEL_TYPE_ID] [int] NULL ,
	[COLOR] [varchar] (10) COLLATE Chinese_PRC_CI_AS NULL ,
	[ENGINE_CODE] [varchar] (30) COLLATE Chinese_PRC_CI_AS NULL ,
	[ENGINE_TYPE] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,
	[CHANGE_BOX_TYPE] [varchar] (30) COLLATE Chinese_PRC_CI_AS NULL ,
	[PURCHASE_DATE] [datetime] NULL ,
	[PRODUCT_DATE] [datetime] NULL ,
	[INSURE_DEADLINE_DATE] [datetime] NULL ,
	[KILO] [numeric](10, 0) NULL ,
	[FIRST_WRONG_KILO] [numeric](10, 2) NULL ,
	[YEAR_CHECK_DEADLINE] [datetime] NULL ,
	[SOLE_COMPANY] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,
	[MIDDLE_FACTORY] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,
	[BACK_FACTORY] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,
	[HAS_INSURED] [int] NULL ,
	[HAS_CLIAM] [int] NULL ,
	[HAS_FIXED] [int] NULL ,
	[CAR_PROPERTY] [int] NULL ,
	[CAR_USED] [int] NULL ,
	[CUSTOMER_ID] [int] NULL 
) ON [PRIMARY]
GO


-- TB_PART_COLLECTION -- 配件集
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TB_PART_COLLECTION]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TB_PART_COLLECTION]
GO

CREATE TABLE [dbo].[TB_PART_COLLECTION] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[STORE_HOUSE_ID] [int] NULL ,
	[PART_INFO_ID] [int] NULL ,
	[PART_QUANTITY] [numeric](10, 2) NULL ,
	[COLLECTION_CODE] [varchar] (50) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[create_date] [datetime] NULL
) ON [PRIMARY]
GO

-- TM_SOLE_TYPE --销售类型
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_SOLE_TYPE]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_SOLE_TYPE]
GO

CREATE TABLE [dbo].[TM_SOLE_TYPE] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[SOLE_NAME] [varchar] (10) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
GO

-- TM_CAR_BODY --车身部位
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_CAR_BODY]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_CAR_BODY]
GO

CREATE TABLE [dbo].[TM_CAR_BODY] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[BODY_NAME] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,
	[BODY_REMARK] [varchar] (1000) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TB_WORKING_INFO]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TB_WORKING_INFO]
GO

--TB_WORKING_INFO--工时工位
CREATE TABLE [dbo].[TB_WORKING_INFO] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[STATION_CODE] [varchar] (100) COLLATE Chinese_PRC_CI_AS NULL ,
	[STATION_NAME] [varchar] (100) COLLATE Chinese_PRC_CI_AS NULL ,
	[PINYIN_CODE] [varchar] (100) COLLATE Chinese_PRC_CI_AS NULL ,
	[WORK_TYPE_ID] [int] NULL ,
	[CAR_BODY_ID] [int] NULL ,
	[FIX_HOUR] [numeric](10, 2) NULL ,
	[SEND_HOUR] [numeric](10, 2) NULL 
) ON [PRIMARY]
GO

--TB_CAR_STATION_WORKING_RELATION--车型工时工位关系表
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TB_CAR_STATION_WORKING_RELATION]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TB_CAR_STATION_WORKING_RELATION]
GO

CREATE TABLE [dbo].[TB_CAR_STATION_WORKING_RELATION] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[WORKING_ID] [int] NULL ,
	[STATION_ID] [int] NULL 
) ON [PRIMARY]
GO

--TB_WORKING_COLLECTION--工位集
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TB_WORKING_COLLECTION]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TB_WORKING_COLLECTION]
GO

CREATE TABLE [dbo].[TB_WORKING_COLLECTION] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[WORKING_COLLECTION_CODE] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,
	[WORKING_COLLECTION_NAME] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[STATION_ID] [int] NULL 
) ON [PRIMARY]
GO


--TB_WORKING_RELATION--工位工位集关系表
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TB_WORKING_RELATION]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TB_WORKING_RELATION]
GO

CREATE TABLE [dbo].[TB_WORKING_RELATION] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[WORKING_ID] [int] NULL ,
	[WORKING_COLLECTION_ID] [int] NULL 
) ON [PRIMARY]
GO
--TM_DICTIONARY---全局字典表
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_DICTIONARY]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_DICTIONARY]
GO

CREATE TABLE [dbo].[TM_DICTIONARY] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[PARAMTYPE] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[PARAMVALUE] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
GO

--TB_PART_SOLE_PRICE---配件销售价格
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TB_PART_SOLE_PRICE]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TB_PART_SOLE_PRICE]
GO

CREATE TABLE [dbo].[TB_PART_SOLE_PRICE] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[PART_INFO_ID] [int] NULL ,
	[SOLE_TYPE_ID] [int] NULL ,
	[SOLE_PRICE] [numeric](10, 2) NULL 
) ON [PRIMARY]
GO


--TM_BALANCE---结算定义
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_BALANCE]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_BALANCE]
GO

CREATE TABLE [dbo].[TM_BALANCE] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[BALANCE_NAME] [varchar] (10) COLLATE Chinese_PRC_CI_AS NULL ,
	[BALANCE_TYPE] [int] NULL 
) ON [PRIMARY]
GO

--TM_BALANCE_ITEM---结算项目
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_BALANCE_ITEM]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_BALANCE_ITEM]
GO

CREATE TABLE [dbo].[TM_BALANCE_ITEM] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[ITEM_CODE] [varchar] (10) COLLATE Chinese_PRC_CI_AS NULL ,               --add
	[ITEM_NAME] [varchar] (10) COLLATE Chinese_PRC_CI_AS NULL ,
	[ITEM_REMARK] [varchar] (1000) COLLATE Chinese_PRC_CI_AS NULL ,
	[BALANCE_ID] [int] NULL ,
	[FORMULA] [varchar] (500) COLLATE Chinese_PRC_CI_AS NULL ,
	[ALLOW_HAND] [int] NULL                                                   --add
) ON [PRIMARY]
GO

--TM_FIX_TYPE---修理类型                  edit  2010-06-08
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_FIX_TYPE]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_FIX_TYPE]
GO

CREATE TABLE [dbo].[TM_FIX_TYPE] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[FIX_TYPE] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,
	[BALANCE_ID] [int] NULL --,
--	[WORKING_HOUR_PRICE] [numeric](10, 2) NULL                      --delete
) ON [PRIMARY]
GO

-- TB_BOOK --维修预约  
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TB_BOOK]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TB_BOOK]
GO

CREATE TABLE [dbo].[TB_BOOK] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[BOOK_CODE] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,
	[REGISTER_DATE] [datetime] NULL ,
	[LICENSE_CODE] [varchar] (10) COLLATE Chinese_PRC_CI_AS NULL ,
	[CHASSIS_CODE] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,
	[USER_ID] [int] NULL ,
	[IS_SYS_CUSTOMER] [int] NULL ,
	[CUSTOMER_NAME] [varchar] (10) COLLATE Chinese_PRC_CI_AS NULL ,
	[LINK_MAN] [varchar] (10) COLLATE Chinese_PRC_CI_AS NULL ,
	[PHONE] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,
	[TELPHONE] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,
	[PLAN_FIX_TIME] [datetime] NULL ,
	[FIX_TYPE_ID] [int] NULL ,
	[MODEL_TYPE_ID] [int] NULL ,
	[FIX_CONTENT] [varchar] (1000) COLLATE Chinese_PRC_CI_AS NULL ,
	[CAR_ADDRESS] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[WRONG_DESCRIBE] [varchar] (1000) COLLATE Chinese_PRC_CI_AS NULL ,
	[BOOK_REMARK] [varchar] (1000) COLLATE Chinese_PRC_CI_AS NULL ,
	[IS_COME] [int] NULL 
) ON [PRIMARY]
GO

--TB_BOOK_FIX_STATION--预约修理工位
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TB_BOOK_FIX_STATION]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TB_BOOK_FIX_STATION]
GO

CREATE TABLE [dbo].[TB_BOOK_FIX_STATION] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[BOOK_ID] [int] NULL ,
	[STATION_ID] [int] NULL ,
	[STATION_COLLECTION_ID] [int] NULL ,
	[FREESYMBOL] [int] NULL 
) ON [PRIMARY]
GO

--TB_BOOK_FIX_PART--预约修理配件        2010-06-06 edit 新增了FREESYSBOL字段     免费标识
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TB_BOOK_FIX_PART]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TB_BOOK_FIX_PART]
GO

CREATE TABLE [dbo].[TB_BOOK_FIX_PART] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[BOOK_ID] [int] NULL ,
	[PART_ID] [int] NULL ,
	[DEAL_TYPE] [int] NULL ,
	[PART_QUANTITY] [numeric](10, 2) NULL, 
	[FREESYMBOL] [int] NULL 
) ON [PRIMARY]
GO

--TB_FIX_ENTRUST--修理委托书               -- 2010-06-06 add        
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TB_FIX_ENTRUST]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TB_FIX_ENTRUST]
GO

CREATE TABLE [dbo].[TB_FIX_ENTRUST] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[ENTRUST_CODE] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,
	[FIX_DATE] [datetime] NULL ,
	[TRANSFER_CAR_DATE] [datetime] NULL ,
	[ESTIMATE_DATE] [datetime] NULL ,                                 --2010-6-7 EDIT 新增  ESTIMATE_DATE
	[USER_ID] [int] NULL ,
	[FIX_TYPE_ID] [int] NULL ,
	[CUSTOMER_ID] [int] NULL ,
	[CAR_INFO_ID] [int] NULL ,
	[OIL_METER] [numeric](10, 2) NULL ,
	[ENTER_STATION_KILO] [numeric](10, 2) NULL ,
	[OUT_STATION_KILO] [numeric](10, 2) NULL ,
	[REMIND_MAINTAIN_KILO] [numeric](10, 2) NULL ,
	[REMIND_MAINTAIN_DATE] [datetime] NULL ,
	[WRONG_DESCRIBE] [varchar] (1000) COLLATE Chinese_PRC_CI_AS NULL ,
	[BEFORE_FIX_STATE] [varchar] (1000) COLLATE Chinese_PRC_CI_AS NULL ,
	[CHECK_RESULT] [varchar] (1000) COLLATE Chinese_PRC_CI_AS NULL ,
	[REMARK] [varchar] (1000) COLLATE Chinese_PRC_CI_AS NULL ,
	[IS_FINISH] [int] NULL ,
	[WORKING_HOUR_PRICE] [numeric](10, 2) NULL ,
	[ISVALID] [int] NULL ,
	[ENTRUST_STATUS] [int] NULL                                         --2010-6-18 added
) ON [PRIMARY]
GO

--TB_FIX_ENTRUST_CONTENT--委托修理                   --2010-6-9 add 
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TB_FIX_ENTRUST_CONTENT]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TB_FIX_ENTRUST_CONTENT]
GO

CREATE TABLE [dbo].[TB_FIX_ENTRUST_CONTENT] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[ENTRUST_ID] [int] NULL ,
	[STATION_ID] [int] NULL ,
	[FIX_HOUR] [numeric](10, 2) NULL ,
	[WORKING_HOUR_PRICE] [numeric](10, 2) NULL ,
	[FIX_HOUR_ALL] [numeric](10, 2) NULL ,
	[SEND_HOUR] [numeric](10, 2) NULL ,
	[FREESYMBOL] [int] NULL  ,
	[BALANCE_ID] [int] NULL 
) ON [PRIMARY]
GO

--TB_FIX_SHARE--修理人员工时分配                   --2010-6-9 add 
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TB_FIX_SHARE]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TB_FIX_SHARE]
GO

CREATE TABLE [dbo].[TB_FIX_SHARE] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,         -- 2010-6-10 edit
	[FIX_ENTRUST_CONTENT_ID] [int] NULL ,
	[SEND_HOUR] [numeric](10, 2) NULL ,
	[FIX_PERSON_ID] [int] NULL 
) ON [PRIMARY]
GO


--TB_ANVANCE_PAY--预付款登记                             --2010--6-16 add
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TB_ANVANCE_PAY]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TB_ANVANCE_PAY]
GO

CREATE TABLE [dbo].[TB_ANVANCE_PAY] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[ANVANCE_CODE] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,
	[CAR_INFO_ID] [int] NULL ,
	[PAY_DAY] [datetime] NULL ,
	[PAY_AMOUNT] [numeric](10, 2) NULL ,
	[PAY_PATTERN] [int] NULL ,
	[CHEQUE_CODE] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[REMARK] [varchar] (1000) COLLATE Chinese_PRC_CI_AS NULL ,
	[USER_ID] [int] NULL 
) ON [PRIMARY]
GO


--TB_BUSINESS_BALANCE_ITEM--结算项目明细清单  --2010-6-23
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TB_BUSINESS_BALANCE_ITEM]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TB_BUSINESS_BALANCE_ITEM]
GO

CREATE TABLE [dbo].[TB_BUSINESS_BALANCE_ITEM] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[BALANCE_ID] [int] NULL ,
	[BALANCE_ITEM_CODE] [varchar] (10) COLLATE Chinese_PRC_CI_AS NULL ,
	[BALANCE_ITEM_NAME] [varchar] (10) COLLATE Chinese_PRC_CI_AS NULL ,
	[BALANCE_ITEM_TOTAL] [numeric](10, 2) NULL ,
	[BALANCE_STATUS] [int] NULL
) ON [PRIMARY]
GO


--TB_PART_INFO-- 配件信息表 
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TB_PART_INFO]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TB_PART_INFO]
GO
CREATE TABLE [dbo].[TB_PART_INFO] (

	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[STORE_HOUSE_ID] [int] NULL ,
	[CAR_MODEL_TYPE_ID] [int] NULL ,
	[PART_CODE] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[PART_NAME] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[PINYIN_CODE] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[UNIT_ID] [int] NULL ,
	[PART_TYPE_ID] [int] NULL ,
	[PART_BROAD_TYPE] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,
	[STORE_LOCATION] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,
	[FACTORY_CODE] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,
	[DANGER_CODE] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,
	[MAX_STORE_QUANTITY] [numeric](10, 2) NULL ,
	[MIN_STORE_QUANTITY] [numeric](10, 2) NULL ,
	[STORE_QUANTITY] [numeric](10, 2) NULL ,
	[COST_PRICE] [numeric](10, 2) NULL ,
	[STOCK_MONEY] [numeric](18, 0) NULL ,
	[STOCK_PRICE] [numeric](18, 0) NULL ,
	[LIANCE_QUANTITY] [numeric](10, 2) NULL ,
	[LOAN_QUANTITY] [numeric](10, 2) NULL ,
	[SELL_PRICE] [numeric](10, 2) NULL 
) ON [PRIMARY]
GO





--TM_LIANCE_REGISTER--借进登记
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_LIANCE_REGISTER]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_LIANCE_REGISTER]
GO
CREATE TABLE [dbo].[TM_LIANCE_REGISTER] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[liance_bill] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[SUPPLIER_id] [int] NULL ,
	[liance_date] [datetime] NULL ,
	[user_id] [int] NULL ,
	[create_date] [datetime] NULL ,
	[duty_people] [int] NULL ,
	[total_price] [numeric](10, 2) NULL ,
	[total_quantity] [numeric](18, 0) NULL ,
	[is_confirm] [int] NULL ,
	[is_return] [int] NULL 
) ON [PRIMARY]
GO

--TM_LIANCE_REGISTER_DETAIL--借进登记明细
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_LIANCE_REGISTER_DETAIL]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_LIANCE_REGISTER_DETAIL]
GO
CREATE TABLE [dbo].[TM_LIANCE_REGISTER_DETAIL] (
	[id] [int] IDENTITY (1, 1) NOT NULL ,
	[liance_id] [int] NULL ,
	[partinfo_id] [int] NULL ,
	[liance_quantity] [numeric](18, 0) NULL ,
	[liance_price] [numeric](18, 0) NULL ,
	[old_cost_price] [numeric](18, 0) NULL ,
	[return_quantity] [numeric](18, 0) NULL ,
	[is_return] [int] NULL 
) ON [PRIMARY]
GO

--TM_LIANCE_RETURN--借进归还登记
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_LIANCE_RETURN]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_LIANCE_RETURN]
GO
CREATE TABLE [dbo].[TM_LIANCE_RETURN] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[LIANCE_RETURN_BILL] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[USER_ID] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[CREATE_DATE] [datetime] NULL ,
	[RETURN_DATE] [datetime] NULL ,
	[IS_CONFIRM] [int] NULL 
) ON [PRIMARY]
GO

--TM_LIANCE_RETURN_DETAIL--借进归还明细
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_LIANCE_RETURN_DETAIL]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_LIANCE_RETURN_DETAIL]
GO
CREATE TABLE [dbo].[TM_LIANCE_RETURN_DETAIL] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[LIANCE_RETURN_ID] [int] NULL ,
	[LIANCE_REGDETAIL_ID] [int] NULL ,
	[LIANCE_BILL] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[RETURN_QUANTITY] [numeric](10, 2) NULL ,
	[LACK_RETURN_QUANTITY] [numeric](10, 2) NULL ,
	[IS_RETURN] [int] NULL ,
	[COST_PRICE] [numeric](10, 2) NULL 
) ON [PRIMARY]
GO

--TM_LOAN_REGISTER--借出登记
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_LOAN_REGISTER]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_LOAN_REGISTER]
GO
CREATE TABLE [dbo].[TM_LOAN_REGISTER] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[LOAN__BILL] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[LOAN_DATE] [datetime] NULL ,
	[CUSTOMER_ID] [int] NULL ,
	[USER_ID] [int] NULL ,
	[CREATE_DATE] [datetime] NULL ,
	[TOTAL_QUANTITY] [numeric](10, 2) NULL ,
	[TOTAL_PRICE] [numeric](10, 2) NULL ,
	[IS_CONFIRM] [int] NULL ,
	[IS_RETURN] [int] NULL 
) ON [PRIMARY]
GO

--TM_LOAN_REGISTER_DETAIL--借出登记明细
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_LOAN_REGISTER_DETAIL]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_LOAN_REGISTER_DETAIL]
GO
CREATE TABLE [dbo].[TM_LOAN_REGISTER_DETAIL] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[LOAN_ID] [int] NULL ,
	[PARTINFO_ID] [int] NULL ,
	[LOAN_PRICE] [numeric](10, 2) NULL ,
	[LOAN_QUANTITY] [numeric](10, 2) NULL ,
	[OLD_COST_PRICE] [numeric](10, 2) NULL ,
	[RETURN_QUANTITY] [numeric](10, 2) NULL ,
	[IS_RETURN] [int] NULL 
) ON [PRIMARY]
GO

--TM_LOAN_RETURN--借出归还
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_LOAN_RETURN]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_LOAN_RETURN]
GO
CREATE TABLE [dbo].[TM_LOAN_RETURN] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[LOAN_RETURN_BILL] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[RETURN_DATE] [datetime] NULL ,
	[USER_ID] [int] NULL ,
	[CREATE_DATE] [datetime] NULL ,
	[IS_CONFIRM] [int] NULL 
) ON [PRIMARY]
GO

--TM_LOAN_RETURN_DETAIL--借出归还明细
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_LOAN_RETURN_DETAIL]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_LOAN_RETURN_DETAIL]
GO
CREATE TABLE [dbo].[TM_LOAN_RETURN_DETAIL] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[LOAN_RETURN_ID] [int] NULL ,
	[LOAN_REGDETAIL_ID] [int] NULL ,
	[LOAN_REG_BILL] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[LACK_RETURN_QUANTITY] [numeric](10, 2) NULL ,
	[RETURN_QUANTITY] [numeric](10, 2) NULL ,
	[COST_PRICE] [numeric](10, 2) NULL ,
	[IS_RETURN] [int] NULL ,
	[LOAN_RETURN_PRICE] [numeric](10, 2) NULL 
) ON [PRIMARY]
GO


--TM_REMOVE_STOCK--移库出仓
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_REMOVE_STOCK]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_REMOVE_STOCK]
GO
CREATE TABLE [dbo].[TM_REMOVE_STOCK] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[REMOVE_STOCK_BILL] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[REMOVE_QUANTITY] [numeric](10, 2) NULL ,
	[REMOVE_PRICE] [numeric](10, 2) NULL ,
	[REMOVE_DATE] [datetime] NULL ,
	[USER_ID] [int] NULL ,
	[CREATE_DATE] [datetime] NULL ,
	[IS_VALID] [int] NULL ,
	[STORE_HOSE_ID] [int] NULL ,
	[IS_CONFIRM] [int] NULL ,
) ON [PRIMARY]
GO

--TM_REMOVE_STOCK_DETAIL--移库出仓细
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_REMOVE_STOCK_DETAIL]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_REMOVE_STOCK_DETAIL]
GO
CREATE TABLE [dbo].[TM_REMOVE_STOCK_DETAIL] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[REMOVE_STOCK_ID] [int] NULL ,
	[PARTINFO_ID] [int] NULL ,
	[QUANTITY] [numeric](10, 2) NULL ,
	[COST_PRICE] [numeric](10, 2) NULL 
) ON [PRIMARY]
GO


--TM_SHIFTIN_STOCK--移库进仓		
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_SHIFTIN_STOCK]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_SHIFTIN_STOCK]
GO
CREATE TABLE [dbo].[TM_SHIFTIN_STOCK] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[SHIFTIN_BILL] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[SHIFTIN_DATE] [datetime] NULL ,
	[STORE_HOSE_ID] [int] NULL ,
	[REMOVE_STOCK_ID] [int] NULL ,
	[USER_ID] [int] NULL ,
	[CREATE_DATE] [datetime] NULL ,
	[IS_CONFIRM] [int] NULL 
) ON [PRIMARY]
GO

--TM_STOCKOUT_DETAIL--出库明细
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_STOCKOUT_DETAIL]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_STOCKOUT_DETAIL]
GO

CREATE TABLE [dbo].[TM_STOCKOUT_DETAIL] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[PARTINFO_ID] [int] NULL ,
	[STOCKOUT_ID] [int] NULL ,
	[QUANTITY] [numeric](10, 2) NULL ,
	[PRICE] [numeric](10, 2) NULL ,
	[STOCK_IN_DETAIL_ID] [int] NULL ,
	[IS_FREE] [int] NULL ,
	[BALANCE_ID] [int] NULL 
) ON [PRIMARY]
GO

--TM_STOCK_OUT--出库
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_STOCK_OUT]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_STOCK_OUT]
GO

CREATE TABLE [dbo].[TM_STOCK_OUT] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[STOCK_OUT_CODE] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[CUSTOMER_BILL] [int] NULL ,
	[TRUST_BILL] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[SOLE_TYPE_ID] [int] NULL ,
	[DRAW_PEOPLE] [int] NULL ,
	[PROFITLOSS_BILL] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[STOCK_OUT_DATE] [datetime] NULL ,
	[USER_ID] [int] NULL ,
	[CREATE_DATE] [datetime] NULL ,
	[TOTAL_QUANTITY] [numeric](10, 2) NULL ,
	[TOTAL_PRICE] [numeric](10, 2) NULL ,
	[OUT_TYPE] [int] NULL ,
	[IS_CONFIRM] [int] NULL ,
	[STOCKIN_ID] [int] NULL ,
	[SELL_BALANCE][int] NULL
) ON [PRIMARY]
GO

--TM_SOLE_TYPE--销售类型
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_SOLE_TYPE]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_SOLE_TYPE]
GO

CREATE TABLE [dbo].[TM_SOLE_TYPE] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[SOLE_NAME] [varchar] (10) COLLATE Chinese_PRC_CI_AS NULL ,
	[IS_DEFAULT] [int] NULL 
) ON [PRIMARY]
GO

--TM_STOCKIN_DETAIL--入库明细
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_STOCKIN_DETAIL]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_STOCKIN_DETAIL]
GO

CREATE TABLE [dbo].[TM_STOCKIN_DETAIL] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[partinfo_id] [int] NULL ,
	[stock_id] [int] NULL ,
	[quantity] [numeric](10, 2) NULL ,
	[price] [numeric](10, 2) NULL ,
	[STOCKOUT_DETAIL_ID] [int] NULL 
) ON [PRIMARY]
GO
--TM_STOCK_IN--入库
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_STOCK_IN]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_STOCK_IN]
GO
CREATE TABLE [dbo].[TM_STOCK_IN] (
	[id] [int] IDENTITY (1, 1) NOT NULL ,
	[stock_in_code] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[SUPPLIER_id] [int] NULL ,
	[user_id] [int] NULL ,
	[create_date] [datetime] NULL ,
	[total_quantity] [numeric](10, 2) NULL ,
	[total_price] [numeric](10, 2) NULL ,
	[in_type] [int] NULL ,
	[indent] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[oucher_code] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[oucher_no] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[business_code] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[arrive_date] [datetime] NULL ,
	[is_confirm] [int] NULL ,
	[PROFITLOSS_BILL] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[STOCKOUT_ID] [int] NULL 
) ON [PRIMARY]
GO

--TB_BUSINESS_BALANCE_ITEM--结算项目明细清单  --2010-6-23
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TB_BUSINESS_BALANCE_ITEM]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TB_BUSINESS_BALANCE_ITEM]
GO

CREATE TABLE [dbo].[TB_BUSINESS_BALANCE_ITEM] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[BALANCE_ID] [int] NULL ,
	[BALANCE_ITEM_CODE] [varchar] (10) COLLATE Chinese_PRC_CI_AS NULL ,
	[BALANCE_ITEM_NAME] [varchar] (10) COLLATE Chinese_PRC_CI_AS NULL ,
	[BALANCE_ITEM_TOTAL] [numeric](10, 2) NULL 
) ON [PRIMARY]
GO


if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TB_BUSINESS_BALANCE]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TB_BUSINESS_BALANCE]
GO

CREATE TABLE [dbo].[TB_BUSINESS_BALANCE] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[BALANCE_CODE] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,
	[ENTRUST_ID] [int] NULL ,
	[BANANCE_DATE] [datetime] NULL ,
	[STOCK_OUT_ID] [int] NULL ,
	[WORKING_HOUR_FAVOUR_RATE] [numeric](10, 2) NULL ,
	[WORKING_HOUR_TOTAL_ALL] [numeric](10, 2) NULL ,
	[FIX_PART_FAVOUR_RATE] [numeric](10, 2) NULL ,
	[FIX_PART_TOTAL_ALL] [numeric](10, 2) NULL ,
	[SOLE_PART_FAVOUR_RATE] [numeric](10, 2) NULL ,
	[SOLE_PART_TOTAL_ALL] [numeric](10, 2) NULL ,
	[BALANCE_TOTAL_ALL] [numeric](10, 2) NULL ,
	[USER_ID] [int] NULL ,
	[OLD_PART_DEAL] [int] NULL ,
	[PAY_PATTERN] [int] NULL ,
	[CHEQUE_CODE] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[BALANCE_DEAD_TIME] [datetime] NULL ,
	[REMARK] [varchar] (1000) COLLATE Chinese_PRC_CI_AS NULL ,
	[SAVE_AMOUNT] [numeric](10, 2) NULL ,
	[USED_SAVE_AMOUNT] [numeric](10, 2) NULL ,
	[CASH_AMOUNT] [numeric](10, 2) NULL ,
	[PAYED_AMOUNT] [numeric](10, 2) NULL ,
	[SHOULD_PAY_AMOUNT] [numeric](10, 2) NULL,
	[BALANCE_STATUS] [int] NULL 
) ON [PRIMARY]
GO

--TB_MAINTAIN_PART_CONTENT--维修发料
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TB_MAINTAIN_PART_CONTENT]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TB_MAINTAIN_PART_CONTENT]
GO

CREATE TABLE [dbo].[TB_MAINTAIN_PART_CONTENT] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[PART_ID] [int] NULL ,
	[PART_QUANTITY] [numeric](10, 2) NULL ,
	[PRICE] [numeric](10, 2) NULL ,
	[TOTAL_PRICE] [numeric](10, 2) NULL ,
	[CLIAM_PART_PERSON_ID] [int] NULL ,
	[ENTRUST_ID] [int] NULL ,
	[MAINTAIN_CODE] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[IS_CONFIRM] [int] NULL ,
	[IS_FREE] [int] NULL ,
	[BALANCE_ID] [int] NULL,
	[STOCK_OUT_DATE] [datetime] NULL
) ON [PRIMARY]
GO

--TB_MAINTAIN_PART_CONTENT--预约配件
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TB_BESPOKE_PART_CONTENT]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TB_BESPOKE_PART_CONTENT]
GO

CREATE TABLE [dbo].[TB_BESPOKE_PART_CONTENT] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[PART_ID] [int] NULL ,
	[PART_QUANTITY] [numeric](10, 2) NULL ,
	[PRICE] [numeric](10, 2) NULL ,
	[TOTAL_PRICE] [numeric](18, 0) NULL ,
	[ENTRUST_ID] [int] NULL 
) ON [PRIMARY]
GO


--TM_RESOURCE----资源树
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_RESOURCE]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_RESOURCE]
GO

CREATE TABLE [dbo].[TM_RESOURCE] (
	[id] [int]  NOT NULL ,
	[parent_id] [int] NULL ,
	[resource_name] [varchar] (100) COLLATE Chinese_PRC_CI_AS NULL ,
	[resource_path] [varchar] (100) COLLATE Chinese_PRC_CI_AS NULL ,
	[resource_desc] [varchar] (1000) COLLATE Chinese_PRC_CI_AS NULL ,
	[is_leaf] [int] NOT NULL 
) ON [PRIMARY]
GO

--TM_ROLE----角色表
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_ROLE]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_ROLE]
GO

CREATE TABLE [dbo].[TM_ROLE] (
	[id] [int] IDENTITY (1, 1) NOT NULL ,
	[role_name] [varchar] (100) COLLATE Chinese_PRC_CI_AS NULL ,
	[role_note] [varchar] (1000) COLLATE Chinese_PRC_CI_AS NULL ,
	[role_code] [varchar] (10) COLLATE Chinese_PRC_CI_AS NOT NULL 
) ON [PRIMARY]
GO

--TM_ROLE_RESOURCE----角色资源关联表
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_ROLE_RESOURCE]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_ROLE_RESOURCE]
GO
CREATE TABLE [dbo].[TM_ROLE_RESOURCE] (
	[id] [int] IDENTITY (1, 1) NOT NULL ,
	[role_id] [int] NOT NULL ,
	[resource_id] [int] NOT NULL 
) ON [PRIMARY]
GO


--TM_USER_ROLE ---- 用户角色关联表
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_USER_ROLE]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_USER_ROLE]
GO
CREATE TABLE [dbo].[TM_USER_ROLE] (
	[id] [int] IDENTITY (1, 1) NOT NULL ,
	[role_id] [int] NOT NULL ,
	[user_id] [int] NOT NULL 
) ON [PRIMARY]
GO


--TM_PERMISSION--角色权限					--2010-6-16 add
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_PERMISSION]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_PERMISSION]
GO

CREATE TABLE [dbo].[TM_PERMISSION] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[ROLE_RESOURCE_ID] [int] NULL ,
	[PERMISSION_TYPE] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
GO



--TM_USER-- 用户表
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_USER]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_USER]
GO

CREATE TABLE [dbo].[TM_USER] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[USER_NAME] [varchar] (10) COLLATE Chinese_PRC_CI_AS NULL ,
	[PASSWORD] [varchar] (10) COLLATE Chinese_PRC_CI_AS NULL ,
	[JOB_CODE] [varchar] (10) COLLATE Chinese_PRC_CI_AS NULL ,
	[USER_REAL_NAME] [varchar] (10) COLLATE Chinese_PRC_CI_AS NULL ,
	[SEX] [int] NULL ,
	[DEPT_ID] [int] NULL ,
	[WORK_TYPE_ID] [int] NULL ,
	[edu] [int] NULL ,
	[TEC_LEVEL] [varchar] (10) COLLATE Chinese_PRC_CI_AS NULL ,
	[IS_FIX_PERSON] [int] NULL ,
	[PHONE] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,
	[TELEPHONE] [char] (11) COLLATE Chinese_PRC_CI_AS NULL ,
	[BIRTHDAY] [datetime] NULL ,
	[ZIP_CODE] [char] (6) COLLATE Chinese_PRC_CI_AS NULL ,
	[SALARY] [numeric](10, 2) NULL ,
	[START_WORK_TIME] [datetime] NULL ,
	[ENTER_STATION_TIME] [datetime] NULL ,
	[ADDRESS] [varchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,
	[CV] [varchar] (1000) COLLATE Chinese_PRC_CI_AS NULL ,
	[TRAIN_INFO] [varchar] (1000) COLLATE Chinese_PRC_CI_AS NULL ,
	[CREATE_DATE] [datetime] NULL 
) ON [PRIMARY]
GO