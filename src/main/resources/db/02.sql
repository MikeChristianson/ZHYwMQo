create table if not exists loan_stats (
    id bigint not null primary key,
    loan_amnt bigint not null,
    term int not null,
    int_rate decimal(5,2) not null,
    grade varchar(5) not null,
    addr_state varchar(5) not null,
    issue_d date not null
);

create index loan_stats_addr_state_index
    on loan_stats (addr_state);

create index loan_stats_grade_index
    on loan_stats (grade);

create index loan_stats_issue_d_index
    on loan_stats (issue_d);