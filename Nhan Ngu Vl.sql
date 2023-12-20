insert into loai_khuyen_mai (id_lkm, ma_lkm, ten_lkm, tg_them, trang_thai)
values (NEWID(), 'LKM01', N'Khuyến mãi hóa đơn', GETDATE(), 1),
(NEWID(), 'LKM02', N'Khuyến mãi sản phẩm', GETDATE(), 1),
(NEWID(), 'LKM03', N'Khuyến mãi giao hàng', GETDATE(), 1),
(NEWID(), 'LKM04', N'Khuyến mãi khách hàng', GETDATE(), 1),
(NEWID(), 'LKM04', N'Khuyến mãi no no', GETDATE(), 0);  


insert into loai_khach_hang(id_lkh,ma_loaikh,ten_loaikh,so_diem,trang_thai, tg_them) values
(newid(),'H4','Kim Cuong',5000, 2, getdate()),
(newid(),'H3','Gold',1000, 1, getdate()),
(newid(),'H2','Silver',500, 1, getdate()),
(newid(),'H1','Bronze',100, 1, getdate())

select don_gia_truoc_khi_giam from hoa_don_chi_tiet

insert into chuc_vu(id_chuc_vu, ma_chuc_vu, ten_chuc_vu, trang_thai, tg_them) values
(NEWID(),'CV01','Quan Ly',1, GETDATE()),
(NEWID(),'CV02','Nhan vien ban hang',1, GETDATE()),
(NEWID(),'CV03','Nhan vien giao hang',1, GETDATE()),
(NEWID(),'CV04','Bao ve',0, GETDATE())

select * from chuc_vu

insert into  nhan_vien(id_nhan_vien, id_chuc_vu, ma_nv, ho_ten_nv, ngay_sinh, email_nv, dia_chi_nv, gioi_tinh, sdt_nv, mk_nv, trang_thai,cccd_nv, anh_nv,tg_them) values
(NEWID(),(SELECT id_chuc_vu FROM chuc_vu WHERE ma_chuc_vu='CV03'),'NV04','Nhan GH','2003-03-14','nhanphh2003@gmail.com','Ha Noi',1,'0375853419','ass',1,'5987654321','anhNV1', GETDATE()),
(NEWID(),(SELECT id_chuc_vu FROM chuc_vu WHERE ma_chuc_vu='CV02'),'NV05','Nhan BH','2003-05-14','nhphh2003@gmail.com','Ha Noi',1,'0987654321','ass',1,'5987654321','anhNV2', GETDATE()),
(NEWID(),(SELECT id_chuc_vu FROM chuc_vu WHERE ma_chuc_vu='CV01'),'NV06','Nhan QL','2003-07-14','nhanph2003@gmail.com','Ha Noi',1,'0375853449','ass',1,'5987654321','anhNV3', GETDATE())




insert into hang (id_hang, trang_thai, ma_hang, ten_hang, tg_them,logo_hang)
values(NEWID(), 1, 'CT01', 'Nike', GETDATE(),'Nike.png')
insert into Hang (id_hang, trang_thai, ma_hang, ten_hang, tg_them,logo_hang)
values(NEWID(), 1, 'CT02', 'Adidas', GETDATE(),'Adidas.png')
insert into Hang (id_hang, trang_thai, ma_hang, ten_hang, tg_them,logo_hang)
values(NEWID(), 1, 'CT03', 'Puma', GETDATE(),'Puma.png')
insert into Hang (id_hang, trang_thai, ma_hang, ten_hang, tg_them,logo_hang)
values(NEWID(), 1, 'CT04', 'Vans', GETDATE(),'Vans.png')
insert into Hang (id_hang, trang_thai, ma_hang, ten_hang, tg_them,logo_hang)
values(NEWID(), 1, 'CT05', 'MLB', GETDATE(),'Mlb.png')
insert into Hang (id_hang, trang_thai, ma_hang, ten_hang, tg_them,logo_hang)
values(NEWID(), 1, 'CT06', 'New Balance', GETDATE(),'New_Balance.png')
insert into Hang (id_hang, trang_thai, ma_hang, ten_hang, tg_them,logo_hang)
values(NEWID(), 1, 'CT07', 'Louis Vuitton', GETDATE(),'Louis_Vuitton.png')
insert into Hang (id_hang, trang_thai, ma_hang, ten_hang, tg_them,logo_hang)
values(NEWID(), 1, 'CT08', 'Converse', GETDATE(),'Converse.png')
insert into Hang (id_hang, trang_thai, ma_hang, ten_hang, tg_them,logo_hang)
values(NEWID(), 1, 'CT09', 'Reebok', GETDATE(),'Reebok.png')
insert into Hang (id_hang, trang_thai, ma_hang, ten_hang, tg_them,logo_hang)
values(NEWID(), 0, 'CT010', 'Supreme', GETDATE(),'Supreme.png')



insert into chat_lieu(id_chat_lieu, ten_chat_lieu, trang_thai, ma_chat_lieu, tg_them)
values(NEWID(), 'Fabric', 1, 'TG01', getdate())
insert into chat_lieu(id_chat_lieu, ten_chat_lieu, trang_thai, ma_chat_lieu, tg_them)
values(NEWID(), 'PU Leather', 1, 'TG02', getdate())
insert into chat_lieu(id_chat_lieu, ten_chat_lieu, trang_thai, ma_chat_lieu, tg_them)
values(NEWID(), 'Leather', 1, 'TG03', getdate())
insert into chat_lieu(id_chat_lieu, ten_chat_lieu, trang_thai, ma_chat_lieu, tg_them)
values(NEWID(), 'Cowhide', 1, 'TG04', getdate())
insert into chat_lieu(id_chat_lieu, ten_chat_lieu, trang_thai, ma_chat_lieu, tg_them)
values(NEWID(), 'Simili', 0, 'TG05', getdate())
insert into chat_lieu(id_chat_lieu, ten_chat_lieu, trang_thai, ma_chat_lieu, tg_them)
values(NEWID(), 'Bonded leather', 0, 'TG06', getdate())

ALTER TABLE Giay
ALTER COLUMN mo_ta varchar(MAX);

INSERT INTO giay (id_giay, id_chat_lieu, id_hang, trang_thai, ten_giay, ma_giay, mo_ta, tg_them)
VALUES (NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG01'), (SELECT id_hang FROM hang WHERE ma_hang='CT01'), 0, 'Nike Cortez', 'Sneaker000','', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG01'), (SELECT id_hang FROM hang WHERE ma_hang='CT01'), 1, 'Nike Jodan', 'Sneaker001','In 1984, the Air Jordan was the first collaboration between Nike and rising star, Michael Jordan. Through multiple products innovations, expansions, and continued collaborations, the Jordan team has turned the Jumpman silhouette into a sneaker icon worthy of Michael Jordan. Today the team is focused on building the future of the brand from product that will become the icons of tomorrow to world class teams and business practices.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG02'), (SELECT id_hang FROM hang WHERE ma_hang='CT01'), 1, 'Nike Air Force 1', 'Sneaker002','The radiance lives on in the Nike Air Force 1 07, the b-ball OG that puts a fresh spin on what you know best: durably stitched overlays, clean finishes and the perfect amount of flash to make you shine.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG03'), (SELECT id_hang FROM hang WHERE ma_hang='CT01'), 1, 'Nike Dunk Low SE', 'Sneaker003','Created for the hardwood but taken to the streets, the is 80s b-ball icon returns with classic details and throwback hoops flair. Channelling vintage style back onto the streets, synthetic suede in the upper that is easy to break in and padded, low-cut collar let you take your game anywhere—in comfort.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG04'), (SELECT id_hang FROM hang WHERE ma_hang='CT01'), 1, 'Nike Blazer Mid White', 'Sneaker004','In the 70s, Nike was the new shoe on the block. So new in fact, we were still breaking into the basketball scene and testing prototypes on the feet of our local team. Of course, the design improved over the years, but the name stuck. The Nike Blazer Mid 77 Vintage—classic since the beginning.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG03'), (SELECT id_hang FROM hang WHERE ma_hang='CT01'), 1, 'Nike Court Vision Low Next Nature All White', 'Sneaker005','In love with the classic look of 80s basketball but have a thing for the fast-paced culture of today is game? Meet the Nike Court Vision Low. A classic remixed with at least 20% recycled materials by weight, its crisp upper and stitched overlays keep the soul of the original style. The plush, low-cut collar keeps it sleek and comfortable for your world.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG02'), (SELECT id_hang FROM hang WHERE ma_hang='CT01'), 1, 'Nike Air Max 90', 'Sneaker006','Nothing as fly, nothing as comfortable, nothing as proven. The Nike Air Max 90 stays true to its OG running roots with the iconic Waffle sole, stitched overlays and classic TPU details. Classic colors celebrate your fresh look while Max Air cushioning adds comfort to the journey.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG02'), (SELECT id_hang FROM hang WHERE ma_hang='CT01'), 1, 'Nike Air Max 1', 'Sneaker007','Meet the leader of the pack. Walking on clouds above the noise, the Air Max 1 blends timeless design with cushioned comfort. Sporting a fast-paced look, wavy mudguard and Nike Air, this classic icon hit the scene in 87 and continues to be the soul of the franchise today.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG02'), (SELECT id_hang FROM hang WHERE ma_hang='CT01'), 1, 'Nike Air Max 95', 'Sneaker008','Taking inspiration from the human body and 90s athletics aesthetics, the Air Max 95 mixes unbelievable comfort with fast-paced style. The wavy side panels add natural flow to any outfit, while visible Nike Air in the heel and forefoot delivers performance comfort.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG03'), (SELECT id_hang FROM hang WHERE ma_hang='CT01'), 1, 'Nike Epic React', 'Sneaker009','The Nike Epic React Flyknit 1 provides crazy comfort that lasts as long as you can run. Its Nike React foam cushioning is responsive yet lightweight, durable yet soft. This attraction of opposites creates a sensation that not only enhances the feeling of moving forwards, but makes running feel fun, too.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG04'), (SELECT id_hang FROM hang WHERE ma_hang='CT01'), 1, 'Nike Cortez OG', 'Sneaker0010','The Nike Classic Cortez Shoe is Nike is original running shoe, designed by Bill Bowerman and released in 1972. This version features a leather and synthetic leather construction for added durability.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG04'), (SELECT id_hang FROM hang WHERE ma_hang='CT01'), 1, 'Nike Air More Uptempo', 'Sneaker0011','How do we describe the Nike Air More Uptempo 96? Bold, eye-catching and all about the AIR. With a design inspired by high-flying basketball style and 90s graffiti art, these kicks cant help but turn heads.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG03'), (SELECT id_hang FROM hang WHERE ma_hang='CT01'), 1, 'Nike Air Zoom', 'Sneaker0012','You ll experience them all with Nike Zoom, our innovative and explosive cushioning system designed for speed and agility. The technology uses pressurized air and tightly stretched fibers to absorb impact and then snap back for fast movement and reduced stress on muscles, joints, and tendons.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG01'), (SELECT id_hang FROM hang WHERE ma_hang='CT01'), 1, 'Nike Jordan 3', 'Sneaker0013','Quality leather in the upper—with that luxurious elephant print texture—is combined with visible Nike Air in the sole to make a comfortable, everyday icon. Genuine and synthetic leathers offer a durable, supportive feel. Foam midsole with Air-Sole unit in the heel and forefoot for street-ready cushioning.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG02'), (SELECT id_hang FROM hang WHERE ma_hang='CT01'), 1, 'Nike Jordan 11', 'Sneaker0014','With its sleek aerodynamics and sophisticated patent leather shine, the Air Jordan 11 became one of the most sought-after sneakers in the game even before its big screen debut in the animated classic Space Jam. An instant favorite among players, the AJ 11 remains an unbeatable blend of elegance and attitude', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG03'), (SELECT id_hang FROM hang WHERE ma_hang='CT01'), 1, 'Nike Air Presto', 'Sneaker0015','With a sleek design that is more comfortable than your favorite tee, the Air Presto is made to feel good and look fast. Its stretchy sleeve creates a cozy, sock-like fit, while the soft foam adds spring to your step.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG04'), (SELECT id_hang FROM hang WHERE ma_hang='CT01'), 1, 'Nike Jordan 4', 'Sneaker0016','Featuring premium materials and an eye-catching design, the Air Jordan 4 Retro upgrades one of Jordan is best-loved silhouettes. Premium leather, synthetic and textile upper adds a stylish finish to classic durability. Visible Nike Air in the heel is paired with an encapsulated forefoot unit for responsive cushioning.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG04'), (SELECT id_hang FROM hang WHERE ma_hang='CT01'), 1, 'Nike Pegasus', 'Sneaker0017','Faster is possible, but you ve got to train for it. The Nike Air Zoom Pegasus 36, the Nike Zoom Pegasus Turbo 2, and Nike Air Zoom Pegasus 36 Trail shoes were all designed with the same purpose in mind—to help reach your speed goals.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG01'), (SELECT id_hang FROM hang WHERE ma_hang='CT01'), 1, 'Nike Kyrie', 'Sneaker0018','As an Amazon Associate I earn from qualifying purchases. Also, if a user clicks on any other product links and purchases a product, I earn a small commission.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG02'), (SELECT id_hang FROM hang WHERE ma_hang='CT01'), 1, 'Nike Lebron', 'Sneaker0019','Now, his latest signature shoe is lighter, low to the ground and turbo-like. It is unlike any design LeBron has donned before—comfortable and supportive, yet low cut, feathery fast and created to stay ahead of today is frenzied style of play.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG04'), (SELECT id_hang FROM hang WHERE ma_hang='CT01'), 1, 'Nike KD', 'Sneaker0020','With its lightweight upper and plush support system, the KD Trey 5 X can help you float like KD, waiting for the perfect moment to drive to the hoop. A secure midfoot strap is suited for scoring binges and defensive stands, so that you can lock in and keep winning. This version is made for outdoor-court use with its extra-durable rubber outsole.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG03'), (SELECT id_hang FROM hang WHERE ma_hang='CT01'), 1, 'Nike Waffle', 'Sneaker0021','Swoosh wraps around your heel to form a pull tab, adding aesthetic appeal to on-and-off functionality. Lifted foam midsoles give you an elevated look and all-day comfort. Soft suede overlays nod to vintage materials while textile underlays add durability.', getdate()),

