USE [cms]
GO
/****** Object:  Table [CMSWEB].[ASSIGNMENT_T]    Script Date: 11/15/2016 3:41:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [CMSWEB].[ASSIGNMENT_T](
	[OBJECTID] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
	[ASSIG_TYPE_OID] [numeric](18, 0) NOT NULL,
	[COURSE_OFFER_OID] [numeric](18, 0) NOT NULL,
	[GRADE_OID] [numeric](18, 0) NULL,
	[REMARKS] [nchar](1000) NULL,
	[ROW_CREATED_DATETIME] [datetime] NOT NULL,
	[ROW_CREATED_BY] [nchar](100) NOT NULL,
	[ROW_LAST_CHANGED_DATETIME] [datetime] NOT NULL,
	[ROW_LAST_CHANGED_BY] [nchar](100) NOT NULL
) ON [PRIMARY]

GO
/****** Object:  Table [CMSWEB].[CODE_VALUE_T]    Script Date: 11/15/2016 3:41:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [CMSWEB].[CODE_VALUE_T](
	[OBJECTID] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
	[CODE] [nchar](50) NOT NULL,
	[CODE_GROUP] [nchar](50) NOT NULL,
	[SHORT_DESCRIPTION] [nchar](100) NOT NULL,
	[LONG_DESCRIPTION] [nchar](100) NULL,
	[EFFECTIVE_DATETIME] [datetime] NULL,
	[EFFECTIVE_THRU_DATETIME] [datetime] NULL,
	[ROW_CREATED_DATETIME] [datetime] NOT NULL,
	[ROW_CREATED_BY] [nchar](100) NOT NULL,
	[ROW_LAST_CHANGED_DATETIME] [datetime] NOT NULL,
	[ROW_LAST_CHANGED_BY] [nchar](100) NOT NULL,
 CONSTRAINT [PK_CODE_VALUE_T] PRIMARY KEY CLUSTERED 
(
	[OBJECTID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [CMSWEB].[COURSE_OFFER_T]    Script Date: 11/15/2016 3:41:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [CMSWEB].[COURSE_OFFER_T](
	[OBJECTID] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
	[COURSE_OID] [numeric](18, 0) NULL,
	[SEMESTER_OID] [numeric](18, 0) NULL,
	[FACULTY_OID] [numeric](18, 0) NULL,
	[LOCATION_OID] [numeric](18, 0) NULL,
	[START_Date] [datetime] NOT NULL,
	[END_Date] [datetime] NOT NULL,
	[ROW_CREATED_DATETIME] [datetime] NOT NULL,
	[ROW_CREATED_BY] [nchar](100) NOT NULL,
	[ROW_LAST_CHANGED_DATETIME] [datetime] NOT NULL,
	[ROW_LAST_CHANGED_BY] [nchar](100) NOT NULL,
 CONSTRAINT [PK_COURSE_OFFER_T] PRIMARY KEY CLUSTERED 
(
	[OBJECTID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [CMSWEB].[COURSE_RELATE_T]    Script Date: 11/15/2016 3:41:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [CMSWEB].[COURSE_RELATE_T](
	[OBJECTID] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
	[COURSE_OID] [numeric](18, 0) NULL,
	[STUDENT_OID] [numeric](18, 0) NULL,
	[FACULTY_OID] [numeric](18, 0) NULL,
	[SEMESTER_OID] [numeric](18, 0) NULL,
	[ROW_CREATED_DATETIME] [datetime] NOT NULL,
	[ROW_CREATED_BY] [nchar](100) NOT NULL,
	[ROW_LAST_CHANGED_DATETIME] [datetime] NOT NULL,
	[ROW_LAST_CHANGED_BY] [nchar](100) NOT NULL,
 CONSTRAINT [PK_COURSE_RELATE_T] PRIMARY KEY CLUSTERED 
(
	[OBJECTID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [CMSWEB].[COURSE_T]    Script Date: 11/15/2016 3:41:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [CMSWEB].[COURSE_T](
	[OBJECTID] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
	[CODE] [nchar](50) NULL,
	[TITLE] [nchar](500) NULL,
	[DESCRIPTION] [nchar](1000) NULL,
	[SYLLABUS] [binary](1) NULL,
	[ROW_CREATED_DATETIME] [datetime] NOT NULL,
	[ROW_CREATED_BY] [nchar](100) NOT NULL,
	[ROW_LAST_CHANGED_DATETIME] [datetime] NOT NULL,
	[ROW_LAST_CHANGED_BY] [nchar](100) NOT NULL,
 CONSTRAINT [PK_COURSE_T] PRIMARY KEY CLUSTERED 
(
	[OBJECTID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [CMSWEB].[Enrollment_T]    Script Date: 11/15/2016 3:41:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [CMSWEB].[Enrollment_T](
	[OBJECTID] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
	[STUDENT_OID] [numeric](18, 0) NULL,
	[COURSE_OFFER_OID] [numeric](18, 0) NULL,
	[Enrollment_Date] [datetime] NOT NULL,
	[ROW_CREATED_DATETIME] [datetime] NOT NULL,
	[ROW_CREATED_BY] [nchar](100) NOT NULL,
	[ROW_LAST_CHANGED_DATETIME] [datetime] NOT NULL,
	[ROW_LAST_CHANGED_BY] [nchar](100) NOT NULL,
 CONSTRAINT [PK_Enrollment_T] PRIMARY KEY CLUSTERED 
(
	[OBJECTID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [CMSWEB].[FACULTY_T]    Script Date: 11/15/2016 3:41:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [CMSWEB].[FACULTY_T](
	[OBJECTID] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
	[FIRST_NAME] [nchar](50) NOT NULL,
	[MIDDLE_NAME] [nchar](50) NULL,
	[LAST_NAME] [nchar](50) NOT NULL,
	[BIRTHDATE] [datetime] NULL,
	[GENDER_OID] [numeric](18, 0) NULL,
	[PREFIX_OID] [numeric](18, 0) NULL,
	[FACULTY_TYPE_OID] [numeric](18, 0) NULL,
	[FACULTY_STATUS_OID] [numeric](18, 0) NULL,
	[CLASS_STATUS_OID] [numeric](18, 0) NULL,
	[ROW_CREATED_DATETIME] [datetime] NOT NULL,
	[ROW_CREATED_BY] [nchar](100) NOT NULL,
	[ROW_LAST_CHANGED_DATETIME] [datetime] NOT NULL,
	[ROW_LAST_CHANGED_BY] [nchar](100) NOT NULL,
 CONSTRAINT [PK_FACULTY_T] PRIMARY KEY CLUSTERED 
(
	[OBJECTID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [CMSWEB].[GRADE_T]    Script Date: 11/15/2016 3:41:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [CMSWEB].[GRADE_T](
	[OBJECTID] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
	[COURSE_OID] [numeric](18, 0) NOT NULL,
	[FACULTY_OID] [numeric](18, 0) NULL,
	[STUDENT_OID] [numeric](18, 0) NOT NULL,
	[SEMESTER_OID] [numeric](18, 0) NOT NULL,
	[GRADE_TYPE_OID] [numeric](18, 0) NOT NULL,
	[TOTAL_POINT] [nchar](10) NOT NULL,
	[ACTUAL_POINT] [nchar](10) NOT NULL,
	[ROW_CREATED_DATETIME] [datetime] NOT NULL,
	[ROW_CREATED_BY] [nchar](100) NOT NULL,
	[ROW_LAST_CHANGED_DATETIME] [datetime] NOT NULL,
	[ROW_LAST_CHANGED_BY] [nchar](100) NOT NULL
) ON [PRIMARY]

GO
/****** Object:  Table [CMSWEB].[ID_T]    Script Date: 11/15/2016 3:41:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [CMSWEB].[ID_T](
	[OBJECTID] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
	[ID_TYPE_OID] [numeric](18, 0) NULL,
	[STUDENT_OID] [numeric](18, 0) NULL,
	[FACULTY_OID] [numeric](18, 0) NULL,
	[VALUE] [nchar](50) NULL,
	[EXPIRY_DATE] [datetime] NOT NULL,
	[ROW_CREATED_DATETIME] [datetime] NOT NULL,
	[ROW_CREATED_BY] [nchar](100) NOT NULL,
	[ROW_LAST_CHANGED_DATETIME] [datetime] NOT NULL,
	[ROW_LAST_CHANGED_BY] [nchar](100) NOT NULL,
 CONSTRAINT [PK_ID_T] PRIMARY KEY CLUSTERED 
(
	[OBJECTID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [CMSWEB].[ROLE_T]    Script Date: 11/15/2016 3:41:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [CMSWEB].[ROLE_T](
	[OBJECTID] [numeric](18, 0) NOT NULL,
	[ROLE_NAME] [nchar](50) NOT NULL,
	[EFFECTIVE_DATETIME] [datetime] NULL,
	[EFFECTIVE_THRU_DATETIME] [datetime] NULL,
	[ROW_CREATED_DATETIME] [datetime] NOT NULL,
	[ROW_CREATED_BY] [nchar](100) NOT NULL,
	[ROW_LAST_CHANGED_DATETIME] [datetime] NOT NULL,
	[ROW_LAST_CHANGED_BY] [nchar](100) NOT NULL,
 CONSTRAINT [PK_ROLE_T] PRIMARY KEY CLUSTERED 
(
	[OBJECTID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [CMSWEB].[SEMESTER_T]    Script Date: 11/15/2016 3:41:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [CMSWEB].[SEMESTER_T](
	[OBJECTID] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
	[SEMESTER_TYPE_OID] [numeric](18, 0) NULL,
	[SEMESTER_YEAR] [nchar](10) NOT NULL,
	[SEM_START_DATE] [datetime] NOT NULL,
	[SEM_END_DATE] [datetime] NOT NULL,
	[ROW_CREATED_DATETIME] [datetime] NOT NULL,
	[ROW_CREATED_BY] [nchar](100) NOT NULL,
	[ROW_LAST_CHANGED_DATETIME] [datetime] NOT NULL,
	[ROW_LAST_CHANGED_BY] [nchar](100) NOT NULL
) ON [PRIMARY]

GO
/****** Object:  Table [CMSWEB].[STUDENT_T]    Script Date: 11/15/2016 3:41:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [CMSWEB].[STUDENT_T](
	[OBJECTID] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
	[STUDENT_ID] [nchar](50) NOT NULL,
	[FIRST_NAME] [nchar](50) NOT NULL,
	[MIDDLE_NAME] [nchar](50) NULL,
	[LAST_NAME] [nchar](50) NOT NULL,
	[BIRTHDATE] [datetime] NULL,
	[GENDER_OID] [numeric](18, 0) NULL,
	[STUDENT_TYPE_OID] [numeric](18, 0) NULL,
	[STUDENT_STATUS_OID] [numeric](18, 0) NULL,
	[CLASS_STATUS_OID] [numeric](18, 0) NULL,
	[ROW_CREATED_DATETIME] [datetime] NOT NULL,
	[ROW_CREATED_BY] [nchar](100) NOT NULL,
	[ROW_LAST_CHANGED_DATETIME] [datetime] NOT NULL,
	[ROW_LAST_CHANGED_BY] [nchar](100) NOT NULL,
 CONSTRAINT [PK_STUDENT_T] PRIMARY KEY CLUSTERED 
(
	[OBJECTID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [CMSWEB].[USER_ROLE_T]    Script Date: 11/15/2016 3:41:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [CMSWEB].[USER_ROLE_T](
	[OBJECTID] [numeric](18, 0) NOT NULL,
	[USER_OID] [numeric](18, 0) NOT NULL,
	[ROLE_OID] [numeric](18, 0) NOT NULL,
 CONSTRAINT [PK_USER_ROLE_T] PRIMARY KEY CLUSTERED 
(
	[OBJECTID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [CMSWEB].[USER_T]    Script Date: 11/15/2016 3:41:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [CMSWEB].[USER_T](
	[OBJECTID] [numeric](18, 0) NOT NULL,
	[USERNAME] [nchar](50) NOT NULL,
	[PASSWORD] [nchar](150) NOT NULL,
	[ENABLED_FLAG] [nchar](1) NOT NULL,
	[ROW_CREATED_DATETIME] [datetime] NOT NULL,
	[ROW_CREATED_BY] [nchar](100) NOT NULL,
	[ROW_LAST_CHANGED_DATETIME] [datetime] NOT NULL,
	[ROW_LAST_CHANGED_BY] [nchar](100) NOT NULL,
 CONSTRAINT [PK_USER_T] PRIMARY KEY CLUSTERED 
(
	[OBJECTID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [CMSWEB].[VACATION_T]    Script Date: 11/15/2016 3:41:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [CMSWEB].[VACATION_T](
	[OBJECTID] [numeric](18, 0) IDENTITY(1,1) NOT NULL,
	[SEMESTER_OID] [numeric](18, 0) NOT NULL,
	[FROM_DATE] [datetime] NOT NULL,
	[TO_DATE] [datetime] NOT NULL,
	[NO_OF_DAYS] [numeric](10, 0) NOT NULL,
	[ROW_CREATED_DATETIME] [datetime] NOT NULL,
	[ROW_CREATED_BY] [nchar](100) NOT NULL,
	[ROW_LAST_CHANGED_DATETIME] [datetime] NOT NULL,
	[ROW_LAST_CHANGED_BY] [nchar](100) NOT NULL
) ON [PRIMARY]

GO
ALTER TABLE [CMSWEB].[USER_ROLE_T]  WITH CHECK ADD  CONSTRAINT [FK_USER_ROLE_T_ROLE_T] FOREIGN KEY([ROLE_OID])
REFERENCES [CMSWEB].[ROLE_T] ([OBJECTID])
GO
ALTER TABLE [CMSWEB].[USER_ROLE_T] CHECK CONSTRAINT [FK_USER_ROLE_T_ROLE_T]
GO
ALTER TABLE [CMSWEB].[USER_ROLE_T]  WITH CHECK ADD  CONSTRAINT [FK_USER_ROLE_T_USER_T] FOREIGN KEY([USER_OID])
REFERENCES [CMSWEB].[USER_T] ([OBJECTID])
GO
ALTER TABLE [CMSWEB].[USER_ROLE_T] CHECK CONSTRAINT [FK_USER_ROLE_T_USER_T]
GO
