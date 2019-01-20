package com.step2qa;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * @author Rahul R on 1/20/2019
 * @version 1.0.1
 */
class Common {

    private static JavascriptExecutor js;

    static void loadJQuery(WebDriver driver) throws InterruptedException {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("if (!window.jQuery) {" +
                "var jquery = document.createElement('script'); jquery.type = 'text/javascript';" +
                "jquery.src = 'https://ajax.googleapis.com/ajax/libs/jquery/2.0.2/jquery.min.js';" +
                "document.getElementsByTagName('head')[0].appendChild(jquery);" +
                "}");

        js.executeScript("$.getScript('http://the-internet.herokuapp.com/js/vendor/jquery.growl.js')");

        js.executeScript("$('head').append('<link rel=\"stylesheet\" " +
                "href=\"http://the-internet.herokuapp.com/css/jquery.growl.css\" " +
                "type=\"text/css\" />');");

        Thread.sleep(5000);

        js.executeScript("$.growl.warning({ title: 'Message', message: 'JQuery Loaded successfully' });");
    }

    static void growlStep(WebDriver driver, String msg) throws InterruptedException {
        Thread.sleep(3000);
        js = (JavascriptExecutor) driver;
        js.executeScript("$.growl({ title: 'Step', message: '" + msg + "' });");
    }

    static void growlError(WebDriver driver, String msg) throws InterruptedException {
        Thread.sleep(3000);
        js = (JavascriptExecutor) driver;
        js.executeScript("$.growl.error({ title: 'Error Occurred', message: '" + msg + "' });");
    }

    static void growlNotice(WebDriver driver, String msg) throws InterruptedException {
        Thread.sleep(3000);
        js = (JavascriptExecutor) driver;
        js.executeScript("$.growl.notice({ title: 'Notice', message: '" + msg + "' });");
    }

    static void growlWarning(WebDriver driver, String msg) throws InterruptedException {
        Thread.sleep(3000);
        js = (JavascriptExecutor) driver;
        js.executeScript("$.growl.warning({ title: 'Script Warning', message: '" + msg + "' });");
    }
}