(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG02'), (SELECT id_hang FROM hang WHERE ma_hang='CT01'), 1, 'Nike Pegasus 40 Premium', 'Sneaker0097','A springy ride for any run, the Peg is familiar, just-for-you feel returns to help you accomplish your goals. This version has the same responsiveness and neutral support you love but with improved comfort in those sensitive areas of your foot, like the arch and toes.', getdate()),

(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG03'), (SELECT id_hang FROM hang WHERE ma_hang='CT02'), 1, 'Adidas Ultraboost', 'Sneaker0022','From a walk in the park to a weekend run with friends, these adidas Ultraboost 1.0 shoes are designed to keep you comfortable. An adidas PRIMEKNIT upper gently hugs your feet while BOOST on the midsole cushions from the first step to the last mile. The Stretchweb outsole flexes naturally for an energized ride, and Continental™ Rubber gives you the', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG03'), (SELECT id_hang FROM hang WHERE ma_hang='CT02'), 1, 'Adidas Galaxy', 'Sneaker0023','Lace up and go. These adidas running shoes will keep you comfortable, however late your day runs. A great everyday shoe, they have a light and airy mesh upper to keep your feet cool and a Cloudfoam midsole for springiness. The rubber outsole is designed to stay firm across all surfaces, from wet grass to slow clay. ', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG03'), (SELECT id_hang FROM hang WHERE ma_hang='CT02'), 1, 'Adidas Alphabounce', 'Sneaker0024','Go for that walk around the neighbourhood or head to the gym in a pair of adidas Alphabounce+ shoes. The mesh upper is soft and breathable to keep you comfortable as you go. A Bounce midsole makes the ride soft and springy while Cloudfoam on the heel cushions every step, so you can cruise smoothly through the day.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG03'), (SELECT id_hang FROM hang WHERE ma_hang='CT02'), 1, 'Adidas Yeezy', 'Sneaker0025','Timeless appeal. Effortless style. Everyday versatility. For over 50 years and counting, adidas Stan Smith Shoes have continued to hold their place as an icon. This pair shows off a fresh redesign as part of adidas  commitment to use only recycled polyester by 2024. With a vegan upper and an outsole made from rubber waste, they still have iconic style, they re just made with the planet in mind.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG02'), (SELECT id_hang FROM hang WHERE ma_hang='CT02'), 1, 'Adidas Stan Smith', 'Sneaker0026','This product is made with vegan alternatives to animal-derived ingredients or materials. It is also made with Primegreen, a series of high-performance recycled materials. ', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG01'), (SELECT id_hang FROM hang WHERE ma_hang='CT02'), 1, 'Adidas Superstar', 'Sneaker0027','Originally created for the basketball court in the 1970s. Worn by hip-hop royalty in the 1980s. Today the adidas Superstar shoe represents streetwear lovers. Its distinctive silhouette with a raised toe remains relevant, providing style and protection. Just like it did on basketball courts in its early days. ', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG02'), (SELECT id_hang FROM hang WHERE ma_hang='CT02'), 1, 'Adidas NMD', 'Sneaker0028','The NMD design dropped in 2015 and took over city streets all around the world. These adidas NMD_V3 shoes evolve the same idea, playing with transparency. You know those signature midsole plugs that you love? On this pair, semi-translucent TPU sends them to the future. ', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG03'), (SELECT id_hang FROM hang WHERE ma_hang='CT02'), 1, 'Adidas Tubular', 'Sneaker0029','Going back a little to the 90s, adidas took inspiration from running shoes from the past and added a little modern technology to create a line of fashionable shoes called Tubular Shadow.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG03'), (SELECT id_hang FROM hang WHERE ma_hang='CT02'), 1, 'Adidas Prophere', 'Sneaker0030','Bold and fearless, the Prophere shoe is designed for personalities who shine on the streets. The shoe has a striking silhouette with a wavy midsole and sculpted high heel panels. The special textile material combines softness and slight roughness for a dusty look. Textile upper with midsole for a supportive, flexible feel.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG03'), (SELECT id_hang FROM hang WHERE ma_hang='CT02'), 1, 'Adidas EQT', 'Sneaker0031','Three Stripes is back with another attractive model. Normally, we d expect the adidas EQT ADV to be delivered in a more casual light. This time, adidas brings this design to the basketball court with a new name. Dubbed the adidas EQT Bask ADV, its height is raised to a mid-cut as it comes in a gray color throughout.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG03'), (SELECT id_hang FROM hang WHERE ma_hang='CT02'), 1, 'Adidas Pulse Boost', 'Sneaker0032','The city has a source of energy. It spreads in the air and throbs in the streets. Immerse yourself in that atmosphere with these adidas running shoes. The elastic cushioning returns energy with each stride with a firm feel, helping you clearly feel the road surface. Because this is the place worth enjoying the most right now.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG03'), (SELECT id_hang FROM hang WHERE ma_hang='CT02'), 1, 'Adidas Campus', 'Sneaker0033','Adidas Campus 80s #Mesa White shoes include colors: Mesa / Cloud White / Off White. Adidas Campus, which dominates Hardwood, has gone beyond the courts and attracted hip-hop musicians and skateboarders.Meet a new release that honors the legacy of a casual shoe made legendary by adoring fans. The soft suede and tanned leather upper features light pastel colors and shiny gold logos.', getdate()),

(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG03'), (SELECT id_hang FROM hang WHERE ma_hang='CT03'), 1, 'Puma Suede Croc Black And White', 'Sneaker0034','Puma Suede Croc Black And White Shoes 384852-01 is a sports shoe model from the Puma brand. These shoes have the main colors Black and White, with a combination of leather and croc patterns.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG03'), (SELECT id_hang FROM hang WHERE ma_hang='CT03'), 1, 'Puma Clyde WH – White Multicolor', 'Sneaker0035','PUMA joined hands with MTV to create a new version of RS-X. Two cultural icons have pushed the boundaries of what you know, bringing you an exclusive pair of shoes with futuristic designs and striking colors. PUMA is cushioning system ensures excellent comfort and cushioning for every stride. Get ready for a new era of sneaker culture with the PUMA MTV RS-X.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG03'), (SELECT id_hang FROM hang WHERE ma_hang='CT03'), 1, 'Puma RS X Efekt Energy', 'Sneaker0036','Mayze Beach Days sneakers are ready to help you become the center of attention during this hot summer. With a uniquely designed transparent sole combined with a simple shoe body, Mayze Beach Days has created a special highlight to help you become more attractive than ever. This is definitely an impressive addition to your summer collection that you cannot do without.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG04'), (SELECT id_hang FROM hang WHERE ma_hang='CT03'), 1, 'Mayze Beach Days', 'Sneaker0037','Mayze Beach Days sneakers are ready to help you become the center of attention during this hot summer. With a uniquely designed transparent sole combined with a simple shoe body, Mayze Beach Days has created a special highlight to help you become more attractive than ever. This is definitely an impressive addition to your summer collection that you cannot do without.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG04'), (SELECT id_hang FROM hang WHERE ma_hang='CT03'), 1, 'RS X Games', 'Sneaker0038','Genuine Puma RS-Fast "International Game" 375149-01 shoes with excellent waterproof ability, color fastness, soft material and meticulous stitching every little bit bring to consumers. A product that reaches high sophistication and is sturdy and durable over time. Not only that, the modern appearance also highlights the beauty of your own personality.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG04'), (SELECT id_hang FROM hang WHERE ma_hang='CT03'), 1, 'Caven 2.0 Spongebob Jr Lifestyle FW23', 'Sneaker0039','Surf is up in Bikini Bottom: PUMA has partnered with the hit Nickelodeon TV series Spongebob Squarepants, to design a new collection for all fans. This season is all about back to school styles. College elements, vibrant colours and lots of Spongebob and Patrick details. The striking tongue of these Caven 2.0 sneakers makes them perfect for jellyfish fishing. Are you ready kids?', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG04'), (SELECT id_hang FROM hang WHERE ma_hang='CT03'), 1, 'Puma Clyde WH – White Multicolor', 'Sneaker0040','PUMA joined hands with MTV to create a new version of RS-X. Two cultural icons have pushed the boundaries of what you know, bringing you an exclusive pair of shoes with futuristic designs and striking colors. PUMA is cushioning system ensures excellent comfort and cushioning for every stride. Get ready for a new era of sneaker culture with the PUMA MTV RS-X', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG04'), (SELECT id_hang FROM hang WHERE ma_hang='CT03'), 1, 'Slipstream Ripndip Lifestyle Shoes', 'Sneaker0041','The PUMA x RIPNDIP takes Lord Nermal and implants the cat on our classic silhouettes for a seriously nonchalant look. Part of the collab is our Slipstream, which features a quirky Lord Nermal graphic on the side.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG04'), (SELECT id_hang FROM hang WHERE ma_hang='CT03'), 1, 'The Smurfs Mayze Women is Sneakers', 'Sneaker0042','Come with us to Smurf Village! PUMA x THE SMURFS takes you into the world of the little blue creatures. As part of our collab, these Mayze sneakers are for all fans with motifs from the popular series.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG04'), (SELECT id_hang FROM hang WHERE ma_hang='CT03'), 1, 'The Smurfs RS-X Little Kids is Sneakers', 'Sneaker0043','Come with us to Smurf Village! PUMA x THE SMURFS takes you into the world of the little blue creatures. As part of our collab, these Mayze sneakers are for all fans with motifs from the popular series.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG02'), (SELECT id_hang FROM hang WHERE ma_hang='CT03'), 1, 'Slipstream Bball Sneakers', 'Sneaker0044','With the Slipstream Bball, we are going back to its proud basketball roots. It features a leather upper with subtle pops of color, as well as a synthetic PUMA Formstrip and synthetic underlays for a clean court-side look.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG02'), (SELECT id_hang FROM hang WHERE ma_hang='CT03'), 1, 'Courtflex V2 PS Lifestyle', 'Sneaker0045','Say hello to our new kids  specific court-inspired trainer for everyday wear. They are easy to get on and off thanks to the wider instep and slip-on construction, and the extra...', getdate()),

