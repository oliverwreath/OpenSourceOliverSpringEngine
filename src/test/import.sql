
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
-- country + catalog
INSERT INTO drop_down_list_entities (id, my_values) VALUES ('COUNTRY', 'USA, Canada, Australia, New_Zealand, China, Japan, Korea, UK, Germany, Switzerland, France, Spain, Italy, Other_EuropeAmerica, Other_AsiaPacific, Other'),('CATALOG', '四大_BIG4, 咨询_CONSULTING, 大数据_BIG_DATA, FLAG, 金融_FINANCE, 快消_CPG');
-- Users
INSERT INTO users (user_email, password, enabled, user_type) VALUES ('admin@gmail.com', '$2a$10$E7z3fBbyHZtqLYre4S9D/uGc8Nhf6S2NnvVkT3lDMMeNkCXc/u2Gu', TRUE, 'ADMIN'),('admin@example.com', '$2a$10$E7z3fBbyHZtqLYre4S9D/uGc8Nhf6S2NnvVkT3lDMMeNkCXc/u2Gu', TRUE, 'ADMIN'),('member@example.com', '$2a$10$E7z3fBbyHZtqLYre4S9D/uGc8Nhf6S2NnvVkT3lDMMeNkCXc/u2Gu', TRUE, 'MEMBER'),('444@example.com', '$2a$10$E7z3fBbyHZtqLYre4S9D/uGc8Nhf6S2NnvVkT3lDMMeNkCXc/u2Gu', TRUE, 'MEMBER'),('555@example.com', '$2a$10$E7z3fBbyHZtqLYre4S9D/uGc8Nhf6S2NnvVkT3lDMMeNkCXc/u2Gu', TRUE, 'MEMBER'),('666@example.com', '$2a$10$E7z3fBbyHZtqLYre4S9D/uGc8Nhf6S2NnvVkT3lDMMeNkCXc/u2Gu', TRUE, 'MEMBER'),('777@example.com', '$2a$10$E7z3fBbyHZtqLYre4S9D/uGc8Nhf6S2NnvVkT3lDMMeNkCXc/u2Gu', TRUE, 'MEMBER'),('888@example.com', '$2a$10$E7z3fBbyHZtqLYre4S9D/uGc8Nhf6S2NnvVkT3lDMMeNkCXc/u2Gu', TRUE, 'MEMBER');
INSERT INTO authorities (user_id, authority) VALUES (1, 'ROLE_ADMIN'),(2, 'ROLE_ADMIN'),(3, 'ROLE_USER');
-- Courses
INSERT INTO courses (catalog, course_name) VALUES ('四大_BIG4', 'example_course1'),('咨询_CONSULTING', 'example_course2'),('大数据_BIG_DATA', 'example_course3'),('FLAG', 'example_course4'),('金融_FINANCE', 'example_course5'),('快消_CPG', 'example_course6');
-- Member
INSERT INTO members (name, is_vip, user_id, country, gender, school, major, job_target) VALUES ('Anne_example', 1, 2, 'Canada', 'FEMALE', 'Sch_example', 'Maj_example', '四大_BIG4');
INSERT INTO members (name, is_vip, user_id, country, gender, job_target) VALUES ('Jack_example',  0, 3, 'Canada', 'MALE', '四大_BIG4'),('Jack_example',  0, 4, 'Canada', 'MALE', '咨询_CONSULTING'),('Jack_example',  0, 5, 'Canada', 'MALE', '大数据_BIG_DATA'),('Jack_example',  0, 6, 'Canada', 'MALE', 'FLAG'),('Jack_example',  0, 7, 'Canada', 'MALE', '金融_FINANCE'),('Jack_example',  0, 8, 'Canada', 'MALE', '快消_CPG');
-- Video
INSERT INTO videos (catalog, video_name) VALUES ('FLAG', 'example_video');
