use SD74_SneakerShop

insert into Hang (id_hang, trang_thai, ma_hang, ten_hang, tg_them,logo_hang)
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
values(NEWID(), 0, 'CT09', 'Reebok', GETDATE(),'Reebok.png')
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
VALUES (NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG01'), (SELECT id_hang FROM hang WHERE ma_hang='CT01'), 1, 'Nike Cortez', 'Sneaker001','City of Champions. City of Stars. We’ve turned the Lunar Cortez, a City of Angels staple, into baseball cleats, bringing the vintage sneaker to the diamond. It maintains the classic Cortez look with a little midsummer classic flair designed to inspire the next generation of players. Nods to the city’s old school charm put the finishing touches on a look made for center stage.', getdate()),
       (NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG02'), (SELECT id_hang FROM hang WHERE ma_hang='CT01'), 1, 'Nike Epic React', 'Sneaker002','The Nike Epic React Flyknit 1 provides crazy comfort that lasts as long as you can run. Its Nike React foam cushioning is responsive yet lightweight, durable yet soft. This attraction of opposites creates a sensation that not only enhances the feeling of moving forwards, but makes running feel fun, too.', getdate()),
       (NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG03'), (SELECT id_hang FROM hang WHERE ma_hang='CT01'), 1, 'Nike Air Force 1', 'Sneaker003','The radiance lives on in the Nike Air Force 1 07, the basketball original that puts a fresh spin on what you know best: durably stitched overlays, clean finishes and the perfect amount of flash to make you shine.', getdate()),
       (NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG04'), (SELECT id_hang FROM hang WHERE ma_hang='CT01'), 1, 'Nike Air Max', 'Sneaker004','The Air Max Pulse pulls inspiration from the London music scene, bringing an underground touch to the iconic Air Max line. Its textile-wrapped midsole and vacuum-sealed accents keep em looking fresh and clean, while colours inspired by the London music scene give your look the edge. Point-loaded Air cushioning—revamped from the incredibly plush Air Max 270—delivers better bounce, helping you push past your limits.', getdate()),
       (NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG05'), (SELECT id_hang FROM hang WHERE ma_hang='CT01'), 1, 'Nike Jordan', 'Sneaker005', 'Luxe embroidery and a subdued palette bring a vibe of understated cool to the AJ1 Mid. The special-edition Swoosh adds a hint of shine to kicks that look good (and feel good) no matter where you wear them.', getdate()),
       (NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG04'), (SELECT id_hang FROM hang WHERE ma_hang='CT02'), 1, 'Adidas Yeezy', 'Sneaker006','Adidas Yeezy (or just Yeezy) was a fashion collaboration between American rapper, designer, and entrepreneur Kanye West and German sportswear company Adidas. It offered sneakers in limited edition colorways, as well as shirts, jackets, track pants, socks, slides, lingerie and slippers.', getdate()),
       (NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG03'), (SELECT id_hang FROM hang WHERE ma_hang='CT02'), 1, 'Adidas Ultra Boost', 'Sneaker007','Do not be fooled by the sharp angles and clean lines. These adidas sneakers come packed with plush cushioning. Ultra-soft BOOST and Bounce blend together to give you incredible comfort day to day. So go ahead and add some edge to your sneaker game without sacrificing softness or support.', getdate()),
       (NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG02'), (SELECT id_hang FROM hang WHERE ma_hang='CT02'), 1, 'Adidas NMD', 'Sneaker008','The adidas NMD_S1 shoes are made to be bold. The design elements from the original take a new shape, pushing the tactical look even further. Sleek and slim midsole plugs enhance the futuristic look while a bulky midsole with responsive BOOST speaks the language of comfort. Although these shoes bring new perspectives, the signature sock-like knit upper retains the OG spirit. Lace them up and make every outfit a statement.', getdate()),
       (NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG03'), (SELECT id_hang FROM hang WHERE ma_hang='CT02'), 1, 'Adidas Stan Smith', 'Sneaker009','These infants badidas shoes build on the iconic Stan Smith white trainer. The OG silhouette is there, from the perforated 3-Stripes to the low-profile cupsole. LEGO® brick knobs decorate the heel and complement pops of colour on the heel tab and hook-and-loop straps.', getdate()),
       (NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG04'), (SELECT id_hang FROM hang WHERE ma_hang='CT02'), 1, 'Adidas Alphabounce', 'Sneaker010','Go for that walk around the neighbourhood or head to the gym in a pair of adidas Alphabounce+ shoes. The mesh upper is soft and breathable to keep you comfortable as you go. A Bounce midsole makes the ride soft and springy while Cloudfoam on the heel cushions every step, so you can cruise smoothly through the day.', getdate()),
       (NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG06'), (SELECT id_hang FROM hang WHERE ma_hang='CT08'), 1, 'Converse All Star', 'Sneaker011','Converse Classic is Converse is best-selling shoe line because of its very popular classic nature. Soft canvas material, rubber sole with characteristic color border combined with simple, dynamic colors always combine many fashion ideas, creating many different styles.', getdate()),
       (NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG01'), (SELECT id_hang FROM hang WHERE ma_hang='CT08'), 1, 'Converse Classic', 'Sneaker012', 'Converse Classic is Converse is best-selling shoe line because of its very popular classic nature. Soft canvas material, rubber sole with characteristic color border combined with simple, dynamic colors always combine many fashion ideas, creating many different styles.', getdate()),
       (NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG02'), (SELECT id_hang FROM hang WHERE ma_hang='CT08'), 1, 'Converse Chuck 70s', 'Sneaker013', 'Hi Summer - Discover the Converse Chuck 70 Nautical Tri Blocked with an impressive marine design style with fresh, elegant and liberal colors. Navy - Aqua Mist - Egret represents the colors of the coast, boats, maritime and of the deep ocean. The collection aims at dynamism in accordance with the spirit of "Hi Summer with many advantages of high quality and durability, promising to bring you a very bright and full of summer with the experiences of youth.', getdate()),
       (NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG04'), (SELECT id_hang FROM hang WHERE ma_hang='CT08'), 1, 'Converse Chuck II', 'Sneaker014','Chuck Taylor All Star II Open Knit uses knit fabric with low density, creating small openings to help you feel more airy, unique and strange design helps you combine many fashion styles. different and become dynamic and individual.', getdate()),
       (NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG02'), (SELECT id_hang FROM hang WHERE ma_hang='CT08'), 1, 'Converse Jack Purcell', 'Sneaker015','Classic Jack Purcell design with elegant, trendy color tones, especially durable high-quality leather material for easy cleaning. Any combination will become easier when you have in your shoe cabinet an item like this!', getdate()),
       (NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG01'), (SELECT id_hang FROM hang WHERE ma_hang='CT03'), 1, 'Puma Fenty Creeper', 'Sneaker016','Like its creator, the Puma by Rihanna Creeper White Leather is a hitter as it comes in with lots of striking detail from the upper, laces, sole and branding. Priced higher than your usual fashionable Puma sneakers, this built super is not only stylish but also extreme as its luxurious shell and thick sole make it durable to last a while. long time.', getdate()),
       (NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG03'), (SELECT id_hang FROM hang WHERE ma_hang='CT03'), 1, 'Puma Bari Mule', 'Sneaker017','A comfortable open back design, lightweight canvas upper, sturdy rubber sole and classic look are the reasons why the Bari is a sought-after shoe. Get ready to glide, glide in style with Puma Bari Mule White!', getdate()),
       (NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG05'), (SELECT id_hang FROM hang WHERE ma_hang='CT03'), 1, 'Puma Ember Trail', 'Sneaker018','Embrace the rugged outdoors in Ember Trail, fusing running performance with trail-ready design. We have combined trail-inspired design elements- corded lacing, a durable upper and lug rubber outsole for next-level traction and stability.', getdate()),
       (NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG06'), (SELECT id_hang FROM hang WHERE ma_hang='CT03'), 1, 'Puma Suede', 'Sneaker019','The SUEDEʼs story has a lot of twists and turn, so lace up. With over 50 years of life in a shoe, the SUEDE became a classic while going through several names. Beginning as the PUMA CRACK, the shoe evolved into the CLYDE, then PUMA STATES and finally globally adopted the name PUMA SUEDE.', getdate()),
       (NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG03'), (SELECT id_hang FROM hang WHERE ma_hang='CT03'), 1, 'Puma Ignite Evoknit', 'Sneaker020', 'Unsurpassed wearing comfort is a given with the all-new IGNITE evoKNIT and its form-fitting, mid-height knitted upper. A TPU piece and support strap stabilize the heel by locking it onto the platform, while seamlessly bonded overlays increase comfort and fit. Mounted on the classical IGNITE tooling for maximum energy return.', getdate()),
       (NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG02'), (SELECT id_hang FROM hang WHERE ma_hang='CT05'), 1, 'Big Ball MLB Chunky Mickey', 'Sneaker021', 'In recent years, the trend of Chunky Sneaker shoes has gradually returned and received an enthusiastic response from young people. The shoes designed according to the Chunky Sneaker trend often have a rather large shoe shape compared to other sports shoes, so it feels strong and extremely personal. MLB Disney Bigball Chunky Mickey New York Yankees 32SHCK941-50I is a product of the Korean MLB shoe brand, considered one of its most popular Chunky Sneaker products.', getdate()),
       (NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG01'), (SELECT id_hang FROM hang WHERE ma_hang='CT05'), 1, 'MLB Chunky Dodgers', 'Sneaker022','MLB LA Dodgers Sneaker - Big Ball Chunky A is a unisex shoe model for both men and women from South Korea that is storming the past time of brand MLB. With products shoes with a youthful and dynamic breath,  MLB Korea  is considered the first choice for young people who want to express their own personality.', getdate()),
       (NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG04'), (SELECT id_hang FROM hang WHERE ma_hang='CT05'), 1, 'LA Big Ball Chunky A', 'Sneaker023','The MLB LA Dodgers Sneakers - Big Ball Chunky A is a unisex shoe suitable for all genders. Chunky Sneaker is designed in a new style, the strong and modern version of the shoe brings a youthful and dynamic style that is especially loved by young people. Especially with the form of hack shoes, the higher padded soles combined with logos in various color schemes such as LA, NY, etc. create a strong attraction that is sought after by fashionistas.', getdate()),
       (NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG03'), (SELECT id_hang FROM hang WHERE ma_hang='CT06'), 1, 'New Balance 9060', 'Sneaker024','The 9060 is a new expression of the refined style and innovation-led design that have made the 99X series home to some of the most iconic models in New Balance history. The 9060 reinterprets familiar elements sourced from classic 99X models with a warped sensibility inspired by the proudly futuristic, visible tech aesthetic of the Y2K era. Sway bars, taken from the 990, are expanded and utilized throughout the entire upper for a sense of visible motion, while wavy lines and scaled up proportions on a sculpted pod midsole place an exaggerated emphasis on the familiar cushioning platforms of ABZORB and SBS. This kids version of the 9060 takes the innovative sneaker model and sizes it down for growing feet.', getdate()),
       (NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG02'), (SELECT id_hang FROM hang WHERE ma_hang='CT06'), 1, 'New Balance 550', 'Sneaker025', 'The original 550 debuted in 1989 and made its mark on basketball courts from coast to coast. After its initial run, the 550 was filed away in the archives, before being reintroduced in limited-edition releases in late 2020, and returned to the full-time lineup in 2021, quickly becoming a global fashion favorite. The 550’s low top, streamlined silhouette offers a clean take on the heavy-duty designs of the late ‘80s, while the dependable leather upper construction is a classic look in any era.', getdate()),
       (NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG05'), (SELECT id_hang FROM hang WHERE ma_hang='CT06'), 1, 'NB 574', 'Sneaker026','New Balance 574 iconic shoes inspired by the 80s style make the old-school style full of charm for your outfit. Delivering clean lines with a combination of vibrant colors and a sturdy outsole, classic suede adds a pop of color to your outfit, while still retaining its timeless charm. Classic 80s breathing.', getdate()),
       (NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG01'), (SELECT id_hang FROM hang WHERE ma_hang='CT04'), 1, 'Vans MN Skate Old Skool', 'Sneaker027','With a classic design, easy to coordinate Vans Old Skool clothes has made a mark in the hearts of Sneaker Fans. Bold street style, dusty style, Vans Old Skool Skate is improved with POP CUSH buffer to bring high smoothness along with a simple, light and attractive appearance.', getdate()),
       (NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG01'), (SELECT id_hang FROM hang WHERE ma_hang='CT04'), 1, 'Vans Authentic 44 DX Anaheim Factory', 'Sneaker028', 'Vans Authentic Anaheim Factory shoes with eye-catching, easy-to-wear, and easy-to-wear ivory white tones with Textile fabric give you a personality, elegance but no less outstanding. Simple traditional design combined with modern UltraCush technology certainly creates a feeling of comfort, lightness, class for the wearer regardless of style.', getdate()),
       (NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG06'), (SELECT id_hang FROM hang WHERE ma_hang='CT04'), 1, 'Vans Authentic', 'Sneaker029','The Vans Platform is a step up from the classic designs, still maintaining the brand values but upgraded with the high and thick soles that set the trend. The collection is a breakthrough innovation with new features such as a low and lightweight collar, a design that balances weight and style, and soft Ortholite cushioning. This is the bridge for the combination of simple style and high comfort.', getdate()),
       (NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG03'), (SELECT id_hang FROM hang WHERE ma_hang='CT04'), 1, 'Vans Classic Slip On', 'Sneaker030','Sleek, easy, and effortlessly stylish. Vans White Slip-On shoes are the ultimate get-up-and-go footwear. The low-profile Slip-On canvas upper offers unbeatable convenience, while the clean design makes this all-white Slip-On the perfect choice for anyone with places to go and things to do. One of the most popular designs, Vans’ Classic Slip-On shoes are the perfect middle ground between style and convenience.', getdate()),
       (NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG02'), (SELECT id_hang FROM hang WHERE ma_hang='CT04'), 0, 'Vans Classic Slip On', 'Sneaker031','Giay nay k hoat dong show len la ngu', getdate()),
       (NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG04'), (SELECT id_hang FROM hang WHERE ma_hang='CT04'), 2, 'Vans Classic  On', 'Sneaker032','Giay nay het hang fill len la ngu', getdate()),
       (NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG01'), (SELECT id_hang FROM hang WHERE ma_hang='CT04'), 2, 'Vans Classic Slip ', 'Sneaker033','Giay nay k hoat dong show len la ngu', getdate()),
       (NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG05'), (SELECT id_hang FROM hang WHERE ma_hang='CT04'), 0, 'Vans Classic Slip On', 'Sneaker034','Giay nay k hoat dong show len la ngu', getdate()),
       (NEWID(), (SELECT id_chat_lieu FROM chat_lieu WHERE ma_chat_lieu='TG05'), (SELECT id_hang FROM hang WHERE ma_hang='CT07'), 1 ,'LV Skate', 'Sneaker034','The LV Skate sneaker references a Nineties-inspired design first seen at Louis Vuitton is Fall-Winter 2022 men is show. Its elaborate, bicolor upper with Monogram Flower detailing combines technical mesh, calf leather and suede.', getdate());

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
values(NEWID(), '46', 0, 'Size010', GETDATE())
insert into [size](id_size,so_size, trang_Thai, ma_size, tg_them)
values(NEWID(), '36', 0, 'Size011', GETDATE())

insert into mau_sac(id_mau, ten_mau,trang_thai, ma_mau, tg_them)
values(NEWID(), 'Black', 1, 'Color01', GETDATE())
insert into mau_sac(id_mau, ten_mau,trang_thai, ma_mau, tg_them)
values(NEWID(), 'White', 1, 'Color02', GETDATE())
insert into mau_sac(id_mau, ten_mau,trang_thai, ma_mau, tg_them)
values(NEWID(), 'Chartreuse', 1, 'Color03', GETDATE())
insert into mau_sac(id_mau, ten_mau,trang_thai, ma_mau, tg_them)
values(NEWID(), 'Blue', 1, 'Color04', GETDATE())
insert into mau_sac(id_mau, ten_mau,trang_thai, ma_mau, tg_them)
values(NEWID(), 'Orange ', 1, 'Color05', GETDATE())
insert into mau_sac(id_mau, ten_mau,trang_thai, ma_mau, tg_them)
values(NEWID(), 'Hotpink', 1, 'Color06', GETDATE())
insert into mau_sac(id_mau, ten_mau,trang_thai, ma_mau, tg_them)
values(NEWID(), 'White Smoke', 1, 'Color07', GETDATE())
insert into mau_sac(id_mau, ten_mau,trang_thai, ma_mau, tg_them)
values(NEWID(), 'Aqua', 1, 'Color08', GETDATE())
insert into mau_sac(id_mau, ten_mau,trang_thai, ma_mau, tg_them)
values(NEWID(), 'Bisque', 1, 'Color09', GETDATE())
insert into mau_sac(id_mau, ten_mau,trang_thai, ma_mau, tg_them)
values(NEWID(), 'Coral', 1, 'Color10', GETDATE())
insert into mau_sac(id_mau, ten_mau,trang_thai, ma_mau, tg_them)
values(NEWID(), 'Darkgray', 1, 'Color11', GETDATE())
insert into mau_sac(id_mau, ten_mau,trang_thai, ma_mau, tg_them)
values(NEWID(), 'Silver ', 0, 'Color13', GETDATE())

insert into hinh_anh(id_hinh_anh, ma_anh , trang_thai, url1, url2, url3, url4, url5, tg_them)
values  (NEWID(), 'HA01', 1, 'product-1.0.png', 'product-1.1.png', 'product-1.2.png', 'product-1.3.png', 'product-1.4.png', getdate()),
        (NEWID(), 'HA02', 1, 'product-2.0.png', 'product-2.1.png', 'product-2.2.png', 'product-2.3.png',NULL, getdate()),
        (NEWID(), 'HA03', 1, 'product-3.0.png', 'product-3.1.png', 'product-3.2.png', 'product-3.3.png',NULL, getdate()),
        (NEWID(), 'HA04', 1, 'product-4.0.png', 'product-4.1.png', 'product-4.2.png', 'product-4.3.png', 'product-4.4.png', getdate()),
        (NEWID(), 'HA05', 1, 'product-5.0.png', 'product-5.1.png', 'product-5.2.png', 'product-5.3.png', 'product-5.4.png', getdate()),
        (NEWID(), 'HA06', 1, 'product-6.0.png', 'product-6.1.png', 'product-6.2.png', 'product-6.3.png', 'product-6.4.png',getdate()),
        (NEWID(), 'HA07', 1, 'product-7.0.png', 'product-7.1.png', 'product-7.2.png', 'product-7.3.png', 'product-7.4.png',getdate()),
        (NEWID(), 'HA08', 1, 'product-8.0.png', 'product-8.1.png', 'product-8.2.png', 'product-8.3.png', 'product-8.4.png',getdate()),
        (NEWID(), 'HA09', 1, 'product-9.0.png', 'product-9.1.png', 'product-9.2.png', 'product-9.3.png', 'product-9.4.png', getdate()),
        (NEWID(), 'HA010', 1, 'product-10.0.png', 'product-10.1.png', 'product-10.2.png', 'product-10.3.png', 'product-10.4.png',getdate()),
        (NEWID(), 'HA011', 1, 'product-11.0.png', 'product-11.1.png', 'product-11.2.png', 'product-11.3.png', 'product-11.4.png', getdate()),
        (NEWID(), 'HA012', 1, 'product-12.0.png', 'product-12.1.png', 'product-12.2.png', 'product-12.3.png', 'product-12.4.png',getdate()),
        (NEWID(), 'HA013', 1, 'product-13.0.png', 'product-13.1.png', 'product-13.2.png', 'product-13.3.png', NULL, getdate()),
        (NEWID(), 'HA014', 1, 'product-14.0.png', 'product-14.1.png', 'product-14.2.png', 'product-14.3.png', NULL, getdate()),
        (NEWID(), 'HA015', 1, 'product-15.0.png', 'product-15.1.png', 'product-15.2.png', 'product-15.3.png', 'product-15.4.png', getdate()),
        (NEWID(), 'HA016', 1, 'product-16.0.png', 'product-16.1.png', 'product-16.2.png', 'product-16.3.png', NULL, getdate()),
        (NEWID(), 'HA017', 1, 'product-17.0.png', 'product-17.1.png', 'product-17.2.png', 'product-17.3.png', 'product-17.4.png', getdate()),
        (NEWID(), 'HA018', 1, 'product-18.0.png', 'product-18.1.png', 'product-18.2.png', 'product-18.3.png', 'product-18.4.png', getdate()),
        (NEWID(), 'HA019', 1, 'product-19.0.png', 'product-19.1.png', 'product-19.2.png', 'product-19.3.png', NULL, getdate()),
        (NEWID(), 'HA020', 1, 'product-20.0.png', 'product-20.1.png', 'product-20.2.png', 'product-20.3.png', NULL, getdate()),
        (NEWID(), 'HA021', 1, 'product-21.0.png', 'product-21.1.png', 'product-21.2.png', 'product-21.3.png', 'product-21.4.png', getdate()),
        (NEWID(), 'HA022', 1, 'product-22.0.png', 'product-22.1.png', 'product-22.2.png', 'product-22.3.png', 'product-22.4.png', getdate()),
        (NEWID(), 'HA023', 1, 'product-23.0.png', 'product-23.1.png', 'product-23.2.png', 'product-23.3.png', 'product-23.4.png', getdate()),
        (NEWID(), 'HA024', 1, 'product-24.0.png', 'product-24.1.png', 'product-24.2.png', 'product-24.3.png', 'product-24.4.png', getdate()),
        (NEWID(), 'HA025', 1, 'product-25.0.png', 'product-25.1.png', 'product-25.2.png', 'product-25.3.png', 'product-25.4.png', getdate()),
        (NEWID(), 'HA026', 1, 'product-26.0.png', 'product-26.1.png', 'product-26.2.png', 'product-26.3.png', NULL, getdate()),
        (NEWID(), 'HA027', 1, 'product-27.0.png', 'product-27.1.png', 'product-27.2.png', 'product-27.3.png', 'product-27.4.png', getdate()),
        (NEWID(), 'HA028', 1, 'product-28.0.png', 'product-28.1.png', 'product-28.2.png', 'product-28.3.png', 'product-28.4.png', getdate()),
        (NEWID(), 'HA029', 1, 'product-29.0.png', 'product-29.1.png', 'product-29.2.png', 'product-29.3.png', 'product-29.4.png', getdate()),
        (NEWID(), 'HA030', 1, 'product-30.0.png', 'product-30.1.png', 'product-30.2.png', 'product-30.3.png', 'product-30.4.png', getdate()),
        (NEWID(), 'HA031', 0, 'product-30.0.png', 'product-30.1.png', 'product-30.2.png', 'product-30.3.png', 'product-30.4.png', getdate()),
        (NEWID(), 'HA032', 0, 'product-30.0.png', 'product-30.1.png', 'product-30.2.png', 'product-30.3.png', 'product-30.4.png', getdate());


insert into chi_tiet_giay(id_chi_tiet_giay, id_giay, id_mau, id_size, id_hinh_anh, trang_thai)
VALUES  (NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker001'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA01'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker001'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA01'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker001'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA01'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker001'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color04'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA01'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker001'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA01'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker001'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color06'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA01'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker001'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA01'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker001'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color08'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA01'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker001'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA01'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker001'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color10'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA01'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker001'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color11'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA01'), 1),

(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color04'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color06'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color08'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color10'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker002'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color11'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA02'), 1),

(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker003'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA03'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker003'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA03'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker003'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA03'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker003'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color04'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA03'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker003'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA03'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker003'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color06'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA03'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker003'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA03'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker003'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color08'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA03'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker003'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA03'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker003'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color10'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA03'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker003'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color11'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA03'), 1),

(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker004'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA04'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker004'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA04'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker004'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA04'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker004'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color04'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA04'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker004'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA04'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker004'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color06'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA04'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker004'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA04'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker004'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color08'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA04'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker004'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA04'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker004'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color10'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA04'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker004'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color11'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA04'), 1),

(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker005'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA05'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker005'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA05'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker005'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA05'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker005'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color04'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA05'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker005'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA05'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker005'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color06'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA05'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker005'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA05'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker005'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color08'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA05'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker005'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA05'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker005'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color10'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA05'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker005'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color11'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA05'), 1),

(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker006'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA06'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker006'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA06'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker006'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA06'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker006'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color04'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA06'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker006'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA06'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker006'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color06'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA06'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker006'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA06'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker006'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color08'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA06'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker006'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA06'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker006'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color10'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA06'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker006'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color11'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA06'), 1),

(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker007'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA07'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker007'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA07'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker007'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA07'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker007'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color04'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA07'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker007'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA07'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker007'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color06'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA07'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker007'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA07'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker007'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color08'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA07'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker007'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA07'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker007'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color10'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA07'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker007'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color11'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA07'), 1),

(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker008'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA08'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker008'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA08'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker008'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA08'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker008'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color04'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA08'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker008'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA08'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker008'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color06'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA08'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker008'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA08'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker008'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color08'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA08'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker008'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA08'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker008'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color10'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA08'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker008'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color11'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA08'), 1),

(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker009'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA09'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker009'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA09'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker009'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA09'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker009'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color04'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA09'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker009'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA09'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker009'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color06'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA09'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker009'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA09'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker009'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color08'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA09'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker009'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA09'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker009'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color10'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA09'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker009'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color11'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA09'), 1),

(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker010'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA010'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker010'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA010'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker010'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA010'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker010'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA010'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker010'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color04'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA010'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker010'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA010'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker010'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color06'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA010'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker010'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA010'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker010'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color08'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA010'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker010'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA010'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker010'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color10'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA010'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker010'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color11'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA010'), 1),

(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker011'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA011'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker011'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA011'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker011'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA011'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker011'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color04'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA011'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker011'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA011'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker011'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color06'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA011'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker011'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA011'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker011'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color08'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA011'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker011'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA011'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker011'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color10'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA011'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker011'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color11'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA011'), 1),

(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker012'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA012'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker012'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA012'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker012'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA012'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker012'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color04'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA012'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker012'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA012'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker012'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color06'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA012'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker012'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA012'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker012'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color08'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA012'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker012'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA012'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker012'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color10'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA012'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker012'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color11'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA012'), 1),

(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker013'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA013'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker013'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA013'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker013'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA013'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker013'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color04'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA013'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker013'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA013'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker013'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color06'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA013'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker013'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA013'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker013'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color08'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA013'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker013'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA013'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker013'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color10'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA013'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker013'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color11'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA013'), 1),

(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker014'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA014'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker014'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA014'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker014'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA014'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker014'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color04'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA014'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker014'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA014'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker014'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color06'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA014'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker014'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA014'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker014'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color08'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA014'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker014'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA014'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker014'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color10'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA014'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker014'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color11'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA014'), 1),

(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker015'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA015'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker015'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA015'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker015'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA015'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker015'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color04'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA015'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker015'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA015'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker015'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color06'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA015'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker015'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA015'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker015'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color08'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA015'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker015'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA015'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker015'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color10'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA015'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker015'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color11'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA015'), 1),

(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker016'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA016'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker016'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA016'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker016'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA016'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker016'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color04'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA016'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker016'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA016'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker016'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color06'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA016'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker016'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA016'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker016'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color08'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA016'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker016'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA016'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker016'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color10'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA016'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker016'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color11'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA016'), 1),

(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker017'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA017'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker017'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA017'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker017'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA017'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker017'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color04'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA017'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker017'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA017'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker017'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color06'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA017'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker017'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA017'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker017'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color08'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA017'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker017'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA017'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker017'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color10'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA017'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker017'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color11'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA017'), 1),

(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker018'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA018'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker018'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA018'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker018'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA018'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker018'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color04'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA018'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker018'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA018'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker018'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color06'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA018'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker018'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA018'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker018'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color08'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA018'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker018'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA018'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker018'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color10'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA018'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker018'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color11'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA018'), 1),

(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker019'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA019'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker019'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA019'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker019'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA019'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker019'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color04'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA019'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker019'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA019'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker019'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color06'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA019'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker019'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA019'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker019'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color08'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA019'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker019'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA019'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker019'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color10'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA019'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker019'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color11'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA019'), 1),

(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker020'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA020'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker020'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA020'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker020'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA020'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker020'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color04'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA020'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker020'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA020'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker020'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color06'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA020'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker020'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA020'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker020'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color08'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA020'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker020'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA020'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker020'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color10'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA020'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker020'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color11'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA020'), 1),

(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker021'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA021'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker021'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA021'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker021'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA021'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker021'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color04'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA021'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker021'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA021'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker021'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color06'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA021'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker021'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA021'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker021'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color08'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA021'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker021'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA021'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker021'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color10'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA021'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker021'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color11'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA021'), 1),

(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker022'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA022'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker022'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA022'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker022'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA022'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker022'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color04'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA022'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker022'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA022'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker022'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color06'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA022'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker022'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA022'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker022'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color08'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA022'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker022'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA022'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker022'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color10'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA022'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker022'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color11'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA022'), 1),

(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker023'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA023'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker023'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA023'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker023'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA023'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker023'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color04'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA023'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker023'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA023'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker023'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color06'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA023'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker023'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA023'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker023'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color08'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA023'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker023'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA023'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker023'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color10'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA023'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker023'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color11'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA023'), 1),

(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker024'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA024'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker024'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA024'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker024'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA024'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker024'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color04'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA024'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker024'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA024'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker024'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color06'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA024'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker024'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA024'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker024'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color08'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA024'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker024'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA024'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker024'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color10'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA024'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker024'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color11'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA024'), 1),

(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker025'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA025'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker025'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA025'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker025'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA025'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker025'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color04'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA025'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker025'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA025'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker025'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color06'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA025'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker025'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA025'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker025'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color08'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA025'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker025'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA025'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker025'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color10'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA025'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker025'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color11'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA025'), 1),

(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker026'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA026'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker026'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA026'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker026'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA026'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker026'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color04'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA026'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker026'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA026'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker026'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color06'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA026'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker026'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA026'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker026'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color08'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA026'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker026'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA026'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker026'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color10'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA026'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker026'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color11'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA026'), 1),

(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker027'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA027'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker027'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA027'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker027'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA027'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker027'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color04'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA027'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker027'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA027'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker027'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color06'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA027'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker027'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA027'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker027'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color08'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA027'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker027'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA027'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker027'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color10'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA027'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker027'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color11'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA027'), 1),

(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker028'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA028'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker028'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA028'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker028'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA028'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker028'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color04'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA028'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker028'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA028'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker028'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color06'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA028'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker028'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA028'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker028'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color08'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA028'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker028'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA028'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker028'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color10'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA028'), 1),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker028'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color11'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA028'), 1),

(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker029'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA029'), 0),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker029'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA029'), 0),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker029'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA029'), 0),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker029'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color04'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA029'), 0),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker029'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA029'), 0),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker029'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color06'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA029'), 0),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker029'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA029'), 0),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker029'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color08'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA029'), 0),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker029'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA029'), 0),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker029'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color10'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA029'), 0),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker029'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color11'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA029'), 0),

(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker030'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color01'), (SELECT id_size FROM [size] WHERE ma_size='Size01'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA030'), 2),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker030'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color02'), (SELECT id_size FROM [size] WHERE ma_size='Size02'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA030'), 2),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker030'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color03'), (SELECT id_size FROM [size] WHERE ma_size='Size03'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA030'), 2),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker030'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color04'), (SELECT id_size FROM [size] WHERE ma_size='Size04'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA030'), 2),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker030'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color05'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA030'), 2),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker030'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color06'), (SELECT id_size FROM [size] WHERE ma_size='Size06'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA030'), 2),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker030'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color07'), (SELECT id_size FROM [size] WHERE ma_size='Size07'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA030'), 2),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker030'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color08'), (SELECT id_size FROM [size] WHERE ma_size='Size08'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA030'), 2),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker030'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color09'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA030'), 2),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker030'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color10'), (SELECT id_size FROM [size] WHERE ma_size='Size09'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA030'), 2),
(NEWID(), (SELECT id_giay FROM giay WHERE ma_giay='Sneaker030'), (SELECT id_mau FROM mau_sac WHERE ma_mau='Color11'), (SELECT id_size FROM [size] WHERE ma_size='Size05'), (SELECT id_hinh_anh FROM hinh_anh WHERE ma_anh ='HA030'), 2);

insert into loai_khach_hang(id_hang,ma_loaikh,ten_loaikh,so_diem,trang_thai, tg_them) values
(newid(),'H3','Kim Cuong',5000, 1, getdate()),
(newid(),'H3','Bach Kim', 3000, 1, getdate()),
(newid(),'H3','Vang',1000, 1, getdate()),
(newid(),'H2','Bac',500, 1, getdate()),
(newid(),'H1','Dong',200, 1, getdate()),
(newid(),'H1','Sat',100, 0, getdate())

insert into chuc_vu(id_chuc_vu, ma_chuc_vu, ten_chuc_vu, trang_thai, tg_them) values
(NEWID(),'CV01','Quan Ly',1, GETDATE()),
(NEWID(),'CV02','Nhan vien',1, GETDATE()),
(NEWID(),'CV03','Bao ve',0, GETDATE())

insert into  nhan_vien(id_nhan_vien, ma_nv, ho_ten_nv, ngay_sinh, email_nv, dia_chi_nv, gioi_tinh, sdt_nv, mk_nv, trang_thai,cccd_nv, anh_nv,tg_them) values
(NEWID(),'NV01','Linhnv','2003-03-14','linhnvph27778@fpt.edu.vn','Ha Noi',1,'0375853419','123456',1,'5987654321','anhNV1', GETDATE()),
(NEWID(),'NV02','TienTV','2003-05-14','tientv@fpt.edu.vn','Ha Noi',1,'0987654321','123456',1,'5987654321','anhNV2', GETDATE()),
(NEWID(),'NV03','thuongnt','2003-07-14','linh@gmail.com','Ha Noi',1,'0375853449','123456',0,'5987654321','anhNV3', GETDATE())


update chi_tiet_giay  set gia_ban = 200, nam_bao_hanh = 2025, nam_san_xuat = 2021, so_luong = 60, trong_luong =600




SELECT
        ctg.id_giay,
        MIN(ctg.gia_ban) AS min_price,
        g.ten_giay,
        SUM(ctg.so_luong) AS sl_ton,
        a.url1,
        COALESCE(SUM(cthd.so_luong), 0) AS so_Luong_Da_Ban 
    FROM
        chi_tiet_giay ctg 
    JOIN
        giay g 
            ON g.id_giay = ctg.id_giay 
    JOIN
        hinh_anh a 
            ON a.id_hinh_anh = ctg.id_hinh_anh 
    LEFT JOIN
        hoa_don_chi_tiet cthd 
            ON cthd.id_ctg = ctg.id_chi_tiet_giay 
    WHERE
        ctg.id_giay IS NOT NULL     
        AND ctg.id_hinh_anh IS NOT NULL  
        AND g.trang_thai Like 1  
        AND ctg.trang_thai like 1
    GROUP BY
        ctg.id_giay,
        g.ten_giay,
        a.url1;