(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG02'), (SELECT id_hang FROM hang WHERE ma_hang='CT04'), 1, 'Vans Authentic', 'Sneaker0046','The Authentic, the original and now iconic Vans style, features a simple low top, lace-up profile with sturdy canvas uppers, metal eyelets, and signature rubber waffle outsoles.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG02'), (SELECT id_hang FROM hang WHERE ma_hang='CT04'), 1, 'VANS ERA BMX WHITE', 'Sneaker0047','The Era BMX white and black skate shoes are part of this unique collection. Designed with the classic look and feel of the low top skate shoes you know and love, these Eras are upgraded with 10 oz. canvas uppers, cream and black checkerboard print, and "thumbs-up" graphics printed on the side walls.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG02'), (SELECT id_hang FROM hang WHERE ma_hang='CT04'), 1, 'VANS OLD SKOOL', 'Sneaker0048','The Iconic Shoe that Brought our Sidestripe to Life: This is the Old Skool. The Old Skool was our first footwear design to showcase the famous Vans Sidestripe—although back then, it was just a random doodle drawn by founder Paul Van Doren. Since its debut in 1977, this low-top silhouette has established itself as an icon in the skate, music, and fashion scenes.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG02'), (SELECT id_hang FROM hang WHERE ma_hang='CT04'), 1, 'VANS OLD SKOOL VANS COLLAGE BLACK/WHITE', 'Sneaker0049','First known as Vans #36, the Old Skool was introduced in 1977 and its iconic features like the Jazz stripes created by founder Paul Van Doren quickly became an unmistakable and recognizable mark. The Vans brand is instantly recognizable to this day.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG01'), (SELECT id_hang FROM hang WHERE ma_hang='CT04'), 1, 'VANS COMFYCUSH ONE CHECKERBOARD MARSHMALLOW', 'Sneaker0050','Inheriting Vans heritage design features, the Vans ComfyCush One is the answer to the modern aesthetics and comfort of ComfyCush foam. Designed for class-leading comfort and ease, Vans revives the classic silhouette by incorporating lines from its legendary Slip on into this comfortable shoe with cutting-edge technology. advanced with a 360-degree ComfyCush foam midsole and removable insole for maximum cushioning.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG01'), (SELECT id_hang FROM hang WHERE ma_hang='CT04'), 1, 'VANS CLASSIC SK8-HI BLACK/WHITE', 'Sneaker0051','VANS SK8 first appeared in 1978 under the name "Style 38" with a high ankle design, protecting the important part where skateboarders abuse a lot for unique tricks, and at the same time Sk8 also brought a special fashion style to the park at that time.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG01'), (SELECT id_hang FROM hang WHERE ma_hang='CT04'), 1, 'VANS SK8 LOW BLACK', 'Sneaker0052','Featuring sturdy canvas and suede uppers, the Sk8-Low also includes re-enforced toe caps to withstand repeated wear, padded collars for support and flexibility, and signature rubber waffle outsoles.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG01'), (SELECT id_hang FROM hang WHERE ma_hang='CT04'), 1, 'VANS FLAME SK8-HI REISSUE', 'Sneaker0053','The Flame Sk8-Hi Reissue, the legendary Vans high top reissued with a vintage sensibility, features printed canvas and suede uppers, re-enforced toecaps to withstand repeated wear, padded collars for support and flexibility, and signature rubber waffle outsoles.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG01'), (SELECT id_hang FROM hang WHERE ma_hang='CT04'), 1, 'VANS X SESAME STREET SK8-HI MULTICOLOUR', 'Sneaker0054','Celebrate everyone is favorite grumpy Muppet with the new Vans x Sesame Street SK8-Hi Skate Shoe! This special edition Sk8-Hi features colorful canvas uppers with allover graphics of Oscar the Grouch is Taxi Service serving customers ranging from Big Bird and Elmo to Cookie Monster and Grover.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG01'), (SELECT id_hang FROM hang WHERE ma_hang='CT04'), 1, 'VANS SK8-HI VINTAGE POP MARSHMALLOW TURT LEDOVE', 'Sneaker0055','Vans Vintage Pop SK8-Hi conquered the sneakerhead community with its main ivory white color tone, accented with moss green, extremely luxurious and trendy. The SK8-Hi high neck design combined with Sidestripe stripes and the "Vans Off The Wall" logo has become a unique and distinctive cultural symbol of the skateboard shoe manufacturer.', getdate()),

(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG02'), (SELECT id_hang FROM hang WHERE ma_hang='CT05'), 1, 'Playball Origin', 'Sneaker0056','What is special about the MLB PLAYBALL series? A pair of old canvas shoes that can be worn for a long time without going out of style or a pair of simple, convenient, pretty shoes or it can also be a pair of shoes that can be easily combined with many sets of clothes in the closet that you have. ? The answer is all of the above. And MLB PLAYBALL ORIGIN - LA BLUE is a versatile shoe among them.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG02'), (SELECT id_hang FROM hang WHERE ma_hang='CT05'), 1, 'Big Ball Chunky A', 'Sneaker0057','MLB Big Ball Chunky A New York Yankees Shoes White Logo Black size 235 are high-end shoes with modern, fashionable design from the famous Korean MLB brand. MLB Big Ball Chunky A will give you the ultimate experience on your feet', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG02'), (SELECT id_hang FROM hang WHERE ma_hang='CT05'), 1, 'BigballI Chunky Air', 'Sneaker0058','The MLB Bigball Chunky Air New York Yankees Cream shoes not only have a unique and attractive design, the Chunky style shoes also help the wearer have a variety of outfit options to coordinate with. You will stand out from the crowd with a pair of Chunky shoes because its design cannot be confused with any other pair.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG02'), (SELECT id_hang FROM hang WHERE ma_hang='CT05'), 1, 'Playball Origin', 'Sneaker0059','Mules refers to women is shoes/sandals designed with an open back heel (no heel) and a closed front toe. Due to the contemporary design of this Mules model, many people do not think that this model has appeared since the early 50s and became popular and attractive thanks to the promotion of attractive actress Marilyn Monroe.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG02'), (SELECT id_hang FROM hang WHERE ma_hang='CT05'), 1, 'Chunky Liner Mid Classic Monogram', 'Sneaker0060','MLB Chunky Liner shoes - a great choice for young people is street fashion style, especially suitable for street walking. Possesses the legendary design of a traditional sneaker model but has been innovated with soft and embossed lines throughout the shoe body. With the modern and innovative beauty it brings, this promises to be a contemporary fashion masterpiece that you cannot miss.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG02'), (SELECT id_hang FROM hang WHERE ma_hang='CT05'), 1, 'Chunky Liner Monogram', 'Sneaker0061','MLB Chunky Liner shoes - a great choice for young people is street fashion style, especially suitable for street walking. Possesses the legendary design of a traditional sneaker model but has been innovated with soft and embossed lines throughout the shoe body. With the modern and innovative beauty it brings, this promises to be a contemporary fashion masterpiece that you cannot miss.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG03'), (SELECT id_hang FROM hang WHERE ma_hang='CT05'), 1, 'Chunky Liner Mid Basic', 'Sneaker0062','MLB Big Ball Chunky is a "hot item" from MLB Korea - a fashion company in Korea. This company specializes in designing and manufacturing everything from clothes, dresses to accessories such as hats, bags, socks, etc. All are inspired by the logos of MLB teams. In 2019, MLB is a bright candidate in the top shoes worth owning.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG03'), (SELECT id_hang FROM hang WHERE ma_hang='CT05'), 1, 'BigballI Chunky', 'Sneaker0063','Whenever sneakers are mentioned, surely everyone is mind conjures up countless famous brands. But we cannot ignore the name MLB - the iconic shoe company for baseball, which has been making waves in recent years.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG03'), (SELECT id_hang FROM hang WHERE ma_hang='CT05'), 1, 'Chunky Classic Heel Dia Monogram', 'Sneaker0064','MLB CHUNKY CLASSIC HEEK DIA MONOGRAM NEW YORK YANKEES SHOES with extremely youthful and luxurious stylization, is a harmonious combination of modern fashion and street style suitable for both men and women', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG03'), (SELECT id_hang FROM hang WHERE ma_hang='CT05'), 1, 'Resort Chunky', 'Sneaker0065','MLB Chunky Sandals Resort Los Angeles Dodgers Green are HOT HIT sandals from the famous Korean brand MLB. Designed with high-quality materials, with elegant sandals form and color, these sandals promise to bring you an enjoyable experience.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG03'), (SELECT id_hang FROM hang WHERE ma_hang='CT05'), 1, 'Big Ball Chunky A Gradient Classic Monogram', 'Sneaker0066','The shoes are designed with a chunky style and a gradient NY logo, changing from pink to blue, creating a dynamic and impressive fashion style. The special thing is that the new design can glow in the entire upper, which is super cool for everyone', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG03'), (SELECT id_hang FROM hang WHERE ma_hang='CT05'), 1, 'Chunky Liner', 'Sneaker0067','Chunky Liner is a completely new shoe model with many breakthrough designs of the MLB Korea fashion shoe collection, featuring the classic form of the traditional Sneaker line, combined with the impressive hacked sole of the Chunky line.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG03'), (SELECT id_hang FROM hang WHERE ma_hang='CT05'), 1, 'Chunky Runner','Sneaker0102', 'A sophisticated look mixed with true sporty dynamism, the new eye-catching Chunky Runner shoes have a harmonious, sophisticated color scheme with a modern chunky sole and a prominent baseball logo printed on the outer cheek. Chunky Runner has an irresistible charm and can match all styles and outfits.', getdate()),

