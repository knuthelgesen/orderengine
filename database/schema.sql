USE orderDB;

-- -----------------------------------------------
-- Table for users
-- -----------------------------------------------
CREATE TABLE users
(
user_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
user_name varchar(255) NOT NULL,
user_password varchar(255) NOT NULL,
user_role_name varchar(255) NOT NULL
);
CREATE UNIQUE INDEX index_users_user_name
ON users (user_name);

-- -----------------------------------------------
-- Table for game IDs sequence
-- -----------------------------------------------
CREATE TABLE game_id_seq
(
game_id int NOT NULL AUTO_INCREMENT PRIMARY KEY
);

-- -----------------------------------------------
-- Table for games
-- -----------------------------------------------
CREATE TABLE games
(
game_id int NOT NULL PRIMARY KEY,
created timestamp NOT NULL,
created_by int NOT NULL REFERENCES users(user_id),
game_data blob NOT NULL
);

-- -----------------------------------------------
-- Junction table between games and users
-- -----------------------------------------------
CREATE TABLE participants
(
game_id int NOT NULL REFERENCES games(game_id),
user_id int NOT NULL REFERENCES users(user_id),
PRIMARY KEY (game_id,user_id)
);
CREATE INDEX index_participants_game_id
ON participants (game_id);
CREATE INDEX index_participants_user_id
ON participants (user_id);

-- -----------------------------------------------
-- Table for orders
-- -----------------------------------------------

