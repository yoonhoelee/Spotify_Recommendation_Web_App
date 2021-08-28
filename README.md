# Spotify_Recommendation_Web_App
![alt text](https://www.theblackhoof.co.nz/wp-content/uploads/2020/07/spotify-logo-1920x1080_fouoik-1536x865.jpg)
A web application that recommends songs based on songs on Top 200 Weekly chart of Spotify between 2020~2021.


## Data
[Spotify Top 200 Charts (2020-2021)](https://www.kaggle.com/sashankpillai/spotify-top-200-charts-20202021) provided on Kaggle.


## Project Overview
This is a web app where users can get recommendation based on the songs that they like. Users can also add songs to the database so that other users can get recommendations that are not in the database.

## Recommendation Model
The recommendation model was made based on k-means clustering.
Special thanks to Tuhin Kumar Dutta, you can check out his [code on Kaggle.](https://www.kaggle.com/tuhinkumardutta/spotify-song-recommendation)

## Specifics of the Application
The web application was made using the following
* Java Spring Boot
* Java 11
* Gradle
* Spring Web
* Spring Data JPA
* Thymeleaf
* MySql 8