(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG04'), (SELECT id_hang FROM hang WHERE ma_hang='CT06'), 1, 'New Balance 327', 'Sneaker0068','The popular New Balance 327 sneaker is such a simple silhouette, albeit elevated with retro textural details, a bold logo, and a lightweight upper. It makes a strong style statement while looking effortless and chic.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG04'), (SELECT id_hang FROM hang WHERE ma_hang='CT06'), 1, 'New Balance 574 Classic Grey', 'Sneaker0069','The midsole is designed with EVA foam and ENCAP technology that increases the stability, rebound, and cushioning feeling in each step. The upper has leather and mesh, whose fabrics offer durability, breathability, and wear resistance.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG04'), (SELECT id_hang FROM hang WHERE ma_hang='CT06'), 1, 'New Balance 550', 'Sneaker0070','The 550 is low top, streamlined silhouette offers a clean take on the heavy-duty designs of the late 80s, while the dependable leather, synthetic, and mesh upper construction is a classic look in any era.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG04'), (SELECT id_hang FROM hang WHERE ma_hang='CT06'), 1, 'New Balance Aim Leon Dore', 'Sneaker0071','he Aimé Leon Dore x New Balance 550 sneakers are equipped with a premium hairy suede upper that is dressed in either a sand or brown color scheme. The low-cut sneaker is also equipped with Aimé Leon Dore is apple logo stamped on the tongue tag, heel, and on the insole.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG04'), (SELECT id_hang FROM hang WHERE ma_hang='CT06'), 1, 'New Balance 530', 'Sneaker0072','The New Balance 530 sneaker is a throwback to one of our classic running looks. This casual shoe combines everyday style with the modern technology of ABZORB cushioning for superior comfort underfoot.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG04'), (SELECT id_hang FROM hang WHERE ma_hang='CT06'), 1, 'New Balance 996', 'Sneaker0073','The 996 is technological features represented six years of evolution, and featured a subtly streamlined exterior design, making for an exceptionally wearable, high-performance shoe. The MADE in USA 996 is outfitted with a premium, pigskin hairy suede and mesh upper featuring a special-edition Grey Day colorway.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG03'), (SELECT id_hang FROM hang WHERE ma_hang='CT06'), 1, 'New Balance 997', 'Sneaker0074','The rubber sole provides maximum traction against several surfaces and the soft and lightweight EVA foam midsole guarantees that every step is cushioned and offers better rebound on the next step.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG03'), (SELECT id_hang FROM hang WHERE ma_hang='CT06'), 1, 'New Balance 109', 'Sneaker0075','A late edition to the popular 90s-inspired series, the womenis WS109 silhouette fits in with some of our most coveted looks. The synthetic mesh upper is breathable, and CUSH+ rubber pods underfoot mean the fit is extremely comfortable. The CUSH+ padded sole unit offers ultra-soft cushioning while maintaining stability for everyday wear.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG03'), (SELECT id_hang FROM hang WHERE ma_hang='CT06'), 1, 'New Balance 57/40', 'Sneaker0076','It presents a braided fastening system with a padded tongue. Internally they are lined with breathable and quick-drying materials. The collar is padded for greater protection of the ankles. It has a midsole made of rubber that provides cushioning, stability and resistance.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG03'), (SELECT id_hang FROM hang WHERE ma_hang='CT06'), 1, 'New Balance 520v5', 'Sneaker0077','Modernized with a flexible leather upper and today´s comfort technology, this reinterpreted version preserves the original sneaker´s spirit and provides both men and women a low-key but sporty look for everyday adventures.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG03'), (SELECT id_hang FROM hang WHERE ma_hang='CT06'), 1, 'New Balance Fresh Foam 1080v11', 'Sneaker0078','Combining an 8-millimeter drop and a pronounced heel-to-toe rocker, the New Balance Fresh Foam 1080v11 is comfortable, supportive, and responsive for all types of running. This is one of the better rocker designs, which are becoming more popular in today is market.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG02'), (SELECT id_hang FROM hang WHERE ma_hang='CT06'), 1, 'New Balance Fresh Foam X', 'Sneaker0079','Fresh Foam X midsole foam with approximately 3% bio-based content delivers our most cushioned Fresh Foam experience for incredible comfort. Bio-based content is made from renewable resources to help reduce our carbon footprint.', getdate()),

(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG02'), (SELECT id_hang FROM hang WHERE ma_hang='CT07'), 1, 'Trainer White Black', 'Sneaker0080','One of the most popular luxury brands also releases its Sneakers. Louis Vuitton LV Trainer White Black White is one of the models released by LV. The color of the shoe is simple, because it is two-colored - black and white. The LV logo is stretched the entire length of the shoe in the form of small logos.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG02'), (SELECT id_hang FROM hang WHERE ma_hang='CT07'), 1, 'Richelieu Ollie', 'Sneaker0081','This version of the LV Ollie richelieu sneaker combines denim and suede calf leather to create the season is key diamond Damier pattern. Inspired by the skate universe, this model has a lightweight rubber outsole and bicolor double laces.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG02'), (SELECT id_hang FROM hang WHERE ma_hang='CT07'), 1, 'LV Skate II', 'Sneaker0082','The new footwear silhouette, which calls on signature ’90s design codes with a bulkier build and statement colorways, nods to the House’s classic motifs with Monogram Flower detailing and bicolor uppers.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG02'), (SELECT id_hang FROM hang WHERE ma_hang='CT07'), 1, 'LV Skate III', 'Sneaker0083','In terms of construction, the shoe enlists technical mesh, calf leather and suede — together, the contrasting textiles appear intricately entangled, with a standout padded collar, double laces and a rubber outsole finished with miniature logo inclusions.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG01'), (SELECT id_hang FROM hang WHERE ma_hang='CT07'), 1, 'LV Trainer', 'Sneaker0084','Crafted from supple white calf leather, this version of the LV Trainer sneaker is part of the season is Rainbow ready-to-wear capsule collection. This cult model was designed by Louis Vuitton Artistic Director Virgil Abloh and inspired by vintage basketball sneakers.', getdate()),

(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG01'), (SELECT id_hang FROM hang WHERE ma_hang='CT08'), 1, 'Converse Classic', 'Sneaker0085','Converse classic shoes have simple colors but do not cause monotony, the color tones from black, red, white, beige, pink... are not too excessive but with extremely youthful tones, trendy and suitable for many styles. different fashion. Whether you are a person with personality or someone with a simple, sophisticated fashion style, you can use it.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG01'), (SELECT id_hang FROM hang WHERE ma_hang='CT08'), 1, 'Converse Classic Red', 'Sneaker0086','Converse classic shoes are Converse is number 1 best-selling line, a traditional Converse shoe line that is kept true to the original. Iconic shoe model for over 100 years & best-selling shoe of all time.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG01'), (SELECT id_hang FROM hang WHERE ma_hang='CT08'), 1, 'Converse Classic Full  Black', 'Sneaker0087','Classic is Converse s No. 1 best-selling line. With 6 basic colors, black and white is an easy color that many people choose. Shoes that everyone should have because of their durability, beauty and are super easy to coordinate, suitable for all types of outfits.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG01'), (SELECT id_hang FROM hang WHERE ma_hang='CT08'), 1, 'Converse Classic White', 'Sneaker0088','Converse classic shoes are the number 1 best-selling line of the Converse brand. Considered a legendary shoe model associated with the youth and youth of many young generations over the past 100 years. Especially the white color scheme makes you satisfied with the extremely accurate mix & match. Above all, the Chuck Taylor Classic White turtleneck will bring a dynamic feeling and the ability to highlight your personality no matter where you are or what you do. What.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG01'), (SELECT id_hang FROM hang WHERE ma_hang='CT08'), 1, 'Run Star Legacy CX Platform', 'Sneaker0089','Run Star Legacy CX Platform. Details like a canvas build, rubber toe cap and Chuck Taylor ankle patch stay true to the original, while a molded platform, two-tone outsole and rounded heel give off futuristic vibes.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG01'), (SELECT id_hang FROM hang WHERE ma_hang='CT08'), 1, 'Chuck 70 Plus Colourblock', 'Sneaker0090','An unexpected update on an all-time classic, the Chuck 70 Plus mixes iconic features with future-forward styling. A fusion of mixed-weight canvas comes together with asymmetrical lines and utility-inspired color for a statement-making look. Spliced rubber and ankle patch details keep all eyes on you, while premium cushioning helps you stay light on your feet.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG04'), (SELECT id_hang FROM hang WHERE ma_hang='CT08'), 1, 'Run Star Hike Platform', 'Sneaker0091','A chunky platform and jagged rubber sole put an unexpected twist on your everyday Chucks. Details like a canvas build, rubber toe cap and Chuck Taylor ankle patch stay true to the original, while a molded platform, two-tone outsole and rounded heel give off futuristic vibes. Canvas high top platform sneakers.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG04'), (SELECT id_hang FROM hang WHERE ma_hang='CT08'), 1, 'Run Star Hike', 'Sneaker0092','A chunky platform and jagged rubber sole put an unexpected twist on your everyday Chucks. Details like a canvas build, rubber toe cap and Chuck Taylor ankle patch stay true to the original, while a molded platform, two-tone outsole and rounded heel give off futuristic vibes.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG04'), (SELECT id_hang FROM hang WHERE ma_hang='CT08'), 1, 'Chuck Taylor All Star Classic', 'Sneaker0093','Converse Chuck Taylor All Star Classic Hi Top Black shoes with a youthful and dynamic design, combined with durable, beautiful, soft materials, bring a comfortable feeling with every step, helping the wearer confidently express their style. individual.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG04'), (SELECT id_hang FROM hang WHERE ma_hang='CT08'), 1, 'Chuck Taylor All Star Lugged 2.0', 'Sneaker0094','Inheriting the traditional beauty of the Chuck Taylor All Star line from the Converse brand, the Lugged 2.0 version is improved with an exaggerated "sawtooth" platform that makes a difference for your feet.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG04'), (SELECT id_hang FROM hang WHERE ma_hang='CT08'), 1, 'Chuck Taylor All Star Lugged 2.0 Counter Climate', 'Sneaker0095','A lugged, chevron-inspired tread and rubber overlays add extra durability and style to waterproof canvas boots.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG04'), (SELECT id_hang FROM hang WHERE ma_hang='CT08'), 1, 'Chuck Taylor All Star Everyday Essentials', 'Sneaker0096','Since 1917, the Chuck Taylor All Star has been an icon of everyday cool. You already know the details that started on the basketball court—the diamond pattern outsole and All Star license plate. OrthoLite cushioning for all-day comfort you can feel good about. The classic style is complemented by heritage design elements, like a rubber toe bumper and woven All Star tongue label. Updated for the season in trending spring color.', getdate()),

(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG02'), (SELECT id_hang FROM hang WHERE ma_hang='CT09'), 1, 'Onitsuka Tiger Mexico 66 SD - Birch / Wood Crepe', 'Sneaker0098','Go retro with the unisex MEXICO 66™ Super Deluxe heritage lifestyle shoe by ONITSUKA TIGER. Our most popular shoe, first debuted in 1968 in Mexico City, has been updated for contemporary urban living. Still rocking that same vintage style, this shoe boasts a suede, original low-top silhouette and classic design touches, but behind that style there is now even more substance. ', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG02'), (SELECT id_hang FROM hang WHERE ma_hang='CT09'), 1, 'Reebok Club C Double - White/Gum', 'Sneaker0099',' These women is tennis-inspired shoes elevate your style with a stacked midsole for extra height and a chunky profile. The upper is made of supple leather for a clean, minimalist look.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG02'), (SELECT id_hang FROM hang WHERE ma_hang='CT09'), 1, 'Playball Origin', 'Sneaker0100','What is special about the MLB PLAYBALL series? A pair of old canvas shoes that can be worn for a long time without going out of style or a pair of simple, convenient, pretty shoes or it can also be a pair of shoes that can be easily combined with many sets of clothes in the closet that you have. ? The answer is all of the above. And MLB PLAYBALL ORIGIN - LA BLUE is a versatile shoe among them.', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG02'), (SELECT id_hang FROM hang WHERE ma_hang='CT09'), 1, 'Royal Complete Clean 2.0', 'Sneaker0101','These predominantly white shoes are accented with a metallic logo. The shoe surface is made of soft, flexible synthetic leather, plus a lightweight, abrasion-resistant and grippy rubber sole, which will help you move more easily. Wear these shoes and immediately feel the comfort.', getdate()),


(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG03'), (SELECT id_hang FROM hang WHERE ma_hang='CT010'), 2, 'No name', 'Sneaker00105','No Description', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG03'), (SELECT id_hang FROM hang WHERE ma_hang='CT010'), 2, 'No name', 'Sneaker00106','No Description', getdate()),
(NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG03'), (SELECT id_hang FROM hang WHERE ma_hang='CT010'), 2, 'No name', 'Sneaker00107','No Description', getdate());

insert into [size](id_size,so_size, trang_Thai, ma_size, tg_them)
values(NEWID(), '36', 0, 'Size00', GETDATE())
insert into [size](id_size,so_size, trang_Thai, ma_size, tg_them)
values(NEWID(), '37', 1, 'Size01', GETDATE())
insert into [size](id_size,so_size, trang_Thai, ma_size, tg_them)
values(NEWID(), '38', 1, 'Size02', GETDATE())
insert into [size](id_size,so_size, trang_Thai, ma_size, tg_them)
values(NEWID(), '39', 1, 'Size03', GETDATE())
insert into [size](id_size,so_size, trang_Thai, ma_size, tg_them)
values(NEWID(), '40', 1, 'Size04', GETDATE())
insert into [size](id_size,so_size, trang_Thai, ma_size, tg_them)
values(NEWID(), '41', 1, 'Size05', GETDATE())
insert into [size](id_size,so_size, trang_Thai, ma_size, tg_them)
values(NEWID(), '42', 1, 'Size06', GETDATE())
insert into [size](id_size,so_size, trang_Thai, ma_size, tg_them)
values(NEWID(), '43', 1, 'Size07', GETDATE())
insert into [size](id_size,so_size, trang_Thai, ma_size, tg_them)
values(NEWID(), '44', 1, 'Size08', GETDATE())
insert into [size](id_size,so_size, trang_Thai, ma_size, tg_them)
values(NEWID(), '45', 1, 'Size09', GETDATE())
insert into [size](id_size,so_size, trang_Thai, ma_size, tg_them)
values(NEWID(), '46', 0, 'Size011', GETDATE())


insert into mau_sac(id_mau, ten_mau,trang_thai, ma, tg_them, ma_mau)
values(NEWID(), 'Dark Blue', 1, 'Color01', GETDATE() ,'rgb(57, 49, 98)' )
insert into mau_sac(id_mau, ten_mau,trang_thai, ma, tg_them, ma_mau)
values(NEWID(), 'Brown', 1, 'Color02', GETDATE(), 'rgb(133, 102, 83)')
insert into mau_sac(id_mau, ten_mau,trang_thai, ma, tg_them, ma_mau)
values(NEWID(), 'Black', 1, 'Color03', GETDATE(), 'rgb(35, 35, 37)')
insert into mau_sac(id_mau, ten_mau,trang_thai, ma, tg_them, ma_mau)
values(NEWID(), 'Pink', 1, 'Color04', GETDATE(), 'rgb(170, 145, 158)')
insert into mau_sac(id_mau, ten_mau,trang_thai, ma, tg_them, ma_mau)
values(NEWID(), 'White', 1, 'Color05', GETDATE(), 'rgb(193, 188, 190)')
insert into mau_sac(id_mau, ten_mau,trang_thai, ma, tg_them, ma_mau)
values(NEWID(), 'Blue & Grey', 1, 'Color06', GETDATE(), 'rgb(116, 112, 110)')
insert into mau_sac(id_mau, ten_mau,trang_thai, ma, tg_them, ma_mau)
values(NEWID(), 'Orange', 1, 'Color07', GETDATE(), 'rgb(177, 98, 9)')
insert into mau_sac(id_mau, ten_mau,trang_thai, ma, tg_them, ma_mau)
values(NEWID(), 'Navi', 1, 'Color08', GETDATE(), 'rgb(16, 20, 77)')
insert into mau_sac(id_mau, ten_mau,trang_thai, ma, tg_them, ma_mau)
values(NEWID(), 'Green', 1, 'Color09', GETDATE(), 'rgb(86, 162, 116)')
insert into mau_sac(id_mau, ten_mau,trang_thai, ma, tg_them, ma_mau)
values(NEWID(), 'Red', 1, 'Color010', GETDATE(), 'rgb(169, 24, 31)')
insert into mau_sac(id_mau, ten_mau,trang_thai, ma, tg_them, ma_mau)
values(NEWID(), ' Navi II', 1, 'Color011', GETDATE(), 'rgb(36, 63, 89)')
insert into mau_sac(id_mau, ten_mau,trang_thai, ma, tg_them, ma_mau)
values(NEWID(), 'Pink II', 1, 'Color012', GETDATE(), 'rgb(193, 90, 109)')
insert into mau_sac(id_mau, ten_mau,trang_thai, ma, tg_them, ma_mau)
values(NEWID(), 'Bluish-Purple', 1, 'Color013', GETDATE(), 'rgb(108, 84, 170)')
insert into mau_sac(id_mau, ten_mau,trang_thai, ma, tg_them, ma_mau)
values(NEWID(), 'Smoke', 1, 'Color014', GETDATE(), 'rgb(82, 82, 82)')
insert into mau_sac(id_mau, ten_mau,trang_thai, ma, tg_them, ma_mau)
values(NEWID(), 'Yellow', 1, 'Color015', GETDATE(), 'rgb(255, 235, 89)')
insert into mau_sac(id_mau, ten_mau,trang_thai, ma, tg_them, ma_mau)
values(NEWID(), 'Dark', 0, 'Color016', GETDATE(), 'rgb(15, 26, 37)')
insert into mau_sac(id_mau, ten_mau,trang_thai, ma, tg_them, ma_mau)
values(NEWID(), 'Blue Grey', 0, 'Color017', GETDATE(), 'rgb(174, 203, 241)')



insert into hinh_anh(id_hinh_anh, ma_anh , trang_thai, url1, url2, url3, url4, tg_them)
values
(NEWID(), 'HA01', 1, 'product-1.1.png', 'product-1.2.png', 'product-1.3.png', 'product-1.4.png', getdate()),

(NEWID(), 'HA02.0', 1, 'product-2.0.1.png', 'product-2.0.2.png', 'product-2.0.3.png', 'product-2.0.4.png', getdate()),
(NEWID(), 'HA02.1', 1, 'product-2.1.1.png', 'product-2.1.2.png', 'product-2.1.3.png', 'product-2.1.4.png', getdate()), 
(NEWID(), 'HA02.2', 1, 'product-2.2.1.png', 'product-2.2.2.png', 'product-2.2.3.png', 'product-2.2.4.png', getdate()), 
(NEWID(), 'HA02.3', 1, 'product-2.3.1.png', 'product-2.3.2.png', 'product-2.3.3.png', 'product-2.3.4.png', getdate()), 
(NEWID(), 'HA02.4', 1, 'product-2.4.1.png', 'product-2.4.2.png', 'product-2.4.3.png', 'product-2.4.4.png', getdate()), 
(NEWID(), 'HA02.5', 1, 'product-2.5.1.png', 'product-2.5.2.png', 'product-2.5.3.png', 'product-2.5.4.png', getdate()), 
(NEWID(), 'HA02.6', 1, 'product-2.6.1.png', 'product-2.6.2.png', 'product-2.6.3.png', 'product-2.6.4.png', getdate()), 

(NEWID(), 'HA03', 1, 'product-3.1.png', 'product-3.2.png', 'product-3.3.png', 'product-3.4.png', getdate()), 
(NEWID(), 'HA04', 1, 'product-4.1.png', 'product-4.2.png', 'product-4.3.png', 'product-4.4.png', getdate()), 
(NEWID(), 'HA05', 1, 'product-5.1.png', 'product-5.2.png', 'product-5.3.png', 'product-5.4.png', getdate()), 
(NEWID(), 'HA06', 1, 'product-6.1.png', 'product-6.2.png', 'product-6.3.png', 'product-6.4.png', getdate()), 
(NEWID(), 'HA07', 1, 'product-7.1.png', 'product-7.2.png', 'product-7.3.png', 'product-7.4.png', getdate()), 
(NEWID(), 'HA08', 1, 'product-8.1.png', 'product-8.2.png', 'product-8.3.png', 'product-8.4.png', getdate()), 
(NEWID(), 'HA09', 1, 'product-9.1.png', 'product-9.2.png', 'product-9.3.png', 'product-9.4.png', getdate()), 
(NEWID(), 'HA010', 1, 'product-10.1.png', 'product-10.2.png', 'product-10.3.png', 'product-10.4.png', getdate()), 
(NEWID(), 'HA011', 1, 'product-11.1.png', 'product-11.2.png', 'product-11.3.png', 'product-11.4.png', getdate()), 
(NEWID(), 'HA012', 1, 'product-12.1.png', 'product-12.2.png', 'product-12.3.png', 'product-12.4.png', getdate()), 
(NEWID(), 'HA013', 1, 'product-13.1.png', 'product-13.2.png', 'product-13.3.png', 'product-13.4.png', getdate()), 
(NEWID(), 'HA014', 1, 'product-14.1.png', 'product-14.2.png', 'product-14.3.png', 'product-14.4.png', getdate()), 
(NEWID(), 'HA015', 1, 'product-15.1.png', 'product-15.2.png', 'product-15.3.png', 'product-15.4.png', getdate()), 
(NEWID(), 'HA016', 1, 'product-16.1.png', 'product-16.2.png', 'product-16.3.png', 'product-16.4.png', getdate()), 
(NEWID(), 'HA017', 1, 'product-17.1.png', 'product-17.2.png', 'product-17.3.png', 'product-17.4.png', getdate()), 
(NEWID(), 'HA018', 1, 'product-18.1.png', 'product-18.2.png', 'product-18.3.png', 'product-18.4.png', getdate()), 
(NEWID(), 'HA019', 1, 'product-19.1.png', 'product-19.2.png', 'product-19.3.png', 'product-19.4.png', getdate()), 
(NEWID(), 'HA020', 1, 'product-20.1.png', 'product-20.2.png', 'product-20.3.png', 'product-20.4.png', getdate()), 
(NEWID(), 'HA021', 1, 'product-21.1.png', 'product-21.2.png', 'product-21.3.png', 'product-21.4.png', getdate()), 
(NEWID(), 'HA022', 1, 'product-22.1.png', 'product-22.2.png', 'product-22.3.png', 'product-22.4.png', getdate()), 
(NEWID(), 'HA023', 1, 'product-23.1.png', 'product-23.2.png', 'product-23.3.png', 'product-23.4.png', getdate()), 
(NEWID(), 'HA024', 1, 'product-24.1.png', 'product-24.2.png', 'product-24.3.png', 'product-24.4.png', getdate()), 
(NEWID(), 'HA025', 1, 'product-25.1.png', 'product-25.2.png', 'product-25.3.png', 'product-25.4.png', getdate()), 
(NEWID(), 'HA026', 1, 'product-26.1.png', 'product-26.2.png', 'product-26.3.png', 'product-26.4.png', getdate()), 
(NEWID(), 'HA027', 1, 'product-27.1.png', 'product-27.2.png', 'product-27.3.png', 'product-27.4.png', getdate()), 
(NEWID(), 'HA028', 1, 'product-28.1.png', 'product-28.2.png', 'product-28.3.png', 'product-28.4.png', getdate()), 
(NEWID(), 'HA029', 1, 'product-29.1.png', 'product-29.2.png', 'product-29.3.png', 'product-29.4.png', getdate()), 
(NEWID(), 'HA030', 1, 'product-30.1.png', 'product-30.2.png', 'product-30.3.png', 'product-30.4.png', getdate()), 
(NEWID(), 'HA031', 1, 'product-31.1.png', 'product-31.2.png', 'product-31.3.png', 'product-31.4.png', getdate()), 
(NEWID(), 'HA032', 1, 'product-32.1.png', 'product-32.2.png', 'product-32.3.png', 'product-32.4.png', getdate()), 
(NEWID(), 'HA033', 1, 'product-33.1.png', 'product-33.2.png', 'product-33.3.png', 'product-33.4.png', getdate()), 
(NEWID(), 'HA034', 1, 'product-34.1.png', 'product-34.2.png', 'product-34.3.png', 'product-34.4.png', getdate()), 
(NEWID(), 'HA035', 1, 'product-35.1.png', 'product-35.2.png', 'product-35.3.png', 'product-35.4.png', getdate()), 
(NEWID(), 'HA036', 1, 'product-36.1.png', 'product-36.2.png', 'product-36.3.png', 'product-36.4.png', getdate()), 
(NEWID(), 'HA037', 1, 'product-37.1.png', 'product-37.2.png', 'product-37.3.png', 'product-37.4.png', getdate()), 
(NEWID(), 'HA038', 1, 'product-38.1.png', 'product-38.2.png', 'product-38.3.png', 'product-38.4.png', getdate()), 
(NEWID(), 'HA039', 1, 'product-39.1.png', 'product-39.2.png', 'product-39.3.png', 'product-39.4.png', getdate()), 
(NEWID(), 'HA040', 1, 'product-40.1.png', 'product-40.2.png', 'product-40.3.png', 'product-40.4.png', getdate()), 
(NEWID(), 'HA041', 1, 'product-41.1.png', 'product-41.2.png', 'product-41.3.png', 'product-41.4.png', getdate()), 
(NEWID(), 'HA042', 1, 'product-42.1.png', 'product-42.2.png', 'product-42.3.png', 'product-42.4.png', getdate()), 
(NEWID(), 'HA043', 1, 'product-43.1.png', 'product-43.2.png', 'product-43.3.png', 'product-43.4.png', getdate()), 
(NEWID(), 'HA044', 1, 'product-44.1.png', 'product-44.2.png', 'product-44.3.png', 'product-44.4.png', getdate()), 
(NEWID(), 'HA045', 1, 'product-45.1.png', 'product-45.2.png', 'product-45.3.png', 'product-45.4.png', getdate()), 
(NEWID(), 'HA046', 1, 'product-46.1.png', 'product-46.2.png', 'product-46.3.png', 'product-46.4.png', getdate()), 
(NEWID(), 'HA047', 1, 'product-47.1.png', 'product-47.2.png', 'product-47.3.png', 'product-47.4.png', getdate()), 
(NEWID(), 'HA048', 1, 'product-48.1.png', 'product-48.2.png', 'product-48.3.png', 'product-48.4.png', getdate()), 
(NEWID(), 'HA049', 1, 'product-49.1.png', 'product-49.2.png', 'product-49.3.png', 'product-49.4.png', getdate()), 
(NEWID(), 'HA050', 1, 'product-50.1.png', 'product-50.2.png', 'product-50.3.png', 'product-50.4.png', getdate()), 
(NEWID(), 'HA051', 1, 'product-51.1.png', 'product-51.2.png', 'product-51.3.png', 'product-51.4.png', getdate()), 
(NEWID(), 'HA052', 1, 'product-52.1.png', 'product-52.2.png', 'product-52.3.png', 'product-52.4.png', getdate()), 
(NEWID(), 'HA053', 1, 'product-53.1.png', 'product-53.2.png', 'product-53.3.png', 'product-53.4.png', getdate()), 
(NEWID(), 'HA054', 1, 'product-54.1.png', 'product-54.2.png', 'product-54.3.png', 'product-54.4.png', getdate()), 
(NEWID(), 'HA055', 1, 'product-55.1.png', 'product-55.2.png', 'product-55.3.png', 'product-55.4.png', getdate()), 
(NEWID(), 'HA056', 1, 'product-56.1.png', 'product-56.2.png', 'product-56.3.png', 'product-56.4.png', getdate()), 
(NEWID(), 'HA057', 1, 'product-57.1.png', 'product-57.2.png', 'product-57.3.png', 'product-57.4.png', getdate()), 
(NEWID(), 'HA058', 1, 'product-58.1.png', 'product-58.2.png', 'product-58.3.png', 'product-58.4.png', getdate()), 
(NEWID(), 'HA059', 1, 'product-59.1.png', 'product-59.2.png', 'product-59.3.png', 'product-59.4.png', getdate()), 
(NEWID(), 'HA060', 1, 'product-60.1.png', 'product-60.2.png', 'product-60.3.png', 'product-60.4.png', getdate()), 
(NEWID(), 'HA061', 1, 'product-61.1.png', 'product-61.2.png', 'product-61.3.png', 'product-61.4.png', getdate()), 
(NEWID(), 'HA062', 1, 'product-62.1.png', 'product-62.2.png', 'product-62.3.png', 'product-62.4.png', getdate()), 
(NEWID(), 'HA063', 1, 'product-63.1.png', 'product-63.2.png', 'product-63.3.png', 'product-63.4.png', getdate()), 

(NEWID(), 'HA064.0', 1, 'product-64.0.1.png', 'product-64.0.2.png', 'product-64.0.3.png', 'product-64.0.4.png', getdate()), 
(NEWID(), 'HA064.1', 1, 'product-64.1.1.png', 'product-64.1.2.png', 'product-64.1.3.png', 'product-64.1.4.png', getdate()), 

(NEWID(), 'HA065', 1, 'product-65.1.png', 'product-65.2.png', 'product-65.3.png', 'product-65.4.png', getdate()), 
(NEWID(), 'HA066', 1, 'product-66.1.png', 'product-66.2.png', 'product-66.3.png', 'product-66.4.png', getdate()),

(NEWID(), 'HA067.0', 1, 'product-67.0.1.png', 'product-67.0.2.png', 'product-67.0.3.png', 'product-67.0.4.png', getdate()), 
(NEWID(), 'HA067.1', 1, 'product-67.1.1.png', 'product-67.1.2.png', 'product-67.1.3.png', 'product-67.1.4.png', getdate()), 
(NEWID(), 'HA067.2', 1, 'product-67.2.1.png', 'product-67.2.2.png', 'product-67.2.3.png', 'product-67.2.4.png', getdate()), 
(NEWID(), 'HA067.3', 1, 'product-67.3.1.png', 'product-67.3.2.png', 'product-67.3.3.png', 'product-67.3.4.png', getdate()), 
(NEWID(), 'HA067.4', 1, 'product-67.4.1.png', 'product-67.4.2.png', 'product-67.4.3.png', 'product-67.4.4.png', getdate()), 
(NEWID(), 'HA067.5', 1, 'product-67.5.1.png', 'product-67.5.2.png', 'product-67.5.3.png', 'product-67.5.4.png', getdate()),  

(NEWID(), 'HA068', 1, 'product-68.1.png', 'product-68.2.png', 'product-68.3.png', 'product-68.4.png', getdate()), 
(NEWID(), 'HA069', 1, 'product-69.1.png', 'product-69.2.png', 'product-69.3.png', 'product-69.4.png', getdate()), 
(NEWID(), 'HA070', 1, 'product-70.1.png', 'product-70.2.png', 'product-70.3.png', 'product-70.4.png', getdate()), 
(NEWID(), 'HA071', 1, 'product-71.1.png', 'product-71.2.png', 'product-71.3.png', 'product-71.4.png', getdate()), 
(NEWID(), 'HA072', 1, 'product-72.1.png', 'product-72.2.png', 'product-72.3.png', 'product-72.4.png', getdate()), 
(NEWID(), 'HA073', 1, 'product-73.1.png', 'product-73.2.png', 'product-73.3.png', 'product-73.4.png', getdate()), 
(NEWID(), 'HA074', 1, 'product-74.1.png', 'product-74.2.png', 'product-74.3.png', 'product-74.4.png', getdate()), 
(NEWID(), 'HA075', 1, 'product-75.1.png', 'product-75.2.png', 'product-75.3.png', 'product-75.4.png', getdate()), 
(NEWID(), 'HA076', 1, 'product-76.1.png', 'product-76.2.png', 'product-76.3.png', 'product-76.4.png', getdate()), 
(NEWID(), 'HA077', 1, 'product-77.1.png', 'product-77.2.png', 'product-77.3.png', 'product-77.4.png', getdate()), 
(NEWID(), 'HA078', 1, 'product-78.1.png', 'product-78.2.png', 'product-78.3.png', 'product-78.4.png', getdate()), 
(NEWID(), 'HA079', 1, 'product-79.1.png', 'product-79.2.png', 'product-79.3.png', 'product-79.4.png', getdate()), 
(NEWID(), 'HA080', 1, 'product-80.1.png', 'product-80.2.png', 'product-80.3.png', 'product-80.4.png', getdate()), 
(NEWID(), 'HA081', 1, 'product-81.1.png', 'product-81.2.png', 'product-81.3.png', 'product-81.4.png', getdate()), 
(NEWID(), 'HA082', 1, 'product-82.1.png', 'product-82.2.png', 'product-82.3.png', 'product-82.4.png', getdate()),

(NEWID(), 'HA083.0', 1, 'product-83.0.1.png', 'product-83.0.2.png', 'product-83.0.3.png', 'product-83.0.4.png', getdate()),
(NEWID(), 'HA083.1', 1, 'product-83.1.1.png', 'product-83.1.2.png', 'product-83.1.3.png', 'product-83.1.4.png', getdate()),
(NEWID(), 'HA083.2', 1, 'product-83.2.1.png', 'product-83.2.2.png', 'product-83.2.3.png', 'product-83.2.4.png', getdate()),

(NEWID(), 'HA084', 1, 'product-84.1.png', 'product-84.2.png', 'product-84.3.png', 'product-84.4.png', getdate()), 
(NEWID(), 'HA085', 1, 'product-85.1.png', 'product-85.2.png', 'product-85.3.png', 'product-85.4.png', getdate()), 

(NEWID(), 'HA086.0', 1, 'product-86.0.1.png', 'product-86.0.2.png', 'product-86.0.3.png', 'product-86.0.4.png', getdate()),
(NEWID(), 'HA086.1', 1, 'product-86.1.1.png', 'product-86.1.2.png', 'product-86.1.3.png', 'product-86.1.4.png', getdate()),

(NEWID(), 'HA087', 1, 'product-87.1.png', 'product-87.2.png', 'product-87.3.png', 'product-87.4.png', getdate()), 
(NEWID(), 'HA088', 1, 'product-88.1.png', 'product-88.2.png', 'product-88.3.png', 'product-88.4.png', getdate()), 
(NEWID(), 'HA089', 1, 'product-89.1.png', 'product-89.2.png', 'product-89.3.png', 'product-89.4.png', getdate()), 
(NEWID(), 'HA090', 1, 'product-90.1.png', 'product-90.2.png', 'product-90.3.png', 'product-90.4.png', getdate()), 
(NEWID(), 'HA091', 1, 'product-91.1.png', 'product-91.2.png', 'product-91.3.png', 'product-91.4.png', getdate()), 
(NEWID(), 'HA092', 1, 'product-92.1.png', 'product-92.2.png', 'product-92.3.png', 'product-92.4.png', getdate()), 
(NEWID(), 'HA093', 1, 'product-93.1.png', 'product-93.2.png', 'product-93.3.png', 'product-93.4.png', getdate()), 
(NEWID(), 'HA094', 1, 'product-94.1.png', 'product-94.2.png', 'product-94.3.png', 'product-94.4.png', getdate()), 
(NEWID(), 'HA095', 1, 'product-95.1.png', 'product-95.2.png', 'product-95.3.png', 'product-95.4.png', getdate()), 
(NEWID(), 'HA096', 1, 'product-96.1.png', 'product-96.2.png', 'product-96.3.png', 'product-96.4.png', getdate()), 
(NEWID(), 'HA097', 1, 'product-97.1.png', 'product-97.2.png', 'product-97.3.png', 'product-97.4.png', getdate()), 
(NEWID(), 'HA098', 1, 'product-98.1.png', 'product-98.2.png', 'product-98.3.png', 'product-98.4.png', getdate()), 
(NEWID(), 'HA099', 1, 'product-99.1.png', 'product-99.2.png', 'product-99.3.png', 'product-99.4.png', getdate()), 
(NEWID(), 'HA0100', 1, 'product-100.1.png', 'product-100.2.png', 'product-100.3.png', 'product-100.4.png', getdate()),
(NEWID(), 'HA0101', 1, 'product-101.1.png', 'product-101.2.png', 'product-101.3.png', 'product-101.4.png', getdate()),
(NEWID(), 'HA0102.0', 1, 'product-102.0.1.png', 'product-102.0.2.png', 'product-102.0.3.png', 'product-102.0.4.png', getdate()), 
(NEWID(), 'HA0102.1', 1, 'product-102.1.1.png', 'product-102.1.2.png', 'product-102.1.3.png', 'product-102.1.4.png', getdate()), 
(NEWID(), 'HA0102.2', 1, 'product-102.2.1.png', 'product-102.2.2.png', 'product-102.2.3.png', 'product-102.2.4.png', getdate()), 

(NEWID(), 'HA0103', 1, 'product-102.3.1.png', 'product-102.3.2.png', 'product-102.3.3.png', 'product-102.3.4.png', getdate()),
(NEWID(), 'HA0104', 1, 'product-102.3.1.png', 'product-102.3.2.png', 'product-102.3.3.png', 'product-102.3.4.png', getdate()),
(NEWID(), 'HA0105', 0, 'product-102.3.1.png', 'product-102.3.2.png', 'product-102.3.3.png', 'product-102.3.4.png', getdate());


insert into chi_tiet_giay(id_chi_tiet_giay, id_giay, id_mau, id_size, id_hinh_anh, trang_thai)
VALUES



(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker001'), (SELECT id_mau FROM mau_sac WHERE ma='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA01'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker001'), (SELECT id_mau FROM mau_sac WHERE ma='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA01'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker001'), (SELECT id_mau FROM mau_sac WHERE ma='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA01'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker001'), (SELECT id_mau FROM mau_sac WHERE ma='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA01'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker001'), (SELECT id_mau FROM mau_sac WHERE ma='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA01'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker001'), (SELECT id_mau FROM mau_sac WHERE ma='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA01'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker001'), (SELECT id_mau FROM mau_sac WHERE ma='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA01'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker001'), (SELECT id_mau FROM mau_sac WHERE ma='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA01'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker001'), (SELECT id_mau FROM mau_sac WHERE ma='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA01'), 1),

(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.0'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.1'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.1'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.1'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.1'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.1'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.1'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.1'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.1'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.1'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color04'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.2'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color04'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.2'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color04'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.2'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color04'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.2'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color04'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.2'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color04'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.2'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color04'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.2'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color04'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.2'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color04'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.2'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.3'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.3'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.3'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.3'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.3'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.3'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.3'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.3'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.3'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color06'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.4'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color06'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.4'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color06'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.4'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color06'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.4'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color06'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.4'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color06'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.4'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color06'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.4'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color06'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.4'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color06'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.4'), 1);



insert into chi_tiet_giay(id_chi_tiet_giay, id_giay, id_mau, id_size, id_hinh_anh, trang_thai)
VALUES



(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.5'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.5'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.5'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.5'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.5'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.5'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.5'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.5'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.5'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color08'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.6'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color08'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.6'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color08'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.6'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color08'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.6'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color08'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.6'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color08'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.6'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color08'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.6'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color08'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.6'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma='Color08'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02.6'), 1),


-- sxshacskcsacsdcdbckskcdsbbsdc


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker003'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA03'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker003'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA03'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker003'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA03'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker003'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA03'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker003'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA03'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker003'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA03'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker003'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA03'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker003'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA03'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker003'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA03'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker004'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA04'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker004'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA04'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker004'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA04'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker004'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA04'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker004'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA04'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker004'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA04'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker004'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA04'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker004'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA04'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker004'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA04'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker005'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA05'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker005'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA05'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker005'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA05'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker005'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA05'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker005'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA05'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker005'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA05'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker005'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA05'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker005'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA05'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker005'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA05'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker006'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA06'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker006'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA06'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker006'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA06'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker006'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA06'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker006'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA06'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker006'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA06'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker006'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA06'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker006'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA06'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker006'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA06'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker007'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA07'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker007'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA07'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker007'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA07'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker007'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA07'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker007'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA07'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker007'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA07'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker007'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA07'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker007'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA07'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker007'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA07'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker008'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA08'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker008'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA08'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker008'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA08'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker008'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA08'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker008'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA08'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker008'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA08'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker008'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA08'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker008'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA08'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker008'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA08'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker009'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA09'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker009'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA09'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker009'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA09'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker009'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA09'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker009'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA09'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker009'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA09'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker009'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA09'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker009'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA09'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker009'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA09'), 1);


--ASDJKALSDBFHALSKDFAS NULL


insert into chi_tiet_giay(id_chi_tiet_giay, id_giay, id_mau, id_size, id_hinh_anh, trang_thai)
VALUES


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0010'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA010'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0010'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA010'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0010'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA010'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0010'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA010'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0010'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA010'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0010'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA010'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0010'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA010'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0010'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA010'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0010'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA010'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0011'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA011'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0011'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA011'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0011'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA011'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0011'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA011'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0011'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA011'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0011'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA011'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0011'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA011'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0011'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA011'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0011'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA011'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0012'), (SELECT id_mau FROM mau_sac WHERE ma='Color011'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA012'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0012'), (SELECT id_mau FROM mau_sac WHERE ma='Color011'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA012'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0012'), (SELECT id_mau FROM mau_sac WHERE ma='Color011'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA012'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0012'), (SELECT id_mau FROM mau_sac WHERE ma='Color011'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA012'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0012'), (SELECT id_mau FROM mau_sac WHERE ma='Color011'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA012'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0012'), (SELECT id_mau FROM mau_sac WHERE ma='Color011'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA012'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0012'), (SELECT id_mau FROM mau_sac WHERE ma='Color011'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA012'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0012'), (SELECT id_mau FROM mau_sac WHERE ma='Color011'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA012'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0012'), (SELECT id_mau FROM mau_sac WHERE ma='Color011'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA012'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0013'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA013'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0013'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA013'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0013'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA013'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0013'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA013'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0013'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA013'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0013'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA013'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0013'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA013'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0013'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA013'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0013'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA013'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0014'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA014'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0014'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA014'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0014'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA014'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0014'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA014'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0014'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA014'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0014'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA014'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0014'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA014'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0014'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA014'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0014'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA014'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0015'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA015'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0015'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA015'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0015'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA015'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0015'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA015'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0015'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA015'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0015'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA015'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0015'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA015'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0015'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA015'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0015'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA015'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0016'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA016'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0016'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA016'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0016'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA016'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0016'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA016'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0016'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA016'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0016'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA016'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0016'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA016'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0016'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA016'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0016'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA016'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0017'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA017'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0017'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA017'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0017'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA017'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0017'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA017'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0017'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA017'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0017'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA017'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0017'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA017'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0017'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA017'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0017'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA017'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0018'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA018'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0018'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA018'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0018'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA018'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0018'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA018'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0018'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA018'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0018'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA018'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0018'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA018'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0018'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA018'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0018'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA018'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0019'), (SELECT id_mau FROM mau_sac WHERE ma='Color010'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA019'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0019'), (SELECT id_mau FROM mau_sac WHERE ma='Color010'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA019'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0019'), (SELECT id_mau FROM mau_sac WHERE ma='Color010'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA019'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0019'), (SELECT id_mau FROM mau_sac WHERE ma='Color010'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA019'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0019'), (SELECT id_mau FROM mau_sac WHERE ma='Color010'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA019'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0019'), (SELECT id_mau FROM mau_sac WHERE ma='Color010'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA019'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0019'), (SELECT id_mau FROM mau_sac WHERE ma='Color010'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA019'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0019'), (SELECT id_mau FROM mau_sac WHERE ma='Color010'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA019'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0019'), (SELECT id_mau FROM mau_sac WHERE ma='Color010'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA019'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0020'), (SELECT id_mau FROM mau_sac WHERE ma='Color010'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA020'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0020'), (SELECT id_mau FROM mau_sac WHERE ma='Color010'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA020'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0020'), (SELECT id_mau FROM mau_sac WHERE ma='Color010'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA020'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0020'), (SELECT id_mau FROM mau_sac WHERE ma='Color010'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA020'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0020'), (SELECT id_mau FROM mau_sac WHERE ma='Color010'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA020'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0020'), (SELECT id_mau FROM mau_sac WHERE ma='Color010'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA020'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0020'), (SELECT id_mau FROM mau_sac WHERE ma='Color010'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA020'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0020'), (SELECT id_mau FROM mau_sac WHERE ma='Color010'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA020'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0020'), (SELECT id_mau FROM mau_sac WHERE ma='Color010'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA020'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0021'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA021'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0021'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA021'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0021'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA021'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0021'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA021'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0021'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA021'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0021'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA021'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0021'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA021'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0021'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA021'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0021'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA021'), 1);







