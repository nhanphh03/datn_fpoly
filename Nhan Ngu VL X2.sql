ALTER TABLE loai_khuyen_mai
ALTER COLUMN ten_lkm NVARCHAR(30);
 
insert into loai_khuyen_mai (id_lkm, ma_lkm, ten_lkm, tg_them, trang_thai)
values (NEWID(), 'LKM01', N'Khuyến mãi hóa đơn', GETDATE(), 1),
(NEWID(), 'LKM02', N'Khuyến mãi sản phẩm', GETDATE(), 1),
(NEWID(), 'LKM03', N'Khuyến mãi giao hàng', GETDATE(), 1),
(NEWID(), 'LKM04', N'Khuyến mãi khách hàng', GETDATE(), 1),
(NEWID(), 'LKM04', N'Khuyến mãi no no', GETDATE(), 0);  

ALTER TABLE khuyen_mai
ALTER COLUMN mo_ta NVARCHAR(max);
ALTER TABLE khuyen_mai
ALTER COLUMN ten_km NVARCHAR(100);
ALTER TABLE khuyen_mai
ALTER COLUMN dk_km NVARCHAR(max); 

insert into khuyen_mai (id_khuyen_mai, gia_tien_giam, ma_km, mo_ta, phan_tram_giam, so_luong, ten_km, tg_bat_dau, tg_ket_thuc, tg_them , trang_thai, id_lkm, dk_km, so_luong_da_dung, loai_giam, gia_tien_giam_toi_da)
values (NEWID(), 100, 'KM001', N'Khuyến Mãi đặc biệt dành cho hóa đơn giảm 10% tối đa 100$', 0.1 , 100, N'Khuyến mãi xuân về', '2023-11-09', '2023-11-12', GETDATE(), 1, (select id_lkm from loai_khuyen_mai where ma_lkm ='LKM01'), 'Điều kiện khuyến mãi 01', 0, 0, 99),
(NEWID(), 99, 'KM002', N'Khuyến Mãi đặc biệt dành cho hóa đơn giảm 12% tối đa 99$', 0.12 , 50, N'Khuyến mãi ngày cô đơn', '2023-11-10', '2023-11-12', GETDATE(), 1, (select id_lkm from loai_khuyen_mai where ma_lkm ='LKM01'), 'Điều kiện khuyến mãi 02' ,0 ,0, 100),
(NEWID(), 75, 'KM003', N'Khuyến Mãi đặc biệt dành cho hóa đơn giảm 9% tối đa 75$', 0.09 , 100, N'Khuyến mãi ngày nắng về', '2023-09-19', '2023-11-18', GETDATE(), 1, (select id_lkm from loai_khuyen_mai where ma_lkm ='LKM01'), 'Điều kiện khuyến mãi 03', 0, 0, 75),
(NEWID(), 50, 'KM004', N'Khuyến Mãi đặc biệt dành cho hóa đơn giảm 15% tối đa 50$', 0.15 , 100, N'Khuyến mãi ngày thất tình', '2023-07-30', '2023-12-30', GETDATE(), 1, (select id_lkm from loai_khuyen_mai where ma_lkm ='LKM01'), 'Điều kiện khuyến mãi 04 ', 0, 0, 50),
(NEWID(), 100, 'KM005', N'Khuyến Mãi đặc biệt dành cho hóa đơn giảm 8% tối đa 100$', 0.08 , 100, N'Khuyến mãi sinh nhật', '2023-09-18', '2023-12-31', GETDATE(), 1, (select id_lkm from loai_khuyen_mai where ma_lkm ='LKM01'), 'Điều kiện khuyến mãi 05', 0 , 0, 45),
(NEWID(), 150, 'KM006', N'Khuyến Mãi đặc biệt dành cho hóa đơn giảm 30% tối đa 150$', 0.3 , 100, N'Khuyến mãi kỷ niệm 5 tháng Nhân mất xe', '2023-07-30', '2023-12-30', GETDATE(), 1, (select id_lkm from loai_khuyen_mai where ma_lkm ='LKM01'), 'Điều kiện khuyến mãi 06', 0, 0, 50),
(NEWID(), 150, 'KM007', N'Khuyến Mãi đặc biệt dành cho hóa đơn giảm 30% tối đa 150$', 0.3 , 100, N'Khuyến mãi kỷ niệm NhanSuy', '2023-09-30', '2023-12-21', GETDATE(), 0, (select id_lkm from loai_khuyen_mai where ma_lkm ='LKM01'), 'Điều kiện khuyến mãi 07', 0, 0, 55),
(NEWID(), 75, 'KM008', N'Khuyến Mãi đặc biệt dành cho hóa đơn giảm  75$', 0.09 , 100, N'Khuyến mãi ngày nắng về', '2023-09-19', '2023-11-18', GETDATE(), 1, (select id_lkm from loai_khuyen_mai where ma_lkm ='LKM01'), 'Điều kiện khuyến mãi 08', 0, 1, 45),
(NEWID(), 50, 'KM009', N'Khuyến Mãi đặc biệt dành cho hóa đơn giảm  50$', 0.15 , 100, N'Khuyến mãi ngày thất tình', '2023-07-30', '2023-12-30', GETDATE(), 1, (select id_lkm from loai_khuyen_mai where ma_lkm ='LKM01'), 'Điều kiện khuyến mãi 09 ', 0, 1, 10),
(NEWID(), 100, 'KM0010', N'Khuyến Mãi đặc biệt dành cho hóa đơn giảm 100$', 0.08 , 100, N'Khuyến mãi sinh nhật', '2023-09-18', '2023-12-31', GETDATE(), 1, (select id_lkm from loai_khuyen_mai where ma_lkm ='LKM01'), 'Điều kiện khuyến mãi 010', 0 , 1, 30);

