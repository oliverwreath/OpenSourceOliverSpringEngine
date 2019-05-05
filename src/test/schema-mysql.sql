-- CREATE DATABASE IF NOT EXISTS
CREATE DATABASE IF NOT EXISTS spring_engine_test;
USE spring_engine_test;


# alter table admins
#     drop
#         foreign key FKgc8dtql9mkq268detxiox7fpm;
#
# alter table authorities
#     drop
#         foreign key FKk91upmbueyim93v469wj7b2qh;
#
# alter table members
#     drop
#         foreign key FKpj3n6wh5muoeakc485whgs3x5;
#
# alter table order_courses
#     drop
#         foreign key FK28cttouuwyofclhcd9ho1dqf9;
#
# alter table order_courses
#     drop
#         foreign key FKime3l43iftmbc8a88utvygqyh;
#
# alter table order_videos
#     drop
#         foreign key FKcb7gt15m2tpxdhd7lkkko7r6g;
#
# alter table order_videos
#     drop
#         foreign key FKb31b9n8c7an13y5hhrr7f67k0;
#
# alter table videos
#     drop
#         foreign key FKrccvc1a5qx2dqsyienwhuk66j;

drop table if exists admins;

drop table if exists authorities;

drop table if exists courses;

drop table if exists drop_down_list_entities;

drop table if exists members;

drop table if exists order_courses;

drop table if exists order_videos;

drop table if exists users;

drop table if exists videos;


create table admins (
                        id bigint not null auto_increment,
                        created_by varchar(255),
                        created_on datetime(6),
                        updated_by varchar(255),
                        updated_on datetime(6),
                        city varchar(255),
                        country varchar(255),
                        email varchar(255),
                        mobile varchar(255),
                        state varchar(255),
                        street_address varchar(255),
                        zipcode varchar(255),
                        gender varchar(255),
                        landing_offer varchar(255),
                        major varchar(255),
                        name varchar(255),
                        personal_interest varchar(255),
                        school varchar(255),
                        year_of_graduation integer,
                        user_id bigint,
                        primary key (id)
) engine=InnoDB;

create table authorities (
                             id bigint not null auto_increment,
                             authority varchar(255),
                             created_by varchar(255),
                             created_on datetime(6),
                             updated_by varchar(255),
                             updated_on datetime(6),
                             user_id bigint,
                             primary key (id)
) engine=InnoDB;

create table courses (
                         id bigint not null auto_increment,
                         catalog varchar(255),
                         course_name varchar(255),
                         course_price decimal(13,5),
                         created_by varchar(255),
                         created_on datetime(6),
                         updated_by varchar(255),
                         updated_on datetime(6),
                         purchase_count bigint,
                         purchase_sum bigint,
                         view_count bigint,
                         primary key (id)
) engine=InnoDB;

create table drop_down_list_entities (
                                         id varchar(255) not null,
                                         created_by varchar(255),
                                         created_on datetime(6),
                                         updated_by varchar(255),
                                         updated_on datetime(6),
                                         my_values varchar(255),
                                         primary key (id)
) engine=InnoDB;

create table members (
                         id bigint not null auto_increment,
                         created_by varchar(255),
                         created_on datetime(6),
                         updated_by varchar(255),
                         updated_on datetime(6),
                         city varchar(255),
                         country varchar(255),
                         email varchar(255),
                         mobile varchar(255),
                         state varchar(255),
                         street_address varchar(255),
                         zipcode varchar(255),
                         video_id bigint,
                         gender varchar(255),
                         is_vip bit,
                         job_target varchar(255),
                         landing_offer varchar(255),
                         major varchar(255),
                         name varchar(255),
                         personal_interest varchar(255),
                         school varchar(255),
                         year_of_graduation integer,
                         user_id bigint,
                         primary key (id)
) engine=InnoDB;

create table order_courses (
                               id bigint not null auto_increment,
                               catalog varchar(255),
                               course_name varchar(255),
                               created_by varchar(255),
                               created_on datetime(6),
                               updated_by varchar(255),
                               updated_on datetime(6),
                               purchase_count bigint,
                               purchase_sum bigint,
                               view_count bigint,
                               order_type varchar(255),
                               course_id bigint not null,
                               member_id bigint not null,
                               primary key (id)
) engine=InnoDB;

create table order_videos (
                              id bigint not null auto_increment,
                              catalog varchar(255),
                              course_name varchar(255),
                              created_by varchar(255),
                              created_on datetime(6),
                              updated_by varchar(255),
                              updated_on datetime(6),
                              purchase_count bigint,
                              purchase_sum bigint,
                              view_count bigint,
                              order_type varchar(255),
                              member_id bigint not null,
                              video_id bigint not null,
                              primary key (id)
) engine=InnoDB;

create table users (
                       id bigint not null auto_increment,
                       created_by varchar(255),
                       created_on datetime(6),
                       updated_by varchar(255),
                       updated_on datetime(6),
                       enabled bit,
                       password varchar(255),
                       user_email varchar(255),
                       user_type varchar(255),
                       primary key (id)
) engine=InnoDB;

create table videos (
                        id bigint not null auto_increment,
                        catalog varchar(255),
                        course_name varchar(255),
                        created_by varchar(255),
                        created_on datetime(6),
                        updated_by varchar(255),
                        updated_on datetime(6),
                        purchase_count bigint,
                        purchase_sum bigint,
                        view_count bigint,
                        video_link varchar(255),
                        video_name varchar(255),
                        video_price decimal(13,5),
                        course_id bigint,
                        primary key (id)
) engine=InnoDB;

alter table admins
    add constraint FKgc8dtql9mkq268detxiox7fpm
        foreign key (user_id)
            references users (id);

alter table authorities
    add constraint FKk91upmbueyim93v469wj7b2qh
        foreign key (user_id)
            references users (id);

alter table members
    add constraint FKpj3n6wh5muoeakc485whgs3x5
        foreign key (user_id)
            references users (id);

alter table order_courses
    add constraint FK28cttouuwyofclhcd9ho1dqf9
        foreign key (course_id)
            references courses (id);

alter table order_courses
    add constraint FKime3l43iftmbc8a88utvygqyh
        foreign key (member_id)
            references members (id);

alter table order_videos
    add constraint FKcb7gt15m2tpxdhd7lkkko7r6g
        foreign key (member_id)
            references members (id);

alter table order_videos
    add constraint FKb31b9n8c7an13y5hhrr7f67k0
        foreign key (video_id)
            references videos (id);

alter table videos
    add constraint FKrccvc1a5qx2dqsyienwhuk66j
        foreign key (course_id)
            references courses (id);


-- Alter
ALTER DATABASE spring_engine_test CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

ALTER TABLE admins CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE authorities CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE courses CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE drop_down_list_entities CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE members CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE order_courses CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE order_videos CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE users CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE videos CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