-- ASDJKLFHASJKFASHNASNFLKJASFBANSLFBASJKU

insert into chi_tiet_giay(id_chi_tiet_giay, id_giay, id_mau, id_size, id_hinh_anh, trang_thai)
VALUES


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0022'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA022'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0022'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA022'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0022'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA022'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0022'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA022'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0022'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA022'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0022'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA022'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0022'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA022'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0022'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA022'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0022'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA022'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0023'), (SELECT id_mau FROM mau_sac WHERE ma='Color011'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA023'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0023'), (SELECT id_mau FROM mau_sac WHERE ma='Color011'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA023'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0023'), (SELECT id_mau FROM mau_sac WHERE ma='Color011'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA023'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0023'), (SELECT id_mau FROM mau_sac WHERE ma='Color011'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA023'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0023'), (SELECT id_mau FROM mau_sac WHERE ma='Color011'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA023'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0023'), (SELECT id_mau FROM mau_sac WHERE ma='Color011'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA023'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0023'), (SELECT id_mau FROM mau_sac WHERE ma='Color011'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA023'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0023'), (SELECT id_mau FROM mau_sac WHERE ma='Color011'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA023'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0023'), (SELECT id_mau FROM mau_sac WHERE ma='Color011'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA023'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0024'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA024'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0024'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA024'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0024'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA024'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0024'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA024'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0024'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA024'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0024'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA024'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0024'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA024'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0024'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA024'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0024'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA024'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0025'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA025'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0025'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA025'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0025'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA025'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0025'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA025'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0025'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA025'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0025'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA025'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0025'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA025'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0025'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA025'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0025'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA025'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0026'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA026'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0026'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA026'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0026'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA026'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0026'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA026'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0026'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA026'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0026'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA026'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0026'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA026'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0026'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA026'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0026'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA026'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0027'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA027'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0027'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA027'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0027'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA027'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0027'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA027'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0027'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA027'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0027'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA027'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0027'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA027'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0027'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA027'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0027'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA027'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0028'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA028'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0028'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA028'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0028'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA028'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0028'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA028'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0028'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA028'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0028'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA028'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0028'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA028'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0028'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA028'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0028'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA028'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0029'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA029'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0029'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA029'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0029'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA029'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0029'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA029'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0029'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA029'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0029'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA029'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0029'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA029'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0029'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA029'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0029'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA029'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0030'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA030'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0030'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA030'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0030'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA030'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0030'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA030'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0030'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA030'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0030'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA030'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0030'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA030'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0030'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA030'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0030'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA030'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0031'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA031'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0031'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA031'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0031'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA031'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0031'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA031'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0031'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA031'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0031'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA031'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0031'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA031'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0031'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA031'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0031'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA031'), 1);



