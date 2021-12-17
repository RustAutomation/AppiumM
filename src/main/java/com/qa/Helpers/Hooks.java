//package com.qa.Helpers;
//
//import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.ios.IOSDriver;
//import io.appium.java_client.remote.AndroidMobileCapabilityType;
//import io.appium.java_client.remote.MobileCapabilityType;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import java.io.IOException;
//import java.net.URL;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//
//public class Hooks {
//    private share r;
//
//    public Hooks(share r) {this.r = r;}
//
//    @Before
//    public void beforeScenario() throws Exception {
//        r.iphoneIncluded = "no";
//        r.androidIncluded = "yes";
//
//        r.appiumAndroidServerPort = 4727;
//        r.appiumIOsServerPort = 4723;
//        r.connectionUrl = Helpers.connectionString();
//        try (Connection connection = DriverManager.getConnection(r.connectionUrl)) {
//            r.schema = connection.getSchema();
//            r.connection = connection;
//        } catch(SQLException e) {
//            e.printStackTrace();
//        }
//
//        if((r.connection == null) || (r.connection.isClosed())) {
//            r.connection = DriverManager.getConnection(r.connectionUrl);
//            r.connection.setAutoCommit(true);
//        }
//
//        if (r.driverAndroid == null && r.androidIncluded.toLowerCase().equals("yes")) {
//            r.capabilities = new DesiredCapabilities();
//            r.capabilities.setCapability("platformName", "Android");
//            r.capabilities.setCapability("deviceName", "Pixel_2_Edited_API_27");//Android emulator Pixel_2_Edited_API_27
//            r.capabilities.setCapability("avd", "Pixel_2_Edited_API_27");//Android emulator Pixel_2_Edited_API_27
////            r.capabilities.setCapability("deviceName", "Samsung Galaxy S7");//Samsung Galaxy S7
////            r.capabilities.setCapability(MobileCapabilityType.UDID,"ce091719d982383203");//Samsung Galaxy S7
////            r.capabilities.setCapability("deviceName", "Galaxy S5");//Samsung Galaxy S5
////            r.capabilities.setCapability(MobileCapabilityType.UDID,"53ddd155");//Samsung Galaxy S5
////            r.capabilities.setCapability("deviceName", "OnePlus 3");//Oneplus 3
////            r.capabilities.setCapability(MobileCapabilityType.UDID,"f0273706");//Oneplus 3
////            r.capabilities.setCapability("automationName", "UiAutomator2");
//            r.capabilities.setCapability("appActivity", "ru.openbroker.app.ui.RealSplashActivity");
//            r.capabilities.setCapability("appMainActivity", "ru.openbroker.app.ui.MainActivity_");
//            r.capabilities.setCapability("appPackage", "ru.openbroker.app");
//            r.capabilities.setCapability("appWaitPackage", "ru.openbroker.app");
//            r.capabilities.setCapability("appWaitActivity", "ru.openbroker.app.ui.SplashActivity_");
//            r.capabilities.setCapability("app", "https://205701.selcdn.ru/spbdev/android/2019_10_11_18_54_open-broker-Apptt_Release-2.7.0(31).apk");
//            r.capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
//            r.capabilities.setCapability("unicodeKeyboard", true);
//            r.capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "320");
//            r.driverAndroid = new AndroidDriver(new URL("http://127.0.0.1:" + r.appiumAndroidServerPort + "/wd/hub"), r.capabilities);
////            r.driverAndroid = new AndroidDriver(new URL("http://127.0.0.1:4726/wd/hub"), r.capabilities);//Samsung Galaxy S5
////            r.driverAndroid = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), r.capabilities);//Samsung Galaxy S7
//            Thread.sleep(15000);
//        }
//
//        if (r.driverIos == null && r.iphoneIncluded.toLowerCase().equals("yes")) {
////            Helpers.startWebDriverAgent();
////            Thread.sleep(100000);
////            executeCommand("appium -a 127.0.0.1 -p " + r.appiumIOsServerPort);
//
//            //simulator iphone 8
//            String udid = "4288DA8E-58B4-446B-9B72-6AB4907CF5FB";
//
//            r.capabilities = new DesiredCapabilities();
//            r.capabilities.setCapability("automationName", "XCUITest");
//            r.capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 8");
//            r.capabilities.setCapability("platformName","iOS");
//            r.capabilities.setCapability("app", "/Users/rustam/Desktop/OpenBroker.app");
//            r.capabilities.setCapability(MobileCapabilityType.UDID, udid);
//            r.capabilities.setCapability("platformVersion", "12.1");
//            r.capabilities.setCapability("appName", "Open Broker");
//            r.capabilities.setCapability(MobileCapabilityType.NO_RESET, "false");
//            r.capabilities.setCapability("autoAcceptAlerts", "false");
//            r.capabilities.setCapability(MobileCapabilityType.SUPPORTS_ALERTS, "true");
//            r.capabilities.setCapability(MobileCapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, "true");
//            r.capabilities.setCapability("wdaEventloopIdleDelay", "5");
//            r.capabilities.setCapability("webDriverAgentUrl", "http://10.54.62.61:8100");
//            r.capabilities.setCapability("wdaBaseUrl", "http://10.54.62.61");
//            r.capabilities.setCapability("wdaLocalPort", 8100);
//            r.capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "320");//new
//            r.capabilities.setCapability("showXcodeLog", "true");
//            r.driverIos = new IOSDriver(new URL("http://127.0.0.1:" + r.appiumIOsServerPort + "/wd/hub"), r.capabilities);
//            Thread.sleep(10000);
//            //simulator iphone 8
//
////            r.capabilities = new DesiredCapabilities();
//////            r.capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone Solovieva");
//////            r.capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone (Антон)");
////            r.capabilities.setCapability("automationName", "XCUITest");
////            r.capabilities.setCapability("noReset", "true");
////            r.capabilities.setCapability("platformName","iOS");
//////            r.capabilities.setCapability("platformVersion", "10.3.3");
//////            r.capabilities.setCapability(MobileCapabilityType.UDID, "a869738397f4ac74600230a06d96bd139d8c771c");
////            r.capabilities.setCapability("platformVersion", "12.0.1");
//////            r.capabilities.setCapability(MobileCapabilityType.UDID, "00008020-001964661428003A");
////            r.capabilities.setCapability("deviceOrientation", "portrait");
////            r.capabilities.setCapability("xcodeOrgId", "4TNLK7ANX6");
////            r.capabilities.setCapability("xcodeSigningId", "iPhone Developer");
//////            r.capabilities.setCapability("agentPath", "/usr/local/lib/node_modules/appium/node_modules/appium-xcuitest-driver_new/appium-xcuitest-driver/WebDriverAgent/WebDriverAgent.xcodeproj");
//////            r.capabilities.setCapability("bootstrapPath", "/usr/local/lib/node_modules/appium/node_modules/appium-xcuitest-driver_new/appium-xcuitest-driver/WebDriverAgent");
//////            r.capabilities.setCapability("app", "http://205701.selcdn.com/d2/2019_5_28_15_34_6_OpenBroker.ipa");
////            r.capabilities.setCapability("app", "/Users/rustam/Desktop/OpenBroker.ipa");
//////            r.capabilities.setCapability("keychainPath", "/Users/rustam/Desktop/cert/OpenDev.p12");
//////            r.capabilities.setCapability("keychainPassword", "111");
//////            r.capabilities.setCapability("xcodeConfigFile", "/usr/local/lib/node_modules/appium/node_modules/appium-xcuitest-driver_new/appium-xcuitest-driver/WebDriverAgent/Configurations/ProjectSettings.xcconfig");
////            r.capabilities.setCapability("webDriverAgentUrl", "http://127.0.0.1:8100");
//////            r.capabilities.setCapability("wdaStartupRetries", 4);
////            r.capabilities.setCapability("wdaBaseUrl", "http://127.0.0.1");
////            r.capabilities.setCapability("wdaLocalPort", 8100);
////
////            r.capabilities.setCapability(MobileCapabilityType.UDID, "F34B7CDE-9CBB-4A40-868F-517D10CBE292");
////            r.capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone X");
////
//////            r.capabilities.setCapability("useNewWDA", "true");
////            r.capabilities.setCapability("showXcodeLog", "true");
//////            if (!WDAServer.getInstance().isRunning()) {
//////                WDAServer.getInstance().restart();
//////            }
////
////            r.driverIos = new IOSDriver(new URL("http://127.0.0.1:4728/wd/hub"), r.capabilities);
////            Thread.sleep(10000);
//        }
//    }
//
//    @After
//    public void afterScenario() throws SQLException, IOException, InterruptedException {
//        r.connection.close();
//        switch (r.iphoneIncluded) {
//            case "no":
////                stopAppiumServer();
////                executeCommand("adb shell ime disable io.appium.settings/.UnicodeIME");
//                break;
//            case "yes":
//                break;
//
//        }
////        AppiumServerJava.stopAppiumServer();
//    }
//
//}
