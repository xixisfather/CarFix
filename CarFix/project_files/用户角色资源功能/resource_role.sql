if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[FK_TM_ROLE_RESOURCE_TM_ROLE]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)
ALTER TABLE [dbo].[TM_ROLE_RESOURCE] DROP CONSTRAINT FK_TM_ROLE_RESOURCE_TM_ROLE
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_RESOURCE]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_RESOURCE]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_ROLE]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_ROLE]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[TM_ROLE_RESOURCE]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[TM_ROLE_RESOURCE]
GO

CREATE TABLE [dbo].[TM_RESOURCE] (
	[id] [int] IDENTITY (1, 1) NOT NULL ,
	[parent_id] [int] NULL ,
	[resource_name] [varchar] (100) COLLATE Chinese_PRC_CI_AS NULL ,
	[resource_path] [varchar] (100) COLLATE Chinese_PRC_CI_AS NULL ,
	[resource_desc] [varchar] (1000) COLLATE Chinese_PRC_CI_AS NULL ,
	[is_leaf] [int] NOT NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[TM_ROLE] (
	[id] [int] NOT NULL ,
	[role_name] [varchar] (100) COLLATE Chinese_PRC_CI_AS NULL ,
	[role_note] [varchar] (1000) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[TM_ROLE_RESOURCE] (
	[id] [int] NOT NULL ,
	[role_id] [int] NOT NULL ,
	[resource_id] [int] NOT NULL 
) ON [PRIMARY]
GO

