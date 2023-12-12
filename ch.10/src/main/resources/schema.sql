create table if not exists `spring`.`token`
(
    `id`
    int
    not
    null
    auto_increment,
    `identifier`
    varchar
(
    45
) null,
    `token` text null,
    primary key
(
    `id`
));