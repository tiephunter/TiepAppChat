USE [Messenger]
GO
/****** Object:  Table [dbo].[FriendShip]    Script Date: 5/31/2020 8:58:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[FriendShip](
	[UserId] [int] NOT NULL,
	[FriendId] [int] NOT NULL,
	[TimeCreated] [datetime] NOT NULL,
	[SessionId] [int] NULL,
 CONSTRAINT [PK_FriendShip] PRIMARY KEY CLUSTERED 
(
	[UserId] ASC,
	[FriendId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Message]    Script Date: 5/31/2020 8:58:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Message](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[TextMess] [nvarchar](50) NOT NULL,
	[IdSession] [int] NOT NULL,
	[Time] [time](7) NOT NULL,
	[IdSender] [int] NOT NULL,
 CONSTRAINT [PK_Messege] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Participate]    Script Date: 5/31/2020 8:58:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Participate](
	[IdUser] [int] NOT NULL,
	[IdSeesion] [int] NOT NULL,
	[TimeJoin] [int] NOT NULL,
 CONSTRAINT [PK_Participate] PRIMARY KEY CLUSTERED 
(
	[IdUser] ASC,
	[IdSeesion] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Session]    Script Date: 5/31/2020 8:58:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Session](
	[Id] [int] IDENTITY(1,1) NOT NULL,
	[AmountUsers] [int] NOT NULL,
	[TimeStart] [time](7) NOT NULL,
	[TimeFinish] [time](7) NOT NULL,
 CONSTRAINT [PK_Seesion] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[Users]    Script Date: 5/31/2020 8:58:56 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[IdUser] [int] IDENTITY(1,1) NOT NULL,
	[HoTen] [nvarchar](50) NOT NULL,
	[NgaySinh] [nvarchar](50) NOT NULL,
	[GioiTinh] [int] NOT NULL,
	[DiaChi] [nvarchar](50) NOT NULL,
	[QueQuan] [nvarchar](50) NOT NULL,
	[Email] [nvarchar](50) NOT NULL,
	[TenTaiKhoan] [nvarchar](50) NOT NULL,
	[MatKhau] [nvarchar](250) NULL,
 CONSTRAINT [PK_Users] PRIMARY KEY CLUSTERED 
(
	[IdUser] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
INSERT [dbo].[FriendShip] ([UserId], [FriendId], [TimeCreated], [SessionId]) VALUES (1, 6, CAST(N'1900-01-01 11:42:41.770' AS DateTime), 2)
INSERT [dbo].[FriendShip] ([UserId], [FriendId], [TimeCreated], [SessionId]) VALUES (6, 1, CAST(N'1900-01-01 11:42:41.770' AS DateTime), 2)
INSERT [dbo].[FriendShip] ([UserId], [FriendId], [TimeCreated], [SessionId]) VALUES (6, 17, CAST(N'1900-01-01 23:31:14.033' AS DateTime), 5)
INSERT [dbo].[FriendShip] ([UserId], [FriendId], [TimeCreated], [SessionId]) VALUES (6, 20, CAST(N'1900-01-01 21:41:34.050' AS DateTime), 4)
INSERT [dbo].[FriendShip] ([UserId], [FriendId], [TimeCreated], [SessionId]) VALUES (6, 21, CAST(N'1900-01-01 17:50:05.800' AS DateTime), 6)
INSERT [dbo].[FriendShip] ([UserId], [FriendId], [TimeCreated], [SessionId]) VALUES (6, 22, CAST(N'1900-01-01 17:49:58.080' AS DateTime), 7)
INSERT [dbo].[FriendShip] ([UserId], [FriendId], [TimeCreated], [SessionId]) VALUES (17, 6, CAST(N'1900-01-01 23:31:14.033' AS DateTime), 5)
INSERT [dbo].[FriendShip] ([UserId], [FriendId], [TimeCreated], [SessionId]) VALUES (20, 6, CAST(N'1900-01-01 21:41:34.050' AS DateTime), 4)
INSERT [dbo].[FriendShip] ([UserId], [FriendId], [TimeCreated], [SessionId]) VALUES (21, 6, CAST(N'1900-01-01 17:50:05.800' AS DateTime), 6)
INSERT [dbo].[FriendShip] ([UserId], [FriendId], [TimeCreated], [SessionId]) VALUES (22, 6, CAST(N'1900-01-01 17:49:58.080' AS DateTime), 7)
INSERT [dbo].[FriendShip] ([UserId], [FriendId], [TimeCreated], [SessionId]) VALUES (27, 28, CAST(N'1900-01-01 17:01:15.113' AS DateTime), 8)
INSERT [dbo].[FriendShip] ([UserId], [FriendId], [TimeCreated], [SessionId]) VALUES (27, 29, CAST(N'1900-01-01 09:23:27.000' AS DateTime), NULL)
INSERT [dbo].[FriendShip] ([UserId], [FriendId], [TimeCreated], [SessionId]) VALUES (28, 27, CAST(N'1900-01-01 17:01:15.113' AS DateTime), 8)
INSERT [dbo].[FriendShip] ([UserId], [FriendId], [TimeCreated], [SessionId]) VALUES (29, 27, CAST(N'1900-01-01 09:23:27.000' AS DateTime), NULL)
INSERT [dbo].[FriendShip] ([UserId], [FriendId], [TimeCreated], [SessionId]) VALUES (30, 31, CAST(N'1900-01-01 00:03:39.610' AS DateTime), 9)
INSERT [dbo].[FriendShip] ([UserId], [FriendId], [TimeCreated], [SessionId]) VALUES (31, 30, CAST(N'1900-01-01 00:03:39.610' AS DateTime), 9)
INSERT [dbo].[FriendShip] ([UserId], [FriendId], [TimeCreated], [SessionId]) VALUES (31, 32, CAST(N'1900-01-01 00:03:47.650' AS DateTime), NULL)
INSERT [dbo].[FriendShip] ([UserId], [FriendId], [TimeCreated], [SessionId]) VALUES (32, 31, CAST(N'1900-01-01 00:03:47.650' AS DateTime), NULL)
SET IDENTITY_INSERT [dbo].[Message] ON 

INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (51, N'hel0 viet', 2, CAST(N'13:41:46.9210000' AS Time), 6)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (52, N'helo hung', 2, CAST(N'13:42:31.3810000' AS Time), 1)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (53, N't k thay tin nhan cua ban', 2, CAST(N'13:45:29.7240000' AS Time), 6)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (54, N'sao bn ko tl tn cua minh', 2, CAST(N'13:45:47.6760000' AS Time), 1)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (55, N'how are u doing v', 2, CAST(N'14:03:06.1280000' AS Time), 6)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (107, N't day', 2, CAST(N'22:43:27.9650000' AS Time), 6)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (108, N'gi the', 2, CAST(N'22:43:56.5740000' AS Time), 1)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (112, N'hung ne', 2, CAST(N'22:48:28.2540000' AS Time), 6)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (113, N'lo', 2, CAST(N'23:20:06.7950000' AS Time), 6)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (114, N't day', 2, CAST(N'23:20:13.0480000' AS Time), 6)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (115, N'what are u doing?', 2, CAST(N'23:20:32.6200000' AS Time), 6)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (116, N'g', 2, CAST(N'23:20:44.5690000' AS Time), 6)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (117, N'g', 2, CAST(N'23:20:46.3990000' AS Time), 6)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (118, N'dc chưa ?', 2, CAST(N'23:22:20.1530000' AS Time), 6)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (119, N'lo, i am nhat', 6, CAST(N'17:51:48.0350000' AS Time), 21)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (120, N'i am hung', 6, CAST(N'17:51:55.8160000' AS Time), 6)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (121, N'lo', 6, CAST(N'17:52:45.4310000' AS Time), 21)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (122, N'lo', 2, CAST(N'17:53:53.1830000' AS Time), 6)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (123, N'he lo', 2, CAST(N'18:00:56.6340000' AS Time), 6)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (124, N'helo', 2, CAST(N'18:05:21.8350000' AS Time), 6)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (125, N'h', 2, CAST(N'18:06:50.8800000' AS Time), 6)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (126, N'h', 2, CAST(N'18:06:52.4770000' AS Time), 6)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (127, N'f', 2, CAST(N'18:17:17.8270000' AS Time), 6)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (128, N'g', 2, CAST(N'18:26:00.0040000' AS Time), 6)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (129, N'g', 2, CAST(N'18:26:01.2480000' AS Time), 6)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (130, N'', 2, CAST(N'18:26:02.2200000' AS Time), 6)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (131, N'g', 2, CAST(N'18:26:05.0360000' AS Time), 6)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (132, N'g', 2, CAST(N'18:26:06.4480000' AS Time), 6)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (133, N'', 2, CAST(N'18:26:07.1960000' AS Time), 6)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (134, N'i am here', 2, CAST(N'18:26:19.0780000' AS Time), 6)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (135, N'lo', 7, CAST(N'18:27:12.2380000' AS Time), 22)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (136, N'lo', 6, CAST(N'18:29:25.9540000' AS Time), 6)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (137, N'f', 2, CAST(N'18:30:23.9220000' AS Time), 6)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (138, N'd', 2, CAST(N'00:06:36.0020000' AS Time), 6)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (139, N'd', 2, CAST(N'00:06:40.4790000' AS Time), 6)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (140, N'i am here', 2, CAST(N'00:06:47.2750000' AS Time), 6)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (141, N'f', 2, CAST(N'00:06:51.2050000' AS Time), 6)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (142, N'f', 2, CAST(N'00:06:53.7030000' AS Time), 6)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (143, N'helo tiep', 8, CAST(N'17:01:43.2800000' AS Time), 28)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (144, N't tiep day', 8, CAST(N'08:27:41.6170000' AS Time), 28)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (145, N'gi', 8, CAST(N'08:28:12.5190000' AS Time), 27)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (146, N'', 8, CAST(N'08:28:14.6170000' AS Time), 27)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (147, N'co vc gi k', 8, CAST(N'08:28:22.0870000' AS Time), 27)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (148, N'hoi tham thoi ma', 8, CAST(N'08:28:35.0850000' AS Time), 28)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (149, N'kho the', 8, CAST(N'08:28:40.3230000' AS Time), 28)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (150, N'ow', 8, CAST(N'08:28:53.3370000' AS Time), 27)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (151, N'the dang lam gi day', 8, CAST(N'08:28:59.4160000' AS Time), 27)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (152, N'd', 8, CAST(N'12:11:15.3100000' AS Time), 28)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (153, N'', 9, CAST(N'00:10:12.9870000' AS Time), 31)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (154, N'helo', 9, CAST(N'00:11:05.3290000' AS Time), 31)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (155, N'đang lci đấy', 9, CAST(N'01:00:39.6960000' AS Time), 31)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (156, N'đang lol', 9, CAST(N'16:47:14.3430000' AS Time), 30)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (157, N'làm trận ko', 9, CAST(N'16:47:19.4090000' AS Time), 30)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (158, N'oke, zo', 9, CAST(N'16:47:27.3350000' AS Time), 31)
INSERT [dbo].[Message] ([Id], [TextMess], [IdSession], [Time], [IdSender]) VALUES (159, N'vào nhanh nha', 9, CAST(N'16:48:17.0260000' AS Time), 30)
SET IDENTITY_INSERT [dbo].[Message] OFF
SET IDENTITY_INSERT [dbo].[Session] ON 

INSERT [dbo].[Session] ([Id], [AmountUsers], [TimeStart], [TimeFinish]) VALUES (1, 2, CAST(N'15:35:47.3420000' AS Time), CAST(N'15:35:47.3420000' AS Time))
INSERT [dbo].[Session] ([Id], [AmountUsers], [TimeStart], [TimeFinish]) VALUES (2, 2, CAST(N'15:42:01.9650000' AS Time), CAST(N'15:42:01.9660000' AS Time))
INSERT [dbo].[Session] ([Id], [AmountUsers], [TimeStart], [TimeFinish]) VALUES (3, 2, CAST(N'10:06:41.3550000' AS Time), CAST(N'10:06:41.3560000' AS Time))
INSERT [dbo].[Session] ([Id], [AmountUsers], [TimeStart], [TimeFinish]) VALUES (4, 2, CAST(N'16:31:49.9590000' AS Time), CAST(N'16:31:49.9590000' AS Time))
INSERT [dbo].[Session] ([Id], [AmountUsers], [TimeStart], [TimeFinish]) VALUES (5, 2, CAST(N'16:32:06.3500000' AS Time), CAST(N'16:32:06.3500000' AS Time))
INSERT [dbo].[Session] ([Id], [AmountUsers], [TimeStart], [TimeFinish]) VALUES (6, 2, CAST(N'17:51:16.9450000' AS Time), CAST(N'17:51:16.9450000' AS Time))
INSERT [dbo].[Session] ([Id], [AmountUsers], [TimeStart], [TimeFinish]) VALUES (7, 2, CAST(N'18:27:09.7330000' AS Time), CAST(N'18:27:09.7330000' AS Time))
INSERT [dbo].[Session] ([Id], [AmountUsers], [TimeStart], [TimeFinish]) VALUES (8, 2, CAST(N'17:01:35.8060000' AS Time), CAST(N'17:01:35.8060000' AS Time))
INSERT [dbo].[Session] ([Id], [AmountUsers], [TimeStart], [TimeFinish]) VALUES (9, 2, CAST(N'00:08:54.3630000' AS Time), CAST(N'00:08:54.3630000' AS Time))
SET IDENTITY_INSERT [dbo].[Session] OFF
SET IDENTITY_INSERT [dbo].[Users] ON 

INSERT [dbo].[Users] ([IdUser], [HoTen], [NgaySinh], [GioiTinh], [DiaChi], [QueQuan], [Email], [TenTaiKhoan], [MatKhau]) VALUES (27, N't', N'3', 0, N't', N't', N't', N't', N'3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2')
INSERT [dbo].[Users] ([IdUser], [HoTen], [NgaySinh], [GioiTinh], [DiaChi], [QueQuan], [Email], [TenTaiKhoan], [MatKhau]) VALUES (28, N'Cao Minh Tiep', N'04 02 199', 0, N'Nghe an', N'Nghe an', N'c.m.tiep@gamil', N'tiep', N'3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2')
INSERT [dbo].[Users] ([IdUser], [HoTen], [NgaySinh], [GioiTinh], [DiaChi], [QueQuan], [Email], [TenTaiKhoan], [MatKhau]) VALUES (29, N'Nguyễn Hồng Lưu ', N'324234', 0, N'nnghe an', N'nghe an', N'luu@gmail.com', N'lưu sa đéc', N'3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2')
INSERT [dbo].[Users] ([IdUser], [HoTen], [NgaySinh], [GioiTinh], [DiaChi], [QueQuan], [Email], [TenTaiKhoan], [MatKhau]) VALUES (30, N'Nguyen van duong', N'0101199', 0, N'Nam Dinh', N'Nam Dinh', N'duongquatlam@gmail.com', N'duong', N'3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2')
INSERT [dbo].[Users] ([IdUser], [HoTen], [NgaySinh], [GioiTinh], [DiaChi], [QueQuan], [Email], [TenTaiKhoan], [MatKhau]) VALUES (31, N'Nguyễn Hồng Lưu', N'10101999', 0, N'Nghệ an', N'Nghệ An', N'luusadec@gmail.com', N'lưu', N'3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2')
INSERT [dbo].[Users] ([IdUser], [HoTen], [NgaySinh], [GioiTinh], [DiaChi], [QueQuan], [Email], [TenTaiKhoan], [MatKhau]) VALUES (32, N'Nghiêm thị thủy', N'24041999', 1, N'Bắc Ninh', N'Bắc Ninh', N'thuytuc@gmail.com', N'thủy', N'3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2')
INSERT [dbo].[Users] ([IdUser], [HoTen], [NgaySinh], [GioiTinh], [DiaChi], [QueQuan], [Email], [TenTaiKhoan], [MatKhau]) VALUES (33, N'Vũ Đức Toàn', N'05021999', 0, N'Phú Thọ', N'PHú THọ', N'toanphutho@gmail.com', N'Toàn', N'3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2')
INSERT [dbo].[Users] ([IdUser], [HoTen], [NgaySinh], [GioiTinh], [DiaChi], [QueQuan], [Email], [TenTaiKhoan], [MatKhau]) VALUES (34, N'Đinh Văn Giang', N'03051999', 0, N'Thái Bình', N'Thái Bình', N'abe@gmail.com', N'Giang', N'3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2')
SET IDENTITY_INSERT [dbo].[Users] OFF
ALTER TABLE [dbo].[Message]  WITH CHECK ADD  CONSTRAINT [FK_Messege_Seesion] FOREIGN KEY([IdSession])
REFERENCES [dbo].[Session] ([Id])
GO
ALTER TABLE [dbo].[Message] CHECK CONSTRAINT [FK_Messege_Seesion]
GO
