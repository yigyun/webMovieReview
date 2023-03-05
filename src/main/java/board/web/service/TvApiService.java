package board.web.service;

import board.crud.domain.movie.Tv;
import board.web.repository.TvRepository;
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
public class TvApiService {

    private final TvRepository tvRepository;

    String result = "";
    public List<Tv> getInfo() {
        log.info("영화 정보 가져오기 서비스");
        int pages = 1;
        List<Tv> tvList = null;
        try {
            tvList = new ArrayList<Tv>();
            for (int i = 1; i <= 1; i++) {
                String apiUrl = "https://api.themoviedb.org/3/trending/tv/week?api_key=" + "2427bac740e35f09aeef05dbe339d784"
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
                    Tv tv = new Tv();
                    JSONObject contents = (JSONObject) list.get(k);
                    String ImgUrl = "https://image.tmdb.org/t/p/w200";
                    String match = "[\"]";
                    tv.setPosterPath(ImgUrl + contents.get("poster_path").toString().replace(match, ""));
                    tv.setName(contents.get("name").toString());
                    tv.setDescription(contents.get("overview").toString());
                    tv.setVoteAverage(((Double)contents.get("vote_average")) / 2);
                    //log.info("average={}", movie.getVoteAverage());
                    tvList.add(tv);
                    tvSave(tv);
                }


            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return tvList;
    }

    @Transactional
    public void tvSave(Tv tv){
        tvRepository.save(tv);
    }

    public Tv getDetail(String name){
        return tvRepository.findByName(name);
    }
}