# Wildlife-Tracker
An app for the forest service to track animals for an environmental impact study
### Author: 
**Odette Ahishakiye** on September 25th, 2019.

## Description
The **Wildlife-tracker app** isdesigned to help in tracking the health statuses of wild animals, their age and the lacatio where they are sighted by Rangers.

## Setup/Installation Requirements
* Open your computer
* Connect to the internet 
* Open the IntelliJ and the Terminal
* Go to  [my GitHub page](https://github.com/ahiodette/Wildlife-Tracker)
    #### PostgreSQL for Database
        * CREATE DATABASE wildlife_tracker;
        * \c wildlife_tracker;
        
        * CREATE TABLE animals (id SERIAL PRIMARY KEY, ranger_name VARCHAR, animal_name VARCHAR,                health VARCHAR, age VARCHAR, location VARCHAR);
        * CREATE TABLE sightings (id SERIAL PRIMARY KEY, ranger_name VARCHAR, animal_name VARCHAR,                health VARCHAR, age VARCHAR, location VARCHAR);
        
        * CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;

## Known bugs

## Support and Contact details
In case you may need any support about this app, do not hesitate to contact the developer on ahiode6@gmail.com