insert into khuyen_mai (id_khuyen_mai, gia_tien_giam, ma_km, mo_ta, phan_tram_giam, so_luong, ten_km, tg_bat_dau, tg_ket_thuc, tg_them , trang_thai, id_lkm, dk_km, so_luong_da_dung, loai_giam,gia_tien_giam_toi_da)
values (NEWID(), 100, 'KM0011', N'Khuyến Mãi đặc biệt dành cho sản phẩm giảm 100$', 0.1 , 1000, N'Khuyến mãi xuân về', '2023-11-09', '2023-11-12', GETDATE(), 1, (select id_lkm from loai_khuyen_mai where ma_lkm ='LKM02'), 'Điều kiện khuyến mãi 01', 0, 1, 80),
(NEWID(), 50, 'KM0012', N'Khuyến Mãi đặc biệt dành cho sản phẩm giảm 50$', 0.12 , 1000, N'Khuyến mãi ngày cô đơn', '2023-11-10', '2023-11-12', GETDATE(), 1, (select id_lkm from loai_khuyen_mai where ma_lkm ='LKM02'), 'Điều kiện khuyến mãi 02' ,0 ,1, 101),
(NEWID(), 99, 'KM0013', N'Khuyến Mãi đặc biệt dành cho sản phẩm giảm 99$', 0.09 , 1000, N'Khuyến mãi ngày nắng về', '2023-09-19', '2023-11-18', GETDATE(), 1, (select id_lkm from loai_khuyen_mai where ma_lkm ='LKM02'), 'Điều kiện khuyến mãi 03', 0, 1, 1),
(NEWID(), 49, 'KM0014', N'Khuyến Mãi đặc biệt dành cho sản phẩm giảm 49$', 0.15 , 1000, N'Khuyến mãi ngày thất tình', '2023-07-30', '2023-12-30', GETDATE(), 1, (select id_lkm from loai_khuyen_mai where ma_lkm ='LKM02'), 'Điều kiện khuyến mãi 04 ', 0, 1, 5),
(NEWID(), 79, 'KM0015', N'Khuyến Mãi đặc biệt dành cho sản phẩm giảm 79$', 0.08 , 1000, N'Khuyến mãi sinh nhật', '2023-09-18', '2023-12-31', GETDATE(), 1, (select id_lkm from loai_khuyen_mai where ma_lkm ='LKM02'), 'Điều kiện khuyến mãi 05', 0, 1, 29),
(NEWID(), 69, 'KM0016', N'Khuyến Mãi đặc biệt dành cho sản phẩm giảm 69$', 0.3 , 1000, N'Khuyến mãi kỷ niệm 5 tháng Nhân mất xe', '2023-07-30', '2023-12-30', GETDATE(), 1, (select id_lkm from loai_khuyen_mai where ma_lkm ='LKM02'), 'Điều kiện khuyến mãi 06', 0, 1, 49);

