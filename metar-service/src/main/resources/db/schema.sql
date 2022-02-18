CREATE TABLE "public".airport_subscription (
	airport_subscription_id varchar NOT NULL,
	icao_code varchar NOT NULL,
	CONSTRAINT airport_subscription_id_pk PRIMARY KEY (airport_subscription_id),
	CONSTRAINT airport_subscription_metar_un UNIQUE (icao_code)
);

CREATE TABLE "public".metar (
	metar_data_id varchar NOT NULL,
	metar_data varchar NOT NULL,
	icao_code varchar NOT NULL,
	CONSTRAINT metar_data_id_pk PRIMARY KEY (metar_data_id),
	CONSTRAINT metar_metar_un UNIQUE (icao_code)
);