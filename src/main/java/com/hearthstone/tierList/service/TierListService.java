package com.hearthstone.tierList.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@Service
public class TierListService {

    public void callTierList() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\yuteacher\\Desktop\\chromedriver-win64\\chromedriver.exe"); // chromedriver 경로 설정

        try {
            WebDriver driver = new ChromeDriver();

            try {

                String url = "https://hearthstone.blizzard.com/ko-kr/community/leaderboards/?region=AP&leaderboardId=arena&seasonId=42&page=";

                int defaultPage = 100;

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
                            System.out.println("Rank: " + rankElement.text());
                            System.out.println("Battletag: " + battletagElement.text());
                            System.out.println("Rating: " + ratingElement.text());
                            System.out.println("-------------");
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

