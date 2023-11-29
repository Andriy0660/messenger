create table users(
    id bigserial primary key,
    name varchar(20)
);


create table messages(
    id bigserial primary key,
    sender_id bigint,
    receiver_id bigint,
    content varchar(500),
    time timestamp,
    foreign key (sender_id) references users(id),
    foreign key (receiver_id) references users(id)
)