insert into khuyen_mai (id_khuyen_mai, gia_tien_giam, ma_km, mo_ta, phan_tram_giam, so_luong, ten_km, tg_bat_dau, tg_ket_thuc, tg_them , trang_thai, id_lkm, dk_km, so_luong_da_dung , loai_giam, gia_tien_giam_toi_da)
values (NEWID(), 10, 'KM0017', N'Khuyến Mãi đặc biệt dành cho tiền ship giảm 10% tối đa 10$', 0.1 , 1000, N'Khuyến mãi xuân về', '2023-11-09', '2023-11-12', GETDATE(), 1, (select id_lkm from loai_khuyen_mai where ma_lkm ='LKM03'), 'Điều kiện khuyến mãi 01', 0, 0,19),
(NEWID(), 15, 'KM0018', N'Khuyến Mãi đặc biệt dành cho tiền ship giảm 10% tối đa 10$', 0.12 , 1000, N'Khuyến mãi ngày cô đơn', '2023-11-10', '2023-11-12', GETDATE(), 1, (select id_lkm from loai_khuyen_mai where ma_lkm ='LKM03'), 'Điều kiện khuyến mãi 02' ,0 , 0, 29),
(NEWID(), 9, 'KM0019', N'Khuyến Mãi đặc biệt dành cho tiền ship giảm 10% tối đa 10$', 0.09 , 1000, N'Khuyến mãi ngày nắng về', '2023-09-19', '2023-11-18', GETDATE(), 1, (select id_lkm from loai_khuyen_mai where ma_lkm ='LKM03'), 'Điều kiện khuyến mãi 03', 0, 0, 9),
(NEWID(), 12, 'KM0020', N'Khuyến Mãi đặc biệt dành cho tiền ship giảm 10% tối đa 10$', 0.15 , 1000, N'Khuyến mãi ngày thất tình', '2023-07-30', '2023-12-30', GETDATE(), 1, (select id_lkm from loai_khuyen_mai where ma_lkm ='LKM03'), 'Điều kiện khuyến mãi 04 ', 0, 0, 5),
(NEWID(), 20, 'KM0021', N'Khuyến Mãi đặc biệt dành cho tiền ship giảm 10% tối đa 10$', 0.08 , 1000, N'Khuyến mãi sinh nhật', '2023-09-18', '2023-12-31', GETDATE(), 1, (select id_lkm from loai_khuyen_mai where ma_lkm ='LKM03'), 'Điều kiện khuyến mãi 05', 0, 0, 12),
(NEWID(), 25, 'KM0022', N'Khuyến Mãi đặc biệt dành cho tiền ship giảm 10% tối đa 10$', 0.3 , 1000, N'Khuyến mãi kỷ niệm 5 tháng Nhân mất xe', '2023-07-30', '2023-12-30', GETDATE(), 1, (select id_lkm from loai_khuyen_mai where ma_lkm ='LKM03'), 'Điều kiện khuyến mãi 06', 0, 0, 11),
(NEWID(), 15, 'KM0023', N'Khuyến Mãi đặc biệt dành cho hóa đơn giảm  15$', 0.09 , 100, N'Khuyến mãi ngày nắng về', '2023-09-19', '2023-11-18', GETDATE(), 1, (select id_lkm from loai_khuyen_mai where ma_lkm ='LKM03'), 'Điều kiện khuyến mãi 08', 0, 1, 7),
(NEWID(), 9, 'KM0024', N'Khuyến Mãi đặc biệt dành cho hóa đơn giảm  9$', 0.15 , 100, N'Khuyến mãi ngày thất tình', '2023-07-30', '2023-12-30', GETDATE(), 1, (select id_lkm from loai_khuyen_mai where ma_lkm ='LKM03'), 'Điều kiện khuyến mãi 09 ', 0, 1, 5),
(NEWID(), 12, 'KM0025', N'Khuyến Mãi đặc biệt dành cho hóa đơn giảm 12$', 0.08 , 100, N'Khuyến mãi sinh nhật', '2023-09-18', '2023-12-31', GETDATE(), 1, (select id_lkm from loai_khuyen_mai where ma_lkm ='LKM03'), 'Điều kiện khuyến mãi 010', 0 , 1, 9);
