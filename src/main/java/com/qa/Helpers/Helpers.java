package com.qa.Helpers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static java.lang.Math.abs;

public class Helpers {

    private static share r;
    private static Connection connection;
    private static ResultSet resultset;
    private static JavascriptExecutor jsIos;
    private static JavascriptExecutor jsAndroid;
    private static Statement statement;
    private static ArrayList columns;

    public Helpers(share r) {this.r = r; }


    public static boolean waitUntilElementDisplayed(final MobileElement mobileElement, AppiumDriver driver) {
        driver.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        ExpectedCondition elementIsDisplayed = (io.appium.java_client.functions.ExpectedCondition) arg0 -> {
            try {
                mobileElement.isDisplayed();
                return true;
            } catch (NoSuchElementException e) {
                return false;
            } catch (StaleElementReferenceException f) {
                return false;
            }
        };
        wait.until(elementIsDisplayed);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return false;
    }

    public static String pluralMerge(long number, String caseOne, String caseTwo, String caseFive) {
        String str = Long.toString(number) + " ";
        number = abs(number);

        if(number % 10 == 1 && number % 100 != 11) {
            str += caseOne;
        } else if (number % 10 >= 2 && number % 10 <= 4 && (number % 100 < 10 || number % 100 >= 20)) {
            str += caseTwo;
        } else {
            str += caseFive;
        }
        return str;
    }

    public static String updateAuthorization() {
        String query = "exec createlogin 1195695";
        return query;
    }

