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

    @Transactional(readOnly = true)
    public List<Movie> getInfo() {
        log.info("영화 정보 가져오기 서비스");
        return movieRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Movie getMovieById(Long id){
        return movieRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    public Movie getMovieByTitle(String title){
        return movieRepository.findByTitle(title).get();
    }

    @Transactional(readOnly = true)
    public Long getIdByTitle(String title){
        return movieRepository.findByTitle(title).get().getId();
    }
}