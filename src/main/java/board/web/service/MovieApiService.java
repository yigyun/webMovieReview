package board.web.service;

import board.crud.domain.movie.Movie;
import board.web.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieApiService {

    String result = "";
    private final MovieRepository movieRepository;

    public List<Movie> getInfo() {
        log.info("영화 정보 가져오기 서비스");
        int pages = 1;
        List<Movie> movieList = null;
        try {
            movieList = new ArrayList<Movie>();
            for (int i = 1; i <= 1; i++) {
                String apiUrl = "https://api.themoviedb.org/3/trending/movie/week?api_key=" + "2427bac740e35f09aeef05dbe339d784"
                        + "&language=ko-KR&page=" + i;
                URL url = new URL(apiUrl);

                BufferedReader br;

                br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));

                result = br.readLine();

                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
                JSONArray list = (JSONArray) jsonObject.get("results");
                int size = list.size() < 4 ? list.size() : 4;
                for (int k = 0; k < size; k++) {
                    Movie movie = new Movie();
                    JSONObject contents = (JSONObject) list.get(k);
                    String ImgUrl = "https://image.tmdb.org/t/p/w200";
                    String match = "[\"]";
                    movie.setPosterPath(ImgUrl + contents.get("poster_path").toString().replace(match, ""));
                    movie.setTitle(contents.get("title").toString());
                    movie.setDescription(contents.get("overview").toString());
                    movie.setVoteAverage(((Double)contents.get("vote_average")) / 2);
                    //log.info("average={}", movie.getVoteAverage());
                    movieList.add(movie);
                    movieSave(movie);
                }


            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return movieList;
    }

    @Transactional
    public void movieSave(Movie movie){
        movieRepository.save(movie);
    }

    public Movie getDetail(String title){
        return movieRepository.findByTitle(title);
    }
}