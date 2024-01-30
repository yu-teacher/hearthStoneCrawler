package com.hearthstone.tierList.service;

import com.hearthstone.tierList.domain.User;
import com.hearthstone.tierList.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TierListService {
    private final UserRepository userRepository;

    public Page<User> allPages(Integer pageNumber){
        Pageable pageable = PageRequest.of(pageNumber - 1,25);
        return userRepository.findAll(pageable);
    }

    public Page<User> searchTagUserData(String battleTag, Integer pageNumber){
        Pageable pageable = PageRequest.of(pageNumber - 1,25);
        Page<User> searchTags = userRepository.findByBattleTagContaining(battleTag,pageable);
        return searchTags;
    }

    public void callTierList() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\yuteacher\\Desktop\\chromedriver-win64\\chromedriver.exe"); // chromedriver 경로 설정

        try {
            WebDriver driver = new ChromeDriver();

            try {

                String url = "https://hearthstone.blizzard.com/ko-kr/community/leaderboards/?region=AP&leaderboardId=arena&seasonId=43&page=";

                int defaultPage = 10;

                for (int i = 1; i <= defaultPage; i++) {
                    driver.get(url+i);

                    // Selenium을 사용하여 페이지 로딩이 완료될 때까지 대기 (3.5초 기다림)
                    Thread.sleep(3500);

                    // 페이지 로딩이 완료된 HTML을 Jsoup으로 파싱
                    Document doc = Jsoup.parse(driver.getPageSource());

                    // 이제 Jsoup을 사용하여 필요한 데이터를 가져올 수 있음
                    // 각각의 요소를 선택하는 선택자 작성
                    if(i == 1){
                        Elements maxPage = doc.select(".PagingButton-enabled");
                        String[] maxPages = maxPage.text().split(" ");
                        System.out.println(maxPages[maxPages.length - 1]);
                    }
                    Elements rows = doc.select(".row");

                    // 선택된 요소들을 출력
                    for (Element row : rows) {
                        Element rankElement = row.select(".col-rank").first();
                        Element battletagElement = row.select(".col-battletag").first();
                        Element ratingElement = row.select(".col-rating").first();

                        if (rankElement != null && battletagElement != null && ratingElement != null) {
                            Long rank = Long.valueOf(rankElement.text());
                            String battleTag = battletagElement.text();
                            Double rating = Double.valueOf(ratingElement.text());

                            System.out.println("Rank: " + rank);
                            System.out.println("Battletag: " + battleTag);
                            System.out.println("Rating: " + rating);
                            System.out.println("-------------");

                            User user = User.builder()
                                    .userRanking(rank)
                                    .battleTag(battleTag)
                                    .avgWins(rating)
                                    .build();

                            userRepository.save(user);
                        }
                    }
                }
            } finally {
                driver.quit(); // 브라우저 종료
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

