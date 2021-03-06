if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[FK_TM_ROLE_RESOURCE_TM_ROLE]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)
ALTER TABLE [dbo].[TM_ROLE_RESOURCE] DROP CONSTRAINT FK_TM_ROLE_RESOURCE_TM_ROLE
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_BACK_MENU]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_BACK_MENU]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_ROLE]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_ROLE]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_ROLE_RESOURCE]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_ROLE_RESOURCE]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_USER]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_USER]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_USER_ROLE]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_USER_ROLE]
GO

CREATE TABLE [dbo].[TM_BACK_MENU] (
	[id] [int] IDENTITY (1, 1) NOT NULL ,
	[parent_id] [int] NULL ,
	[tree_name] [varchar] (100) COLLATE Chinese_PRC_CI_AS NULL ,
	[url] [varchar] (500) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[TM_ROLE] (
	[id] [int] IDENTITY (1, 1) NOT NULL ,
	[role_name] [varchar] (100) COLLATE Chinese_PRC_CI_AS NULL ,
	[role_note] [varchar] (1000) COLLATE Chinese_PRC_CI_AS NULL ,
	[role_code] [varchar] (10) COLLATE Chinese_PRC_CI_AS NOT NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[TM_ROLE_RESOURCE] (
	[id] [int] IDENTITY (1, 1) NOT NULL ,
	[role_id] [int] NOT NULL ,
	[resource_id] [int] NOT NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[TM_USER] (
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[USER_NAME] [varchar] (10) COLLATE Chinese_PRC_CI_AS NOT NULL ,
	[PASSWORD] [varchar] (10) COLLATE Chinese_PRC_CI_AS NOT NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[TM_USER_ROLE] (
	[id] [int] IDENTITY (1, 1) NOT NULL ,
	[role_id] [int] NOT NULL ,
	[user_id] [int] NOT NULL 
) ON [PRIMARY]
GO

