# Spotify_Recommendation_Web_App
![alt text](https://www.theblackhoof.co.nz/wp-content/uploads/2020/07/spotify-logo-1920x1080_fouoik-1536x865.jpg)
A web application that recommends songs based on songs on Top 200 Weekly chart of Spotify between 2020~2021.


## Data
[Spotify Top 200 Charts (2020-2021)](https://www.kaggle.com/sashankpillai/spotify-top-200-charts-20202021) provided on Kaggle.


## Project Overview
This is a web app where users can get recommendation based on the songs that they like. Users can also add songs to the database so that other users can get recommendations that are not in the database. Furthermore, I used Spotify API so that users can get a list of their favorite artist.


## Recommendation Model
The recommendation model was made based on k-means clustering.
Special thanks to Tuhin Kumar Dutta, you can check out his [code on Kaggle.](https://www.kaggle.com/tuhinkumardutta/spotify-song-recommendation)


## Spotify API
You can find more info about the [Spotify API](https://developer.spotify.com/documentation/web-api/) in their web-page.
I have used an external library to help me retrieve the necessary data such as authorization token and a list of favorite songs.
Special thanks to Jonas Thelemann, you can find the links to his [library posted on Github](https://github.com/thelinmichael/spotify-web-api-java)


## Limitations
The Spotify API part of the recommendation returns a list of user's favorite artist in a json format. As this was a project intended to focus mainly on the backend side of a web application, the projet displays a raw json file which may not be visually satisfying. I recommend using a json formatter extension to beautify this file.


## Deployment
This project is deployed on AWS. I have used AWS's Elastic Beanstalk to deploy this app. You can find the [deployed application on AWS.](http://spotifyrecommendation-env.eba-mqmmdyum.ap-northeast-2.elasticbeanstalk.com/) 
I have used a MySql client on AWS RDS for the database, but this will work on any other form of relational DBs.
I am planning on taking this application down after the free-trial of AWS ends (August 2022).
For those who wish to deploy this application, you would need to refactor the 'SpotifyApiController' code so that it will return the URI of your actual application insted of local host.