insert into chi_tiet_giay(id_chi_tiet_giay, id_giay, id_mau, id_size, id_hinh_anh, trang_thai)
VALUES


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0032'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA032'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0032'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA032'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0032'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA032'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0032'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA032'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0032'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA032'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0032'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA032'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0032'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA032'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0032'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA032'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0032'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA032'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0033'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA033'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0033'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA033'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0033'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA033'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0033'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA033'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0033'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA033'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0033'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA033'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0033'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA033'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0033'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA033'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0033'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA033'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0034'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA034'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0034'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA034'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0034'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA034'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0034'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA034'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0034'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA034'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0034'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA034'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0034'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA034'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0034'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA034'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0034'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA034'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0035'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA035'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0035'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA035'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0035'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA035'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0035'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA035'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0035'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA035'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0035'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA035'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0035'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA035'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0035'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA035'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0035'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA035'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0036'), (SELECT id_mau FROM mau_sac WHERE ma='Color015'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA036'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0036'), (SELECT id_mau FROM mau_sac WHERE ma='Color015'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA036'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0036'), (SELECT id_mau FROM mau_sac WHERE ma='Color015'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA036'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0036'), (SELECT id_mau FROM mau_sac WHERE ma='Color015'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA036'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0036'), (SELECT id_mau FROM mau_sac WHERE ma='Color015'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA036'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0036'), (SELECT id_mau FROM mau_sac WHERE ma='Color015'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA036'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0036'), (SELECT id_mau FROM mau_sac WHERE ma='Color015'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA036'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0036'), (SELECT id_mau FROM mau_sac WHERE ma='Color015'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA036'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0036'), (SELECT id_mau FROM mau_sac WHERE ma='Color015'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA036'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0037'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA037'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0037'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA037'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0037'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA037'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0037'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA037'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0037'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA037'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0037'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA037'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0037'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA037'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0037'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA037'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0037'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA037'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0038'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA038'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0038'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA038'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0038'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA038'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0038'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA038'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0038'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA038'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0038'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA038'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0038'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA038'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0038'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA038'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0038'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA038'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0039'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA039'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0039'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA039'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0039'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA039'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0039'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA039'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0039'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA039'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0039'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA039'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0039'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA039'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0039'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA039'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0039'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA039'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0040'), (SELECT id_mau FROM mau_sac WHERE ma='Color016'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA040'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0040'), (SELECT id_mau FROM mau_sac WHERE ma='Color016'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA040'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0040'), (SELECT id_mau FROM mau_sac WHERE ma='Color016'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA040'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0040'), (SELECT id_mau FROM mau_sac WHERE ma='Color016'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA040'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0040'), (SELECT id_mau FROM mau_sac WHERE ma='Color016'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA040'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0040'), (SELECT id_mau FROM mau_sac WHERE ma='Color016'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA040'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0040'), (SELECT id_mau FROM mau_sac WHERE ma='Color016'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA040'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0040'), (SELECT id_mau FROM mau_sac WHERE ma='Color016'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA040'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0040'), (SELECT id_mau FROM mau_sac WHERE ma='Color016'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA040'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0041'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA041'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0041'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA041'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0041'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA041'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0041'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA041'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0041'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA041'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0041'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA041'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0041'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA041'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0041'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA041'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0041'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA041'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0042'), (SELECT id_mau FROM mau_sac WHERE ma='Color017'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA042'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0042'), (SELECT id_mau FROM mau_sac WHERE ma='Color017'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA042'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0042'), (SELECT id_mau FROM mau_sac WHERE ma='Color017'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA042'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0042'), (SELECT id_mau FROM mau_sac WHERE ma='Color017'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA042'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0042'), (SELECT id_mau FROM mau_sac WHERE ma='Color017'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA042'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0042'), (SELECT id_mau FROM mau_sac WHERE ma='Color017'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA042'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0042'), (SELECT id_mau FROM mau_sac WHERE ma='Color017'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA042'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0042'), (SELECT id_mau FROM mau_sac WHERE ma='Color017'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA042'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0042'), (SELECT id_mau FROM mau_sac WHERE ma='Color017'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA042'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0043'), (SELECT id_mau FROM mau_sac WHERE ma='Color017'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA043'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0043'), (SELECT id_mau FROM mau_sac WHERE ma='Color017'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA043'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0043'), (SELECT id_mau FROM mau_sac WHERE ma='Color017'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA043'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0043'), (SELECT id_mau FROM mau_sac WHERE ma='Color017'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA043'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0043'), (SELECT id_mau FROM mau_sac WHERE ma='Color017'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA043'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0043'), (SELECT id_mau FROM mau_sac WHERE ma='Color017'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA043'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0043'), (SELECT id_mau FROM mau_sac WHERE ma='Color017'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA043'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0043'), (SELECT id_mau FROM mau_sac WHERE ma='Color017'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA043'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0043'), (SELECT id_mau FROM mau_sac WHERE ma='Color017'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA043'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0044'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA044'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0044'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA044'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0044'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA044'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0044'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA044'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0044'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA044'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0044'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA044'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0044'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA044'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0044'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA044'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0044'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA044'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0045'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA045'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0045'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA045'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0045'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA045'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0045'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA045'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0045'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA045'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0045'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA045'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0045'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA045'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0045'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA045'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0045'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA045'), 1);

