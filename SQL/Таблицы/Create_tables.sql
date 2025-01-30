CREATE SEQUENCE landmark_id_seq;
CREATE SEQUENCE history_id_seq;
CREATE SEQUENCE photo_id_seq;
CREATE SEQUENCE user_id_seq;


CREATE TABLE landmark
(
    id INTEGER NOT NULL DEFAULT nextval('landmark_id_seq'),
    name VARCHAR(50) NOT NULL,
    description TEXT NOT NULL,
    history TEXT NOT NULL,
    CONSTRAINT pk_landmark PRIMARY KEY (id)
);

ALTER TABLE landmark
ADD category INT NOT NULL DEFAULT 0;


CREATE TABLE historical_clipping
(
    id INTEGER NOT NULL DEFAULT nextval('history_id_seq'),
	name_clipping VARCHAR(255) NOT NULL,
	description TEXT NOT NULL,
    source VARCHAR(50) NOT NULL,
    date_history DATE,
    id_landmark INTEGER NOT NULL,
    CONSTRAINT pk_hisorical_clipping PRIMARY KEY (id),
    CONSTRAINT fk_landmsrk FOREIGN KEY (id_landmark)
        REFERENCES landmark (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);


CREATE TABLE location_landmark
(
    id INTEGER NOT NULL UNIQUE REFERENCES landmark (id),
    street_name VARCHAR(50) NOT NULL,
    home VARCHAR(10) NOT NULL,
    coordinates VARCHAR(50),
    CONSTRAINT pk_location PRIMARY KEY (id)
);


CREATE TABLE photo
(
    id INTEGER NOT NULL DEFAULT nextval('photo_id_seq'),
    image_URL VARCHAR(255) NOT NULL,
    date_photo DATE,
    id_landmark INTEGER NOT NULL,
    CONSTRAINT pk_photo PRIMARY KEY (id),
    CONSTRAINT fk_landmark FOREIGN KEY (id_landmark)
        REFERENCES landmark (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);


CREATE TABLE user_date
(
	id INTEGER NOT NULL DEFAULT nextval('user_id_seq'),
    login VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id),
	CONSTRAINT unique_user UNIQUE (login)
);


CREATE TABLE user_landmark (
	user_id INTEGER NOT NULL REFERENCES user_date (id),
  	landmark_id INTEGER NOT NULL REFERENCES landmark (id),
  	CONSTRAINT pk_user_landmark PRIMARY KEY (user_id, landmark_id)
);
