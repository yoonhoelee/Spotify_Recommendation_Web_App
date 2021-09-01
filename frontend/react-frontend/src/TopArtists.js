import {useEffect, useState} from "react";
import App from "./App";

function TopArtists(){
    const [topArtists, setTopArtists] = useState();
    useEffect(() =>{
        fetch("http://localhost:8080/api/topartist")
            .then(response => response.json())
            .then(data => {
                console.log(data)
                setTopArtists(data)
            })
    }, [])
    return(
        <div>
            {topArtists ? (
                topArtists.map((artistResult) => {
                    return <h1 key={artistResult.name}>{artistResult.name}</h1>
                })
            ):
                (
                    <h1> LOADING...</h1>
                )}
        </div>
    );
}
export default TopArtists;