insert into chi_tiet_giay(id_chi_tiet_giay, id_giay, id_mau, id_size, id_hinh_anh, trang_thai)
VALUES

(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0046'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA046'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0046'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA046'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0046'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA046'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0046'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA046'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0046'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA046'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0046'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA046'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0046'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA046'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0046'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA046'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0046'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA046'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0047'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA047'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0047'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA047'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0047'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA047'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0047'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA047'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0047'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA047'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0047'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA047'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0047'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA047'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0047'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA047'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0047'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA047'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0048'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA048'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0048'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA048'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0048'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA048'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0048'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA048'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0048'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA048'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0048'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA048'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0048'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA048'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0048'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA048'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0048'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA048'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0049'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA049'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0049'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA049'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0049'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA049'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0049'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA049'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0049'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA049'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0049'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA049'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0049'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA049'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0049'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA049'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0049'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA049'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0050'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA050'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0050'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA050'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0050'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA050'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0050'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA050'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0050'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA050'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0050'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA050'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0050'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA050'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0050'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA050'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0050'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA050'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0051'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA051'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0051'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA051'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0051'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA051'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0051'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA051'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0051'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA051'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0051'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA051'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0051'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA051'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0051'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA051'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0051'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA051'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0052'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA052'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0052'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA052'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0052'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA052'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0052'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA052'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0052'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA052'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0052'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA052'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0052'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA052'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0052'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA052'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0052'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA052'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0053'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA053'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0053'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA053'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0053'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA053'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0053'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA053'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0053'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA053'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0053'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA053'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0053'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA053'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0053'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA053'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0053'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA053'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0054'), (SELECT id_mau FROM mau_sac WHERE ma='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA054'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0054'), (SELECT id_mau FROM mau_sac WHERE ma='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA054'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0054'), (SELECT id_mau FROM mau_sac WHERE ma='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA054'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0054'), (SELECT id_mau FROM mau_sac WHERE ma='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA054'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0054'), (SELECT id_mau FROM mau_sac WHERE ma='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA054'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0054'), (SELECT id_mau FROM mau_sac WHERE ma='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA054'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0054'), (SELECT id_mau FROM mau_sac WHERE ma='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA054'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0054'), (SELECT id_mau FROM mau_sac WHERE ma='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA054'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0054'), (SELECT id_mau FROM mau_sac WHERE ma='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA054'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0055'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA055'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0055'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA055'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0055'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA055'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0055'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA055'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0055'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA055'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0055'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA055'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0055'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA055'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0055'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA055'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0055'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA055'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0056'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA056'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0056'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA056'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0056'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA056'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0056'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA056'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0056'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA056'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0056'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA056'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0056'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA056'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0056'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA056'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0056'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA056'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0057'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA057'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0057'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA057'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0057'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA057'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0057'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA057'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0057'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA057'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0057'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA057'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0057'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA057'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0057'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA057'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0057'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA057'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0058'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA058'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0058'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA058'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0058'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA058'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0058'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA058'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0058'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA058'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0058'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA058'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0058'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA058'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0058'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA058'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0058'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA058'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0059'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA059'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0059'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA059'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0059'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA059'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0059'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA059'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0059'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA059'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0059'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA059'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0059'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA059'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0059'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA059'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0059'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA059'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0060'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA060'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0060'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA060'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0060'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA060'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0060'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA060'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0060'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA060'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0060'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA060'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0060'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA060'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0060'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA060'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0060'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA060'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0061'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA061'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0061'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA061'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0061'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA061'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0061'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA061'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0061'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA061'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0061'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA061'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0061'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA061'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0061'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA061'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0061'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA061'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0062'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA062'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0062'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA062'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0062'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA062'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0062'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA062'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0062'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA062'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0062'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA062'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0062'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA062'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0062'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA062'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0062'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA062'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0063'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA063'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0063'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA063'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0063'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA063'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0063'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA063'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0063'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA063'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0063'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA063'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0063'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA063'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0063'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA063'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0063'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA063'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0064'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA064.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0064'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA064.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0064'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA064.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0064'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA064.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0064'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA064.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0064'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA064.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0064'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA064.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0064'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA064.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0064'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA064.0'), 1),

(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0064'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA064.1'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0064'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA064.1'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0064'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA064.1'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0064'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA064.1'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0064'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA064.1'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0064'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA064.1'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0064'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA064.1'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0064'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA064.1'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0064'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA064.1'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0065'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA065'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0065'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA065'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0065'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA065'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0065'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA065'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0065'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA065'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0065'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA065'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0065'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA065'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0065'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA065'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0065'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA065'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0066'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA066'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0066'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA066'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0066'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA066'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0066'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA066'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0066'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA066'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0066'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA066'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0066'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA066'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0066'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA066'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0066'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA066'), 1),