    public static void executeQueryCU(String query) {

        try {
            r.statement = r.connection.createStatement();
            r.resultset = r.statement.executeQuery(query);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public static void createRegUser() throws SQLException {
        String query = "exec createregistration";
        executeQueryCU(query);
            while(r.resultset.next()) {
                r.columns.add(r.resultset.getObject("surname").toString());
                r.columns.add(r.resultset.getObject("firstname").toString());
                r.columns.add(r.resultset.getObject("secondname").toString());
                r.columns.add(r.resultset.getObject("phone_cell").toString());
            }
    }

    public static String connectionString() {
        String jdbcConnection = "jdbc:sqlserver://";
        String host = "10.157.36.146:";
        String port = "1433";
        String dbname = "databaseName=";
        String username = "teamcityCI";
        String password = "phaLawraHA";
        String connectionString = jdbcConnection + host + port + ";" + "user=" + username + ";" +"password=" + password + ";" + "integratedSecurity=false;" + dbname + "APL";
        return connectionString;
    }

    public static void createRegistrationUseer() throws SQLException {
        System.out.println("Connected to database");
        try(Connection con = DriverManager.getConnection(connectionString())) {
            String schema = con.getSchema();
            connection = con;
        }catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        if ((connection == null) || (connection.isClosed())) {
            connection = DriverManager.getConnection(connectionString());
            connection.setAutoCommit(true);
        }
        statement = connection.createStatement();
        columns = new ArrayList<String>();
        try {
            resultset = statement.executeQuery("exec Test.[Client.CreateRegistration] 'Simple'");
            while(resultset.next()) {
                r.columns.add(resultset.getObject(1).toString());
                r.columns.add(resultset.getObject(2).toString());
                r.columns.add(resultset.getObject(3).toString());
                r.columns.add(resultset.getObject(4).toString());
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public static void createLoginRecoveryUser() throws SQLException {
        createDbConnection();
        try {
            switch (r.iphoneIncluded) {
                case "no": resultset = statement
                        .executeQuery("exec [Test].[Client.CreateLoginRecover.ByType] 'MLKAndroid'");
                    break;
                case "yes": resultset = statement
                        .executeQuery("exec [Test].[Client.CreateLoginRecover.ByType] 'MLKiOS'");
                    break;
            }
            while(resultset.next()) {
                r.lastName = resultset
                        .getObject(1).toString();//lastName
                r.firstname = resultset
                        .getObject(2).toString();//firstName
                r.middleName = resultset
                        .getObject(3).toString();//middleName
                r.email = resultset
                        .getObject(5).toString();//email
                r.date = resultset
                        .getObject(4).toString();
                r.month = r.date
                        .replaceAll("[\\d]", "")
                        .replaceAll(" ", "");
                String ddyy = r.date.replaceAll("[А-Яа-яA-Za-z]","");
                r.day = ddyy
                        .replaceAll("(\\w+)$","")/*works with single number - debug with ios*/
                        .replaceAll(" ", "");
                r.year = ddyy
                        .substring(2)
                        .replaceAll(" ", "");
                System.out.println(r.lastName + "\n" + r.firstname + "\n" + r.middleName + "\n"
                        + r.email + "\n" + r.month + "\n" + r.day + "\n" + r.year);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    static void createDbConnection() throws SQLException {
        System.out.println("Connected to database");
        try(Connection con = DriverManager.getConnection(connectionString())) {
            String schema = con.getSchema();
            connection = con;
        }catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        if ((connection == null) || (connection.isClosed())) {
            connection = DriverManager.getConnection(connectionString());
            connection.setAutoCommit(true);
        }
        statement = connection.createStatement();
        columns = new ArrayList<String>();
    }

    public static String getTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMddHHmmss");
        LocalDateTime now = LocalDateTime.now();
        return String.valueOf(dtf.format(now));
    }

    public static void elementWait(MobileElement element, int time) throws InterruptedException {
        while (!element.isDisplayed()) {
            Thread.sleep(time);
        }
    }

    public static void explicitWaitForIOSElement(String locatorType, String locatorBody, int time) {
        WebDriverWait wait = new WebDriverWait(r.driverIos, time);
        switch (locatorType) {
            case "xpath":
                wait.until(ExpectedConditions
                        .visibilityOfElementLocated(By
                        .xpath(locatorBody)));
                break;
            case "id":
                wait.until(ExpectedConditions
                        .visibilityOfElementLocated(By
                        .id(locatorBody)));
                break;
            case "name":
                wait.until(ExpectedConditions
                        .visibilityOfElementLocated(By
                        .name(locatorBody)));
                break;
        }
    }

    public static void explicitWaitForElement(String locatorType, String locatorBody, int time) {
        WebDriverWait wait = new WebDriverWait(r.driverAndroid, time);
        switch (locatorType) {
            case "xpath":
                wait.until(ExpectedConditions
                        .visibilityOfElementLocated(By
                                .xpath(locatorBody)));
                break;
            case "id":
                wait.until(ExpectedConditions
                        .visibilityOfElementLocated(By
                                .id(locatorBody)));
                break;
            case "name":
                wait.until(ExpectedConditions
                        .visibilityOfElementLocated(By
                                .name(locatorBody)));
                break;
        }
    }

    public static void sendKeys(MobileElement element, String string) {
        for(char s: string.toCharArray()) {
            element.sendKeys(String.valueOf(s));
        }
    }

    public static void sendKeysWAtel(final MobileElement element, final String keys) throws InterruptedException {
        String x = element.getText();
        while(!keys.equals(x)) {
            for (int i = 0; i < keys.length(); i++) {
                element.setValue(Character.toString(keys.charAt(i)));
                Thread.sleep(700);
            }
            x = element.getText().replaceAll("[^\\d]", "").substring(1);
            if(!x.equals(keys)) {
                element.clear();
            }
        }
    }

    public static void sendKeys(List<MobileElement> element, int i, String string) {
        for(char s: string.toCharArray()) {
            element.get(i).setValue(String.valueOf(s));
        }
    }

    public static void elementsListWaitById(List<MobileElement> elementList) throws InterruptedException {
        while (elementList.isEmpty()) {
            Thread.sleep(1000);
        }
    }

    public static void elementWaitById(String element, int time) throws InterruptedException {
        while (!r.driverAndroid.findElementById(element).isDisplayed()) {
            Thread.sleep(time);
            r.driverAndroid.findElementById(element);
        }
    }

    public static boolean checkElementNotFound(String xpath) {
        try {
            MobileElement element = (MobileElement) r.driverAndroid.findElementByXPath(xpath);
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public static int[] convert(String str) {
        char[] chars = str.toCharArray();
        int[] ints = new int[chars.length];
        for (int i=0; i<chars.length; i++) {
            int anInt = (int) chars[i];
            ints[i] = anInt;
        }
        return ints;
    }

    public static boolean permutation(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] letters = new int[256];
        char[] s_array = s.toCharArray();
        for (char c : s_array) {
            letters[c]++;
        }

        for(int i=0; i<t.length(); i++) {
            int c = (int) t.charAt(i);
            if (--letters[c]<0) {
                return false;
            }
        }
        return true;
    }

    public static void startWebDriverAgent() {
        String home = System.getProperty("user.home");
        Thread webDriverAgent = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        Process process = null;
                        try {
                            process = new ProcessBuilder(home + "/IdeaProjects/AppiumTests/src/test/java/org/openmlk/launch_WDA.sh").start();
                            Thread.sleep(3000);
                            r.reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                            String line;
                            while ((line = r.reader.readLine()) != null)
                            {
                                System.out.println(line);
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        r.webDriverAgent = webDriverAgent;
        webDriverAgent.setDaemon(true);
        webDriverAgent.start();
    }

//    public static void startAppiumServer() {
//        String home = System.getProperty("user.home");
//        Thread appiumServer = new Thread(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            epicOne.executeCommand("appium -a 127.0.0.1 -p " + r.appiumAndroidServerPort);
//                            Thread.sleep(15000);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//        );
//        r.appiumServer = appiumServer;
//        appiumServer.setDaemon(true);
//        appiumServer.start();
//    }
//
//    public static void stopAppiumServer() {
//        String home = System.getProperty("user.home");
//        Thread appiumServerStop= new Thread(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            epicOne.executeCommand("killall -KILL node");
//                            Thread.sleep(2000);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//        );
//        r.appiumServerStop = appiumServerStop;
//        appiumServerStop.setDaemon(true);
//        appiumServerStop.start();
//    }

    public static void installApp() {
        Map<String, Object> params = new HashMap<>();
        switch (r.iphoneIncluded) {
            case "no":
                params.put("app", "https://205701.selcdn.ru/spbdev/android/2019_04_12_03_53_open-broker-Apptt_Release-1.8.0(7).apk");
                jsAndroid = (JavascriptExecutor)r.driverAndroid;
                jsAndroid.executeScript("mobile: installApp", params);
                break;
            case "yes":
                params.put("app", "/Users/rustam/Desktop/OpenBroker.app");
                jsIos = (JavascriptExecutor)r.driverIos;
                jsIos.executeScript("mobile: installApp", params);
                break;
        }
    }

    public static void removeApp() {
        Map<String, Object> params = new HashMap<>();
        switch (r.iphoneIncluded) {
            case "no":
                params.put("bundleId", "ru.open-broker.app");//???
                jsAndroid = (JavascriptExecutor)r.driverAndroid;
                jsAndroid.executeScript("mobile: removeApp", params);
                break;
            case "yes":
                params.put("bundleId", "ru.open-broker.app");
                jsIos = (JavascriptExecutor)r.driverIos;
                jsIos.executeScript("mobile: removeApp", params);
                break;
        }
    }

    public static void terminateApp() {
        Map<String, Object> params = new HashMap<>();
        final boolean wasRunningBefore;
        switch (r.iphoneIncluded) {
            case "no":
                params.put("bundleId", "ru.open-broker.app");
                jsAndroid = (JavascriptExecutor)r.driverIos;
                wasRunningBefore = (Boolean)jsAndroid.executeScript("mobile: terminateApp", params);
                break;
            case "yes":
                params.put("bundleId", "ru.open-broker.app");
                jsIos = (JavascriptExecutor)r.driverIos;
                wasRunningBefore = (Boolean)jsIos.executeScript("mobile: terminateApp", params);
                break;
        }
    }

    public static void iOSClearTextField(MobileElement element, String text) throws InterruptedException {
        int x = text.length();
//unfortunately we cannot read line from ios locator within a cycle correctly, that's why we're gonna use static string length
        while(x > 0) {
            element.sendKeys(Keys.BACK_SPACE);
            x = x - 1;
            System.out.println(x);
            Thread.sleep(100);
        }
    }

    public static void scrollDown() {
        (new TouchAction(r.driverAndroid))
                .press(PointOption.point(352, 1000))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1500)))
                .moveTo(PointOption.point(352, 400)).release().perform();
        System.out.println("\nsecond method ended " + r.x);
    }

    public static void scroll(int xStart, int yStart, int xEnd, int yEnd) {
        (new TouchAction(r.driverAndroid))
                .press(PointOption
                        .point(xStart, yStart))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1500)))
                .moveTo(PointOption
                        .point(xEnd, yEnd))
                .release()
                .perform();
        System.out.println("\nsecond method ended " + r.x);
    }

    public static void scrollUp() {
        (new TouchAction(r.driverAndroid))
                .press(PointOption.point(573, 825))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1500)))
                .moveTo(PointOption.point(573, 1293)).release().perform();
        System.out.println("\nsecond method ended " + r.x);
    }

    public static void iOSscrollDown() {
        (new TouchAction(r.driverIos))
                .press(PointOption.point(172, 340))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1500)))
                .moveTo(PointOption.point(172, 152)).release().perform();
        System.out.println("\nsecond method ended " + r.x);
    }

    public static void iOSscrollUp() {
        (new TouchAction(r.driverIos))
                .press(PointOption.point(172, 152))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1500)))
                .moveTo(PointOption.point(172, 340)).release().perform();
        System.out.println("\nsecond method ended " + r.x);
    }

    public static void adbRemoveApp() throws IOException, InterruptedException {
        Process proc = Runtime.getRuntime().exec("adb uninstall ru.openbroker.app");
        proc.waitFor();
        StringBuffer output = new StringBuffer();
        BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        String line = "";
        while ((line = reader.readLine()) != null) {
            output.append(line + "\n");
        }
        System.out.println(output);
    }

    public static String getElementAttrributeById(String id, String attr) {
        try {
            return r.driverAndroid.findElementById(id).getAttribute(attr);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return "\nnothing found";
        }
    }

    public static boolean iOSswipeToDirection(MobileElement el, String direction) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) r.driverIos;
            HashMap<String, String> swipeObject = new HashMap<String, String>();
            if (direction.equals("d")) {
                swipeObject.put("direction", "down");
            } else if (direction.equals("u")) {
                swipeObject.put("direction", "up");
            } else if (direction.equals("l")) {
                swipeObject.put("direction", "left");
            } else if (direction.equals("r")) {
                swipeObject.put("direction", "right");
            }
            swipeObject.put("element", el.getId());
            js.executeScript("mobile:swipe", swipeObject);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void scrollAndClick(String selector) {
        String selectorString = String.format("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("+ selector+")");
        r.driverAndroid.findElement(MobileBy.AndroidUIAutomator(selectorString));
    }

    public static boolean waitUntilLocatorContainsText(RemoteWebDriver driver, final By locator, int timeoutSeconds) {
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                    .withTimeout(timeoutSeconds, TimeUnit.SECONDS)
                    .pollingEvery(1, TimeUnit.SECONDS)
                    .ignoring(org.openqa.selenium.NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(InvalidElementStateException.class);
            return wait.until(drv -> drv.findElements(locator).size() > 0 && !drv.findElement(locator).getText().isEmpty());
        } catch (TimeoutException ex) {
            return false;
        }
    }

}

