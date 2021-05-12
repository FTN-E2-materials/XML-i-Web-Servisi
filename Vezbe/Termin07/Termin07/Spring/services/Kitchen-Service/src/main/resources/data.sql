insert into RESTAURANT(id, name) values (1, 'McDonalds')

insert into MENU_ITEM(id, name, restaurant_id) values (1, 'Pizza', 1)
insert into MENU_ITEM(id, name, restaurant_id) values (2, 'Burger', 1)
insert into MENU_ITEM(id, name, restaurant_id) values (3, 'Burito', 1)
insert into MENU_ITEM(id, name, restaurant_id) values (4, 'Crunchwarp', 1)
insert into MENU_ITEM(id, name, restaurant_id) values (5, 'Gyros', 1)

insert into RESTAURANT_MENU_ITEMS(restaurant_id, menu_items_id) values (1, 1)
insert into RESTAURANT_MENU_ITEMS(restaurant_id, menu_items_id) values (1, 2)
insert into RESTAURANT_MENU_ITEMS(restaurant_id, menu_items_id) values (1, 3)
insert into RESTAURANT_MENU_ITEMS(restaurant_id, menu_items_id) values (1, 4)
insert into RESTAURANT_MENU_ITEMS(restaurant_id, menu_items_id) values (1, 5)