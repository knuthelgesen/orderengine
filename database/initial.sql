-- -----------------------------------------------
-- Create the database
-- -----------------------------------------------
CREATE DATABASE orderDB character set utf8 collate utf8_general_ci;

-- -----------------------------------------------
-- Create user for JETTY
-- -----------------------------------------------
GRANT ALL ON orderDB.* TO 'order'@'%' IDENTIFIED BY 'order';
GRANT ALL ON orderDB.* TO 'order'@'localhost' IDENTIFIED BY 'order';

