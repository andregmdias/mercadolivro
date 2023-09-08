create table purchases(
	id serial primary key,
	customer_id int not null,
	nfe varchar(255),
	created_at timestamp not null default now(),
	foreign key (customer_id) references customers(id)
);



create table purchases_books(
    id serial primary key,
	purchase_id int not null,
	book_id int not null,
	foreign key (purchase_id) references purchases(id),
	foreign key (book_id) references books(id)
);