(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color012'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color012'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color012'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color012'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color012'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color012'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color012'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color012'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color012'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.0'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.1'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.1'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.1'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.1'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.1'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.1'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.1'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.1'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.1'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.2'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.2'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.2'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.2'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.2'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.2'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.2'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.2'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.2'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color015'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.3'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color015'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.3'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color015'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.3'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color015'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.3'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color015'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.3'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color015'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.3'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color015'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.3'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color015'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.3'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color015'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.3'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.4'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.4'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.4'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.4'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.4'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.4'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.4'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.4'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.4'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color011'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.5'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color011'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.5'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color011'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.5'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color011'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.5'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color011'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.5'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color011'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.5'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color011'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.5'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color011'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.5'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0067'), (SELECT id_mau FROM mau_sac WHERE ma='Color011'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA067.5'), 1);



insert into chi_tiet_giay(id_chi_tiet_giay, id_giay, id_mau, id_size, id_hinh_anh, trang_thai)
VALUES

(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0068'), (SELECT id_mau FROM mau_sac WHERE ma='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA068'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0068'), (SELECT id_mau FROM mau_sac WHERE ma='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA068'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0068'), (SELECT id_mau FROM mau_sac WHERE ma='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA068'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0068'), (SELECT id_mau FROM mau_sac WHERE ma='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA068'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0068'), (SELECT id_mau FROM mau_sac WHERE ma='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA068'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0068'), (SELECT id_mau FROM mau_sac WHERE ma='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA068'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0068'), (SELECT id_mau FROM mau_sac WHERE ma='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA068'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0068'), (SELECT id_mau FROM mau_sac WHERE ma='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA068'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0068'), (SELECT id_mau FROM mau_sac WHERE ma='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA068'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0069'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA069'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0069'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA069'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0069'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA069'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0069'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA069'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0069'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA069'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0069'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA069'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0069'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA069'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0069'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA069'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0069'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA069'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0070'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA070'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0070'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA070'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0070'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA070'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0070'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA070'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0070'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA070'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0070'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA070'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0070'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA070'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0070'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA070'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0070'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA070'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0071'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA071'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0071'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA071'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0071'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA071'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0071'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA071'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0071'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA071'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0071'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA071'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0071'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA071'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0071'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA071'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0071'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA071'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0072'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA072'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0072'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA072'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0072'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA072'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0072'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA072'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0072'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA072'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0072'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA072'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0072'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA072'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0072'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA072'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0072'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA072'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0073'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA073'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0073'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA073'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0073'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA073'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0073'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA073'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0073'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA073'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0073'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA073'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0073'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA073'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0073'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA073'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0073'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA073'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0074'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA074'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0074'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA074'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0074'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA074'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0074'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA074'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0074'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA074'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0074'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA074'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0074'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA074'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0074'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA074'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0074'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA074'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0075'), (SELECT id_mau FROM mau_sac WHERE ma='Color012'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA075'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0075'), (SELECT id_mau FROM mau_sac WHERE ma='Color012'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA075'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0075'), (SELECT id_mau FROM mau_sac WHERE ma='Color012'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA075'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0075'), (SELECT id_mau FROM mau_sac WHERE ma='Color012'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA075'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0075'), (SELECT id_mau FROM mau_sac WHERE ma='Color012'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA075'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0075'), (SELECT id_mau FROM mau_sac WHERE ma='Color012'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA075'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0075'), (SELECT id_mau FROM mau_sac WHERE ma='Color012'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA075'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0075'), (SELECT id_mau FROM mau_sac WHERE ma='Color012'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA075'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0075'), (SELECT id_mau FROM mau_sac WHERE ma='Color012'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA075'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0076'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA076'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0076'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA076'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0076'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA076'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0076'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA076'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0076'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA076'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0076'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA076'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0076'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA076'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0076'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA076'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0076'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA076'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0077'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA077'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0077'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA077'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0077'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA077'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0077'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA077'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0077'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA077'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0077'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA077'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0077'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA077'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0077'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA077'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0077'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA077'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0078'), (SELECT id_mau FROM mau_sac WHERE ma='Color017'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA078'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0078'), (SELECT id_mau FROM mau_sac WHERE ma='Color017'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA078'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0078'), (SELECT id_mau FROM mau_sac WHERE ma='Color017'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA078'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0078'), (SELECT id_mau FROM mau_sac WHERE ma='Color017'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA078'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0078'), (SELECT id_mau FROM mau_sac WHERE ma='Color017'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA078'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0078'), (SELECT id_mau FROM mau_sac WHERE ma='Color017'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA078'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0078'), (SELECT id_mau FROM mau_sac WHERE ma='Color017'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA078'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0078'), (SELECT id_mau FROM mau_sac WHERE ma='Color017'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA078'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0078'), (SELECT id_mau FROM mau_sac WHERE ma='Color017'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA078'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0079'), (SELECT id_mau FROM mau_sac WHERE ma='Color017'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA079'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0079'), (SELECT id_mau FROM mau_sac WHERE ma='Color017'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA079'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0079'), (SELECT id_mau FROM mau_sac WHERE ma='Color017'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA079'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0079'), (SELECT id_mau FROM mau_sac WHERE ma='Color017'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA079'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0079'), (SELECT id_mau FROM mau_sac WHERE ma='Color017'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA079'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0079'), (SELECT id_mau FROM mau_sac WHERE ma='Color017'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA079'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0079'), (SELECT id_mau FROM mau_sac WHERE ma='Color017'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA079'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0079'), (SELECT id_mau FROM mau_sac WHERE ma='Color017'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA079'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0079'), (SELECT id_mau FROM mau_sac WHERE ma='Color017'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA079'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0080'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA080'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0080'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA080'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0080'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA080'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0080'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA080'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0080'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA080'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0080'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA080'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0080'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA080'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0080'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA080'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0080'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA080'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0081'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA081'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0081'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA081'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0081'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA081'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0081'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA081'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0081'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA081'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0081'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA081'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0081'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA081'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0081'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA081'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0081'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA081'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0082'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA082'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0082'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA082'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0082'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA082'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0082'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA082'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0082'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA082'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0082'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA082'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0082'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA082'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0082'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA082'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0082'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA082'), 1),

---------------------
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0083'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA083.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0083'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA083.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0083'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA083.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0083'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA083.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0083'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA083.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0083'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA083.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0083'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA083.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0083'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA083.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0083'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA083.0'), 1),

(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0083'), (SELECT id_mau FROM mau_sac WHERE ma='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA083.1'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0083'), (SELECT id_mau FROM mau_sac WHERE ma='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA083.1'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0083'), (SELECT id_mau FROM mau_sac WHERE ma='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA083.1'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0083'), (SELECT id_mau FROM mau_sac WHERE ma='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA083.1'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0083'), (SELECT id_mau FROM mau_sac WHERE ma='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA083.1'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0083'), (SELECT id_mau FROM mau_sac WHERE ma='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA083.1'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0083'), (SELECT id_mau FROM mau_sac WHERE ma='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA083.1'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0083'), (SELECT id_mau FROM mau_sac WHERE ma='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA083.1'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0083'), (SELECT id_mau FROM mau_sac WHERE ma='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA083.1'), 1),

(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0083'), (SELECT id_mau FROM mau_sac WHERE ma='Color015'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA083.2'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0083'), (SELECT id_mau FROM mau_sac WHERE ma='Color015'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA083.2'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0083'), (SELECT id_mau FROM mau_sac WHERE ma='Color015'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA083.2'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0083'), (SELECT id_mau FROM mau_sac WHERE ma='Color015'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA083.2'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0083'), (SELECT id_mau FROM mau_sac WHERE ma='Color015'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA083.2'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0083'), (SELECT id_mau FROM mau_sac WHERE ma='Color015'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA083.2'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0083'), (SELECT id_mau FROM mau_sac WHERE ma='Color015'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA083.2'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0083'), (SELECT id_mau FROM mau_sac WHERE ma='Color015'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA083.2'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0083'), (SELECT id_mau FROM mau_sac WHERE ma='Color015'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA083.2'), 1),
-----------------------

(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0084'), (SELECT id_mau FROM mau_sac WHERE ma='Color011'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA084'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0084'), (SELECT id_mau FROM mau_sac WHERE ma='Color011'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA084'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0084'), (SELECT id_mau FROM mau_sac WHERE ma='Color011'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA084'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0084'), (SELECT id_mau FROM mau_sac WHERE ma='Color011'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA084'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0084'), (SELECT id_mau FROM mau_sac WHERE ma='Color011'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA084'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0084'), (SELECT id_mau FROM mau_sac WHERE ma='Color011'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA084'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0084'), (SELECT id_mau FROM mau_sac WHERE ma='Color011'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA084'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0084'), (SELECT id_mau FROM mau_sac WHERE ma='Color011'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA084'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0084'), (SELECT id_mau FROM mau_sac WHERE ma='Color011'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA084'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0085'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA085'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0085'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA085'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0085'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA085'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0085'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA085'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0085'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA085'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0085'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA085'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0085'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA085'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0085'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA085'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0085'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA085'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0086'), (SELECT id_mau FROM mau_sac WHERE ma='Color010'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA086.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0086'), (SELECT id_mau FROM mau_sac WHERE ma='Color010'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA086.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0086'), (SELECT id_mau FROM mau_sac WHERE ma='Color010'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA086.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0086'), (SELECT id_mau FROM mau_sac WHERE ma='Color010'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA086.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0086'), (SELECT id_mau FROM mau_sac WHERE ma='Color010'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA086.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0086'), (SELECT id_mau FROM mau_sac WHERE ma='Color010'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA086.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0086'), (SELECT id_mau FROM mau_sac WHERE ma='Color010'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA086.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0086'), (SELECT id_mau FROM mau_sac WHERE ma='Color010'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA086.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0086'), (SELECT id_mau FROM mau_sac WHERE ma='Color010'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA086.0'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0086'), (SELECT id_mau FROM mau_sac WHERE ma='Color015'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA086.1'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0086'), (SELECT id_mau FROM mau_sac WHERE ma='Color015'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA086.1'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0086'), (SELECT id_mau FROM mau_sac WHERE ma='Color015'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA086.1'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0086'), (SELECT id_mau FROM mau_sac WHERE ma='Color015'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA086.1'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0086'), (SELECT id_mau FROM mau_sac WHERE ma='Color015'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA086.1'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0086'), (SELECT id_mau FROM mau_sac WHERE ma='Color015'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA086.1'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0086'), (SELECT id_mau FROM mau_sac WHERE ma='Color015'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA086.1'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0086'), (SELECT id_mau FROM mau_sac WHERE ma='Color015'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA086.1'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0086'), (SELECT id_mau FROM mau_sac WHERE ma='Color015'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA086.1'), 1),




(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0087'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA087'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0087'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA087'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0087'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA087'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0087'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA087'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0087'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA087'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0087'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA087'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0087'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA087'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0087'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA087'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0087'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA087'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0088'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA088'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0088'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA088'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0088'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA088'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0088'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA088'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0088'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA088'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0088'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA088'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0088'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA088'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0088'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA088'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0088'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA088'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0089'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA089'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0089'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA089'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0089'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA089'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0089'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA089'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0089'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA089'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0089'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA089'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0089'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA089'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0089'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA089'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0089'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA089'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0090'), (SELECT id_mau FROM mau_sac WHERE ma='Color012'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA090'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0090'), (SELECT id_mau FROM mau_sac WHERE ma='Color012'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA090'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0090'), (SELECT id_mau FROM mau_sac WHERE ma='Color012'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA090'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0090'), (SELECT id_mau FROM mau_sac WHERE ma='Color012'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA090'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0090'), (SELECT id_mau FROM mau_sac WHERE ma='Color012'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA090'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0090'), (SELECT id_mau FROM mau_sac WHERE ma='Color012'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA090'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0090'), (SELECT id_mau FROM mau_sac WHERE ma='Color012'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA090'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0090'), (SELECT id_mau FROM mau_sac WHERE ma='Color012'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA090'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0090'), (SELECT id_mau FROM mau_sac WHERE ma='Color012'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA090'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0091'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA091'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0091'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA091'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0091'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA091'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0091'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA091'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0091'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA091'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0091'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA091'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0091'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA091'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0091'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA091'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0091'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA091'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0092'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA092'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0092'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA092'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0092'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA092'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0092'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA092'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0092'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA092'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0092'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA092'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0092'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA092'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0092'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA092'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0092'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA092'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0093'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA093'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0093'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA093'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0093'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA093'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0093'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA093'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0093'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA093'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0093'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA093'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0093'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA093'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0093'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA093'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0093'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA093'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0094'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA094'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0094'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA094'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0094'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA094'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0094'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA094'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0094'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA094'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0094'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA094'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0094'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA094'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0094'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA094'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0094'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA094'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0095'), (SELECT id_mau FROM mau_sac WHERE ma='Color012'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA095'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0095'), (SELECT id_mau FROM mau_sac WHERE ma='Color012'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA095'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0095'), (SELECT id_mau FROM mau_sac WHERE ma='Color012'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA095'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0095'), (SELECT id_mau FROM mau_sac WHERE ma='Color012'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA095'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0095'), (SELECT id_mau FROM mau_sac WHERE ma='Color012'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA095'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0095'), (SELECT id_mau FROM mau_sac WHERE ma='Color012'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA095'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0095'), (SELECT id_mau FROM mau_sac WHERE ma='Color012'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA095'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0095'), (SELECT id_mau FROM mau_sac WHERE ma='Color012'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA095'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0095'), (SELECT id_mau FROM mau_sac WHERE ma='Color012'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA095'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0096'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA096'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0096'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA096'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0096'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA096'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0096'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA096'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0096'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA096'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0096'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA096'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0096'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA096'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0096'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA096'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0096'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA096'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0097'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA097'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0097'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA097'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0097'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA097'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0097'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA097'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0097'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA097'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0097'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA097'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0097'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA097'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0097'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA097'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0097'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA097'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0098'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA098'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0098'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA098'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0098'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA098'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0098'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA098'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0098'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA098'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0098'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA098'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0098'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA098'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0098'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA098'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0098'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA098'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0099'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA099'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0099'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA099'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0099'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA099'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0099'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA099'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0099'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA099'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0099'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA099'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0099'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA099'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0099'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA099'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0099'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA099'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0100'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0100'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0100'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0100'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0100'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0100'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0100'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0100'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0100'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0100'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0100'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0100'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0100'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0100'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0100'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0100'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0100'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0100'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0101'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0101'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0101'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0101'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0101'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0101'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0101'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0101'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0101'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0101'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0101'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0101'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0101'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0101'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0101'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0101'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0101'), (SELECT id_mau FROM mau_sac WHERE ma='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0101'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0102'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0102.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0102'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0102.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0102'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0102.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0102'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0102.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0102'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0102.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0102'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0102.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0102'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0102.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0102'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0102.0'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0102'), (SELECT id_mau FROM mau_sac WHERE ma='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0102.0'), 1),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0102'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0102.1'), 0),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0102'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0102.1'), 0),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0102'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0102.1'), 0),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0102'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0102.1'), 0),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0102'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0102.1'), 0),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0102'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0102.1'), 0),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0102'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0102.1'), 0),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0102'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0102.1'), 0),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0102'), (SELECT id_mau FROM mau_sac WHERE ma='Color014'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0102.1'), 0),


(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0102'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0102.2'), 2),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0102'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0102.2'), 2),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0102'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0102.2'), 2),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0102'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0102.2'), 2),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0102'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0102.2'), 2),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0102'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0102.2'), 2),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0102'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0102.2'), 2),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0102'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0102.2'), 2),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker0102'), (SELECT id_mau FROM mau_sac WHERE ma='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA0102.2'), 2);

update chi_tiet_giay  set gia_ban = 3000000, so_tien_truoc_khi_giam = 3000000, nam_bao_hanh = 2025, nam_san_xuat = 2021, so_luong = 60, trong_luong =600, tg_them = getdate()
 