create schema RECFLIX;

CREATE TABLE RECFLIX.movies(
	id int not null,
    title varchar(255),
    imdbID varchar(7),
    spanishTitle varchar(255),
    imdbPictureURL varchar(255),
    year year(4),
    rtID varchar(255),
    rtAllCriticsRating double,
    rtAllCriticsNumReviews int,
    rtAllCriticsNumFresh int,
    rtAllCriticsNumRotten    int,
    rtAllCriticsScore    int,
    rtTopCriticsRating   double,
    rtTopCriticsNumReviews   int,
    rtTopCriticsNumFresh int,
    rtTopCriticsNumRotten    int,
    rtTopCriticsScore    int,
    rtAudienceRating double,
    rtAudienceNumRatings int,
    rtAudienceScore  int,
    rtPictureURL varchar(255),
    PRIMARY KEY (id)
    );
    
CREATE TABLE RECFLIX.movie_actors(
	movieID	int	not null,
    actorID	varchar(255) not null,
    actorName	varchar(255) not null,
    ranking	int,
	PRIMARY KEY (movieID, actorID)
);

CREATE TABLE RECFLIX.movie_countries(
	movieID	int not null,
    country varchar(255) not null,
    PRIMARY KEY (movieID)
);

CREATE TABLE RECFLIX.movie_directors(
	movieID	int not null,
    directorID	varchar(255) not null,
    directorName	varchar(255) not null,
    PRIMARY KEY(movieID)
);

CREATE TABLE RECFLIX.movie_genres(
	movieID	int not null,
    genre	varchar(255) not null,
    PRIMARY KEY(movieID, genre)
);

CREATE TABLE RECFLIX.movie_locations(
	movieID	int not null,
    location1	varchar(255) not null,
    location2	varchar(255) not null,
    location3	varchar(255) not null,
    location4	varchar(255),
    PRIMARY KEY(movieID, location1, location2, location3)
);

CREATE TABLE RECFLIX.movie_tags(
	movieID	int not null,
    tagID	int not null,
    tagWeight	int,
    PRIMARY KEY(movieID, tagID)
);

CREATE TABLE RECFLIX.tags(
	id	int not null,
    value	varchar(255) not null,
    PRIMARY KEY(id)
);
    
CREATE TABLE RECFLIX.user_ratedmovies(
	userID	int	not null,
    movieID	int not null,
    rating	double not null,
    date_day int,
    date_month int,
    date_year int,
    date_hour int,
    date_minute	int,
    date_second int,
    PRIMARY KEY(userID,movieID)
);

#CREATE TABLE RECFLIX.user_ratedmovies-timestamps(
#	userID	int	not null,
#	movieID	int not null,
#	rating double not null,
#   timestamp varchar(13) not null,
#	PRIMARY KEY(userID, movieID)
#);

CREATE TABLE RECFLIX.user_taggedmovies(
	userID int not null,
    movieID int not null,
    tagID	int,
    date_day	int,
    date_month	int,
    date_year	int,
    date_hour	int,
    date_minute	int,
    date_second	int,
    PRIMARY KEY(userID, movieID, tagID)
);

#CREATE TABLE RECFLIX.user_taggedmovies-timestamps(
#	userID	int not null,
#    movieID	int not null,
#    tagID	int not null,
#    timestamp	varchar(13)
#    PRIMARY KEY(userID, movieID, tagID)
#);

ALTER TABLE RECFLIX.movie_actors
	ADD FOREIGN KEY (movieID)
    REFERENCES RECFLIX.movies(id)
    ON DELETE CASCADE;

ALTER TABLE RECFLIX.movie_countries
	ADD FOREIGN KEY (movieID)
    REFERENCES RECFLIX.movies(id)
	ON DELETE CASCADE;

ALTER TABLE RECFLIX.movie_genres
	ADD FOREIGN KEY (movieID)
	REFERENCES RECFLIX.movies(id)
    ON DELETE CASCADE;

ALTER TABLE RECFLIX.movie_directors
	ADD FOREIGN KEY (movieID)
	REFERENCES RECFLIX.movies(id)
    ON DELETE CASCADE;

ALTER TABLE RECFLIX.movie_locations
	ADD FOREIGN KEY (movieID)
	REFERENCES RECFLIX.movies(id)
    ON DELETE CASCADE;

ALTER TABLE RECFLIX.movie_tags
	ADD FOREIGN KEY (movieID)
	REFERENCES RECFLIX.movies(id)
    ON DELETE CASCADE;

ALTER TABLE RECFLIX.user_ratedmovies
	ADD FOREIGN KEY (movieID)
	REFERENCES RECFLIX.movies(id)
    ON DELETE CASCADE;

ALTER TABLE RECFLIX.user_taggedmovies
	ADD FOREIGN KEY (movieID)
	REFERENCES RECFLIX.movies(id)
    ON DELETE CASCADE,
    ADD FOREIGN KEY (tagID)
    REFERENCES RECFLIX.tags(id);

load data local infile '/home/justin/sync/documents/school/courses/CS4721/recflix/data/movies.dat'
	into table RECFLIX.movies
    fields terminated by '\t'
    lines terminated by '\n';

load data local infile '/home/justin/sync/documents/school/courses/CS4721/recflix/data/movie_actors.dat'
	into table RECFLIX.movie_actors
    fields terminated by '\t'
    lines terminated by '\n';

load data local infile '/home/justin/sync/documents/school/courses/CS4721/recflix/data/movie_directors.dat'
	into table RECFLIX.movie_directors
    fields terminated by '\t'
    lines terminated by '\n';

load data local infile '/home/justin/sync/documents/school/courses/CS4721/recflix/data/movie_countries.dat'
	into table RECFLIX.movie_countries
    fields terminated by '\t'
    lines terminated by '\n';

load data local infile '/home/justin/sync/documents/school/courses/CS4721/recflix/data/movie_genres.dat'
	into table RECFLIX.movie_genres
    fields terminated by '\t'
    lines terminated by '\n';

load data local infile '/home/justin/sync/documents/school/courses/CS4721/recflix/data/movie_locations.dat'
	into table RECFLIX.movie_locations
    fields terminated by '\t'
    lines terminated by '\n';

load data local infile '/home/justin/sync/documents/school/courses/CS4721/recflix/data/movie_tags.dat'
	into table RECFLIX.movie_tags
    fields terminated by '\t'
    lines terminated by '\n';

load data local infile '/home/justin/sync/documents/school/courses/CS4721/recflix/data/tags.dat'
	into table RECFLIX.tags
    fields terminated by '\t'
    lines terminated by '\n';

load data local infile '/home/justin/sync/documents/school/courses/CS4721/recflix/data/user_ratedmovies.dat'
	into table RECFLIX.user_ratedmovies
    fields terminated by '\t'
    lines terminated by '\n';

#load data local infile '/home/justin/sync/documents/school/courses/CS4721/recflix/data/user_ratedmovies-timestamps.dat'
#	into table RECFLIX.user_ratedmovies-timestamps
#    fields terminated by '\t'
#    lines terminated by '\n';

load data local infile '/home/justin/sync/documents/school/courses/CS4721/recflix/data/user_taggedmovies.dat'
	into table RECFLIX.user_taggedmovies
    fields terminated by '\t'
    lines terminated by '\n';
    
#load data local infile '/home/justin/sync/documents/school/courses/CS4721/recflix/data/user_taggedmovies-timestamps.dat'
#	into table RECFLIX.user_taggedmovies-timestamps
#    fields terminated by '\t'
#    lines terminated by '\n';
