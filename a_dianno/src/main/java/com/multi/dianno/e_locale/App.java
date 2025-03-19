package com.multi.dianno.e_locale;

import com.multi.dianno.e_locale.config.ContextConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;
import java.util.Locale;

public class App {

    // I18N 소프트웨어의 국제화 (Internationalization에서 첫글자인 I와 마지막글자인 N 사이 알파벳 갯수가 18개)
    //(국제화 말고 현지화도 있음(Localization (L10N): 현지화 - 정서적, 문화적 고려))
     /*
      error.404=페이지를 찾을 수 없습니다. 다시 시도해 주세요!
      error.500=서버에 문제가 발생했습니다. 개발자는 {0}, 현재시간은 {1}입니다. 불편을 드려 죄송합니다.

      error.404=Page not found. Please try again!
      error.500=There seems to be a server issue. Developer: {0}, Time: {1}. We apologize for the inconvenience.

      */
    //getMessage("읽어올메세지 키값", 메세지에 전달할 데이터  없으면 null, 로케일 );

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(ContextConfiguration.class);

        String error404 = context.getMessage("error.404", null, Locale.KOREA);
        String error500 = context.getMessage("error.500", new Object[] {"jenny", new Date()}, Locale.US);

        System.out.println(error404);
        System.out.println(error500);

    }
}
