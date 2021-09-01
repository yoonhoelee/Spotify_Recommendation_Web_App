import axios from 'axios'

const Artist_API_BASE_URL = "http://localhost:8080/api/topartist";

class ArtistService {

    getEmotions(){
        return axios.get(Artist_API_BASE_URL)
    }

}
export default new ArtistService()