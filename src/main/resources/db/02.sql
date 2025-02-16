create table if not exists loan_stats (
    id bigint not null primary key,
    loan_amnt bigint not null,
    int_rate varchar(10) not null
)
