set global local_infile = 1;

load data infile 'LoanStats_securev1_2017Q4.first-10-rows.csv' into table backend.loan_stats
    fields terminated by ','
    enclosed by '"'
    escaped by '"'
    lines terminated by '\n'
    ignore 1 lines (
                    id, @dummy, loan_amnt, @dummy, @dummy, @dummy, int_rate
        );